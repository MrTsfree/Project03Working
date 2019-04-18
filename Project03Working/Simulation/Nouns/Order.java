/*
 * Order
 * Author:
 * Last Updated: Sprint03
 */
package Simulation.Nouns;

import Simulation.Enumerators.Chips;
import Simulation.Enumerators.Drinks;
import Simulation.Enumerators.Sandwiches;

import java.util.Random;

public class Order {

    Sandwiches sandwich;
    Chips chip;
    Drinks drink;

    static Random rand = new Random();

    public Order(){
        sandwich = randomEnum(Sandwiches.class);
        chip = randomEnum(Chips.class);
        drink = randomEnum(Drinks.class);
    }

    public Sandwiches getSandwich() {
        return sandwich;
    }

    public void setSandwich(Sandwiches sandwich) {
        this.sandwich = sandwich;
    }

    public void setChips(Chips chips) {
        this.chip = chips;
    }

    public Drinks getDrink() {
        return drink;
    }

    public void setDrink(Drinks drink) {
        this.drink = drink;
    }

    public Chips getChips() {
        return chip;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = rand.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants() [x];
    }

    @Override
    public String toString() {
        return sandwich + " " + chip + " " + drink;
    }

}
