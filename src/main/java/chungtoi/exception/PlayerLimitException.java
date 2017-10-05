package chungtoi.exception;

/**
 * Exceção lançada caso o número limite de jogadores seja excedido.
 */
public class PlayerLimitException extends Exception {
    public PlayerLimitException() {
        super("Número de jogadores excedido");
    }
}
