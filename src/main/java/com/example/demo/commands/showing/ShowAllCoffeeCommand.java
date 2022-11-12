package com.example.demo.commands.showing;

import com.example.demo.DAO.QueryConstant;
import com.example.demo.Model.Model;
import com.example.demo.commands.Command;
public class ShowAllCoffeeCommand extends Command {
    public ShowAllCoffeeCommand(Model model) {
        super(model);
    }

    @Override
    public boolean execute() {
        model.getCoffee(QueryConstant.selectAllCoffee);
        return false;
    }

    @Override
    public String toString() {
        return "Показати всю каву";
    }
}
