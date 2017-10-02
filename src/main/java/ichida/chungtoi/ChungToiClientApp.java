package ichida.chungtoi;

import ichida.chungtoi.model.Player;

import java.util.Scanner;

import static ichida.chungtoi.util.ClientConstants.HAS_GAME;
import static ichida.chungtoi.util.ClientConstants.QUIT_GAME;
import static ichida.chungtoi.util.ClientConstants.VERIFY_TURN;

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
                    default:
                        System.out.println("Opção inválida");
                        break;
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
        System.out.println("4 - Inserir Peça");
        System.out.println("5 - Movimentar Peça");
        System.out.println("6 - Nome do Adversário");
    }

}
