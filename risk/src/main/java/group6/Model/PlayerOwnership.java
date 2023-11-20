package group6.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import group6.Model.Interfaces.Ownable;

public class PlayerOwnership{
    
    private Map<Ownable, Player> ownablesPlayerMap = new HashMap<>();
    private Map<Player, ArrayList<Ownable>> playersOwnablesMap = new HashMap<>();

    public void assignOwnership(Ownable ownable, Player player) {
        ownablesPlayerMap.put(ownable, player);

        if  (!playersOwnablesMap.containsKey(player)){
            playersOwnablesMap.put(player, new ArrayList<Ownable>());
        }
        
        playersOwnablesMap.get(player).add(ownable);
    }

    public void removeOwnership(Ownable ownable, Player player) {
        ownablesPlayerMap.remove(ownable, player);
        playersOwnablesMap.get(player).remove(ownable);
    }

    public ArrayList<Ownable> getPLayersOwnables(Player player){

        return playersOwnablesMap.get(player);
    }

    

}