package samples.pid;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class PidServer {

	public static void main(String[] args) {
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (RemoteException e) {
			System.out.println("RMI registry already running.");
		}
		try {
			Naming.rebind ("PID", new PidImpl ());
			System.out.println ("PidServer is ready.");
		} catch (Exception e) {
			System.out.println ("PidServer failed:");
			e.printStackTrace();
		}
	}

}
