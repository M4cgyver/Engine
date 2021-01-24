package engine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjglx.util.vector.Vector2f;
import org.lwjglx.util.vector.Vector3f;

import collision.PhysicsObject;
import collision.PhysicsWorld;
import lwjgl3.CameraPov;
import lwjgl3.Entity;
import lwjgl3.Fbo;
import lwjgl3.GLTexture;
import lwjgl3.GLWindow;
import lwjgl3.GuiTexture;
import lwjgl3.Loader;
import lwjgl3.ModleRaw;
import lwjgl3.ModleTexture;
import lwjgl3.ObjectLoader;
import lwjgl3.PostProcessing;
import lwjgl3.Render;
import lwjgl3.RenderGui;
import lwjgl3.ShaderBasic;
import lwjgl3.lwjgl3;

public class Game {
	private static Loader 			loader;					//Loader
	private static GLWindow 		window;					//Main Window
	private static CameraPov 		camera;					//default camera
	private static Entity			eTable, eSphere;		//basic entity
	private static ShaderBasic		shBasic;				//basic shader
	private static Render			renderer;				//The renderer 
	private static RenderGui 		renderGui;				//Gui renderer
	private static Fbo				fbo;					//Basic fbo 
	private static PhysicsWorld		physicsWorld;			//The struct is the physics worlds
	private static List<GuiTexture>	guis;					//The total list of guis
	
	public static void Initalize()
	{
		System.out.println("Game initalizing...");				//Debug print

		loader = new Loader();									//Create a new loaer
		
		window = new GLWindow(800, 600, "Hello World!"); 		//Create a opengl window
		window.windowHint(GLFW.GLFW_RESIZABLE, 0);				//Set resizable to false
		window.create(true);									//Create the window
		window.isBorderless(true);								//Set the window to borderless 
		window.swapInterval = 0;								//Enable vsync
		Engine.fFps = 60;										//Uncap the fps from the engine since we enabled vsync
		
		Entity eZero = new Entity(null, new Vector3f(), 0, 180, 0, 0);	//Make a new entity at zero 
		camera = new CameraPov(eZero);	
		camera.setPosition(new Vector3f(0,3,0));
		
		shBasic = new ShaderBasic();							//Make a new shader wit the basic instance
		 
		try {
			ModleRaw mFloor = ObjectLoader.loadObjModleFromJar("/objects/floor.obj", loader);
			ModleRaw mSphere = ObjectLoader.loadObjModleFromJar("/objects/sphere.obj", loader);
			GLTexture gltFloor = new GLTexture(loader.loadTexture("/objects/floor.png"));
			ModleTexture mtFloor = new ModleTexture(mFloor, gltFloor);
			ModleTexture mtSphere = new ModleTexture(mSphere, gltFloor);
			eTable = new Entity(mtFloor, new Vector3f(0,5,-10),0,0,0,3);
			eSphere = new Entity(mtSphere, new Vector3f(0,5,-10),0,0,0,1);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		renderer = new Render(800,600);							//Make a new instance of the renderer
		renderer.loadProjectionMatrixOntoShader(shBasic);		//Load the projection matrix onto the shader
		
		fbo = new Fbo(800,600, Fbo.DEPTH_RENDER_BUFFER);		//Create a new frame buffer the size of the screen
		
		physicsWorld = new PhysicsWorld();						//Create a new physics world
		PhysicsObject pobj = new PhysicsObject();
		pobj.velocity.y += 10;
		pobj.position = eSphere.getPosition();
		physicsWorld.objects.add(pobj);
		
		renderGui = new RenderGui(loader);						//Handle a new gui loader
		guis = new ArrayList<GuiTexture>();						//Create the list of guis
		guis.add(new GuiTexture(fbo.getColorTexture()));		//Add the gui texture
		guis.get(0).position = new Vector2f(0f, 0f);			//Set the position
		guis.get(0).scale = new Vector2f(1f, -1f);				//Set the scale
		
		System.out.println("Game initalized!");					//Debug print
	}
	
	public static void Update(double dElapesed)
	{ 
		//Poll lwjgl3 											//
		lwjgl3.Poll();											//Poll for updates

		//Basic game code										//
		physicsWorld.Step(dElapesed*0.0015);
		System.out.println(physicsWorld.objects.get(0).position.y);
		camera.move(0);											//Update the camera 
		
		//Basic updates											//
		Engine.shouldDetroy(window.update()); 					//Update the window
		 
	}
	
	public static void Draw(double dElapesed)
	{
		//Clear the screen										//
		float fm = (float) ((Engine.dElapsed*0.001)%1);			//Get the multiple
		window.clearColor(1f*fm,.5f*fm,.25f*fm,0f);				//Set the clear color 
        window.clearGL();										//Clear the console
        
        //Draw the 3d view										//
		fbo.bindFrameBuffer();									//Bind the framebuffer
		renderer.prepare(); 									//Prepare the renderer 
		shBasic.start();										//Start the basic shader
		shBasic.loadViewMatrix(camera);							//Load the projection matrix
		renderer.render(eTable, shBasic);						//Render the table
		renderer.render(eSphere, shBasic);						//Render the sphere
		shBasic.stop();  										//Stop the basic shader
		fbo.unbindFrameBuffer();								//Unbind the framebuffer
		
        //Draw the GUI
		renderGui.render(guis);									//Render the list of guis	
	}
	
	public static void Destroy()
	{
		System.out.println("Game is destroying...");
		fbo.cleanUp();
		PostProcessing.cleanUp(); 
		renderGui.cleanup();
	}
}
