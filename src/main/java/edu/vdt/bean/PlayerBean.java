package edu.vdt.bean;

import edu.vdt.model.Player;
import edu.vdt.service.PlayerService;
import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeFacesContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PlayerBean implements Serializable {

    private Player currentPlayer;

    @Inject
    PlayerService playerService;

    private List<Player> players;

    @PostConstruct
    public void init(){
        currentPlayer = new Player();
        players = playerService.fetchPlayers();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void processPlayer(){
        Player persistedPlayer = playerService.savePlayer(currentPlayer);
        players.add(persistedPlayer);
        showInfo(persistedPlayer);
        PrimeFaces.current().ajax().update("growl");
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo(Player player) {
        addMessage(FacesMessage.SEVERITY_INFO, "Player added", player.toString());
    }
}
