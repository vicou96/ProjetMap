import controller.MainController;
import model.MainModel;
import view.MainView;

/**
 * Created by Roland on 30-Nov-16.
 */
public class Map
{
    public static void main(String[] args)
    {
        MainView view = new MainView();
        MainModel model = new MainModel();
        MainController controller= new MainController(model,view);
        view.setVisible(true);
    }
}
