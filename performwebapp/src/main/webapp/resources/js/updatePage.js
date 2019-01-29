function getPerformance() {
	$.ajax({
		url : "update", 
		type : "GET", 
		success : function(responseText) {
			
			var count = $('#messageList tr').length;
			if (count > 100) {
				$('#messageList tr').remove();
			}
			
			var text = $.parseJSON(responseText);
			var tr = $('#table tr');
			
			var cpuValue = text.cpu;
			var memoryValue = text.memory;
			
			var cpuValueElement = $('font', $('td', tr.eq(0)).eq(1)).eq(0);
			var cpuPercentElement = $('font', $('td', tr.eq(0)).eq(2)).eq(0);
			
			var memoryValueElement = $('font', $('td', tr.eq(1)).eq(1)).eq(0);
			var memoryPercentElement = $('font', $('td', tr.eq(1)).eq(2)).eq(0);
			
			if (RegExp('([89]\\d)|100').test(cpuValue)) {
				cpuValueElement.attr("color", "red");
				cpuPercentElement.attr("color", "red");	
			} else if (RegExp('\\d|([1-7]\\d)').test(cpuValue)) {
				cpuValueElement.attr("color", "green");
				cpuPercentElement.attr("color", "green");
			}			
			
			if (RegExp('([3-9]\\d)|100').test(memoryValue)) {
				memoryValueElement.attr("color", "green");
				memoryPercentElement.attr("color", "green");
			} else if (RegExp('\\d|([12]\\d)').test(memoryValue)) {
				memoryValueElement.attr("color", "red");
				memoryPercentElement.attr("color", "red");
			}
			
			cpuValueElement.html(cpuValue);
			memoryValueElement.html(memoryValue);
			
			if ("messageCpu" in text) {
				$('#messageList').prepend($('<tr>').append($('<td>').text(text.messageCpu)));
			}
			if ("messageMemory" in text) {
				$('#messageList').prepend($('<tr>').append($('<td>').text(text.messageMemory)));
			}
			setTimeout(getPerformance, 1000);
		}, 
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			setTimeout(getPerformance, 1000);
		}
	});
}