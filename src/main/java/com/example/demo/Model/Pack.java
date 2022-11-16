
package com.example.demo.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pack{
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty weight;
    private final SimpleIntegerProperty id;

    public Pack(String name, double weight, int id)
    {
        this.name = new SimpleStringProperty(name);
        this.weight = new SimpleDoubleProperty(weight);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public double getWeight() {
        return weight.get();
    }

    public SimpleDoubleProperty weightProperty() {
        return weight;
    }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "Id "+id.get()+ ", Назва: "+ name.get() + ", об'єм=" + weight.get();
    }

    public String toStringWithoutId()
    {
        return "Назва: "+ name +", об'єм: "+ weight;
    }

}




