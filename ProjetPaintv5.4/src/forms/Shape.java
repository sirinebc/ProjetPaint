package forms;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Shape implements Serializable{
	protected int x;
	protected int y;
	protected int red=App.basic_red;
	protected int green=App.basic_green;
	protected int blue=App.basic_blue;
	protected int stroke=App.basic_stroke;
	protected char stroketype='n';
	protected boolean filled = App.basic_filled;
	protected boolean selected = false;
	

	public void select() {
		this.selected = true;
	}
	
	public void unselect() {
		this.selected = false;
	}
	
	public boolean getselected() {
		return this.selected;
	}
	
	public Boolean clicked(int x, int y, int x2, int y2) { 
		return false;
	}
	
	public void changestroketype(char stroketype) {
		this.stroketype = stroketype;
	}
	
	public void changefilled(boolean filled) {
		this.filled = filled;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void move(int dx, int dy) {
		if (selected) {
			this.x += dx;
			this.y += dy;
		}	
	}
	
	//Operations to overwrite later on subclasses
	public void resize(int kx,int ky) {
	}
	
	public void properties(int x,int y,int w, int h) {
	}
	
	public void paint(Graphics g) {
	}
	

	
	
}
