package samples.pid;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicInteger;

public class PidImpl extends UnicastRemoteObject implements PidInterface {

	private static final long serialVersionUID = 1234L;
	static private AtomicInteger nextPID = new AtomicInteger(1);

	protected PidImpl() throws RemoteException {
	}

	@Override
	public int getPID() throws RemoteException {
		int pid;

		System.out.println("PidServer> Entrada");
		pid = nextPID.incrementAndGet();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("PidServer> Saida");
		return pid;
	}

}
