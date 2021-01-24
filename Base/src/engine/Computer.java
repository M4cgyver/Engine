package engine;

public class Computer {

	public static final int processors = Runtime.getRuntime().availableProcessors();
	public static final long maxMemory = Runtime.getRuntime().maxMemory();
	
	public static long getMemoryAvaliable()
	{
		return Runtime.getRuntime().freeMemory();
	}

	public static long getMemoryUsed()
	{
		return Runtime.getRuntime().totalMemory();
	} 
}
