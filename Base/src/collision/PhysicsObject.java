package collision;

import org.lwjglx.util.vector.Vector3f;

public class PhysicsObject {
	
	public Vector3f position, velocity, force;
	public float mass;
	public PhysicsCollider collider;
	public PhysicsTransform transform;
	
	public PhysicsObject()
	{
		position 	= new Vector3f();
		velocity 	= new Vector3f();
		force 		= new Vector3f();
		mass 		= 1;
	}

}
