package performwebapp.service;

import static java.lang.Math.*;

import com.sun.management.OperatingSystemMXBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Объект <code> класса ScheduledTaskServiceImplementation</code> предоставляет 
 * сервис для получения параметров производительности
 * @version 1.0
 *
 */
@Service
public class ScheduledTaskServiceImplementation implements ScheduledTaskService {

	// операционная система, на которой запущена JVM
	@Autowired
	private OperatingSystemMXBean operatingSystemMXBean;
	
	private long cpu;
	private long memory;
	
	@Override
	public long getCurrentCpuLoad() {
		return cpu;
	}
	
	@Override
	public long getCurrentMemory() {
		return memory;
	}
	
	/**
	 * Расчитывает значение загрузки CPU и свободную физическую память
	 * метод является планируемой задачей
	 * вызывается каждую секунду
	 */
	@Scheduled(fixedDelay = 1000)
	private void task() throws ArithmeticException {
		cpu = round(operatingSystemMXBean.getSystemCpuLoad() * 100);
		memory = round((operatingSystemMXBean.getFreePhysicalMemorySize() * 100) / operatingSystemMXBean.getTotalPhysicalMemorySize());
	}

}
