package edu.vdt.repository;

import com.github.javafaker.Faker;
import edu.vdt.model.Player;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PlayerRepository implements Serializable {

    private List<Player> players;

    @PostConstruct
    public void init(){
        players = new ArrayList<>();
        for (int i = 0; i < 10 ; i++){
            Player player = new Player();
            Faker faker = new Faker();
            player.setName(faker.name().fullName());
            player.setAge(faker.number().numberBetween(23, 40));
            players.add(player);
        }
    }

    public List<Player> loadPlayers() {
        return players;
    }
}
