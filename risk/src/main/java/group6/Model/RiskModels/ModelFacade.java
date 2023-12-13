package group6.Model.RiskModels;

public class ModelFacade {
    private GameInitializer gameInitializer;
    private GameMechanics gameMechanics;
    private PlayerManager playerManager;
    private BoardManager boardManager;
    private ObserverManager observerManager;
    private FileParser fileParser;

    public ModelFacade() {
        this.boardManager = new BoardManager();
        this.gameMechanics = new GameMechanics();
        this.playerManager = new PlayerManager();
        this.observerManager.getInstance(); // Singleton Example, see ObserverManager class.
        this.fileParser = new FileParser();
        this.gameInitializer = new GameInitializer(this.boardManager, this.playerManager, this.fileParser);
    }

}
