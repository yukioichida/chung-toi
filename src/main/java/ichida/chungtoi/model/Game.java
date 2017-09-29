package ichida.chungtoi.model;

public class Game {

    private int playerIdC;

    private int playerIdE;

    private final char[][] game;

    public Game() {
        game = new char[3][3];
    }

    public void putChar(int x, int y, char piece) throws Exception {
        game[x][y] = piece;
    }

    public int getPlayerIdC() {
        return playerIdC;
    }

    public void setPlayerIdC(int playerIdC) {
        this.playerIdC = playerIdC;
    }

    public int getPlayerIdE() {
        return playerIdE;
    }

    public void setPlayerIdE(int playerIdE) {
        this.playerIdE = playerIdE;
    }

    /**
     * @return String de representação do tabuleiro do jogo para envio e recebimento de mensagens.
     * <p>
     *     Exemplo: C.E.e.CEc
     * </p>
     */
    public String getBoardString() {
        StringBuffer boardString = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardString.append(this.game[i][j]);
            }
        }
        return boardString.toString();
    }

    @Override
    public String toString() {
        String gameBoardPattern = new StringBuffer()
                .append("%c|%c|%c\n")
                .append("-+-+-\n")
                .append("%c|%c|%c\n")
                .append("-+-+-\n")
                .append("%c|%c|%c")
                .toString();

        return String.format(gameBoardPattern,
                this.game[0][0], this.game[0][1], this.game[0][2],
                this.game[1][0], this.game[1][1], this.game[1][2],
                this.game[2][0], this.game[2][1], this.game[2][2]
        );
    }
}
