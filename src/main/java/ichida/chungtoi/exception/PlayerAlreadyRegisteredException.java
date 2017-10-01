package ichida.chungtoi.exception;

/**
 * Exceção lançada no caso de registro de jogador com o nome já existente na base.
 */
public class PlayerAlreadyRegisteredException extends Exception {

    public PlayerAlreadyRegisteredException(String s) {
        super(s);
    }

}
