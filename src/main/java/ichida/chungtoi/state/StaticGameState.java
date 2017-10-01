package ichida.chungtoi.state;

import ichida.chungtoi.exception.InvalidOrientationException;
import ichida.chungtoi.exception.PlayerUndefinedException;
import ichida.chungtoi.game.GameValidator;
import ichida.chungtoi.model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ichida.chungtoi.util.GameConstants.*;
import static ichida.chungtoi.util.ResultConstants.*;

/**
 * Realiza as operações de acesso e modificações de dados relacionados a partidas, gerenciando o estado das partidas.
 */
public class StaticGameState {

    private static final List<Game> games;
    private static final Logger LOG = Logger.getGlobal();

    static {
        games = new ArrayList<>();
    }

    /**
     * Inclui um jogador em uma partida. Caso não exista uma partida, será criado uma nova partida.
     */
    public static synchronized void includePlayer(Integer player) {
        for (Game game : games) {
            if (game.getPlayerIdE() == EMPTY_PLAYER) {
                LOG.info("Jogador " + game.getPlayerIdC() + " vs Jogador" + player);
                game.setPlayerIdE(player);
                game.start();
                return;
            }
        }
        // Caso não exista nenhum jogo disponível, crie um novo para o jogador
        Game game = new Game();
        game.setPlayerIdC(player);
        games.add(game);
        LOG.info("Partida nova criada para o jogador " + player);
    }

    /***
     * Encerra a partida em que um determinado jogador está participando.
     * @param player - Identificador do jogador que deseja encerrar a partida.
     */
    public static synchronized void endGame(Integer player) throws Exception {
        if (StaticPlayerState.existPlayer(player)) {
            for (Game game : games) {
                if (game.getPlayerIdE() == player || game.getPlayerIdC() == player) {
                    game.endGame();
                }
            }
        } else {
            throw new PlayerUndefinedException(player);
        }
    }

    /**
     * Verifica se um determinado jogador está participando de uma partida, retornando sua peça caso esteja.
     *
     * @param playerId - Identificador do jogador
     * @return Se não está participando retorna vazio, senão retorna a peça
     */
    public static synchronized char hasGame(Integer playerId) throws Exception {
        if (StaticPlayerState.existPlayer(playerId)) {
            for (Game game : games) {
                if (game.isOpen()) {
                    if (game.getPlayerIdC() == playerId) {
                        return PLAYER_C;
                    } else if (game.getPlayerIdE() == playerId) {
                        return PLAYER_E;
                    }
                }
            }
            return EMPTY;
        } else {
            throw new PlayerUndefinedException(playerId);
        }
    }

    private static Game getPlayerGame(Integer playerId) {
        for (Game game : games) {
            if (game.getPlayerIdE() == playerId || game.getPlayerIdC() == playerId) {
                return game;
            }
        }
        return null;
    }

    public static synchronized int verifyTurn(Integer playerId) throws Exception {
        // FIXME: falta temporização e verificação de vencedor por WO
        if (StaticPlayerState.existPlayer(playerId)) {
            for (Game game : games) {
                // Busca o jogo em que o jogador está participando
                if (game.getPlayerIdC() == playerId || game.getPlayerIdE() == playerId) {
                    if (game.isOpen()) {
                        //verificar se ganhou ou perdeu ou empatou
                        char winner = GameValidator.getWinner(game);
                        if (game.getPlayerPiece(playerId) == winner) {
                            return WINNER;
                        } else if (game.getAdversarialPiece(playerId) == winner) {
                            return LOSER;
                        } else {
                            if (game.getActualPlayer() == playerId) {
                                return PLAYER_TURN;
                            } else {
                                return PLAYER_ADVERSARY_TURN;
                            }
                        }
                    } else {
                        // de repente marcar quem venceu no próprio game, ou quem saiu aqui
                        return -2;
                    }
                }
            }
            // jogador não está em nenhuma partida - estado inconsistente
            return -1;
        } else {
            throw new PlayerUndefinedException(playerId);
        }
    }

    public static String getGameBoard(Integer playerId) throws Exception {
        Game playerGame = getPlayerGame(playerId);
        if (playerGame != null) {
            return playerGame.getBoardString();
        } else {
            throw new Exception("Jogador não está participando de nenhuma partida");
        }
    }

    /**
     * Insere a peça no tabuleiro do jogador
     */
    // FIXME: Falta tratar timeout
    public static int insertPiece(int playerId, int position, int orientation)
            throws Exception {
        Game playerGame = getPlayerGame(playerId);
        if (playerGame != null) {
            /* Verifica se a partida foi iniciada. */
            if (playerGame.isOpen()) {
                if (playerGame.getActualPlayer() == playerId) {
                    char piece = playerGame.getPlayerPiece(playerId);

                    /* Verifica se a peça deve ser inserida em modo diagonal ou perpendicular */
                    if (position == INPUT_PERPENDICULAR) {
                        playerGame.putChar(position, Character.toUpperCase(piece));
                    } else if (position == INPUT_DIAGONAL) {
                        playerGame.putChar(position, Character.toLowerCase(piece));
                    } else {
                        // parâmetro inválido
                        throw new InvalidOrientationException(orientation);
                    }

                    return PIECE_PLACED;
                } else {
                    // Não é a vez do jogador
                    return ADVERSARY_TURN;
                }
            } else {
                // O jogo não iniciou ainda pois não tem dois jogadores registrados.
                return GAME_NOT_STARTED;
            }
        } else {
            throw new Exception("Jogador não está participando de nenhuma partida");
        }
    }

    public static int getAdversaryPlayer(Integer playerId) {
        Game playerGame = getPlayerGame(playerId);
        if (playerGame != null) {
            return playerGame.getAdversarialPiece(playerId);
        } else {
            //Jogador não está em nenhuma partida
            return EMPTY_PLAYER;
        }
    }

}
