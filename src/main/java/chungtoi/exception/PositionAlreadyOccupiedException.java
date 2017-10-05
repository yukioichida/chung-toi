package chungtoi.exception;

import chungtoi.model.GamePosition;

public class PositionAlreadyOccupiedException extends Exception {

    public PositionAlreadyOccupiedException(GamePosition pos) {
        super(String.format("Posição (%s, %s) já ocupada", pos.getLine(), pos.getColumn()));
    }

}
