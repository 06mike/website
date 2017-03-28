package org.com.demo.jdk8;

import java.io.Serializable;

public class Apple implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -7066594677029032155L;

    private String color;
    private Integer weight;

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
