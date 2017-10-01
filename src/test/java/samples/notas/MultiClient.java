package samples.notas;

import samples.pid.PidClient;

public class MultiClient {

    public static void main(String... args) throws Exception{

        NotasClient clientA = new NotasClient();

        NotasClient clientB = new NotasClient();
        NotasClient clientC = new NotasClient();
        NotasClient clientD = new NotasClient();

        Thread ta = new Thread(() -> {
            try {
                clientA.incrementaNota();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        Thread tb = new Thread(() -> {
            try {
                clientB.incrementaNota();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        Thread tc = new Thread(() -> {
            try {
                clientC.incrementaNota();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        Thread td = new Thread(() -> {
            try {
                clientD.incrementaNota();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        ta.start();
        tb.start();
        tc.start();
        td.start();
    }
}
