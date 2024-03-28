package OS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Union extends Shape{
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public void add(Shape s) {
		shapes.add(s);
	}
	
	public Boolean clicked(int x, int y, int x2, int y2) { 
		for (Shape s : shapes ) {
			if (s.clicked(x,y,x2,y2)) return true;
		}
		return false;
	}

	
	public void paint(Graphics g) {
		for (Shape s : shapes ) {
			s.paint(g);
		}
	}
}


