package collision;

import java.util.ArrayList;

import org.lwjglx.util.vector.Vector3f;

public class PhysicsWorld {
	public ArrayList<PhysicsObject> objects;
	public ArrayList<PhysicsSolver> solvers;
	public Vector3f gravity;
	
	public PhysicsWorld()
	{
		objects = new ArrayList<PhysicsObject>();
		solvers = new ArrayList<PhysicsSolver>();
		gravity = new Vector3f(0,-9.8f,0);
	}
	
	public void ResolveCollisions(double dt)
	{
		ArrayList<PhysicsCollision> collisions = new ArrayList<PhysicsCollision>();
		
		for(PhysicsObject a : objects)									//Loop through all the objects to test for collision objects
		{
			for(PhysicsObject b : objects)
			{
				if (a == b)	break;										//The the pairs are the same
				
				if (a.collider!=null||b.collider!=null) continue;		//Check if both already have colliders
				
				PhysicsCollisionPoint points = a.collider.TestCollision(a.transform, b.collider, b.transform);
				
				if (points.HasCollision)
				{
					collisions.add(new PhysicsCollision(a,b,points));
				}
				
			}	
		}
	
		for(PhysicsSolver solver : solvers)
			solver.Solve(collisions, dt);
		
	}
	
	public void Step(double varry)
	{
		for(PhysicsObject obj : objects)
		{
			obj.force.x += obj.mass * gravity.x;
			obj.force.y += obj.mass * gravity.y;
			obj.force.z += obj.mass * gravity.z;

			obj.velocity.x += obj.force.x / obj.mass * varry;
			obj.velocity.y += obj.force.y / obj.mass * varry;
			obj.velocity.z += obj.force.z / obj.mass * varry;
			

			obj.position.x += obj.velocity.x * varry;
			obj.position.y += obj.velocity.y * varry;
			obj.position.z += obj.velocity.z * varry;

			obj.force.x = obj.force.y = obj.force.z = 0; 
		}
	}
}
