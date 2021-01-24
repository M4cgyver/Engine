package lwjgl3;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class GLWindow {
	private int width, height;
	private String title;
	private long windowId; 
	public int swapInterval = 0;
	
	public GLWindow(int width, int height, String title) 
	{
		this.width = width;
		this.height = height;
		this.title = title; 
	}
	
	public void windowHint(int id, int value)
	{
		GLFW.glfwWindowHint(id, value);
	}

	public boolean create(boolean resizable)
	{	
		windowId = GLFW.glfwCreateWindow(this.width, this.height, this.title, 0, 0);					//Attempt to create the window on the primary monitor
		
		if(windowId == 0)																				//Check if we failed
		{
			System.err.println("ERROR: Lwjgl3 failed to create window!");								//If we did print that we failed
			return false;
		}
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());					//Get the videomode
		GLFW.glfwSetWindowPos(windowId, (videoMode.width()-width)/2, (videoMode.height()-height)/2);	//Center the window
		
		GLFW.glfwSetKeyCallback(windowId, Input.getKeyboardCallback());
		GLFW.glfwSetCursorPosCallback(windowId, Input.getMouseMoveCallback());
		GLFW.glfwSetMouseButtonCallback(windowId, Input.getMouseButtonsCallback());
		GLFW.glfwSetScrollCallback(windowId, Input.getMouseScrollCallback()); 
 
		GLFW.glfwMakeContextCurrent(windowId); 
		GL.createCapabilities(); 
		
		GLFW.glfwShowWindow(windowId);																	//Show the window
		
		return true;
	}
	
	public boolean update()
	{
		GLFW.glfwSwapInterval(swapInterval);
		GLFW.glfwSwapBuffers(windowId);
		return !GLFW.glfwWindowShouldClose(windowId);
	}

	public long getId() {
		return windowId;
	}
	
	public void setSwapInterval(int i)
	{
		GLFW.glfwSwapInterval(i);
	}

	public void clearColor(float r, float g, float b, float a) {
        GL13.glClearColor(r,g,b,a);
	}

	public void isBorderless(boolean b) { 
		GLFW.glfwMakeContextCurrent(windowId); 
	}

	public void clearGL() { 
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
	} 
}
