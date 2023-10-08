package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class Order
{
    private int orderNumber;
    private int totalSum;
    private List<Pizza> orderedPizzasList;

    public Order(List<Pizza> orderedPizzasList) {
        this.orderedPizzasList = orderedPizzasList;
        totalSum = orderedPizzasList.size();
    }
}
