package collision;

import org.lwjglx.util.vector.Vector3f;

public class PhysicsCollisionPoint {
	public Vector3f A; 						// Furthest point of A into B
	public Vector3f B; 						// Furthest point of B into A
	public Vector3f Normal; 				// B � A normalized
	public float Depth;    					// Length of B � A
	public boolean HasCollision;			// Check for collision
}
