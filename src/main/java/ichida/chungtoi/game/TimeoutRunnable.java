package ichida.chungtoi.game;

import ichida.chungtoi.model.Game;

public class TimeoutRunnable implements Runnable {

    private final Game game;

    public TimeoutRunnable(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        try {
            System.out.println(game.getLastUpdate());
            System.out.println("Waiting");
            Thread.sleep(5000L);
            System.out.println(game.getLastUpdate());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
