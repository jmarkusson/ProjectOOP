package group6.Model.Interfaces;

import group6.Model.Player;

public interface PlayerOwnership {

    void assignOwnership(Ownable ownable, Player player);
    void removeOwnership(Ownable ownable, Player player);
}
