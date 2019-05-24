import processing.core.PApplet;

public class YMovingObjects extends Objects {
	PApplet window;
	float x, y, YStart, YEnd;

	public YMovingObjects(PApplet window, float StartingX, float StartingY, float YSpeed, float YEnd) {
		super(window, StartingX, StartingY, YSpeed);
		StartingX = x;
		StartingY = y;
		this.YStart = StartingY;
		this.YEnd = YEnd;

	}

	public void draw() {
		window.fill(window.color(255, 0, 0));
		window.ellipse(this.x, this.y, 10, 10);
	}

	public void move() {
		this.y = this.y + Speed;
		changeDirection();
	}

	public void changeDirection() {
		if (YStart < YEnd) {
			if (this.y < this.YStart) {
				Speed = Math.abs(Speed);
			}
			if (this.y > this.YEnd) {
				Speed = -Speed;
			}
		}
		if (YStart > YEnd) {
			if (this.y > this.YStart) {
				Speed = -Speed;
			}
			if (this.y < this.YEnd) {
				Speed = Math.abs(Speed);
			}
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
