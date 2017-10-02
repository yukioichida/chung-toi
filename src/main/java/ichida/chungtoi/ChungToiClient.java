package ichida.chungtoi;

import com.sun.org.apache.xpath.internal.SourceTree;
import ichida.chungtoi.game.GameInterface;
import ichida.chungtoi.model.Player;

import java.rmi.Naming;
import java.rmi.RemoteException;

import static ichida.chungtoi.util.ResultConstants.*;

public class ChungToiClient {

    private GameInterface game;

    public ChungToiClient(String serverHost) throws Exception {
        this.game = (GameInterface) Naming.lookup("//" + serverHost + "/chungtoi");
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

}
