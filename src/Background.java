import processing.core.PApplet;

public class Background {
	
	int height;
	int width;
	
	PApplet window;
	public Background(int w, int h, PApplet win) {
		this.width = w;
		this.height = h;
		this.window = win;
	}
	
	public void DrawBackground(int g) {
		
		for(int i = 0; i<width; i+= g) {
			for(int h = 0; h<height; h+= g) {
				window.stroke(20);
				window.fill(255);
				window.rect(i, h, g, g);
				
				
				
				
			}
		}
	}
}
