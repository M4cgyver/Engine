package engine;
import lwjgl3.lwjgl3;

public class Engine {
	
	public static boolean 	bRunning 	= false;
	public static float 	fFps 		= 60;
	public static double	dElapsed	= 0;
	public static double 	totalMillis = 0;
	
	public static void Initalize()
	{
		System.out.println("Engine initalizing...");
		lwjgl3.Initalize();										//LWJGL3 initialize 
		Game.Initalize();										//Game initalzie
		System.out.println("Engine initalized!");
	}
	
	public static void shouldDetroy(boolean b)
	{
		if(b==false)bRunning=false;
	}
	
	public static double lastRun = 0;
	public static void Run()
	{
		System.out.println("\nRunning...");
		
		bRunning = true;						//Set running to true
		
		while(bRunning)							//Main running loop	
		{
			long startTime = System.nanoTime();			//Get the current nanotime
			Game.Update(totalMillis);					//Update the game
			Game.Draw(totalMillis);						//Draw the game

			if(fFps > 0)
			{
				long endTime = System.nanoTime();			//Get the current nanotime
				long totalNano = endTime-startTime;			//Calculate the total time elapsed in nanotime
				float fElapsed = (totalNano/1000000.0f);	//Get the total millis elapsed
				float fSleep = (1000/fFps)-fElapsed;		//Get the ammount of millis to sleep

				try {
					if (Math.floor(fSleep) > 0)
						Thread.sleep((long) Math.floor(fSleep));		//Sleep
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			totalMillis = ((System.nanoTime()-startTime)/1000000.0f);
			dElapsed+=totalMillis; 
		}
		
		Destroy();								//Cleanup and destroy
	}
	
	public static void Destroy()
	{
		System.out.println("Engine is destorying...");
		Game.Destroy(); 
	}
}
