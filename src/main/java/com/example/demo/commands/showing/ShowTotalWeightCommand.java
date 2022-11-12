package com.example.demo.commands.showing;

import com.example.demo.Model.Model;
import com.example.demo.commands.Command;

public class ShowTotalWeightCommand extends Command {
    public ShowTotalWeightCommand(Model model) {
        super(model);
    }

    @Override
    public boolean execute() {
        model.showTotalWeight();
        return false;
    }

    @Override
    public String toString() {
        return "Показати загальну вагу усієї кави";
    }
}
