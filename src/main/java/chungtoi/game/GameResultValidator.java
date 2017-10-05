package chungtoi.game;

import chungtoi.model.Game;

/**
 * Classe que valida se o jogo possui um vencedor/perdedor.
 */
public class GameResultValidator {

    /**
     * Retorna o vencedor da partida. Deve ser executado passo a passo,
     * pois esse método não valida se existem dois jogadores.
     *
     * @param game - Jogo que está sendo disputado
     * @return peça vencedora
     */
    public static char getWinner(Game game) {
        char winner = verifyDiagonalWinner(game);
        if (winner == ' ') {
            winner = verifyPerpendicularWinner(game);
        }
        return winner;
    }

    private static char verifyPerpendicularWinner(Game game) {
        char[][] board = game.getGame();
        /**
         * VERIFICAÇÃO HORIZONTAL
         * */
        for (int i = 0; i < 3; i++) {
            boolean match = true;
            /* Varre linha a linha, verificando se uma linha contem peças iguais */
            char actualPiece = Character.toLowerCase(board[i][0]);
            for (int j = 1; j < 3; j++) {
                char nextPiece = Character.toLowerCase(board[i][j]);
                if (nextPiece != actualPiece) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return actualPiece;
            }
        }
        /**
         * VERIFICAÇÃO VERTICAL
         * */
        for (int i = 0; i < 3; i++) {
            boolean match = true;
            /* Varre coluna a coluna, verificando se uma linha contem peças iguais */
            char actualPiece = Character.toLowerCase(board[0][i]);
            for (int j = 1; j < 3; j++) {
                char nextPiece = Character.toLowerCase(board[j][i]);
                // Se alguma peça difere da coluna varrida, ignore
                if (nextPiece != actualPiece) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return actualPiece;
            }
        }
        return ' ';
    }

    public static char verifyDiagonalWinner(Game game) {
        char[][] board = game.getGame();
        char actualChar = Character.toLowerCase(board[0][0]);
        // Diagonal principal
        boolean match = true;
        for (int i = 1; i < 3; i++) {
            if (actualChar != Character.toLowerCase(board[i][i])) {
                match = false;
                break;
            }
        }
        if (match) {
            return actualChar;
        } else {
            // verifica Diagonal Secundária
            match = true;
            actualChar = Character.toLowerCase(board[2][2]);
            for (int i = 1; i < 3; i++) {
                if (actualChar != Character.toLowerCase(board[i][2 - i])) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return actualChar;
            }
        }
        return ' ';
    }

}
