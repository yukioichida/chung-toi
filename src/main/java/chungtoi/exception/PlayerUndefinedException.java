package ichida.chungtoi.exception;

public class PlayerUndefinedException extends Exception {

    public PlayerUndefinedException(Integer id) {
        super("Jogador com código " + id + " não existe");
    }
}
