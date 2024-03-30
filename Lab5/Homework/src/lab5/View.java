package lab5;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class View implements Command{
    private final String path;

    public View(String path) {
        this.path = path;
    }

    @Override
    public void execute() {
        try{
            Desktop.getDesktop().open(new File(path));
        }
        catch (IOException e){
            System.out.println("Invalid path of document");
        }
    }
}
