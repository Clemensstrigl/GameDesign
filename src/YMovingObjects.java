import processing.core.PApplet;

public class YMovingObjects {
	PApplet window;
	float x, y, YSpeed, StartingX, StartingY, YStart, YEnd;

	public YMovingObjects(PApplet window, float StartingX, float StartingY, float YSpeed, float YStart, float YEnd) {
		this.window = window;
		this.StartingX = StartingX;
		this.StartingY = StartingY;
		this.YSpeed = YSpeed;
		StartingX = x;
		StartingY = y;
		this.YStart = YStart;
		this.YEnd = YEnd;

	}

	public void draw() {
		window.fill(window.color(255, 0, 0));
		window.ellipse(x, y, 10, 10);
	}

	public void move() {
		y = y + YSpeed;
		changeDirection();
	}

	public void changeDirection() {
		if (y < YStart) {
			YSpeed = Math.abs(YSpeed);
		}
		if (y > YEnd) {
			YSpeed = -YSpeed;
		}
	}
}
