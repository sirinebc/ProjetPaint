package OS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Shape{
	private int width;
	private int height;
	private boolean selected = false;
	
	
	public Rectangle(int x0, int y0, int w, int h) {
		this.x = x0 - w/2;
		this.y = y0 - h/2 ;
		this.width = w;
		this.height = h;
	}
	
	
	public Boolean clicked(int x, int y) { 
		if ( x >= this.x && y >= this.y && x <= this.x + this.width && y<= this.x + this.height) {
			return true;
		}
		return false;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));
		g2.setColor(new Color(red,green,blue));
		g2.drawRect(this.x, this.y,this.width, this.height);
	}
}
