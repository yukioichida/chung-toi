/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chungtoi;

import chungtoi.integration.ws.client.ChungToiWSServer;
import chungtoi.integration.ws.client.ChungToiWSServer_Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author ichida
 */
public class ChungToiWSClient {

    static ChungToiWSServer port;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ChungToiWSServer_Service service = new ChungToiWSServer_Service();
        port = service.getChungToiWSServerPort();
        String basePath = "src/main/resources/";
        executaTeste(basePath + "ChungToi-0000");
    }

    private static int preRegistro(String p1name, int p1id, java.lang.String p2name, int p2id) {
        return port.preRegister(p1name, p1id, p2name, p2id);
    }

    private static void executaTeste(String rad) throws IOException {
        String inFile = rad + ".in";
        FileInputStream is = new FileInputStream(new File(inFile));
        System.setIn(is);

        String outFile = rad + ".out";
        FileWriter outWriter = new FileWriter(outFile);
        try (PrintWriter out = new PrintWriter(outWriter)) {
            Scanner leitura = new Scanner(System.in);
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
            leitura.close();
        }
    }

    private static void erro(String arq, int operacao) {
        System.err.println("Entrada invalida: erro na operacao " + operacao + " do arquivo " + arq);
        System.exit(1);
    }
}
