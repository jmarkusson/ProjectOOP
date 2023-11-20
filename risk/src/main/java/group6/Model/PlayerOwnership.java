package group6.Model;

import java.util.HashMap;
import java.util.Map;

import group6.Model.Interfaces.Ownable;

public class PlayerOwnership{
    
    private Map<Ownable, Player> ownershipMap = new HashMap<>();




    public void assignOwnership(Ownable ownable, Player player) {
        ownershipMap.put(ownable, player);
    }

    public void removeOwnership(Ownable ownable, Player player) {
        ownershipMap.remove(ownable, player);
    }
}
