package ichida.chungtoi;

import ichida.chungtoi.integration.ChungToiInterface;
import ichida.chungtoi.model.Game;
import ichida.chungtoi.model.Player;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import static ichida.chungtoi.util.GameConstants.NO_MOVENT;
import static ichida.chungtoi.util.ResultConstants.*;

public class ChungToiClient {

    private ChungToiInterface game;

    private static final String gameBoardBase = new StringBuffer()
            .append("0|1|2\n")
            .append("-+-+-\n")
            .append("3|4|5\n")
            .append("-+-+-\n")
            .append("6|7|8")
            .toString();

    public ChungToiClient(String serverHost) throws Exception {
        this.game = (ChungToiInterface) Naming.lookup("//" + serverHost + "/chungtoi");
    }

    public Player registerPlayer(String name) {
        Player player = new Player(name);
        try {
            int result = game.createPlayer(name);
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
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void hasGame(Player player) {
        try {
            int result = game.hasGame(player.getId());
            if (result == PLAYER_C_GAME) {
                System.out.println("Sua peça: C");
            } else if (result == PLAYER_E_GAME) {
                System.out.println("Sua peça: E");
            } else if (result == NO_GAME) {
                System.out.println("Partida sem jogador adversário. Aguarde.");
            } else {
                System.out.printf("Erro ao consultar a partida. Código: " + result);
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void endGame(Player player) {
        try {
            int result = game.endGame(player.getId());
            if (result == OK) {
                System.out.println("Jogo encerrado com sucesso");
            } else {
                System.out.println("Ocorreu um erro ao encerrar o jogo. Saindo da aplicação...");
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void verifyTurn(Player player) {
        try {
            int result = game.isMyTurn(player.getId());
            if (result == PLAYER_ADVERSARY_TURN) {
                System.out.println("Turno do adversário");
            } else if (result == PLAYER_TURN) {
                System.out.println("É a sua vez");
            } else if (result == WINNER) {
                System.out.println("Você venceu a partida");
            } else if (result == LOSER) {
                System.out.println("Você perdeu a partida");
            } else if (result == GAME_NOT_STARTED) {
                System.out.println("Sua partida ainda não começou");
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void showGameBoard(Player player) {
        try {
            String gameBoard = game.getGameStatus(player.getId());
            if (gameBoard.isEmpty()) {
                System.out.println("Partida não iniciada");
            } else {
                Game game = new Game();
                game.populateFromString(gameBoard);
                System.out.println("Tabuleiro:");
                System.out.println(game.toString());
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void showOpponentName(Player player) {
        try {
            String oppositePlayer = game.getOppositePlayer(player.getId());
            if (oppositePlayer.isEmpty()) {
                System.out.println("Partida não iniciada");
            } else {
                System.out.println("Adversário: " + oppositePlayer);
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void insertPiece(Player player) {
        try {
            int turn = game.isMyTurn(player.getId());
            if (turn == PLAYER_TURN) {
                System.out.println("Digite a posição que deseja inserir a próxima peça [0-8] conforme abaixo:");
                System.out.println(gameBoardBase);

                Scanner scanner = new Scanner(System.in);
                int position = scanner.nextInt();

                System.out.println("Defina a orientação da peça: (0-perpendicular, 1-diagonal)");
                int orientation = scanner.nextInt();

                int result = game.insertPiece(player.getId(), position, orientation);
                if (result == ADVERSARY_TURN) {
                    System.out.println("Não é o seu turno. Aguarde o seu oponente.");
                } else if (result == POSITION_ALREADY_OCCUPIED) {
                    System.out.println("Posição já ocupada");
                } else if (result == INVALID_PARAMETERS) {
                    System.out.println("Posição ou orientação da peça inválida");
                } else if (result == GAME_NOT_STARTED) {
                    System.out.println("O jogo ainda não começou");
                } else if (result == PIECE_PLACED) {
                    System.out.println("Peça inserida com sucesso");
                } else {
                    System.out.println("Retorno não esperado");
                }
            } else {
                System.out.println("Turno do adversário ou partida já encerrada");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void movePiece(Player player) {
        try {

            System.out.println("Selecione a peça que deseja mover [0-8]:");
            Scanner scanner = new Scanner(System.in);
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

            int result = game.movePiece(player.getId(), actualPosition, direction, stepSize, orientation);

            if (result == ADVERSARY_TURN) {
                System.out.println("Não é o seu turno. Aguarde o seu oponente.");
            } else if (result == POSITION_ALREADY_OCCUPIED) {
                System.out.println("Posição destino já está ocupada ou a posição destino está fora do tabuleiro");
            } else if (result == INVALID_PARAMETERS) {
                System.out.println("Posição ou orientação destino da peça inválida");
            } else if (result == GAME_NOT_STARTED) {
                System.out.println("O jogo ainda não começou.");
            } else if (result == PIECE_PLACED) {
                System.out.println("Movimento realizado com sucesso");
            } else {
                System.out.println("Retorno não esperado");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
