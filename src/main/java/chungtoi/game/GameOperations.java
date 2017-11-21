package chungtoi.game;

import chungtoi.exception.InvalidMoveException;
import chungtoi.exception.InvalidOrientationException;
import chungtoi.exception.InvalidPositionException;
import chungtoi.exception.PositionAlreadyOccupiedException;
import chungtoi.model.Game;
import chungtoi.model.GamePosition;

import static chungtoi.util.GameConstants.*;

/**
 * Classe que realiza a movimentação e inserção de peças em um determinado jogo,
 * centralizando as regras do jogo.
 */
public class GameOperations {

    /**
     * Insere uma determinada peça de acordo com as regras do jogo, sem se
     * preocupar com o estado do jogo.
     */
    public static void insertPiece(char piece, int position, int orientation, Game playerGame) throws Exception {

        /* Verifica se a peça deve ser inserida em modo diagonal ou perpendicular */
        if (orientation == INPUT_PERPENDICULAR) {
            playerGame.putChar(position, Character.toUpperCase(piece));
        } else if (orientation == INPUT_DIAGONAL) {
            playerGame.putChar(position, Character.toLowerCase(piece));
        } else {
            // parâmetro inválido
            throw new InvalidOrientationException(orientation);
        }

    }

    /**
     * Realiza a movimentação de uma determinada peça.
     *
     * @param actualPosition - Posição atual da peça que será movida
     * @param movementDirection - Direção do movimento
     * @param stepSize - Quantidade de passos do movimento
     * @param nextOrientation - Orientação da peça após o movimento
     * @param playerGame - Jogo atual
     * 
     * @return verdadeiro se o movimento foi válido
     */
    public static boolean movePiece(int actualPosition, int movementDirection, int stepSize, int nextOrientation, Game playerGame)
            throws InvalidPositionException, PositionAlreadyOccupiedException, InvalidOrientationException, InvalidMoveException {
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

        char nextPiece = getModifiedPiece(actualPiece, nextOrientation);
        
        // mudança de orientação sem mover a peça
        if (stepSize == 0 && nextPiece != actualPiece){
            // Coloca a peça no novo lugar, removendo a peça do lugar antigo
            playerGame.putChar(gameActualPos.getLine(), gameActualPos.getColumn(), nextPiece);
            return true;
        }
        // Antes de inserir, verifica se é uma posição válida do tabuleiro
        if (targetPosition.isValidPosition()) {
            // peça que está na posição que
            char targetPiece = playerGame.getPiece(targetPosition.getLine(), targetPosition.getColumn());

            if (movementDirection == NO_MOVENT) {
                if (nextPiece != actualPiece){
                    playerGame.changePiece(gameActualPos.getLine(), gameActualPos.getColumn(), nextPiece);
                    return true;
                }else{
                    // não fez nada que alterasse o jogo
                    return false;
                }
            } else {
                if (targetPiece == EMPTY) {
                    // Coloca a peça no novo lugar, removendo a peça do lugar antigo
                    playerGame.putChar(targetPosition.getLine(), targetPosition.getColumn(), nextPiece);
                    playerGame.changePiece(gameActualPos.getLine(), gameActualPos.getColumn(), EMPTY);
                    return true;
                } else {
                    throw new PositionAlreadyOccupiedException(targetPosition);
                }
            }

        } else {
            throw new InvalidMoveException(targetPosition);
        }
    }

    /**
     * @return código da peça de acordo com a orientação definida (perpendicular
     * ou diagonal)
     */
    private static char getModifiedPiece(char actualPiece, int orientation) throws InvalidOrientationException {
        // Define a peça de acordo com a orientação definida
        if (orientation == INPUT_PERPENDICULAR) {
            return Character.toUpperCase(actualPiece);
        } else if (orientation == INPUT_DIAGONAL) {
            return Character.toLowerCase(actualPiece);
        } else {
            throw new InvalidOrientationException(orientation);
        }
    }

}
