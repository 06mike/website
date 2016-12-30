package org.com.jdk8;

import java.io.Serializable;

/**
 * java8实战 Stream API例子实体
 * 
 * @author binary
 *
 */
public class Dish implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2808866456386528055L;

    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public Dish() {
        super();
    }

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Type {
        MEAT, FISH, OTHER
    }

}
