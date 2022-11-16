package com.example.demo.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Coffee {
    private final SimpleIntegerProperty id;
    private SimpleDoubleProperty weight;
    private SimpleDoubleProperty pricePerKilogram;
    private final SimpleObjectProperty<Pack> pack;
    private final PhysicalCondition physicalCondition;

    private final Sort sort;


    public Coffee(Sort sort, double pricePerKilogram, Pack pack, PhysicalCondition physicalCondition, int id, double weight) {

        this.sort = sort;
        this.pricePerKilogram =new SimpleDoubleProperty(pricePerKilogram) ;
        this.pack = new SimpleObjectProperty<>(pack);
        this.physicalCondition = physicalCondition;
        this.weight = new SimpleDoubleProperty(weight);
        this.id = new SimpleIntegerProperty(id);
    }
    public void setPricePerKilogram(double price){
        this.pricePerKilogram = new SimpleDoubleProperty(price);
    }
    public void setWeight(double weight) {
        this.weight = new SimpleDoubleProperty(weight);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleDoubleProperty weightProperty() {
        return weight;
    }

    public Sort getSort() {
        return sort;
    }

    public double getPricePerKilogram() {
        return pricePerKilogram.get();
    }

    public SimpleDoubleProperty pricePerKilogramProperty() {
        return pricePerKilogram;
    }

    public Pack getPack() {
        return pack.get();
    }

    public SimpleObjectProperty<Pack> packProperty() {
        return pack;
    }

    public PhysicalCondition getPhysicalCondition() {
        return physicalCondition;
    }

    public double getWeight() {
        return weight.get();
    }

    public int getId() {
        return id.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coffee coffee)) return false;
        return pricePerKilogram == coffee.pricePerKilogram
                && Objects.equals(sort, coffee.sort)
                && Objects.equals(pack, coffee.pack)
                && physicalCondition == coffee.physicalCondition;
    }

    @Override
    public String toString() {
        return "id=" + id.get() +
                ", вага = " + weight.get() +
                ", ціна за кілограм = " + pricePerKilogram.get() +
                ", упаковка: " + pack.get() +
                ", фізичний стан= " + physicalCondition +
                ", сорт = " + sort;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sort, pricePerKilogram, pack, physicalCondition);
    }


}
