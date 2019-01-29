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
 * ������ <code> ������ WebConfig</code> ������������� 
 * ������� ������������ web ����������
 * @version 1.0
 *
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = {"performwebapp"})
public class WebConfig implements WebMvcConfigurer {
	
	// ��������� webapp �������
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	/**
	 * �������� ������ ��� ������ ������������� �� ������������
	 * @return ������ ��� ������ ������������� �� ������������
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/**
	 * �������� ������������ �������, �� ������� �������� JVM
	 * @return ������������ �������, �� ������� �������� JVM
	 */
	@Bean
	public OperatingSystemMXBean getOperatingSystemMXBean() {
		OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		return operatingSystemMXBean;
	}

}
