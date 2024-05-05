package OS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Rectangle extends Shape {
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
	
	public void properties(int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
	}
	
	public void resize(int kx, int ky) {
		this.width += kx;
		this.height +=ky;
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
		float[] dashpattern = {5};
		g2.setStroke(new BasicStroke(stroke));
		if (this.selected) g2.setColor(new Color(0,255,0));
		else g2.setColor(new Color(red,green,blue));
		if (stroketype=='d') {
			g2.setColor(new Color(0,0,0));
			g2.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10,dashpattern,5));
			g2.drawRect(this.x, this.y,this.width, this.height);
		}
		else if (filled) g2.fillRect(this.x, this.y,this.width, this.height);
		else g2.drawRect(this.x, this.y,this.width, this.height);
		
	}
	
	private boolean containsPoint(int x, int y) {
	    return x >= this.x && y >= this.y && x <= this.x + this.width && y <= this.y + this.height;
	}
	
	public boolean contains(Rectangle rect2) {
		 int x2 = rect2.getX();
		    int y2 = rect2.getY();
		    int w2 = rect2.getWidth();
		    int h2 = rect2.getHeight();
		   	 
		    boolean topLeftInside = this.containsPoint(x2, y2);
		    boolean topRightInside = this.containsPoint(x2 + w2, y2);
		    boolean bottomLeftInside = this.containsPoint(x2, y2 + h2);
		    boolean bottomRightInside = this.containsPoint(x2 + w2, y2 + h2);
		     
		    return topLeftInside && topRightInside && bottomLeftInside && bottomRightInside;
	}
}
