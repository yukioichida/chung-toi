/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chungtoi.game;

import chungtoi.model.Game;

/**
 *
 * @author ichida
 */
public class ResultValidatorTest {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        System.out.println("test1");
        String board = "...ee.CCC";
        Game game = new Game();
        game.populateFromString(board);
        System.out.println(game);
        char winner = GameResultValidator.getWinner(game);
        if (winner != 'c') {
            throw new RuntimeException("Test error");
        }
    }

    public static void test2() {
        System.out.println("test2");
        String board = "C.c.e.ECE";
        Game game = new Game();
        game.populateFromString(board);
        System.out.println(game);
        char winner = GameResultValidator.getWinner(game);
        System.out.println(winner);
        if (winner != ' ') {
            throw new RuntimeException("Test error");
        }
    }
    
    public static void test3(){
        String board = "C.c.C.cCE";
        Game game = new Game();
        game.populateFromString(board);
        System.out.println(game);
        char winner = GameResultValidator.getWinner(game);
        if (winner != 'c') {
            throw new RuntimeException("Test error");
        }
    }

}
