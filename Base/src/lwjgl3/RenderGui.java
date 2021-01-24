package lwjgl3;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjglx.util.vector.Matrix4f;

//Essentially this class is made for rendering 2d 
public class RenderGui {
	private final ModleRaw 		modle;
	private final ShaderGui 	shader;
	
	public RenderGui(Loader loader)
	{
		float[] positions = {-1,1,-1,-1,1,1,1,-1};
		modle = loader.loadToVAO(positions);
		shader = new ShaderGui();
	}
	
	public void render(List<GuiTexture> guis)
	{
		shader.start();
		GL30.glBindVertexArray(modle.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		for(GuiTexture gui : guis)
		{
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.texture.getId());
			Matrix4f matrix = Maths.createTransformationMatrix(gui.position, gui.rotation, gui.scale);
			shader.loadTransformationMatrix(matrix);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, modle.getVertexCount());
		}
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	public void render(List<GuiTexture> guis, Shader shader)
	{
		shader.start();
		GL30.glBindVertexArray(modle.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		for(GuiTexture gui : guis)
		{
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.texture.getId());
			Matrix4f matrix = Maths.createTransformationMatrix(gui.position, gui.rotation, gui.scale);
			shader.loadTransformationMatrix(matrix);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, modle.getVertexCount());
		}
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void cleanup()
	{
		shader.cleanup();
	}
}
