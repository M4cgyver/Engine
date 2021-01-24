package collision;

public abstract class PhysicsCollider {
	public abstract PhysicsCollisionPoint TestCollision( PhysicsTransform transform, PhysicsCollider collider,	PhysicsTransform colliderTransform);
	 
	public abstract PhysicsCollisionPoint TestCollision( PhysicsTransform transform, PhysicsSphereCollider sphere, PhysicsTransform sphereTransform);
	 
	public abstract PhysicsCollisionPoint TestCollision( PhysicsTransform transform, PhysicsPlaneCollider plane, PhysicsTransform planeTransform);
}
