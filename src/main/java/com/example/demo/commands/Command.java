package com.example.demo.commands;

import com.example.demo.Model.Model;

public abstract class Command {
    protected Model model;
    public Command(Model model)
    {
        this.model=model;
    }
    public abstract boolean execute();
}
