package edu.vdt.service;

import edu.vdt.model.Player;
import edu.vdt.repository.PlayerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class PlayerService implements Serializable {

    @Inject
    PlayerRepository playerRepository;

    public List<Player> fetchPlayers(){
        return playerRepository.loadPlayers();
    }

    public Player savePlayer(Player player){
        return player;
    }

}
