package OS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape{
	private int width;
	private int height;
	
	
	public Rectangle(int x0, int y0, int w, int h) {
		this.x = x0 ;
		this.y = y0 ;
		this.width = w;
		this.height = h;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Boolean clicked(int x, int y, int x2, int y2) { 
		for (int i=x;i<=x2;i++) {
			for (int j=y;j<=y2;j++) {
				
				if ( i >= this.x && j >= this.y && i <= this.x + this.width && j<= this.y + this.height) {
					return true;
				}
				
			}
		}
		return false;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		float[] dashpattern = {10};
		g2.setStroke(new BasicStroke(stroke));
		g2.setColor(new Color(red,green,blue));
		if (stroketype=='d') {
			g2.setStroke(new BasicStroke(stroke,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10,dashpattern,5));
			g2.drawRect(this.x, this.y,this.width, this.height);
		}
		else {
			g2.fillRect(this.x, this.y,this.width, this.height);
		}
		
		
		
	}
}
