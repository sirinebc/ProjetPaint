package OS;

import java.awt.BasicStroke;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OS extends JPanel{

	private Shape currentshape = null;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selected = new ArrayList<Shape>();
	private Rectangle selected_area = null;
	private int startX, startY;
	
	public void clear_all() {
		currentshape = null;
		shapes.clear();
	    selected.clear();
	    selected_area = null;
	    startX = 0;
	    startY = 0;
		repaint();
	}
	
	
	public void union() {
		if (selected.size()>1) {
			Union u = new Union();
			System.out.println(selected);
			for (int i=0;i<selected.size();i++ ) {
				Shape s = selected.get(i);
				s.unselect();
				u.add(s);
				selected.remove(s);
				shapes.remove(s);
				i--;
			}
			System.out.println(selected);
			
			shapes.add(u);
			System.out.println(shapes);
			repaint();
		};
	}
	
	public void inter() {
		if (selected.size() == 2 && selected.get(0) instanceof Rectangle && selected.get(1) instanceof Rectangle) {
	        Rectangle r1 = (Rectangle) selected.get(0);
	        Rectangle r2 = (Rectangle) selected.get(1);
	        Rectangle intersection = Intersection.computeIntersection(r1, r2);
	        if (intersection != null) {
	            shapes.remove(r1);
	            shapes.remove(r2);
	            shapes.add(intersection); 
	            selected.clear();
	            repaint();
	        }
	    };
	}
	
	public void save_local(String filename) {
		Local localsave = new Local();
		localsave.save(shapes,filename);
	}
	
	public void load_local(String filename) {
		Local localload = new Local();
        shapes = localload.load(filename);
        repaint();
	}
	
	public void save_server(String filename, String ip_server) {
		try {
    		Registry registry = LocateRegistry.getRegistry(ip_server, 1099);
    		ServerInterface server = (ServerInterface) registry.lookup("ServerDrawing");
    		server.save(shapes,filename);
    		System.out.println("Saved successfully in server !");
			} catch (Exception f) {
				f.printStackTrace();
		};
	}
	
	public void load_server(String filename, String ip_server) {
		try {
    		Registry registry = LocateRegistry.getRegistry(ip_server, 1099);
    		ServerInterface server = (ServerInterface) registry.lookup("ServerDrawing");
    		shapes = server.load(filename);
    		repaint();
    		System.out.println("Restored successfully from server !");
			} catch (Exception f) {
				f.printStackTrace();
		};
	}
	
	public OS() {
		
		
		this.addMouseListener(new java.awt.event.MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				startX = e.getX();
				startY = e.getY();
				
				//Initialize adding
				if (Gui.mode == 'A') {
					selected_area = null;
					switch (Gui.shapemode) {
					case 'R' : currentshape = new Rectangle(startX,startY,0,0); break;
					case 'C' : currentshape = new Circle(startX,startY,0); break;
					case 'P' : currentshape = new Pencil(startX,startY);
					}
					shapes.add(currentshape); 
					repaint();
				}
				
				//Initialize selecting
				if (Gui.mode == 'S') {
					selected_area = new Rectangle(startX,startY,0,0);
					selected_area.changestroketype('d');
					repaint();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			
			public void mouseDragged(MouseEvent e) {
				
				//Move shapes
				if (Gui.mode == 'M') {
					selected_area = null;
					for (Shape s : shapes ) {
						if (s.clicked(e.getX()-5, e.getY()-5, e.getX()+5, e.getY()+5)) {
							s.move(e.getX()-startX, e.getY()-startY);
							startX = e.getX();
							startY = e.getY();
						}
					}
					repaint();
				};
				
				//Select shapes
				if (Gui.mode == 'S') {
					if (selected_area != null) {
						dragShape4dir(selected_area,e.getX(),e.getY());
						for (Shape s : shapes ) {
							if (s.clicked(selected_area.getX(), selected_area.getY(), selected_area.getX() + selected_area.getWidth(), selected_area.getY() + selected_area.getHeight()))
									{
								
								if (!selected.contains(s))selected.add(s);
								s.select();
								
							}else {
								
								selected.remove(s);
								s.unselect();
								
								
							}
							//FOR TESTING
							System.out.println(selected);
						}
					repaint();
					};
				};
				
				//Adding shapes
				if (Gui.mode == 'A') {
					if (currentshape != null)dragShape4dir(currentshape,e.getX(),e.getY());
					repaint();
				};
				
				//Resizing shapes
				if (Gui.mode == 'D') {
					for (Shape s : shapes ) {
						if (s.clicked(e.getX()-5, e.getY()-5, e.getX()+5, e.getY()+5)) {
							s.resize(e.getX()-startX, e.getY()-startY);	
							startX = e.getX();
							startY = e.getY();
						}
					}
					repaint();
				};
			};

			@Override
			public void mouseMoved(MouseEvent e) {
			}
		
			
		});
	}

	
	public void paint(Graphics g) {
		g.clearRect(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		for (Shape s : shapes ) {
			s.paint(g);
		}
		if (selected_area!= null) selected_area.paint(g);
	}
	
	
	//4 directions dragging
	public void dragShape4dir(Shape shape, int x, int y) {
		if (shape instanceof Rectangle) {
			if (x<startX && y<startY) ((Rectangle)shape).properties(x,y,startX-x , startY-y);
			else if (x<startX && y>startY) ((Rectangle)shape).properties(x,startY,startX-x , y-startY);
			else if (x>startX && y<startY) ((Rectangle)shape).properties(startX,y,x-startX , startY-y);
			else ((Rectangle)shape).properties(startX,startY,x-startX , y-startY);
		}else if (shape instanceof Circle){
			if (x<startX && y<startY) ((Circle)shape).properties(x,startY,startX-x);
			else if (x<startX && y>startY) ((Circle)shape).properties(x,startY,startX-x); 
			else if (x>startX && y<startY) ((Circle)shape).properties(startX,startY,startY-y);
			else ((Circle)shape).properties(startX,startY,x-startX);
		}else {
			((Pencil)shape).addcoords(x,y);
		}
	}
	

}
