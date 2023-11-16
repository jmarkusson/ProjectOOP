public interface PlayerTurnState {
    void placeSoldiers(Player player, int numberOfSoldiers, Planet planet);
    void attack(Player player, Planet fromPlanet, Planet toPlanet);
}
