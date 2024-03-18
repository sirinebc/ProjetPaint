package OS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Shape {
	protected int x;
	protected int y;
	protected int red=0;
	protected int green=0;
	protected int blue=0;
	protected boolean selected = false;
	
	public void changeColor(int red, int green, int blue) {
		this.red=red;
		this.green=green;
		this.blue=blue;
	}
	
	public void select() {
		this.selected = true;
		this.changeColor(0, 255, 0);
	}
	
	public void unselect() {
		this.selected = false;
		this.changeColor(0, 0, 0);
	}
	
	public boolean getselected() {
		return this.selected;
	}
	
	public Boolean clicked(int x, int y) { 
		return false;
	}
	
	public void paint(Graphics g) {
	}

	public void move(int dx, int dy) {
		if (selected) {
			this.x += dx;
			this.y += dy;
		}	
	}
	
}
