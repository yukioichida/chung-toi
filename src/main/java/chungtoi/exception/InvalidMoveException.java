/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chungtoi.exception;

import chungtoi.model.GamePosition;

/**
 *
 * @author ichida
 */
public class InvalidMoveException extends Exception {

    public InvalidMoveException(GamePosition pos) {
        super("Posição do tabuleiro inválida para movimentação: " + pos);
    }

}
