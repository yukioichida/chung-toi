package chungtoi.model;

public class GamePosition {

    private final int line;

    private final int column;

    public GamePosition(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public boolean isValidPosition() {
        if ((line >= 0) && (line < 3) && (column >= 0) && (column < 3)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + line + "," + column + ")";
    }
}
