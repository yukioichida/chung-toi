package ichida.chungtoi.game;

import ichida.chungtoi.state.StaticGameState;
import ichida.chungtoi.state.StaticPlayerState;
import ichida.chungtoi.exception.PlayerAlreadyRegisteredException;
import ichida.chungtoi.exception.PlayerLimitException;
import ichida.chungtoi.model.Player;
import ichida.chungtoi.util.ResultConstants;

import static ichida.chungtoi.util.GameConstants.EMPTY_PLAYER;
import static ichida.chungtoi.util.GameConstants.PLAYER_C;
import static ichida.chungtoi.util.GameConstants.PLAYER_E;

public class GameInterfaceImpl implements GameInterface {

    @Override
    public int createPlayer(String name) {
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
    public int endGame(int playerId) {
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
    public int hasGame(int playerId) {
        try {
            char result = StaticGameState.hasGame(playerId);
            // TODO: Botar em constantes
            // FIXME: temporização
            if (result == PLAYER_C) {
                return 1;
            } else if (result == PLAYER_E) {
                return 2;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultConstants.ERROR;
        }
    }

    @Override
    public int isMyTurn(int playerId) {
        try {
            return StaticGameState.verifyTurn(playerId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultConstants.ERROR;
        }
    }

    @Override
    public String getGameStatus(int playerId) {
        try {
            return StaticGameState.getGameBoard(playerId);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public int insertPiece(int playerId, int position, int orientation) {
        return 0;
    }

    @Override
    public int movePiece(int playerId, int actualPosition, int stepSize, int orientation) {
        return 0;
    }

    @Override
    public String getOppositePlayer(int playerId) {
        Integer adversaryId = StaticGameState.getAdversaryplayer(playerId);
        if (adversaryId == EMPTY_PLAYER) {
            return "";
        } else {
            Player adversaryPlayer = StaticPlayerState.getPlayer(adversaryId);
            return adversaryPlayer.getName();
        }
    }
}
