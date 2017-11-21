/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chungtoi.integration.ws;

import chungtoi.exception.InvalidOrientationException;
import chungtoi.exception.InvalidPositionException;
import chungtoi.exception.PlayerAlreadyRegisteredException;
import chungtoi.exception.PlayerLimitException;
import chungtoi.exception.PositionAlreadyOccupiedException;
import chungtoi.integration.ChungToiInterface;
import chungtoi.model.Player;
import chungtoi.state.StaticGameState;
import chungtoi.state.StaticPlayerState;
import static chungtoi.util.GameConstants.EMPTY_PLAYER;
import chungtoi.util.ResultConstants;
import static chungtoi.util.ResultConstants.ERROR;
import static chungtoi.util.ResultConstants.GAME_NOT_STARTED;
import static chungtoi.util.ResultConstants.INVALID_MOVE;
import static chungtoi.util.ResultConstants.INVALID_PARAMETERS;
import static chungtoi.util.ResultConstants.POSITION_ALREADY_OCCUPIED;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ichida
 */
@WebService(serviceName = "ChungToiWSServer")
public class ChungToiWSServer implements ChungToiInterface {

    private static final Logger LOG = Logger.getLogger(ChungToiWSServer.class.getName());

    @WebMethod(operationName = "preRegister")
    public int preRegister(
            @WebParam(name = "player1Name") String player1Name,
            @WebParam(name = "player1Id") int player1Id,
            @WebParam(name = "player2Name") String player2Name,
            @WebParam(name = "player2Id") int player2Id) {
        //LOG.log(Level.INFO, String.format("Pre register players %s-%s and %s-%s", player1Name, player1Id, player2Name, player2Id));
        try {
            StaticPlayerState.preRegisterId(player1Name, player1Id);
            StaticPlayerState.preRegisterId(player2Name, player2Id);
            
            StaticPlayerState.registerPlayer(new Player(player1Name), player1Id);
            StaticGameState.includePlayer(player1Id, false);
            StaticPlayerState.registerPlayer(new Player(player2Name), player2Id);
            StaticGameState.includePlayer(player2Id, false);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @WebMethod(operationName = "createPlayer")
    @Override
    public int createPlayer(@WebParam(name = "name") String name) {
        //LOG.log(Level.INFO, String.format("Create Player %s", name));
        Player player = new Player(name);
        try {
            Integer playerId = StaticPlayerState.registerPlayer(player);
            StaticGameState.includePlayer(playerId, true);
            return playerId;
        } catch (PlayerAlreadyRegisteredException e) {
            System.out.println("Jogador " + name + " ja cadastrado");
            //e.printStackTrace(); //TODO: Mudar pra LOGGER
            return ResultConstants.PLAYER_ALREADY_REGISTERED;
        } catch (PlayerLimitException e) {
            return ResultConstants.PLAYER_LIMIT_REACHED;
        }
    }

    @WebMethod(operationName = "endGame")
    @Override
    public int endGame(@WebParam(name = "playerId") int playerId) {
        //LOG.log(Level.INFO, String.format("End Game from player %s", playerId));

        try {
            StaticGameState.endGame(playerId);
            StaticPlayerState.removePlayer(playerId);
            return ResultConstants.OK;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            //TODO: Mudar pra LOGGER
            return ResultConstants.ERROR;
        }
    }

    @WebMethod(operationName = "hasGame")
    @Override
    public int hasGame(@WebParam(name = "playerId") int playerId) {
        //LOG.log(Level.INFO, String.format("Has Game from player %s", playerId));
        try {
            int result = StaticGameState.hasGame(playerId);
            return result;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            return ERROR;
        }
    }

    @WebMethod(operationName = "isMyTurn")
    @Override
    public int isMyTurn(@WebParam(name = "playerId") int playerId) {
        //LOG.log(Level.INFO, String.format("isMyTurn from player %s", playerId));
        try {
            return StaticGameState.verifyTurn(playerId);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            return ResultConstants.ERROR;
        }
    }

    @WebMethod(operationName = "getGameStatus")
    @Override
    public String getGameStatus(@WebParam(name = "playerId") int playerId) {
        //LOG.log(Level.INFO, String.format("gameStatus from player %s", playerId));
        try {
            return StaticGameState.getGameBoard(playerId);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            return "";
        }
    }

    @WebMethod(operationName = "insertPiece")
    @Override
    public int insertPiece(@WebParam(name = "playerId") int playerId,
            @WebParam(name = "position") int position,
            @WebParam(name = "orientation") int orientation) {

        //LOG.log(Level.INFO, String.format("insert piece from player %s, position %s, orientation %s",
        //        playerId, position, orientation));
        try {
            return StaticGameState.insertPiece(playerId, position, orientation);
        } catch (InvalidPositionException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            return INVALID_PARAMETERS;
        } catch (InvalidOrientationException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            return INVALID_PARAMETERS;
        } catch (PositionAlreadyOccupiedException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            return POSITION_ALREADY_OCCUPIED;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            return GAME_NOT_STARTED;
        }
    }

    @WebMethod(operationName = "movePiece")
    @Override
    public int movePiece(@WebParam(name = "playerId") int playerId,
            @WebParam(name = "actualPosition") int actualPosition,
            @WebParam(name = "movementDirection") int movementDirection,
            @WebParam(name = "stepSize") int stepSize,
            @WebParam(name = "orientation") int orientation) {
        //LOG.log(Level.INFO, String.format("move piece from player %s, actualPosition %s, movement direction %s, "
        //        + "stepSize %s, orientation %s",
         //       playerId, actualPosition, movementDirection, stepSize, orientation));
        try {
            return StaticGameState.movePiece(playerId, actualPosition, movementDirection, stepSize, orientation);
        } catch (InvalidOrientationException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            return INVALID_PARAMETERS;
        } catch (PositionAlreadyOccupiedException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            // posição valida mas ja ocupada
            return POSITION_ALREADY_OCCUPIED;
        } catch (InvalidPositionException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            // posição invalida
            return INVALID_MOVE;
        }

    }

    @WebMethod(operationName = "getOppositePlayer")
    @Override
    public String getOppositePlayer(@WebParam(name = "playerId") int playerId) {
        Integer adversaryId = StaticGameState.getAdversaryPlayer(playerId);
        if (adversaryId == EMPTY_PLAYER) {
            return "";
        } else {
            Player adversaryPlayer = StaticPlayerState.getPlayer(adversaryId);
            return adversaryPlayer.getName();
        }
    }

}
