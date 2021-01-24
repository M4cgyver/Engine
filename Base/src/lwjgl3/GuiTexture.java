package lwjgl3;

import org.lwjglx.util.vector.Vector2f;

public class GuiTexture {
	public GLTexture texture;
	public Vector2f position, scale, rotation;
	
	public GuiTexture(String path)
	{
		texture 	= new GLTexture(path);
		position	= new Vector2f(0,0);
		scale 		= new Vector2f(1,1);
		rotation	= new Vector2f(0,0);
	} 	

	public GuiTexture(int id)
	{
		texture 	= new GLTexture(id);
		position	= new Vector2f(0,0);
		scale 		= new Vector2f(1,1);
		rotation	= new Vector2f(0,0);
	} 	
}
