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
					case 'c' : shapemode = 'C';
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
				if (mode == 'S') {
					/*for (Shape s : shapes ) {
						if (s.getselected()) {
							s.move(e.getX()-startX, e.getY()-startY);
							startX = e.getX();
							startY = e.getY();
						}
					}*/
					if (selected_area != null) {
						selected_area.resize(e.getX()-startX , e.getY()-startY);
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
						switch (shapemode) {
						case 'R' :  ((Rectangle)currentshape).resize(e.getX()-startX , e.getY()-startY);break;
						case 'C' : ((Circle)currentshape).resize(e.getX()-startX);break;
						}
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
	
	public static void main(String[] args) {
		OS os = new OS();
}

	
}
