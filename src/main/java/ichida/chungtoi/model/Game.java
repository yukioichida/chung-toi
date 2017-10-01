package ichida.chungtoi.model;

import ichida.chungtoi.util.GameConstants;

import static ichida.chungtoi.util.GameConstants.*;

public class Game {

    private int playerIdC = EMPTY_PLAYER;

    private int playerIdE = EMPTY_PLAYER;

    private final char[][] game;

    private int actualPlayer;

    private boolean open = false;

    public Game() {
        game = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = GameConstants.EMPTY;
            }
        }
    }

    public void start() {
        this.actualPlayer = playerIdC;
        this.open = true;
    }

    public void endGame() {
        this.open = false;
    }

    public boolean isOpen() {
        return this.open;
    }

    /**
     * @return Código do jogador da rodada
     */
    public int getActualPlayer() {
        return actualPlayer;
    }

    /**
     * @param id - Identificador do jogador
     * @return Peça do jogador
     */
    public char getPlayerPiece(Integer id) {
        if (id == this.playerIdC) {
            return PLAYER_C;
        } else if (id == this.playerIdE) {
            return PLAYER_E;
        }
        return EMPTY;
    }

    /**
     * @param id - Identificador do jogador
     * @return Peça do jogador adversário
     */
    public char getAdversarialPiece(Integer id) {
        if (id == this.playerIdC) {
            return PLAYER_E;
        } else if (id == this.playerIdE) {
            return PLAYER_C;
        }
        return EMPTY;
    }

    /**
     * @return Identificador do jogador adversário
     */
    public int getAdversarialPlayerId(Integer id) {
        if (id == this.playerIdC) {
            return this.playerIdE;
        } else if (id == this.playerIdE) {
            return this.playerIdC;
        }
        return EMPTY_PLAYER;
    }

    /**
     * Define o jogador da rodada
     *
     * @param actualPlayer - Jogador da rodada
     */
    public void setActualPlayer(int actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    public char[][] getGame() {
        return this.game;
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
     * Exemplo: C.E.e.CEc
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

    /**
     * Recebe a representação em string do tabuleiro e popula os dados internos.
     *
     * @param boardString - Representação do tabuleiro em string
     */
    public void populateFromString(String boardString) {
        if (boardString.length() == 9) {
            int l = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    this.game[i][j] = boardString.charAt(l);
                    l++;
                }
            }
        } else {
            throw new IllegalArgumentException("String de tabuleiro inválida");
        }

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
