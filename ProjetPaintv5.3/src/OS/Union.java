package OS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Union extends Shape {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Boolean adding = false;
	
	public void add(Shape s) {
		shapes.add(s);
	}
	
	
	public void select() {
		for (Shape s : shapes ) {
			s.select();
		}
	}
	
	public void unselect() {
		for (Shape s : shapes ) {
			s.unselect();
		}
	}
	
	public void move(int dx, int dy) {
		for (Shape s : shapes ) {
			s.move(dx, dy);
		}
	}
	
	public void resize(int kx, int ky) {
		for (Shape s : shapes ) {
			s.resize(kx, ky);
		}
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
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


