package OS;

public class Intersection {

	public static Rectangle computeIntersection(Rectangle rect1, Rectangle rect2){
	
    int xIntersection = Math.max(rect1.getX(), rect2.getX());
    int yIntersection = Math.max(rect1.getY(), rect2.getY());
    int intersectionWidth = Math.min(rect1.getX() + rect1.getWidth(), rect2.getX() + rect2.getWidth()) - xIntersection;
    int intersectionHeight = Math.min(rect1.getY() + rect1.getHeight(), rect2.getY() + rect2.getHeight()) - yIntersection;

   
    if (intersectionWidth <= 0 || intersectionHeight <= 0) {
        return null;
    }

   
    return new Rectangle(xIntersection, yIntersection, intersectionWidth, intersectionHeight);
}

}
