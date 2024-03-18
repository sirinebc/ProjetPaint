package OS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circle extends Shape{
	private int radius;
	
	
	public Circle(int x0, int y0, int r0) {
		this.x = x0 - r0/2;
		this.y = y0 - r0/2 ;
		this.radius = r0;
	}
	
	public Boolean clicked(int x, int y) { 
		if ( x >= this.x && y >= this.y && x <= this.x + radius && y<= this.y + radius) {
			return true;
		}
		return false;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));
		g2.setColor(new Color(red,green,blue));
		g2.drawOval(this.x, this.y, radius, radius);
	}
	
}
