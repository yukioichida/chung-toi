package chungtoi.state;

import chungtoi.exception.InvalidMoveException;
import chungtoi.exception.InvalidOrientationException;
import chungtoi.exception.InvalidPositionException;
import chungtoi.exception.PlayerUndefinedException;
import chungtoi.exception.PositionAlreadyOccupiedException;
import chungtoi.game.GameOperations;
import chungtoi.game.GameResultValidator;
//import chungtoi.game.TimeoutThread;
import chungtoi.model.Game;

import java.util.ArrayList;
import java.util.List;

import static chungtoi.util.GameConstants.*;
import static chungtoi.util.ResultConstants.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Realiza as operações de acesso e modificações de dados relacionados a
 * partidas, gerenciando o estado das partidas.
 */
public class StaticGameState {

    //TODO: testar os efeitos de botar a lista como synchronized
    private static final List<Game> games;

    private static final Map<Integer, Integer> reservedOpponents;

    static {
        games = new ArrayList<>();
        reservedOpponents = new HashMap<Integer, Integer>();
    }

    /**
     * Inclui um jogador em uma partida. Caso não exista uma partida, será
     * criado uma nova partida.
     */
    public static synchronized void includePlayer(Integer player, boolean start) {
        Game playerGame = getPlayerGame(player);
        /* caso preregister */
        if (playerGame != null) {
            char piece = playerGame.getPlayerPiece(player);
            if (piece == PLAYER_C) {
                playerGame.playerCReady();
            } else {
                playerGame.playerEReady();
            }
            if (playerGame.playersReady() && start) {
                playerGame.start();
            }
            return;
        }
        /* caso normal */
        for (Game game : games) {
            boolean isAvailable = game.getPlayerIdE() == EMPTY_PLAYER;
            if (isAvailable && !game.isTimeout()) {
                game.setPlayerIdE(player);
                if (start) {
                    game.start();
                }
                game.setLastUpdate(System.currentTimeMillis());
                return;
            }
        }
        // Caso não exista nenhum jogo disponível, crie um novo para o jogador
        Game game = new Game();
        game.setPlayerIdC(player);
        games.add(game);
        //new TimeoutThread(game, WAITING_PLAYER_TIMEOUT).start();
    }

    /**
     * *
     * Encerra a partida em que um determinado jogador está participando.
     *
     * @param player - Identificador do jogador que deseja encerrar a partida.
     */
    public static synchronized void endGame(Integer player) throws Exception {
        if (StaticPlayerState.existPlayer(player)) {
            for (Game game : games) {
                if (game.getPlayerIdE() == player || game.getPlayerIdC() == player) {
                    game.endGame(player);
                    game.reset();
                }
            }
        } else {
            throw new PlayerUndefinedException(player);
        }
    }

    /**
     * Verifica se um determinado jogador está participando de uma partida,
     * retornando sua peça caso esteja.
     *
     * @param playerId - Identificador do jogador
     * @return Se não está participando retorna vazio, senão retorna a peça
     */
    public static synchronized int hasGame(Integer playerId) throws Exception {
        if (StaticPlayerState.existPlayer(playerId)) {
            Game game = getPlayerGame(playerId);
            if (game != null) {
                if ((game.isOpen())) {
                    if (game.getPlayerIdC() == playerId) {
                        return PLAYER_C_GAME;
                    } else if (game.getPlayerIdE() == playerId) {
                        return PLAYER_E_GAME;
                    }
                } else if (game.isTimeout()) {
                    return REGISTER_TIMEOUT;
                }
            }
            return NO_GAME;
        } else {
            throw new PlayerUndefinedException(playerId);
        }
    }

