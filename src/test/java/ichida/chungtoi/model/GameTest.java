package ichida.chungtoi.model;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void shouldCreateBoard() throws Exception{

        Game game = new Game();
        game.putChar(0,0,'C');

        Assert.assertEquals("C........", game.getBoardString());

    }

}
