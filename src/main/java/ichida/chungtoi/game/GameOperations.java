package ichida.chungtoi.game;

import ichida.chungtoi.exception.InvalidOrientationException;
import ichida.chungtoi.exception.InvalidPositionException;
import ichida.chungtoi.exception.PositionAlreadyOccupiedException;
import ichida.chungtoi.model.Game;
import ichida.chungtoi.model.GamePosition;

import static ichida.chungtoi.util.GameConstants.*;

/**
 * Classe que realiza a movimentação e inserção de peças em um determinado jogo, centralizando as regras do jogo.
 */
public class GameOperations {

    /**
     * Insere uma determinada peça de acordo com as regras do jogo, sem se preocupar com o estado do jogo.
     */
    public static void insertPiece(char piece, int position, int orientation, Game playerGame) throws Exception {
        /* Verifica se a peça deve ser inserida em modo diagonal ou perpendicular */
        if (position == INPUT_PERPENDICULAR) {
            playerGame.putChar(position, Character.toUpperCase(piece));
        } else if (position == INPUT_DIAGONAL) {
            playerGame.putChar(position, Character.toLowerCase(piece));
        } else {
            // parâmetro inválido
            throw new InvalidOrientationException(orientation);
        }
    }

    /**
     * Realiza a movimentação de uma determinada peça.
     *
     * @param actualPosition    - Posição atual da peça que será movida
     * @param movementDirection - Direção do movimento
     * @param nextOrientation   - Orientação da peça após o movimento
     * @param stepSize          - Quantidade de passos do movimento
     * @param playerGame        - Jogo atual
     */
    public static void movePiece(int actualPosition, int movementDirection, int nextOrientation, int stepSize, Game playerGame)
            throws InvalidPositionException, PositionAlreadyOccupiedException, InvalidOrientationException {
        GamePosition gameActualPos = playerGame.getPosition(actualPosition);
        GamePosition targetPosition;
        char actualPiece = playerGame.getPiece(actualPosition);
        if (movementDirection != NO_MOVENT) {
            int newX = 0;
            int newY = 0;
            // verifica se a peça atual está vazia
            if (actualPiece != EMPTY) {
                if (Character.isUpperCase(actualPiece)) {
                    // Movimentos apenas perpendiculares
                    if (movementDirection == MOVEMENT_UPPER) {
                        // -1, -1
                        newX = gameActualPos.getLine() - stepSize;
                        newY = gameActualPos.getColumn();
                    } else if (movementDirection == MOVEMENT_LEFT) {
                        // 0, -1
                        newX = gameActualPos.getLine();
                        newY = gameActualPos.getColumn() - stepSize;
                    } else if (movementDirection == MOVEMENT_RIGHT) {
                        // 0, +1
                        newX = gameActualPos.getLine();
                        newY = gameActualPos.getColumn() + stepSize;
                    } else if (movementDirection == MOVEMENT_BOTTOM) {
                        // +1, 0
                        newX = gameActualPos.getLine() + stepSize;
                        newY = gameActualPos.getColumn();
                    }
                } else if (Character.isLowerCase(actualPiece)) {
                    // Movimentos apenas diagonais
                    if (movementDirection == MOVEMENT_UPPER_LEFT) {
                        // -1, -1
                        newX = gameActualPos.getLine() - stepSize;
                        newY = gameActualPos.getColumn() - stepSize;
                    } else if (movementDirection == MOVEMENT_UPPER_RIGHT) {
                        // -1, +1
                        newX = gameActualPos.getLine() - stepSize;
                        newY = gameActualPos.getColumn() + stepSize;
                    } else if (movementDirection == MOVEMENT_BOTTOM_LEFT) {
                        // +1, -1
                        newX = gameActualPos.getLine() + stepSize;
                        newY = gameActualPos.getColumn() - stepSize;
                    } else if (movementDirection == MOVEMENT_BOTTOM_RIGHT) {
                        // +1, +1
                        newX = gameActualPos.getLine() + stepSize;
                        newY = gameActualPos.getColumn() + stepSize;
                    }
                }
            } else {
                // Tentando movimentar uma peça vazia
                throw new InvalidPositionException(gameActualPos);
            }
            targetPosition = new GamePosition(newX, newY);
        } else {
            targetPosition = gameActualPos;
        }

        // Antes de inserir, verifica se é uma posição válida do tabuleiro
        if (targetPosition.isValidPosition()) {
            char targetPiece = playerGame.getPiece(targetPosition.getLine(), targetPosition.getColumn());
            if (targetPiece == EMPTY) {
                char nextPiece;
                if (nextOrientation == INPUT_PERPENDICULAR) {
                    nextPiece = Character.toUpperCase(actualPiece);
                } else if (nextOrientation == INPUT_DIAGONAL) {
                    nextPiece = Character.toLowerCase(actualPiece);
                } else {
                    throw new InvalidOrientationException(nextOrientation);
                }
                // Coloca a peça no novo lugar, removendo a peça do lugar antigo
                playerGame.putChar(targetPosition.getLine(), targetPosition.getColumn(), nextPiece);
                playerGame.putChar(gameActualPos.getLine(), gameActualPos.getColumn(), EMPTY);
            } else {
                throw new PositionAlreadyOccupiedException(targetPosition);
            }
        } else {
            throw new InvalidPositionException(targetPosition);
        }
    }

}
