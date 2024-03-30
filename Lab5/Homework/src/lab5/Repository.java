package lab5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private final String directoryPath;

    public Repository(String masterDirectoryPath) {
        this.directoryPath = masterDirectoryPath;
    }

    public List<String> displayRepositoryContent() throws Exception {
        List<String> repositoryContent = new ArrayList<>();
        File masterDirectory = new File(directoryPath);

        if (!masterDirectory.exists() || !masterDirectory.isDirectory()) {
            throw new Exception("Directory doesn't exist or is not a directory");
        }

        File[] directories = masterDirectory.listFiles(File::isDirectory);
        if (directories == null) {
            throw new Exception("Error while listing employee directories");
        }

        for (File directory : directories) {
            repositoryContent.add(directory.getName());
        }

        return repositoryContent;
    }
}
