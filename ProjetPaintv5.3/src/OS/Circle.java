package OS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Circle extends Shape {
	private int radius;
	
	
	public Circle(int x0, int y0, int r0) {
		this.x = x0 - r0/2;
		this.y = y0 - r0/2 ;
		this.radius = r0;
	}
	
	public int getRadius() {
		return this.radius;
	}
	
	public void properties(int x, int y, int radius) {
		this.x=x;
		this.y=y;
		this.radius=radius;
	}
	
	public void resize(int kx,int ky) {
		this.radius +=kx;
	}
	
	public Boolean clicked(int x, int y, int x2, int y2) { 
		for (int i=x;i<=x2;i++) {
			for (int j=y;j<=y2;j++) {
				if ( i >= this.x && j >= this.y && i <= this.x + radius && j<= this.y + radius) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(stroke));
		g2.setColor(new Color(red,green,blue));
		if (this.selected) g2.setColor(new Color(0,255,0));
		else g2.setColor(new Color(red,green,blue));
		if (filled) g2.fillOval(this.x, this.y, radius, radius);
		else g2.drawOval(this.x, this.y, radius, radius);
	}
	
}
