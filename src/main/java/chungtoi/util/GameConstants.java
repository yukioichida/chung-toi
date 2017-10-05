package ichida.chungtoi.util;

public class GameConstants {

    public static final char EMPTY = '.';

    public static final char PLAYER_E = 'E';

    public static final char PLAYER_C = 'C';

    public static final int EMPTY_PLAYER = -1;

    /* Constantes relacionado a posicionamento de peças */
    public static final int INPUT_PERPENDICULAR = 0;

    public static final int INPUT_DIAGONAL = 1;

    /* Constantes de direção de movimentação de peça */
    public static final int MOVEMENT_UPPER_LEFT = 0;
    public static final int MOVEMENT_UPPER = 1;
    public static final int MOVEMENT_UPPER_RIGHT = 2;
    public static final int MOVEMENT_LEFT = 3;
    public static final int NO_MOVENT = 4;
    public static final int MOVEMENT_RIGHT = 5;
    public static final int MOVEMENT_BOTTOM_LEFT = 6;
    public static final int MOVEMENT_BOTTOM = 7;
    public static final int MOVEMENT_BOTTOM_RIGHT = 8;

    public static final long WAITING_PLAYER_TIMEOUT = 120000L;

    public static final long WAITING_NEXT_PLAY_TIMEOUT = 60000L;


}
