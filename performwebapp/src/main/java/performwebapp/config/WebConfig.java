package performwebapp.config;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Объект <code> класса WebConfig</code> предоставляет 
 * базовую конфигурацию web приложения
 * @version 1.0
 *
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = {"performwebapp"})
public class WebConfig implements WebMvcConfigurer {
	
	// Добавляет webapp ресурсы
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	/**
	 * Получает объект для поиска представлений по наименованию
	 * @return объект для поиска представлений по наименованию
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/**
	 * Получает операционную систему, на которой запущена JVM
	 * @return операционная система, на которой запущена JVM
	 */
	@Bean
	public OperatingSystemMXBean getOperatingSystemMXBean() {
		OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		return operatingSystemMXBean;
	}

}
