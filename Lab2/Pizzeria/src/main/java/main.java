import exception.PizzaNotFoundException;
import model.Order;
import model.Pizza;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.PizzaService;

import java.util.List;

public class main {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args){
        Pizza pizza1 = new Pizza("Hawajska", true, 150.24);
        Pizza pizza2 = new Pizza("Capricioza", false, 15.42);
        Pizza pizza3 = new Pizza("Americano", true, 53.21);
        Pizza pizza4 = new Pizza("Halapeno", true, 54.69);

        PizzaService mario = new PizzaService(List.of(pizza1, pizza2, pizza3, pizza4));
        try {
            Order order1 = mario.makeOrder(List.of(pizza2, pizza1));
            Order order2 = mario.makeOrder(List.of(pizza3, pizza4));
            System.out.println(order1);
        }
        catch (PizzaNotFoundException e){
            logger.error("Tried to fetch unavailable pizza. Aborting");
        }
    }
}
