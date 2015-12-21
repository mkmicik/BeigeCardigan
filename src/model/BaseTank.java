package model;

public abstract class BaseTank {

	protected String id;
	
	protected int pos_x;
	protected int pos_y;
	protected double turretDirection;
	protected double tracksDirection;
	
	protected double movementSpeed;
	protected double trackRotation;
	protected double turretRotation;
	protected double fireRate;
	protected double health;
}
