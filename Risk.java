
public class Risk {

    public static void main(String[] args) {
        MainMenuView view = new MainMenuView();
        RiskModel model = new RiskModel();


        MainMenuController controller = new MainMenuController(model, view);

    }
}
