package ichida.chungtoi.exception;

import ichida.chungtoi.model.GamePosition;

public class PositionAlreadyOccupiedException extends Exception {

    public PositionAlreadyOccupiedException(GamePosition pos) {
        super(String.format("Posição (%s, %s) já ocupada", pos.getLine(), pos.getColumn()));
    }

}
