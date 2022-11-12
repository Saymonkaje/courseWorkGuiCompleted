package com.example.demo.commands;

import com.example.demo.DAO.DBManager;
import com.example.demo.Model.Model;

public class ExitCommand extends Command{
    public ExitCommand(Model model) {
        super(model);
    }

    @Override
    public boolean execute() {
        DBManager.getInstance().close();
        System.exit(1);
        return false;
    }

    @Override
    public String toString() {
        return "Вихід";
    }
}
