package com.example.demo.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PhysicalCondition {
    private final SimpleStringProperty physicalCondition;

    private final SimpleIntegerProperty id;

    public PhysicalCondition(String physicalCondition, int id)
    {
        this.physicalCondition = new SimpleStringProperty(physicalCondition);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getPhysicalCondition() {
        return physicalCondition.get();
    }

    public SimpleStringProperty physicalConditionProperty() {
        return physicalCondition;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "Id "+id.get()+ ", фізичний стан: "+ physicalCondition.get();
    }
}
