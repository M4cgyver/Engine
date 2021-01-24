package lwjgl3;

import org.lwjglx.util.vector.Matrix4f;

public class ShaderGui extends Shader{
	private static final String VERTEX_FILE = "/shaders/vertexShaderGui.txt";
	private static final String FRAGMENT_FILE = "/shaders/fragmentShaderGui.txt";
	
	private int location_transformationMatrix;

	protected ShaderGui() {
		super(VERTEX_FILE, FRAGMENT_FILE); 
	}

	@Override
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
		
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		
	}

	@Override
	protected void loadProjectionMatrix(Matrix4f projectionMatrix) {
		// TODO Auto-generated method stub
		
	}
}