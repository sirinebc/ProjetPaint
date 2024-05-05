package forms;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {
	void save(ArrayList<Shape> shapes, String filename) throws RemoteException;
    ArrayList<Shape> load(String filename) throws RemoteException;
}
