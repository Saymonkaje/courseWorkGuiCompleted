package com.example.demo.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Sort {
    private final SimpleStringProperty name;

    private final SimpleIntegerProperty id;

    public Sort(String name,int id)
    {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "Id "+id.get()+ ", сорт: "+ name.get();
    }

}
