package org.example.lab5;


import org.example.lab5.exception.CommandExecutionException;
import org.example.lab5.exception.InvalidPath;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class View implements Command{
    private String path;

    public View() {
        path = null;
    }
    public View(String path){
        this.path = path;
    }
    public void setPath(String path){
        this.path = path;
    }
    @Override
    public void execute() throws CommandExecutionException, IOException {
        try{
            Desktop.getDesktop().open(new File(Objects.requireNonNull(path)));
        }
        catch (InvalidPath e){
            System.out.println("Invalid path of document");
        }
    }

    @Override
    public String toString() {
        return "View{" +
                "path='" + path + '\'' +
                '}';
    }
}
