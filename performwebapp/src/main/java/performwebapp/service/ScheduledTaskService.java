package performwebapp.service;

/**
 * Объект <code> класса реализующий ScheduledTaskService</code> предоставляет 
 * получение производительности
 * @version 1.0
 *
 */
public interface ScheduledTaskService {
	
	/**
	 * Получает текущую загрузку CPU в процентах
	 * @return текущая загрузка CPU
	 */
	long getCurrentCpuLoad();
	
	/**
	 * Получает свободную физическую память в процентах на текущий момент времени
	 * @return свободная физическая память
	 */
	long getCurrentMemory();

}
