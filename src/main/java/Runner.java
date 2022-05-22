import controller.Controller;
import model.Laptop;
import view.View;

public class Runner {

    public static void main(String[] args) {
        Laptop laptop = new Laptop();
        View view = new View();
        Controller controller = new Controller(laptop, view);
        controller.processUserInput();
    }
}
