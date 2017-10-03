package ichida.chungtoi.game;

import ichida.chungtoi.model.Game;

/**
 * Thread que gerencia o timout de operações
 */
public class TimeoutThread extends Thread {

    public TimeoutThread(Game game, long timeout) {
        super(new TimeoutRunnable(game, timeout));
    }

}
