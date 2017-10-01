package samples.notas;// Arquivo: NotasInterface.java (Roland Teodorowitsch; 28 ago. 2013)
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotasInterface extends Remote {
	double obtemNota(String nome) throws RemoteException;

	double incrementaNota(String nome) throws RemoteException;
}

