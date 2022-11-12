package com.example.demo.commands.showing;

import com.example.demo.Model.Model;
import com.example.demo.controllers.SafeScan;
import com.example.demo.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class ShowCoffeeCommand extends Command {
    List<Command> commandList;


    public ShowCoffeeCommand(Model model) {
        super(model);
        commandList = new ArrayList<>();
        commandList.add(new ShowAllCoffeeCommand(model));
        commandList.add(new ShowCoffeeThatWillSoonRunOutCommand(model));
        commandList.add(new ShowTotalPriceCommand(model));
        commandList.add(new ShowTotalWeightCommand(model));
    }

    @Override
    public boolean execute() {
        showMenu();
        int choose;
        choose = SafeScan.getInstance().safeScanIntInTheRange(1,commandList.size());
        commandList.get(choose-1).execute();
        return true;
    }

    private void showMenu()
    {
        for(int i = 0;i< commandList.size();i++)
        {
            System.out.println((i+1)+" - "+commandList.get(i));
        }
    }
    @Override
    public String toString() {
        return "Показати інформацію про каву";
    }
}
