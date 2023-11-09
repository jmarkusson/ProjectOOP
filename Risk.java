
public class Risk {

    public static void main(String[] args) {
        RiskView view = new RiskView();
        RiskModel model = new RiskModel();

        RiskController controller = new RiskController(model, view);
    }
}
