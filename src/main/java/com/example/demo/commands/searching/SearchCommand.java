package com.example.demo.commands.searching;

import com.example.demo.Model.Model;
import com.example.demo.commands.Command;
public class SearchCommand extends Command {

    public SearchCommand(Model model) {
        super(model);
    }

    @Override
    public boolean execute() {
        //model.searchCoffee();
        return false;
    }

    @Override
    public String toString() {
        return "Пошук кави за заданими критеріями";
    }
}
