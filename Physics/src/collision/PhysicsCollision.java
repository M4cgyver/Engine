package collision;

public class PhysicsCollision { 
	public PhysicsObject objA, objB;
	public PhysicsCollisionPoint points;
	
	public PhysicsCollision() {
	}
 
	public PhysicsCollision(PhysicsObject objA, PhysicsObject objB, PhysicsCollisionPoint points) { 
		this.objA = objA;
		this.objB = objB;
		this.points = points;
	}
}
