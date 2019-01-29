package performwebapp.service;

/**
 * ������ <code> ������ ����������� ScheduledTaskService</code> ������������� 
 * ��������� ������������������
 * @version 1.0
 *
 */
public interface ScheduledTaskService {
	
	/**
	 * �������� ������� �������� CPU � ���������
	 * @return ������� �������� CPU
	 */
	long getCurrentCpuLoad();
	
	/**
	 * �������� ��������� ���������� ������ � ��������� �� ������� ������ �������
	 * @return ��������� ���������� ������
	 */
	long getCurrentMemory();

}
