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
 * объект <code> класса IndexController</code> предоставляет
 * контроллер для работы с отображением производительности
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	// сервис планируемого опроса параметров производительности
	@Autowired
	private ScheduledTaskService task;
	
	/**
	 * обрабатывает результат запроса "get" и отображает 
	 * загрузку CPU и свободную физическую память на странице
	 * @param modelMap содержит параметры производительности
	 * @return страницу с результатом опроса производительности
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
	 * запрос "get" на обновление параметров производительности на странице
	 * и выводом предупреждения, если параметр превышает допустимое значение
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
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss")) + " - Загрузка CPU превышает 80 % (" + cpu + ")");
		if (memory < 30)
			jObject.addProperty("messageMemory", 
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss")) + " - Физическая память меньше 30 % (" + memory + ")");
		response.setContentType("text/plain");
		response.getWriter().write(jObject.toString());
	}

}
