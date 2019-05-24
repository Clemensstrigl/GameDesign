import processing.core.PApplet;

public class XMovingObjects extends Objects {
	float x, y, XStart, XEnd;

	public XMovingObjects(PApplet window, float StartingX, float StartingY, float XSpeed, float XEnd) {
		super(window,StartingX,StartingY,XSpeed);
		StartingX = x;
		StartingY = y;
		this.XStart = StartingX;
		this.XEnd = XEnd;

	}

	public void draw() {
		window.fill(window.color(255, 0, 0));
		window.ellipse(this.x, this.y, 10, 10);
	}

	public void move() {
		this.x = this.x + Speed;
		changeDirection();
	}

	public void changeDirection() {
		if (XStart < XEnd) {
			if (this.x < this.XStart) {
				Speed = Math.abs(Speed);
			}
			if (this.x > this.XEnd) {
				Speed = -Speed;
			}
		}
		if (XStart > XEnd) {
			if (this.x > this.XStart) {
				Speed = -Speed;
			}
			if (this.x < this.XEnd) {
				Speed = Math.abs(Speed);
			}
		}

	}
	
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}
}
