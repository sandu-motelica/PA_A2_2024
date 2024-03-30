import lab5.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository("D:/Programs");
        try {
            List<String> repositoryContent = repository.displayRepositoryContent();
            System.out.println("Repository Content:");
            for (String content : repositoryContent) {
                System.out.println(content);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        View comanda1 = new View("D:/PATH.txt");
        comanda1.execute();
    }
}