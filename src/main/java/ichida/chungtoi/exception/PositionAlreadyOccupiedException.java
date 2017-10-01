package ichida.chungtoi.exception;

public class PositionAlreadyOccupiedException extends Exception{

    public PositionAlreadyOccupiedException(int x, int y) {
        super(String.format("Posição (%s, %s) já ocupada", x, y));
    }

}
