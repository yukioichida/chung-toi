package chungtoi;

import chungtoi.integration.ws.client.ChungToiWSServer;
import chungtoi.integration.ws.client.ChungToiWSServer_Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChungToiWSClientParallel {

    static ChungToiWSServer port;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ChungToiWSServer_Service service = new ChungToiWSServer_Service();
        port = service.getChungToiWSServerPort();
        String basePath = "C:\\Users\\ichida\\Downloads\\ChungToi\\";
        runSequential(new ArrayList<>(Arrays.asList(basePath + "ChungToi-0000", basePath + "ChungToi-0100", basePath + "ChungToi-1000", basePath + "ChungToi-3000")));
        runParallel(new ArrayList<>(Arrays.asList(basePath + "ChungToi-2000", basePath + "ChungToi-2500")));
        runParallel(new ArrayList<>(Arrays.asList(basePath + "ChungToi-4000", basePath + "ChungToi-4500")));
    }

    private static void runParallel(List<String> tests) {
        List<Thread> threads = new ArrayList<>();

        // Prepara threads
        for (String test : tests) {
            threads.add(new Thread() {
                @Override
                public void run() {
                    try {
                        executaTeste(test);
                    } catch (IOException ex) {
                        System.out.println("Falha na execução do teste.");
                        ex.printStackTrace(System.out);
                    }
                }
            });
        }

        // Roda threads
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                System.out.println("Falha ao reagrupar thread.");
                ex.printStackTrace(System.out);
            }
        }
        System.out.println("Teste paralelo concluido.");
    }

    private static void runSequential(List<String> tests) {
        for (String test : tests) {
            try {
                executaTeste(test);
            } catch (IOException ex) {
                System.out.println("Falha ao executar teste " + test);
                ex.printStackTrace(System.out);
            }
        }
        System.out.println("Teste sequencial concluido.");
    }

    private static void executaTeste(String rad) throws IOException {
        String inFile = rad + ".in";
        FileInputStream is = new FileInputStream(new File(inFile));
        //System.setIn(is);

        String outFile = rad + ".out";
        FileWriter outWriter = new FileWriter(outFile);
        try (PrintWriter out = new PrintWriter(outWriter); Scanner leitura = new Scanner(is)) {
            int numOp = leitura.nextInt();
            for (int i = 0; i < numOp; ++i) {
                System.out.print("\r" + rad + ": " + (i + 1) + "/" + numOp);
                int op = leitura.nextInt();
                String parametros = leitura.next();
                String param[] = parametros.split(":", -1);
                switch (op) {
                    case 0:
                        if (param.length != 4) {
                            erro(inFile, i + 1);
                        } else {
                            out.println(port.preRegister(param[0], Integer.parseInt(param[1]), param[2], Integer.parseInt(param[3])));
                        }
                        break;
                    case 1:
                        if (param.length != 1) {
                            erro(inFile, i + 1);
                        } else {
                            out.println(port.createPlayer(param[0]));
                        }
                        break;
                    case 2:
                        if (param.length != 1) {
                            erro(inFile, i + 1);
                        } else {
                            out.println(port.endGame(Integer.parseInt(param[0])));
                        }
                        break;
                    case 3:
                        if (param.length != 1) {
                            erro(inFile, i + 1);
                        } else {
                            out.println(port.hasGame(Integer.parseInt(param[0])));
                        }
                        break;
                    case 4:
                        if (param.length != 1) {
                            erro(inFile, i + 1);
                        } else {
                            out.println(port.getOppositePlayer(Integer.parseInt(param[0])));
                        }
                        break;
                    case 5:
                        if (param.length != 1) {
                            erro(inFile, i + 1);
                        } else {
                            out.println(port.isMyTurn(Integer.parseInt(param[0])));
                        }
                        break;
                    case 6:
                        if (param.length != 1) {
                            erro(inFile, i + 1);
                        } else {
                            out.println(port.getGameStatus(Integer.parseInt(param[0])));
                        }
                        break;
                    case 7:
                        if (param.length != 3) {
                            erro(inFile, i + 1);
                        } else {
                            out.println(port.insertPiece(Integer.parseInt(param[0]), Integer.parseInt(param[1]), Integer.parseInt(param[2])));
                        }
                        break;
                    case 8:
                        if (param.length != 5) {
                            erro(inFile, i + 1);
                        } else {
                            out.println(port.movePiece(Integer.parseInt(param[0]), Integer.parseInt(param[1]), Integer.parseInt(param[2]), Integer.parseInt(param[3]), Integer.parseInt(param[4])));
                        }
                        break;
                    default:
                        erro(inFile, i + 1);
                }
            }
            System.out.println("... terminado!");
            out.close();
        }
    }

    private static void erro(String arq, int operacao) {
        System.err.println("Entrada invalida: erro na operacao " + operacao + " do arquivo " + arq);
        System.exit(1);
    }
}
