package forms;

import java.util.ArrayList;

public class Substract {
	//Array of the four new formed rectangles
    public static ArrayList<Rectangle> subtractRectangles(Rectangle rect1, Rectangle rect2) {
        ArrayList<Rectangle> subtract = new ArrayList<>();
      
       //case where rect1 contains rect2
       if (rect1.contains(rect2)) {
    	 
    	int x1= rect1.getX();
    	int y1= rect1.getY();
    	int w1= rect2.getX() - rect1.getX() ;
    	int h1= rect1.getHeight();
    	
    	int x2= rect2.getX() + rect2.getWidth();
    	int y2= rect1.getY();
    	int w2= (rect1.getX()+rect1.getWidth()) - (rect2.getX()+rect2.getWidth());
     	int h2= rect1.getHeight();
    	
    	int x3= rect2.getX();
    	int y3= rect1.getY();
    	int w3= rect2.getWidth();
    	int h3= rect2.getY()- rect1.getY();
    	
    	int x4= rect2.getX();
    	int y4= rect2.getY() + rect2.getHeight();
    	int w4= rect2.getWidth();
    	int h4= (rect1.getY()+rect1.getHeight())-(rect2.getY()+rect2.getHeight());
    	
    	  
    	Rectangle rect3 = new Rectangle(x1, y1, w1, h1);
        Rectangle rect4 = new Rectangle(x2, y2, w2, h2);
        Rectangle rect5 = new Rectangle(x3, y3, w3, h3);
        Rectangle rect6 = new Rectangle(x4, y4, w4, h4);
        
        
        subtract.add(rect3);
        subtract.add(rect4);
        subtract.add(rect5);
        subtract.add(rect6);
      }
       //any other case
       else {
    	int x1 = rect1.getX();
        int y1 = rect1.getY();
        int h1 = rect1.getHeight();
        int w1 = rect1.getWidth() - (Math.min(rect1.getX() + rect1.getWidth(), rect2.getX() + rect2.getWidth()) - Math.max(rect1.getX(), rect2.getX()));
        
        int x2 = rect2.getX();
        int y2 = rect1.getY();
        int h2 = rect1.getHeight() - (Math.min(rect1.getY() + rect1.getHeight(), rect2.getY() + rect2.getHeight()) - Math.max(rect1.getY(), rect2.getY()));
        int w2 = (Math.min(rect1.getX() + rect1.getWidth(), rect2.getX() + rect2.getWidth()) - Math.max(rect1.getX(), rect2.getX()));
        
        int x3 = rect2.getX();
        int y3 = rect1.getY()+ rect1.getHeight();
        int h3 = rect2.getHeight() - (Math.min(rect1.getY() + rect1.getHeight(), rect2.getY() + rect2.getHeight()) - Math.max(rect1.getY(), rect2.getY()));
        int w3 = rect2.getWidth();
        
        int x4 = rect1.getX() + rect1.getWidth();
        int y4 = rect2.getY();
        int w4 = rect2.getWidth()- (Math.min(rect1.getX() + rect1.getWidth(), rect2.getX() + rect2.getWidth()) - Math.max(rect1.getX(), rect2.getX()));
        int h4 = Math.min(rect1.getY() + rect1.getHeight(), rect2.getY() + rect2.getHeight()) - (Math.max(rect1.getY(), rect2.getY()));
        
        Rectangle rect3 = new Rectangle(x1, y1, w1, h1);
        Rectangle rect4 = new Rectangle(x2, y2, w2, h2);
        Rectangle rect5 = new Rectangle(x3, y3, w3, h3);
        Rectangle rect6 = new Rectangle(x4, y4, w4, h4);
        
        
        subtract.add(rect3);
        subtract.add(rect4);
        subtract.add(rect5);
        subtract.add(rect6);
        
      }
	return subtract;
    }
}
