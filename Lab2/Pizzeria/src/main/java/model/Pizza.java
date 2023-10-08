package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Pizza
{
    private String name;
    private boolean isAvailable;
    private double price;

    public Pizza(String name, boolean isAvailable, double price) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.price = price;
    }
}
