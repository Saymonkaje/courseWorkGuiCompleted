package com.example.demo.commands;

import com.example.demo.Model.Model;

public class LoadCoffeeCommand extends Command{
    public LoadCoffeeCommand(Model model) {
        super(model);
    }

    @Override
    public boolean execute() {
        //model.loadCoffee();
        return false;
    }

    @Override
    public String toString() {
        return "Завантажити каву у фургон";
    }
}
