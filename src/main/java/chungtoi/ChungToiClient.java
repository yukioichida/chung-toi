package ichida.chungtoi;

import ichida.chungtoi.integration.ChungToiInterface;
import ichida.chungtoi.model.Game;
import ichida.chungtoi.model.Player;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import static ichida.chungtoi.util.ClientConstants.*;
import static ichida.chungtoi.util.GameConstants.NO_MOVENT;
import static ichida.chungtoi.util.ResultConstants.*;

public class ChungToiClient {

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
        System.out.println("=============================================");
        System.out.println("Partida iniciada. Sua peça é " + piece);
        String opponentName = client.getOppositePlayer(player.getId());
        System.out.println("Seu adversário: " + opponentName);
        System.out.println("=============================================");
        boolean gameRunning = true;
        int insertedPieces = 0;

        while (gameRunning) {
            int gameStatus = client.isMyTurn(player.getId());
            System.out.println("Esperando o turno do adversário");
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
                    gameRunning = showInsertPieceMenu(player);
                    insertedPieces++;
                } else {
                    gameRunning = showMovePieceMenu(player);
                }
            } else if (gameStatus == GAME_TIMEOUT) {
                System.out.println("Tempo de espera encerrado");
                gameRunning = false;
            }
        }
    }
    /**
     * Mostra o menu de posicionamento de peças.
     *
     * @param player - Jogador que irá inserir a peça
     * @return true se o jogo deve continuar depois da ação.
     */
    public static boolean showInsertPieceMenu(Player player) throws Exception {
        System.out.println("Digite a posição do tabuleiro que será inserido a peça [0-8]:");
        int position = scanner.nextInt();
        System.out.println("Defina a orientação da peça(0-perpendicular, 1-diagonal):");
        int orientation = scanner.nextInt();
        int result = client.insertPiece(player.getId(), position, orientation);

        if (result == GAME_TIMEOUT) {
            System.out.println("Partida encerrada. Tempo de espera esgotado.");
        } else if (result == PIECE_PLACED) {
            System.out.println("Peça inserida com sucesso");
            return true;
        } else if (result == POSITION_ALREADY_OCCUPIED) {
            System.out.println("Posição já ocupada");
            return showInsertPieceMenu(player);
        } else if (result == INVALID_PARAMETERS) {
            System.out.println("Dados de inserção de peça inválidos.");
            return showInsertPieceMenu(player);
        } else {
            System.out.println("Retorno do servidor não esperado.");
        }

        return false;
    }

    public static boolean showMovePieceMenu(Player player) throws Exception {
        System.out.println("Selecione a peça que deseja mover [0-8]:");
        int actualPosition = scanner.nextInt();

        System.out.println("Selecione o sentido do movimento:");
        System.out.println("0 - esquerda superior");
        System.out.println("1 - superior");
        System.out.println("2 - direita superior");
        System.out.println("3 - esquerda");
        System.out.println("4 - sem movimento");
        System.out.println("5 - direita");
        System.out.println("6 - esquerda inferior");
        System.out.println("7 - inferior");
        System.out.println("8 - direita inferior");
        int direction = scanner.nextInt();
        int stepSize = 0;
        if (direction != NO_MOVENT) {
            System.out.println("Defina o número de passos do movimento [1-2]");
            stepSize = scanner.nextInt();
        }
        System.out.println(stepSize);
        System.out.println("Defina a orientação destino da peça: (0-perpendicular, 1-diagonal)");
        int orientation = scanner.nextInt();

        int result = client.movePiece(player.getId(), actualPosition, direction, stepSize, orientation);

        if (result == ADVERSARY_TURN) {
            System.out.println("Não é o seu turno. Aguarde o seu oponente.");
        } else if (result == POSITION_ALREADY_OCCUPIED) {
            System.out.println("Posição destino já está ocupada ou a posição destino está fora do tabuleiro");
            return showMovePieceMenu(player); // Caso ocorra problema de entrada, peça novamente
        } else if (result == INVALID_PARAMETERS) {
            System.out.println("Posição ou orientação destino da peça inválida");
            return showMovePieceMenu(player); // Caso ocorra problema de entrada, peça novamente
        } else if (result == PIECE_PLACED) {
            System.out.println("Movimento realizado com sucesso");
            return true;
        } else if (result == GAME_TIMEOUT) {
            System.out.println("Jogo encerrado. Algum dos jogadores não executou uma ação dentro do tempo limite");
        } else {
            System.out.println("Retorno não esperado");
        }
        return false;
    }

    /**
     * Mostra o quadro do jogo atual no console
     */
    public static void showGameBoard(Player player) throws Exception {
        String boardGame = client.getGameStatus(player.getId());
        Game game = new Game();
        game.populateFromString(boardGame);
        System.out.println("Tabuleiro:");
        System.out.println(game.toString());
        System.out.println("-------------");
    }


}
