import processing.core.PApplet;

public class XMovingObjects {
	PApplet window;
	float x, y, XSpeed , XStart, XEnd;
	private static float StartingX, StartingY;

	public XMovingObjects(PApplet window, float StartingX, float StartingY, float XSpeed, float XStart, float XEnd) {
		this.window = window;
		this.StartingX = StartingX;
		this.StartingY = StartingY;
		this.XSpeed = XSpeed;
		StartingX = x;
		StartingY = y;
		this.XStart = XStart;
		this.XEnd = XEnd;

	}

	public void draw() {
		window.fill(window.color(255, 0, 0));
		window.ellipse(x, y, 10, 10);
	}

	public void move() {
		x = x + XSpeed;
		changeDirection();
	}

	public void changeDirection() {
		if (y < XStart) {
			XSpeed = Math.abs(XSpeed);
		}
		if (y > XEnd) {
			XSpeed = -XSpeed;
		}
	}
}
