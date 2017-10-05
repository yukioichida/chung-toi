package chungtoi.state;

import chungtoi.exception.PlayerAlreadyRegisteredException;
import chungtoi.exception.PlayerLimitException;
import chungtoi.model.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StaticPlayerState {

    private static AtomicInteger lastIdentifier;

    private static Map<Integer, Player> data;

    private static final int LIMIT = 1000;

    static {
        lastIdentifier = new AtomicInteger(0);
        data = new ConcurrentHashMap<>();
    }

    private static Integer getNewId() {
        return lastIdentifier.incrementAndGet();
    }

    /**
     * Registra um jogador no jogo, gerando um identificador.
     *
     * @param player - Jogador a ser registrado
     * @return Identificador gerado para o jogador
     */
    public static synchronized Integer registerPlayer(Player player) throws PlayerAlreadyRegisteredException, PlayerLimitException {

        String name = player.getName();
        // verifica o limite
        if (data.size() < LIMIT) {
            // verifica se já existe um usuário com o mesmo nome
            if (!playerNameAlreadyExists(name)) {
                Integer id = getNewId();
                player.setId(id);
                data.put(id, player);
                return id;
            } else {
                throw new PlayerAlreadyRegisteredException("Jogador " + name + " já cadastrado");
            }
        } else {
            throw new PlayerLimitException();
        }
    }

    /**
     * Verifica se o nome de um determinado jogador já foi cadastrado.
     *
     * @param name - Nome do jogador
     * @return Verdadeiro, caso já exista um jogador com o nome
     */
    private static boolean playerNameAlreadyExists(String name) {
        for (Player player: data.values()){
            if (player.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * @param id - Identificador do jogador a ser buscado
     * @return Jogador cujo o id foi passado como parâmetro
     */
    public static synchronized Player getPlayer(Integer id) {
        return data.get(id);
    }

    public static synchronized boolean existPlayer(Integer id){
        if (data.containsKey(id)){
            return true;
        }
        return false;
    }


}
