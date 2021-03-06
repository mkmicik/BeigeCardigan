package controller;

import model.*;
/* This controller deals with targetting the turrets of each tank.
 * Steps for targetting:
 * 
 * 1) Check which enemy tank is the target (fast or slow, set by global var)
 * 2) If target is alive, get its location and aim toward it
 * 3) If targetting for the fast tank, lead the shots to where the tank will be
 * Otherwise, target the current location of the tank.
 * 4) Shoot if turret is lined up and its not on cooldown
 * Repeat calculations each time you get new info from the command channel/whatever
 * 
 * */
public class TurretController {
	private static TurretController _instance;

	public TurretController() {
	}
	
	public TurretController getInstance() {
		if (_instance == null) {
			_instance = new TurretController();
		}
		return _instance;
	}
	
	public void update(){
	// For each of our tanks
		// If its a slow tank aim for where the target currently is.
		// Find location of target if our tank is the origin.
		
		Tank target = new SlowTank(); // TODO: this must be chosen later on by the targeting strat
		
		Tank slowTank = GameState.getInstance().getSlowTank();
		double relativeX = target.getPos_x() - slowTank.getPos_x();
		double relativeY = target.getPos_y() - slowTank.getPos_y();
		
		double slowAngleToTarget = (Math.atan2(relativeY, relativeX));
		
		Tank fastTank = GameState.getInstance().getFastTank();
		double fastAngleToTarget = calculateLeadLocation(fastTank);
		
		
		if ((slowTank.getTurretAngle() - slowAngleToTarget) > 3.14) {
			//send a rotate counterclockwise command of tank.angle - angletoTarget
		} else {
			//send a rotate clockwise command of tank.angle - angletotarget
		}
		
		if ((fastTank.getTurretAngle() - fastAngleToTarget) > 3.14) {
			//send a rotate counterclockwise command of tank.angle - angletoTarget
		} else {
			//send a rotate clockwise command of tank.angle - angletotarget
		}
	}


// http://stackoverflow.com/questions/2248876/2d-game-fire-at-a-moving-target-by-predicting-intersection-of-projectile-and-u
/*
* Return the firing solution for a projectile starting at 'src' with
 * velocity 'v', to hit a target, 'dst'.
 *
 * @param Object src position of shooter
 * @param Object dst position & velocity of target
 * @param Number v   speed of projectile
 * @return Object Coordinate at which to fire (and where intercept occurs)
 *
 * E.g.
 * >>> intercept({x:2, y:4}, {x:5, y:7, vx: 2, vy:1}, 5)
 * = {x: 8, y: 8.5}
 */
	public double calculateLeadLocation(Tank tank) {
		
		Tank target = new SlowTank(); // TODO: this must be chosen later on by the targeting strat
		
		double relativeX = target.getPos_x() - tank.getPos_x();
		double relativeY = target.getPos_y() - tank.getPos_y();

		double targetVelocityX = Math.cos(target.getTracksAngle()) * target.getMovementSpeed();
		double targetVelocityY = Math.sin(target.getTracksAngle()) * target.getMovementSpeed();

		// Get quadratic equation components
		double a = targetVelocityX*targetVelocityX + targetVelocityY*targetVelocityY - target.getMovementSpeed()*target.getMovementSpeed();
		double b = 2 * (targetVelocityX * relativeX + targetVelocityY * relativeY);
		double c = relativeX*relativeX + relativeY*relativeY;    

		// Solve quadratic
		double[] ts = quad(a, b, c); // See quad(), below

		double interceptLocationX = 0;
    	double interceptLocationY = 0;
    	
		// Find smallest positive solution
		if (ts != null) {
		    double t0 = ts[0];
		    double t1 = ts[1];
		    double t = Math.min(t0, t1);
		    if (t < 0)
		    	t = Math.max(t0, t1);    
		    if (t > 0) {
		    	interceptLocationX = target.getPos_x() + targetVelocityX*t;
		    	interceptLocationY = target.getPos_y() + targetVelocityY*t;
		    }
		}

		// get angle between your location and the new interception location
		double relativeAngleX = interceptLocationX - tank.getPos_x();
		double relativeAngleY = interceptLocationX - tank.getPos_y();
		return Math.atan2(relativeAngleY, relativeAngleX);
	}


	/**
	 * Return solutions for quadratic
	 */
	public double[] quad(double a, double b, double c) {
	  double [] sol = null;
	  if (Math.abs(a) < 1e-6) {
	    if (Math.abs(b) < 1e-6) {
	      sol = (Math.abs(c) < 1e-6) ? new double[]{0,0} : null;
	    } else {
	      sol = new double[]{(-c/b), (-c/b)};
	    }
	  } else {
	    double disc = b*b - 4*a*c;
	    if (disc >= 0) {
	      disc = Math.sqrt(disc);
	      a = 2*a;
	      sol = new double[]{(-b-disc)/a, (-b+disc)/a};
	    }
	  }
	  return sol;
	}
}
