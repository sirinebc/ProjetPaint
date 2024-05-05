package forms;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerInterface{

	protected Server() throws RemoteException {
        super();
    }

    @Override
    public void save(ArrayList<Shape> shapes, String filename) throws RemoteException {
    	Local localsave = new Local();
		localsave.save(shapes,filename);
    }

    @Override
    public ArrayList<Shape> load(String filename) throws RemoteException {
	    Local localload = new Local();
	    return localload.load(filename);
    }
}
