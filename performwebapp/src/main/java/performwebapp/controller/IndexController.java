package performwebapp.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonObject;

import performwebapp.service.ScheduledTaskService;

/**
 * ������ <code> ������ IndexController</code> �������������
 * ���������� ��� ������ � ������������ ������������������
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	// ������ ������������ ������ ���������� ������������������
	@Autowired
	private ScheduledTaskService task;
	
	/**
	 * ������������ ��������� ������� "get" � ���������� 
	 * �������� CPU � ��������� ���������� ������ �� ��������
	 * @param modelMap �������� ��������� ������������������
	 * @return �������� � ����������� ������ ������������������
	 */
	@RequestMapping("/")
	public String indexPage(ModelMap modelMap) {
		long cpu = task.getCurrentCpuLoad();
		long memory = task.getCurrentMemory();
		modelMap.addAttribute("cpu", cpu);
		modelMap.addAttribute("memory", memory);
		return "index";
	}
	
	/**
	 * ������ "get" �� ���������� ���������� ������������������ �� ��������
	 * � ������� ��������������, ���� �������� ��������� ���������� ��������
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void updatePage(HttpServletResponse response) throws IOException {
		JsonObject jObject = new JsonObject();
		long cpu = task.getCurrentCpuLoad();
		jObject.addProperty("cpu", cpu);
		long memory = task.getCurrentMemory();
		jObject.addProperty("memory", memory);
		if (cpu > 79) 
			jObject.addProperty("messageCpu", 
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss")) + " - �������� CPU ��������� 80 % (" + cpu + ")");
		if (memory < 30)
			jObject.addProperty("messageMemory", 
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss")) + " - ���������� ������ ������ 30 % (" + memory + ")");
		response.setContentType("text/plain");
		response.getWriter().write(jObject.toString());
	}

}
