package forms;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Pencil extends Shape{
	private ArrayList<Point> coords = new ArrayList<Point>();
	
	public Pencil(int x0, int y0) {
		addcoords(x0,y0);
	}
	
	public void move(int dx, int dy) {
		if (selected) {
			for (int i = 0; i < coords.size(); i++) {
	            coords.get(i).x +=dx;
	            coords.get(i).y +=dy;
	        }
		}
	}
	
	public void addcoords(int x, int y) {
		coords.add(new Point(x,y));
	}
	
	
	public Boolean clicked(int x, int y, int x2, int y2) { 
		for (int i=x;i<=x2;i++) {
			for (int j=y;j<=y2;j++) {
				for (int p = 0; p < coords.size(); p++)if (coords.get(p).x==i && coords.get(p).y==j) return true;
			}
		}
		return false;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(stroke));
		g2.setColor(new Color(red,green,blue));
		if (this.selected) g2.setColor(new Color(40,150,80));
		else g2.setColor(new Color(red,green,blue));
		for (int i = 1; i < coords.size(); i++) {
            Point p1 = coords.get(i - 1);
            Point p2 = coords.get(i);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
	}
}
