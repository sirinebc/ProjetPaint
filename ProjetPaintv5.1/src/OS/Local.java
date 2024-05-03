package OS;
import java.io.*;
import java.util.ArrayList;

public class Local {
	
	public void save(ArrayList<Shape> shapes, String filename) {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(shapes);
			output.close();
			file.close();
			System.out.println("File " + filename + " saved successfully ! ");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Shape> load(String filename) {
		ArrayList<Shape> shapes = null ;
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream input = new ObjectInputStream(file);
			shapes = (ArrayList<Shape>) input.readObject();
			input.close();
			file.close();
			System.out.println("File " + filename + " loaded successfully ! ");
			
		} catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return shapes;
	}

}
