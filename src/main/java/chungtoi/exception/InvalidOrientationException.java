package chungtoi.exception;

public class InvalidOrientationException extends Exception {

    public InvalidOrientationException(int orientation) {
        super("Orientação inválida = " + orientation);
    }
}
