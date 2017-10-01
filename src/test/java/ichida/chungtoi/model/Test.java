package ichida.chungtoi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Test {

    public static void main(String... args) throws Exception{
        List<Game> list = new ArrayList<>();
        list.add(new Game());
        list.add(new Game());

        for(Game game : list){
            game.putChar(1,1,'c');
        }

        System.out.println(list);

    }

}
