package chungtoi.exception;

import chungtoi.model.GamePosition;

public class InvalidPositionException extends Exception {

    public InvalidPositionException(GamePosition gamePos) {
        super("Posiçao do tabuleiro inválida = " + gamePos.toString());
    }
}
