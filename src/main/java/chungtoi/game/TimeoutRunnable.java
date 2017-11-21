package chungtoi.game;

import chungtoi.model.Game;

public class TimeoutRunnable implements Runnable {

    private final Game game;

    private final long firstTimestamp;

    private final long timeout;

    /**
     * @param game    - Jogo a ser monitorado
     * @param timeout - tempo de espera, em milissegundos
     */
    public TimeoutRunnable(Game game, long timeout) {
        this.game = game;
        this.firstTimestamp = game.getLastUpdate();
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeout);
            if (game.getLastUpdate() == firstTimestamp) {
                System.out.println("Timeout");
                game.timeout();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
