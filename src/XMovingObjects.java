import processing.core.PApplet;

public class XMovingObjects {
	PApplet window;
	float x, y, XSpeed, YSpeed, Radius, CircleSpeed, StartingX,StartingY, XStart, XEnd,YStart,YEnd;

	public XMovingObjects(PApplet window, float StartingX, float StartingY , float XSpeed, float YSpeed, float XStart, float XEnd, float yStart, float YEnd, float Radius, float CircleSpeed) {
		this.window = window;
		this.StartingX = StartingX;
		this.StartingY = StartingY;
		this.XSpeed = XSpeed;
		this.YSpeed = YSpeed;
		this.Radius = Radius;
		this.CircleSpeed = CircleSpeed;
		StartingX = x;
		StartingY = y;

	}

	public void draw() {
		window.fill(window.color(255, 0, 0));
		window.ellipse(x, y, 10, 10);
	}

	public void moveX() {
		x = x - XSpeed;
	}
	public void moveY() {
		y = y + YSpeed;
	}
}
