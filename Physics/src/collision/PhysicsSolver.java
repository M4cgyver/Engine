package collision;

import java.util.ArrayList;

public abstract class PhysicsSolver {

	public abstract void Solve(ArrayList<PhysicsCollision> collisions, double dt);
}
