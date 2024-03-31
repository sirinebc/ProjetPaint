package OS;

import java.awt.BasicStroke;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class OS extends JFrame{
	
	private Character mode = 'N';
	private Character shapemode = 'R';

	private Shape currentshape = null;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selected = new ArrayList<Shape>();
	private Rectangle selected_area = null;
	private int startX, startY;
	
	public OS() {
		this.setLocation(300,200);
		this.setSize(1000,700);
		this.addKeyListener(new java.awt.event.KeyListener() {
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyChar()) {
					case 'q' : System.exit(0);
					case 'a' : mode = 'A'; break;
					case 's' : mode = 'S'; break;
					case 'r' : shapemode = 'R'; break;
					case 'm' : selected_area=null;mode = 'M';break;
					case 'c' : shapemode = 'C'; break;
					case 'u' : if (selected.size()>1) {
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
								}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.addWindowListener(new java.awt.event.WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}});
		this.addMouseListener(new java.awt.event.MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				startX = e.getX();
				startY = e.getY();
				if (mode == 'A') {
					selected_area = null;
					switch (shapemode) {
					case 'R' : currentshape = new Rectangle(startX,startY,0,0); break;
					case 'C' : currentshape = new Circle(startX,startY,0); break;
					}
					shapes.add(currentshape); 
					repaint();
				}
				if (mode == 'S') {
					selected_area = new Rectangle(startX,startY,0,0);
					selected_area.changestroketype('d');
					repaint();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if (mode == 'M') {
					for (Shape s : shapes ) {
						if (s.clicked(e.getX(), e.getY(), e.getX()+1, e.getY()+1)) {
							s.move(e.getX()-startX, e.getY()-startY);
							startX = e.getX();
							startY = e.getY();
						}
					}
					repaint();
				}
				
				if (mode == 'S') {
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
							System.out.println(selected);
						}
					repaint();
				}
				}
				if (mode == 'A') {
					if (currentshape != null) {
						dragShape4dir(currentshape,e.getX(),e.getY());
					}
					repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			
		});

		this.setVisible(true);
	}

	
	public void paint(Graphics g) {
		g.clearRect(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		for (Shape s : shapes ) {
			s.paint(g);
		}
		if (selected_area!= null) selected_area.paint(g);
	}
	
	public void dragShape4dir(Shape shape, int x, int y) {
		if (shape instanceof Rectangle) {
			if (x<startX && y<startY) ((Rectangle)shape).resize(x,y,startX-x , startY-y);
			else if (x<startX && y>startY) ((Rectangle)shape).resize(x,startY,startX-x , y-startY);
			else if (x>startX && y<startY) ((Rectangle)shape).resize(startX,y,x-startX , startY-y);
			else ((Rectangle)shape).resize(startX,startY,x-startX , y-startY);
		}else {
			if (x<startX && y<startY) ((Circle)shape).resize(x,startY,startX-x);
			else if (x<startX && y>startY) ((Circle)shape).resize(x,startY,startX-x); 
			else if (x>startX && y<startY) ((Circle)shape).resize(startX,startY,startY-y);
			else ((Circle)shape).resize(startX,startY,x-startX);
		}
	}
	
	public static void main(String[] args) {
		OS os = new OS();
}

	
}
