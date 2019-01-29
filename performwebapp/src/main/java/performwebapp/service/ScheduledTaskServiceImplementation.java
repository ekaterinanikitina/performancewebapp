package performwebapp.service;

import static java.lang.Math.*;

import com.sun.management.OperatingSystemMXBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * ������ <code> ������ ScheduledTaskServiceImplementation</code> ������������� 
 * ������ ��� ��������� ���������� ������������������
 * @version 1.0
 *
 */
@Service
public class ScheduledTaskServiceImplementation implements ScheduledTaskService {

	// ������������ �������, �� ������� �������� JVM
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
	 * ����������� �������� �������� CPU � ��������� ���������� ������
	 * ����� �������� ����������� �������
	 * ���������� ������ �������
	 */
	@Scheduled(fixedDelay = 1000)
	private void task() throws ArithmeticException {
		cpu = round(operatingSystemMXBean.getSystemCpuLoad() * 100);
		memory = round((operatingSystemMXBean.getFreePhysicalMemorySize() * 100) / operatingSystemMXBean.getTotalPhysicalMemorySize());
	}

}
