package collision;

public class PhysicsSphereCollider extends PhysicsCollider{

	@Override
	public PhysicsCollisionPoint TestCollision(PhysicsTransform transform, PhysicsCollider collider, PhysicsTransform colliderTransform) {
		return collider.TestCollision(colliderTransform, this, transform);
	}

	@Override
	public PhysicsCollisionPoint TestCollision(PhysicsTransform transform, PhysicsSphereCollider sphere, PhysicsTransform sphereTransform) {
		
		return PhysicsAlgorithm.FindCollisionPoints(this, transform, sphere, sphereTransform);
	}

	@Override
	public PhysicsCollisionPoint TestCollision(PhysicsTransform transform, PhysicsPlaneCollider plane, PhysicsTransform planeTransform) 
	{

		return PhysicsAlgorithm.FindCollisionPoints(this, transform, plane, planeTransform);
	}

}
