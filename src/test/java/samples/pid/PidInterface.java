package samples.pid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PidInterface extends Remote {
	public int getPID() throws RemoteException;
}
