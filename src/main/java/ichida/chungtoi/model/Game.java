package ichida.chungtoi.model;

import ichida.chungtoi.exception.InvalidPositionException;
import ichida.chungtoi.exception.PositionAlreadyOccupiedException;
import ichida.chungtoi.util.GameConstants;

import static ichida.chungtoi.util.GameConstants.*;

public class Game {

    private int playerIdC = EMPTY_PLAYER;

    private int playerIdE = EMPTY_PLAYER;

    private final char[][] game;

    private int actualPlayer;

    private int quitter = EMPTY_PLAYER;

    private boolean open = false;

    private long lastUpdate = 0L;

    private boolean timeout = false;

    public void timeout() {
        this.timeout = true;
        this.open = false;
    }

    public boolean isTimeout() {
        return this.timeout;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Game() {
        game = new char[3][3];
        reset();
    }

    public void start() {
        this.actualPlayer = playerIdC;
        this.open = true;
    }

    /**
     * Sinaliza o final do jogo.
     *
     * @param playerId - Identificador do jogador que decidiu terminar a partida
     */
    public void endGame(int playerId) {
        this.open = false;
        this.quitter = playerId;
    }

    public int getQuitter() {
        return quitter;
    }

    /**
     * Reinicia o tabuleiro, removendo todas as peças
     */
    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = GameConstants.EMPTY;
            }
        }
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
     * @param linearPos - Posição linear da peça
     * @return Peça de acordo com a posição linear
     */
    public char getPiece(int linearPos) {
        GamePosition position = getPosition(linearPos);
        if (position.isValidPosition()) {
            return game[position.getLine()][position.getColumn()];
        } else {
            // TODO: tratar melhor o retorno inválido
            return EMPTY;
        }
    }

    /**
     * @param line   - Linha da peça
     * @param column - Coluna da peça
     * @return Peça de acordo com a linha/coluna provida
     */
    public char getPiece(int line, int column) {
        return game[line][column];
    }

    /**
     * @return Identificador do jogador adversário
     */
    public int getOpponentPlayerId(Integer id) {
        if (id == this.playerIdC) {
            return this.playerIdE;
        } else if (id == this.playerIdE) {
            return this.playerIdC;
        }
        return EMPTY_PLAYER;
    }

    /**
     * Converte uma posição linear em uma posição da matriz do jogo.
     *
     * @param pos - posição linear
     * @return GamePosition com as coordenadas x,y da matriz
     */
    public GamePosition getPosition(int pos) {
        int linearPosition = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (linearPosition == pos) {
                    return new GamePosition(i, j);
                }
                linearPosition++;
            }
        }
        return null;
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

    /**
     * Substitui uma peça em um espaço
     *
     * @param l     - Linha do tabuleiro
     * @param c     - coluna do tabuleiro
     * @param piece - peça que substituirá a antiga
     */
    public void changePiece(int l, int c, char piece) throws InvalidPositionException {
        try {
            game[l][c] = piece;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new InvalidPositionException(new GamePosition(l, c));
        }
    }

    /**
     * Insere uma peça em um espaço previamente vazio
     *
     * @param l     - Linha do tabuleiro
     * @param c     - coluna do tabuleiro
     * @param piece - peça a ser inserida
     */
    public void putChar(int l, int c, char piece) throws InvalidPositionException, PositionAlreadyOccupiedException {
        try {
            if (game[l][c] == EMPTY) {
                game[l][c] = piece;
            } else {
                throw new PositionAlreadyOccupiedException(new GamePosition(l, c));
            }
        } catch (ArrayIndexOutOfBoundsException aiobex) {
            throw new InvalidPositionException(new GamePosition(l, c));
        }
    }

    /**
     * Método para inserir peça usando coordenada de 1 dimensão.
     *
     * @param pos   - posição do jogo em relação a representação de 1 dimensão do jogo.
     * @param piece - peça a ser inserida no jogo
     */
    public void putChar(int pos, char piece) throws InvalidPositionException, PositionAlreadyOccupiedException {
        int linearPosition = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (linearPosition == pos) {
                    this.putChar(i, j, piece);
                    return;
                }
                linearPosition++;
            }
        }
        throw new InvalidPositionException(new GamePosition(-1, -1));
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
     * Recebe a representação em string do tabuleiro (representação linear)
     * e popula os dados internos na matriz do jogo.
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
