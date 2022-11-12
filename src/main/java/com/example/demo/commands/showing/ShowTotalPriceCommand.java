package com.example.demo.commands.showing;

import com.example.demo.Model.Model;
import com.example.demo.commands.Command;

public class ShowTotalPriceCommand extends Command {
    public ShowTotalPriceCommand(Model model) {
        super(model);
    }

    @Override
    public boolean execute() {
        model.showTotalPrice();
        return false;
    }

    @Override
    public String toString() {
        return "Показати загальну ціну усієї кави";
    }

}
