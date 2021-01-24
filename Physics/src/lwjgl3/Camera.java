package lwjgl3;

import org.lwjglx.util.vector.Vector3f;

public abstract class Camera {
	
	protected Vector3f position = new Vector3f();
	protected double pitch=0;
	protected double yaw=0;
	protected double roll=0;
	
	public Camera()
	{
		
	}
	
	public abstract void move(double fSensitivity); 
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public double getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public double getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	} 
}
