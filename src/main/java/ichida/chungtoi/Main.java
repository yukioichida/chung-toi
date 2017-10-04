package ichida.chungtoi;

import ichida.chungtoi.integration.ChungToiInterface;
import ichida.chungtoi.model.Player;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import static ichida.chungtoi.util.ClientConstants.*;
import static ichida.chungtoi.util.ResultConstants.*;

public class Main {

    private static ChungToiInterface client;

    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void main(String... args) throws Exception {
        System.out.println("Defina seu nome:");
        String playerName = scanner.next();
        int option = -1;
        System.out.println("Conectando com o servidor...");
        try {
            client = connect("localhost");
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o servidor do jogo.");
            e.printStackTrace();
            System.exit(0);
            return;
        }
        System.out.println("Conexão estabelecida");

        Player player = registerPlayer(playerName);
        if (player != null) {
            System.out.println("Jogador Registrado: " + player);


        }
    }

    public static ChungToiInterface connect(String serverHost) throws Exception {
        return (ChungToiInterface) Naming.lookup("//" + serverHost + "/chungtoi");
    }

    public static Player registerPlayer(String name) throws Exception {
        Player player = new Player(name);
        int result = client.createPlayer(name);
        if (result == PLAYER_ALREADY_REGISTERED) {
            System.out.println("Jogador " + name + " já cadastrado");
            return null;
        } else if (result == PLAYER_LIMIT_REACHED) {
            System.out.println("Limite de jogadores atingido");
            return null;
        } else {
            player.setId(result);
            return player;
        }
    }

    public static void showMenu(Player player) throws Exception {
        int hasGameResult = client.hasGame(player.getId());
        while (hasGameResult == NO_GAME) {
            Thread.sleep(1000L);
            hasGameResult = client.hasGame(player.getId());
        }
        if (hasGameResult == REGISTER_TIMEOUT) {
            System.out.println("Tempo limite de espera atingido");
        } else if (hasGameResult == OK) {
            String opponentName = client.getOppositePlayer(player.getId());
            System.out.println("Seu adversário: " + opponentName);
        } else {
            // Inconsistência do servidor
            System.out.println("Retorno do método hasGame inesperado: " + hasGameResult);
        }
    }

    public static void showGameMenu(Player player, String opponent) throws Exception {
        boolean gameRunning = true;
        while (gameRunning) {
            int gameStatus = client.isMyTurn(player.getId());
            System.out.println("Esperando o turno do adversário");
            int insertedPieces = 0;
            while (gameStatus == ADVERSARY_TURN) {
                Thread.sleep(1000L);
                gameStatus = client.isMyTurn(player.getId());
            }
            if (gameStatus == WINNER) {
                gameRunning = false;
                System.out.println("Você venceu");
            } else if (gameStatus == LOSER) {
                gameRunning = false;
                System.out.println("Voce perdeu");
            } else if (gameStatus == PLAYER_TURN) {
                if (insertedPieces < 3) {


                    insertedPieces++;
                }
                // Insere se não tiver tres peças
                // se ja tiver tres peças, move
                // a contagem de peças pode ser controlada no lado do cliente
            }
        }
    }

    public static void showInsertPieceMenu(Player player) throws Exception {
        System.out.println("Digite a posição do tabuleiro que será inserido a peça [0-8]:");
        int position = scanner.nextInt();
        System.out.println("Defina a orientação da peça(0-perpendicular, 1-diagonal):");
        int orientation = scanner.nextInt();
        int result = client.insertPiece(player.getId(), position, orientation);
    }

}
