package ichida.chungtoi.game;

import java.rmi.Remote;

public interface GameInterface extends Remote {

    /**
     * Registra um jogador
     *
     * @param name - Nome do jogador
     * @return Identificador do jogador gerado
     */
    int createPlayer(String name);

    /**
     * Encerra a partida
     *
     * @param playerId - Identificador do jogador
     * @return código de status
     */
    int endGame(int playerId);

    /**
     * Verifica se é a vez de um determinado jogador
     *
     * @param playerId - Identificador do jogador que perguntou
     * @return
     */
    int hasGame(int playerId);

    int isMyTurn(int playerId);

    String getGameStatus(int playerId);

    int insertPiece(int playerId, int position, int orientation);

    int movePiece(int playerId, int actualPosition, int movementDirection, int stepSize, int orientation);

    String getOppositePlayer(int playerId);

}
