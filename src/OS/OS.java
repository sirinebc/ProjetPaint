package OS;

import java.awt.BasicStroke;
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
	private Shape selected = null;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private int startX, startY;
	
	public OS() {
		//javax.swing.JFrame os = new javax.swing.JFrame("OS");
		this.setLocation(400,300);
		this.setSize(800,500);
		/*GraphicsEnvironment.getLocalGraphicsEnvironment()
		  .getDefaultScreenDevice()
		  .setFullScreenWindow(this);*/
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
				
				if (mode=='A') {
					switch (shapemode) {
						case 'R' : shapes.add(new Rectangle(e.getX(),e.getY(),100,100)); break;
						case 'C' : shapes.add(new Circle(e.getX(),e.getY(),100));
					}
				}
				if (mode=='S') {
					startX = e.getX();
					startY = e.getY();
					for (Shape s : shapes ) {
						if (s.clicked(e.getX(),e.getY())) { 
							if (selected != null && s.equals(selected)==false) selected.unselect(); 
							selected = s ; 
							s.select();
						}
					} 
				}
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if (mode == 'S') {
					for (Shape s : shapes ) {
						if (s.getselected()) {
							s.move(e.getX()-startX, e.getY()-startY);
							startX = e.getX();
							startY = e.getY();
						}
					}
				}
				repaint();
			}
			public void mouseMoved(MouseEvent e) {
		}});

		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		
		g.clearRect(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		for (Shape s : shapes ) {
			s.paint(g);
		}
	}
	
	public static void main(String[] args) {
		OS os = new OS();
}

	
}
