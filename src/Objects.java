import processing.core.PApplet;

public abstract class Objects {

	protected final float StartingY, StartingX;
	protected float Speed;
	PApplet window;
	public Objects(PApplet window, float StartingX, float StartingY, float Speed) {
		this.window = window;
		this.StartingX = StartingX;
		this.StartingY = StartingY;
		this.Speed = Speed;
		
	}
	public abstract void draw();
	public abstract void move();
	public abstract void changeDirection();
	public abstract float getStartingX();
	public abstract float getStartingY();	
	public abstract float getSpeed();	
	public abstract float getEndPosition();	
	public abstract int whichObject();
	public abstract float getX();
	public abstract float getY();


}
