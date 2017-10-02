package ichida.chungtoi;

import ichida.chungtoi.model.Player;

import java.util.Scanner;

import static ichida.chungtoi.util.ClientConstants.*;

public class ChungToiClientApp {

    public static void main(String... args) {
        System.out.println("Defina seu nome:");
        Scanner input = new Scanner(System.in);
        String playerName = input.next();
        int option = -1;
        System.out.println("Conectando com o servidor...");
        ChungToiClient client;
        try {
            client = new ChungToiClient("localhost");
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o servidor do jogo.");
            e.printStackTrace();
            System.exit(0);
            return;
        }
        System.out.println("Conexão estabelecida");

        Player player = client.registerPlayer(playerName);
        if (player != null) {
            while (option != QUIT_GAME) {
                showMenu(player);
                option = input.nextInt();
                switch (option) {
                    case HAS_GAME:
                        client.hasGame(player);
                        break;
                    case QUIT_GAME:
                        client.endGame(player);
                        break;
                    case VERIFY_TURN:
                        client.verifyTurn(player);
                        break;
                    case GET_GAME_BOARD:
                        client.showGameBoard(player);
                        break;
                    case GET_ADVERSARY_NAME:
                        client.showOpponentName(player);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;

                }
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        }


    }

    private static final void showMenu(Player player) {
        System.out.println("Jogador: " + player.getName() + " - Identificador: " + player.getId());
        System.out.println("Selecione as seguintes opções:");
        System.out.println("1 - Verificar o status de sua partida");
        System.out.println("2 - Sair do jogo");
        System.out.println("3 - Verificar o turno da partida");
        System.out.println("4 - Mostrar Tabuleiro do Jogo");
        System.out.println("5 - Inserir Peça");
        System.out.println("6 - Movimentar Peça");
        System.out.println("7 - Nome do Adversário");
    }

}
