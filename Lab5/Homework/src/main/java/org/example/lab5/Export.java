package org.example.lab5;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.lab5.exception.CommandExecutionException;
import org.example.lab5.exception.InvalidPath;

import java.io.File;
import java.io.IOException;

public class Export implements Command {
    private final Repository repository;

    public Export(String repository) {
        this.repository = new Repository(repository);

    }

    @Override
    public void execute() throws CommandExecutionException, InvalidPath, IOException {
        String path = System.getProperty("user.dir");
        path += "/export.json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), repository.getRepositoryContent());
        System.out.println("Repository exported successfully to " + path);
    }
}


