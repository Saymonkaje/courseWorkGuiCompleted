
package com.example.demo.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pack{
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty volume;
    private final SimpleIntegerProperty id;

    public Pack(String name,double volume,int id)
    {
        this.name = new SimpleStringProperty(name);
        this.volume = new SimpleDoubleProperty(volume);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public double getVolume() {
        return volume.get();
    }

    public SimpleDoubleProperty volumeProperty() {
        return volume;
    }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "Id "+id.get()+ ", Назва: "+ name.get() + ", об'єм=" + volume.get();
    }

    public String toStringWithoutId()
    {
        return "Назва: "+ name +", об'єм: "+ volume;
    }

}




