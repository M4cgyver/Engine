package lwjgl3;

import org.lwjglx.util.vector.Matrix4f;

public class ShaderContrast extends Shader{

	private static final String VERTEX_FILE = "/shaders/fragmentShaderContrast.txt";
	private static final String FRAGMENT_FILE = "/shaders/vertexShaderContrast.txt";
	
	public ShaderContrast() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {	
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	@Override
	public void loadTransformationMatrix(Matrix4f matrix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadProjectionMatrix(Matrix4f projectionMatrix) {
		// TODO Auto-generated method stub
		
	}

}