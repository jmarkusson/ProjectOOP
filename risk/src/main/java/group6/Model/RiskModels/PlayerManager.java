package group6.Model.RiskModels;

import java.lang.reflect.Array;
import java.util.ArrayList;

import group6.Model.Player;
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


    public PlayerManager(){
        // Nothing should happen when an instance of PlayerManager is created
    }

    protected void initPlayers(ArrayList<String> playerNames, ArrayList<Color> playerColors){
    players = new ArrayList<Player>();

        for(int i = 0; i < playerNames.size(); i++){
            Player newPlayer = new Player(playerNames.get(i), playerColors.get(i), i);
            players.add(newPlayer);

        }
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
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

}
