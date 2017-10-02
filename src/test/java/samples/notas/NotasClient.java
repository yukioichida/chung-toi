package samples.notas;// Arquivo: NotasClient.java (Roland Teodorowitsch; 28 ago. 2013)

import java.rmi.Naming;

public class NotasClient {

    private final NotasInterface notas;

    public NotasClient() throws Exception {
        this.notas = (NotasInterface) Naming.lookup("//localhost/Notas");
    }



    public void incrementaNota() throws Exception {
        double n = notas.incrementaNota("Alexandre");
        System.out.println(n);

    }
}

