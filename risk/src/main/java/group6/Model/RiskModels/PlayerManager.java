package group6.Model.RiskModels;

import java.lang.reflect.Array;
import java.util.ArrayList;

import group6.Model.Planet;
import group6.Model.Player;
import group6.Model.PlayerOwnership;
import group6.Model.Interfaces.Ownable;

import java.awt.Color;
import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class PlayerManager {
    private ArrayList<Player> players;
    private int totalPlayers;
    private int currentPlayerIndex;
    private Player currentPlayer;
    private PlayerOwnership playerOwnership;
    private int nmbrOfPlayers = 2;
    private Color[] colorChoices = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK};
    


    public PlayerManager(){
        // Nothing should happen when an instance of PlayerManager is created
    }

    protected void initPlayers(ArrayList<String> playerNames, ArrayList<Color> playerColors){
    players = new ArrayList<Player>();

        for(int i = 0; i < playerNames.size(); i++){
            Player newPlayer = new Player(playerNames.get(i), playerColors.get(i), i);
            this.players.add(newPlayer);

        }
    }

    public Player getCurrentPlayer(){
        return this.players.get(currentPlayerIndex);
    }

    public void nextPlayer(){
        int numberOfPlayers = this.getTotalPlayers();
        this.currentPlayerIndex = ((this.currentPlayerIndex + 1) % numberOfPlayers);
    }

    public int getTotalPlayers(){
        return this.totalPlayers;
    }

    private void initializePlayersReinforceableSoldiers(){
        for (Player player : players){
            player.setReinforceableSoldiers(player.getSoldiers());
        }

    }

    public PlayerOwnership getPlayerOwnership(){
        return this.playerOwnership;
    }

    public ArrayList<Ownable> getPlayerOwnables(){
        return playerOwnership.getPlayersOwnables(currentPlayer);
    }

    public void assignOwnership(Planet planet, Player player){
        playerOwnership.assignOwnership(planet, player);
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public Player getOwner(Planet planet){
        return playerOwnership.getOwner(planet);
    }

    public void resetReinforcableSoldierForNextTurn(Player player){
        player.setReinforceableSoldiers(player.getBonusSoldiers());
    }

    private void resetAllReinforcableSoldierForNextTurn(){
        for (Player player : players){
            resetReinforcableSoldierForNextTurn(player);
        }
    }

    public int getCurrentPlayersReinforcableSoldier(){
        return this.getCurrentPlayer().getReinforceableSoldiers();
    }

    public void setCurrentPlayersReinforcableSoldier(int soldiers){
        getCurrentPlayer().setReinforceableSoldiers(soldiers);
    }
    public void removeOwnership(Planet planetToAttack, Player defendingPlayer ){
        this.playerOwnership.removeOwnership(planetToAttack, defendingPlayer);
    }

    public void setnmbOfPlayers(int nmbrOfPlayers){
        this.nmbrOfPlayers = nmbrOfPlayers;
    }

    public int getnmbrOfPlayers(){
        return this.nmbrOfPlayers;
    }

    public Color[] getPlayerColors(){
        return colorChoices;
    } 

    public Player getPlayer(int i){
        return this.players.get(i);
    }

    public Color getPlayerColor(Player player){
        return player.getColor();
    }

    public boolean isOwnedCurrentPlayer(Ownable ownable){
        return isOwned(ownable, getCurrentPlayer());
    }

    public boolean isOwned(Ownable ownable, Player player){
        return this.playerOwnership.isOwned(ownable, player);
    }

    

    
    
    
}
