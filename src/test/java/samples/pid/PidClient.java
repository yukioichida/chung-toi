package samples.pid;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PidClient {

	private final PidInterface pid;

	public PidClient() throws Exception{
		pid = (PidInterface)Naming.lookup("//localhost/PID");

	}

	public void printPid() throws Exception{
		System.out.println("PID="+pid.getPID());
	}

}
