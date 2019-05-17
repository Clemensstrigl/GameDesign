import processing.core.PApplet;

public class Play {
	float x, y;
	PApplet window;

	public Play(float x, float y, PApplet window) {
		this.x = x;
		this.y = y;
		this.window = window;
	}

	public void draw() {
		window.ellipse(x, y, 20, 20);
	}

	public void move(int direction) {
		if (direction == 0) {
			x = x - 5;
		}
		if (direction == 1) {
			x = x - 5;
			y = y - 5;
		}
		if (direction == 2) {
			y = y - 5;

		}
		if (direction == 3) {
			y = y - 5;
			x = x + 5;
		}
		if (direction == 4) {
			x = x + 5;
		}
		if (direction == 5) {
			x = x + 5;
			y = y + 5;
		}
		if (direction == 6) {
			y = y + 5;
			
		}
		if (direction == 7) {
			x = x - 5;
			y = y + 5;
		}

	}
}
