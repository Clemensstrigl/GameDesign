import processing.core.PApplet;

public class YMovingObjects extends Objects {
	float x, y, YStart, YEnd;

	public YMovingObjects(PApplet window, float StartingX, float StartingY, float YSpeed, float YEnd) {
		super(window, StartingX, StartingY, YSpeed);
		this.YStart = StartingY;
		this.YEnd = YEnd;
		this.x = StartingX;
		this.y = StartingY;

	}

	public void draw() {
		window.fill(window.color(0, 0, 255));
		window.ellipse(this.x, this.y, 20, 20);
	}

	public void move() {
		this.y = this.y + Speed;
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
		return this.x;
	}

	public float getY() {
		return this.y;
	}
}
