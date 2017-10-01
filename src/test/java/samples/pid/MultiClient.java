package samples.pid;

public class MultiClient {

    public static void main(String... args) throws Exception{

        PidClient clientA = new PidClient();

        PidClient clientB = new PidClient();

        Thread threadA = new Thread(() -> {
            try {
                clientA.printPid();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                clientB.printPid();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();

    }
}
