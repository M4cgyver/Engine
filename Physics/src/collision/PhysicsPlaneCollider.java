package collision;

public class PhysicsPlaneCollider extends PhysicsCollider{

	@Override
	public PhysicsCollisionPoint TestCollision(PhysicsTransform transform, PhysicsCollider collider, PhysicsTransform colliderTransform) {
		return collider.TestCollision(colliderTransform, this, transform);
	}

	@Override
	public PhysicsCollisionPoint TestCollision(PhysicsTransform transform, PhysicsSphereCollider sphere, PhysicsTransform sphereTransform) {

		return PhysicsAlgorithm.FindCollisionPoints(this, transform, sphere, sphereTransform);
	}

	@Override
	public PhysicsCollisionPoint TestCollision(PhysicsTransform transform, PhysicsPlaneCollider plane, PhysicsTransform planeTransform) {

		return null;	//No plave v plane collision
	}

}
