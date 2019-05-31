import processing.core.PApplet;

public class XMovingObjects extends Objects {
	float x, y, XStart, XEnd;

	public XMovingObjects(PApplet window, float StartingX, float StartingY, float XSpeed, float XEnd) {
		super(window, StartingX, StartingY, XSpeed);
		this.XStart = StartingX;
		this.XEnd = XEnd;
		this.x = StartingX;
		this.y = StartingY;

	}

	public void draw() {
		window.fill(window.color(0, 0, 255));
		window.ellipse(this.x, this.y, 20, 20);
	}

	public void move() {
		this.x = this.x + Speed;
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
