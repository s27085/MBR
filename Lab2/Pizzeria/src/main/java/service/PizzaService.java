package service;

import exception.PizzaNotFoundException;
import model.Order;
import model.Pizza;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class PizzaService {
    private List<Pizza> pizzaList;
    private List<Order> orderList = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger();
    public PizzaService(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }
    public List<Pizza> getAvailablePizzas(){
        logger.trace("Fetching available pizzas...");
        return pizzaList.stream().filter(Pizza::isAvailable).toList();
    }

    public Order makeOrder(List<Pizza> orderedPizzas) {
        // oczekujemy tutaj zwrócenia informacji o zamówieniu
        // order number, price and pizzas
        logger.trace("Creating order...");
        Order order = null;
        if (getAvailablePizzas().containsAll(orderedPizzas)) {
            double su = orderedPizzas.stream()
                    .map(Pizza::getPrice)
                    .reduce((double) 0, Double::sum);
            order = new Order(orderedPizzas);
            orderList.add(order);
            logger.trace("Your order has been successfully created!");
        }
        else{
            throw new PizzaNotFoundException("Pizza not found");
        }
        return order;
    }
}