    private static synchronized Game getPlayerGame(Integer playerId) {
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
            Game game = getPlayerGame(playerId);
            if (game != null) {
                if (game.isOpen()) {
                    if (game.getActualPlayer() == playerId) {
                        return PLAYER_TURN;
                    } else {
                        return PLAYER_ADVERSARY_TURN;
                    }
                } else {
                    if (game.getQuitter() != EMPTY_PLAYER) {
                        if (game.getQuitter() == playerId) {
                            System.out.println("Jogador " + playerId + " perdeu por W.O.");
                            return LOSER;
                        } else {
                            System.out.println("Jogador " + playerId + " ganhou por W.O.");
                            return WINNER;
                        }
                    }
                    if (game.getWinner() != EMPTY_PLAYER) {
                        if (game.getWinner() == playerId) {
                            System.out.println("Jogador " + playerId + " Ganhou a partida");
                            return WINNER;
                        } else {
                            System.out.println("Jogador " + playerId + " perdeu a partida.");
                            return LOSER;
                        }
                    }
                    if (game.isTimeout()) {
                        System.out.println("Timeout");
                        return GAME_TIMEOUT;
                    }
                    return GAME_NOT_STARTED;
                }
            }
            // jogador não está em nenhuma partida - estado inconsistente
            return -1;
        } else {
            throw new PlayerUndefinedException(playerId);
        }
    }

    public static synchronized String getGameBoard(Integer playerId) throws Exception {
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
    public static synchronized int insertPiece(int playerId, int position, int orientation)
            throws Exception {
        Game playerGame = getPlayerGame(playerId);
        if (playerGame != null) {
            if (playerGame.isTimeout()) {
                return GAME_TIMEOUT;
            } else if (playerGame.isOpen()) {/* Verifica se a partida foi iniciada. */
                if (playerGame.getActualPlayer() == playerId) {
                    char piece = playerGame.getPlayerPiece(playerId);
                    if (playerGame.getPieceQuantity(piece) < 3) {
                        GameOperations.insertPiece(piece, position, orientation, playerGame);
                        // troca o turno
                        int opponent = playerGame.getOpponentPlayerId(playerId);
                        playerGame.setActualPlayer(opponent);
                        // atualiza o timestamp da ultima ação para controle de timeout
                        playerGame.setLastUpdate(System.currentTimeMillis());
                        //new TimeoutThread(playerGame, WAITING_NEXT_PLAY_TIMEOUT).start();
                        //verifica se houve um vencedor
                        defineWinner(playerId, playerGame);
                        return PIECE_PLACED;
                    } else {
                        /* fase de posicionamento encerrada */
                        return PHASE_INSERT_ENDED;
                    }
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

    /**
     * Realiza a movimentação das peças de acordo com o estado atual das
     * partidas.
     *
     * @param playerId - Identificador do jogador que está movimentando a peça
     * @param actualPosition - Posição da peça que será movida
     * @param movementDirection - Direção do movimento
     * @param orientation - Orientação do movimento
     * @param stepSize - Distância do movimento
     */
    public static synchronized int movePiece(int playerId, int actualPosition, int movementDirection, int stepSize, int orientation)
            throws InvalidPositionException, InvalidOrientationException, PositionAlreadyOccupiedException {
        Game playerGame = getPlayerGame(playerId);
        if (playerGame != null) {

            if (playerGame.isTimeout()) {
                return GAME_TIMEOUT;
            } else if (playerGame.isOpen()) {

                if (playerGame.getActualPlayer() == playerId) {

                    char playerPiece = playerGame.getPlayerPiece(playerId);
                    char movedPiece = playerGame.getPiece(actualPosition);
                    // verifica se a peça que o jogador está tentando movimentar realmente é a dele
                    if (Character.toLowerCase(playerPiece) == Character.toLowerCase(movedPiece)) {
                        boolean moved = true;
                        try {
                            moved = GameOperations.movePiece(actualPosition, movementDirection, stepSize, orientation, playerGame);
                            // muda o turno se o jogador moveu a peça
                            if (moved) {
                                int opponent = playerGame.getOpponentPlayerId(playerId);
                                playerGame.setActualPlayer(opponent);
                                // atualiza o timestamp para controle de timeout
                                playerGame.setLastUpdate(System.currentTimeMillis());
                                //new TimeoutThread(playerGame, WAITING_NEXT_PLAY_TIMEOUT).start();
                                //verifica se houve um vencedor
                                defineWinner(playerId, playerGame);
                                return PIECE_PLACED;
                            } else {
                                /* Não fez nada */
                                return INVALID_MOVE;
                            }
                        } catch (InvalidMoveException ex) {
                            Logger.getLogger(StaticGameState.class.getName()).log(Level.SEVERE, null, ex);
                            return INVALID_MOVE;
                        }

                    } else {
                        System.out.println("Jogador não pode mover a peça do adversário");
                        return INVALID_MOVE;
                    }

                } else {
                    return ADVERSARY_TURN;
                }

            } else {
                return GAME_NOT_STARTED;
            }
        } else {
            System.out.println("Jogador sem partida ou não existente: " + playerId);
            return PLAYER_NOT_FOUND;
            //throw new RuntimeException("Jogador está sem partida");
        }
    }

    private static synchronized void defineWinner(int actualPlayerId, Game game) {
        char winner = GameResultValidator.getWinner(game);
        char playerPiece = game.getPlayerPiece(actualPlayerId);
        char adversaryPiece = game.getAdversarialPiece(actualPlayerId);
        /* Verifica quem é o vencedor, se existir um */
        if (Character.toLowerCase(playerPiece) == Character.toLowerCase(winner)) {
            System.out.println("Jogador venceu: " + actualPlayerId);
            game.setWinner(actualPlayerId);
            game.closeGame();
        } else if (Character.toLowerCase(adversaryPiece) == Character.toLowerCase(winner)) {
            System.out.println("Jogador perdeu: " + actualPlayerId);
            game.setWinner(game.getOpponentPlayerId(actualPlayerId));
            game.closeGame();
        }
    }

    public static synchronized int getAdversaryPlayer(Integer playerId) {
        Game playerGame = getPlayerGame(playerId);
        if (playerGame != null && playerGame.playersReady()) {
            return playerGame.getOpponentPlayerId(playerId);
        } else {
            //Jogador não está em nenhuma partida
            return EMPTY_PLAYER;
        }
    }

}
