package lwjgl3;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import resources.ResourceManager;


public class GLTexture 
{
	private int width;
	private int height;
	private int	Id;

	public GLTexture()
	{
		
	}

	public GLTexture(String file)
	{
		//Load the texture
		Texture t = null;
		try 
		{
			t = TextureLoader.getTexture("PNG", ResourceManager.getStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Set the texture parameters
		Id = t.getTextureID();
		width = t.getTextureWidth();
		height = t.getTextureHeight();
		
		//Debug print
		System.out.println("Texture loaded: " + this.Id + ", " + this.width + ", " + this.height);
	}
	
	public GLTexture(int id)
	{
		this.Id = id;
	}

	public int getId() 
	{
		return Id;
	}
	
	public void bind(int target)
	{
		GL11.glBindTexture(target, Id);
	}

}
