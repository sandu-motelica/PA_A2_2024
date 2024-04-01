package org.example.lab5;
import org.example.lab5.exception.*;

import java.io.IOException;

public interface Command {
    void execute() throws CommandExecutionException, IOException;
}
