package chungtoi.integration;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChungToiInterface extends Remote {

    /*
     * Registra um jogador
     *
     * @param name - Nome do jogador
     * @return Identificador do jogador gerado
     */
    int createPlayer(String name) throws RemoteException;

    /**
     * Encerra a partida
     *
     * @param playerId - Identificador do jogador
     * @return código de status
     */
    int endGame(int playerId) throws RemoteException;

    /**
     * Verifica se é a vez de um determinado jogador
     *
     * @param playerId - Identificador do jogador que perguntou
     * @return
     */
    int hasGame(int playerId) throws RemoteException;

    int isMyTurn(int playerId) throws RemoteException;

    String getGameStatus(int playerId) throws RemoteException;

    int insertPiece(int playerId, int position, int orientation) throws RemoteException;

    int movePiece(int playerId, int actualPosition, int movementDirection, int stepSize, int orientation) throws RemoteException;

    String getOppositePlayer(int playerId) throws RemoteException;

}
