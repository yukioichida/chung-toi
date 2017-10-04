package ichida.chungtoi;

import ichida.chungtoi.integration.ChungToiInterface;
import ichida.chungtoi.model.Game;
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
        System.out.println("Conexão estabelecida.");
        Player player = registerPlayer(playerName);
        if (player != null) {
            System.out.println("Jogador Registrado: " + player);
            showMenu(player);

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

        System.out.println("Esperando outro jogador para iniciar a partida");

        int hasGameResult = client.hasGame(player.getId());
        while (hasGameResult == NO_GAME) {
            Thread.sleep(1000L);
            hasGameResult = client.hasGame(player.getId());
        }
        if (hasGameResult == REGISTER_TIMEOUT) {
            System.out.println("Tempo limite de espera atingido");
        } else if (hasGameResult == PLAYER_C_GAME) {
            showGameMenu(player, "C");
        } else if (hasGameResult == PLAYER_E_GAME) {
            showGameMenu(player, "E");
        } else {
            // Inconsistência do servidor
            System.out.println("Retorno do método hasGame inesperado: " + hasGameResult);
        }
    }

    /**
     * Mostra o menu do jogo ao ser inicializado
     */
    public static void showGameMenu(Player player, String piece) throws Exception {

        System.out.println("Partida iniciada. Sua peça é " + piece);
        String opponentName = client.getOppositePlayer(player.getId());
        System.out.println("Seu adversário: " + opponentName);
        boolean gameRunning = true;
        while (gameRunning) {
            int gameStatus = client.isMyTurn(player.getId());
            System.out.println("Esperando o turno do adversário");
            int insertedPieces = 0;
            while (gameStatus == PLAYER_ADVERSARY_TURN) {
                Thread.sleep(1000L);
                gameStatus = client.isMyTurn(player.getId());
            }
            showGameBoard(player);
            System.out.println("Sua vez:");
            if (gameStatus == WINNER) {
                gameRunning = false;
                System.out.println("Você venceu");
            } else if (gameStatus == LOSER) {
                gameRunning = false;
                System.out.println("Voce perdeu");
            } else if (gameStatus == PLAYER_TURN) {
                if (insertedPieces < 3) {
                    showInsertPieceMenu(player);
                    insertedPieces++;
                } else {

                }
                // Insere se não tiver tres peças
                // se ja tiver tres peças, move
                // a contagem de peças pode ser controlada no lado do cliente
            } else if (gameStatus == GAME_TIMEOUT) {
                System.out.println("Tempo de espera encerrado");
                gameRunning = false;
            }
        }
    }

    public static void showGameBoard(Player player) throws Exception {
        String boardGame = client.getGameStatus(player.getId());
        Game game = new Game();
        game.populateFromString(boardGame);
        System.out.println("Tabuleiro:");
        System.out.println(game.toString());
        System.out.println("-------------");
    }

    public static void showInsertPieceMenu(Player player) throws Exception {
        System.out.println("Digite a posição do tabuleiro que será inserido a peça [0-8]:");
        int position = scanner.nextInt();
        System.out.println("Defina a orientação da peça(0-perpendicular, 1-diagonal):");
        int orientation = scanner.nextInt();
        int result = client.insertPiece(player.getId(), position, orientation);
        if (result == GAME_TIMEOUT) {
            System.out.println("Partida encerrada. Tempo de espera esgotado.");
        } else if (result == PIECE_PLACED) {
            System.out.println("Peça inserida com sucesso");
        } else if (result == POSITION_ALREADY_OCCUPIED) {
            System.out.println("Posição já ocupada");
            showInsertPieceMenu(player);
        } else if (result == INVALID_PARAMETERS) {
            System.out.println("Dados de inserção de peça inválidos.");
            showInsertPieceMenu(player);
        } else {
            System.out.println("Retorno do servidor não esperado.");
        }
    }

}
