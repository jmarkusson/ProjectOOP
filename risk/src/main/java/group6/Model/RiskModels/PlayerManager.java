package group6.Model.RiskModels;

import java.util.ArrayList;

import group6.Model.Planet;
import group6.Model.Player;
import group6.Model.PlayerOwnership;
import group6.Model.Interfaces.Ownable;

import java.awt.Color;

public class PlayerManager {
    private ArrayList<Player> players;
    private int currentPlayerIndex = 0;
    private PlayerOwnership playerOwnership;
    private int numberOfPlayers = 2;
    private final Color[] colorChoices = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK};

    public PlayerManager(PlayerOwnership playerOwnership){
        this.playerOwnership = playerOwnership;
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
        int numberOfPlayers = this.getNumberOfPlayers();
        this.currentPlayerIndex = ((this.currentPlayerIndex + 1) % numberOfPlayers);
    }

    protected void initializePlayersReinforceableSoldiers(){
        for (Player player : players){
            player.setReinforceableSoldiers(player.getSoldiers());
        }
    }

    public PlayerOwnership getPlayerOwnership(){
        return this.playerOwnership;
    }

    public ArrayList<Ownable> getPlayerOwnables(){
        return playerOwnership.getPlayersOwnables(getCurrentPlayer());
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

    protected void resetAllReinforcableSoldierForNextTurn(){
        for (Player player : players){
            resetReinforcableSoldierForNextTurn(player);
        }
    }

    protected int getCurrentPlayersReinforcableSoldier(){
        return this.getCurrentPlayer().getReinforceableSoldiers();
    }

    public void removeOwnership(Planet planetToAttack, Player defendingPlayer ){
        this.playerOwnership.removeOwnership(planetToAttack, defendingPlayer);
    }

    protected void setNumberOfPlayers(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    protected int getNumberOfPlayers(){
        return this.numberOfPlayers;
    }

    protected Color[] getPlayerColors(){
        return colorChoices;
    } 

    protected Player getPlayer(int i){
        return this.players.get(i);
    }

    protected Color getPlayerColor(Player player){
        return player.getColor();
    }

    public boolean isOwnedCurrentPlayer(Ownable ownable){
        return isOwned(ownable, getCurrentPlayer());
    }

    private boolean isOwned(Ownable ownable, Player player){
        return this.playerOwnership.isOwned(ownable, player);
    }
    
    public void removeReinforceableSoldiers(Player player, int soldiers){
        player.removeReinforceableSoldiers(soldiers);
    }

    protected void addSoldiers(Player player, int soldiers){
        player.addSoldiers(soldiers);
    }

    protected void removeSoldiers(Player player, int soldiers){
        player.removeSoldiers(soldiers);
    }
    
    
}
