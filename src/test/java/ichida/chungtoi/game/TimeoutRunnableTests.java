package ichida.chungtoi.game;

import ichida.chungtoi.model.Game;

public class TimeoutRunnableTests {

    public static void main(String... args) throws Exception {
        Game game = new Game();
        game.setLastUpdate(1L);

        Thread thread = new Thread(new TimeoutRunnable(game,1L));

        game.setLastUpdate(1L);
        thread.start();
        Thread.sleep(1L);
        game.setLastUpdate(2L);

    }

}
