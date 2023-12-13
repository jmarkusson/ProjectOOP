package group6.Model.RiskModels;

public class ObserverManager {
    private static final ObserverManager observerManager = new ObserverManager();

    private ObserverManager(){}
    public static ObserverManager getInstance(){
        return observerManager;
    }

}
