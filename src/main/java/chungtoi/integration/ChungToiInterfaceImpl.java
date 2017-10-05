package ichida.chungtoi.integration;

import ichida.chungtoi.exception.*;
import ichida.chungtoi.state.StaticGameState;
import ichida.chungtoi.state.StaticPlayerState;
import ichida.chungtoi.model.Player;
import ichida.chungtoi.util.ResultConstants;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static ichida.chungtoi.util.GameConstants.EMPTY_PLAYER;
import static ichida.chungtoi.util.GameConstants.PLAYER_C;
import static ichida.chungtoi.util.GameConstants.PLAYER_E;
import static ichida.chungtoi.util.ResultConstants.*;

public class ChungToiInterfaceImpl extends UnicastRemoteObject implements ChungToiInterface {

    public ChungToiInterfaceImpl() throws RemoteException {
    }

    @Override
    public int createPlayer(String name) throws RemoteException  {
        Player player = new Player(name);
        try {
            Integer playerId = StaticPlayerState.registerPlayer(player);
            StaticGameState.includePlayer(playerId);
            return playerId;
        } catch (PlayerAlreadyRegisteredException e) {
            e.printStackTrace(); //TODO: Mudar pra LOGGER
            return ResultConstants.PLAYER_ALREADY_REGISTERED;
        } catch (PlayerLimitException e) {
            return ResultConstants.PLAYER_LIMIT_REACHED;
        }
    }

    @Override
    public int endGame(int playerId) throws RemoteException {
        try {
            StaticGameState.endGame(playerId);
            return ResultConstants.OK;
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Mudar pra LOGGER
            return ResultConstants.ERROR;
        }
    }

    @Override
    public int hasGame(int playerId) throws RemoteException {
        try {
            int result = StaticGameState.hasGame(playerId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    @Override
    public int isMyTurn(int playerId) throws RemoteException {
        try {
            return StaticGameState.verifyTurn(playerId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultConstants.ERROR;
        }
    }

    @Override
    public String getGameStatus(int playerId) throws RemoteException  {
        try {
            return StaticGameState.getGameBoard(playerId);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public int insertPiece(int playerId, int position, int orientation) throws RemoteException {
        try {
            return StaticGameState.insertPiece(playerId, position, orientation);
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
            return INVALID_PARAMETERS;
        } catch (InvalidOrientationException ex) {
            ex.printStackTrace();
            return INVALID_PARAMETERS;
        } catch (PositionAlreadyOccupiedException ex) {
            ex.printStackTrace();
            return POSITION_ALREADY_OCCUPIED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return GAME_NOT_STARTED;
        }
    }

    @Override
    public int movePiece(int playerId, int actualPosition, int movementDirection, int stepSize, int orientation) throws RemoteException {
        try {
            return StaticGameState.movePiece(playerId, actualPosition, movementDirection, stepSize, orientation);
        } catch (InvalidOrientationException e) {
            e.printStackTrace();
            return INVALID_PARAMETERS;
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
            // posição valida mas ja ocupada
            return POSITION_ALREADY_OCCUPIED;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
            // posição invalida
            return INVALID_PARAMETERS;
        }

    }

    @Override
    public String getOppositePlayer(int playerId) throws RemoteException{
        Integer adversaryId = StaticGameState.getAdversaryPlayer(playerId);
        if (adversaryId == EMPTY_PLAYER) {
            return "";
        } else {
            Player adversaryPlayer = StaticPlayerState.getPlayer(adversaryId);
            return adversaryPlayer.getName();
        }
    }
}