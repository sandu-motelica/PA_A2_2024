package org.example.lab5;

import java.util.*;

public class Shell {
    private final Map<String, Command> commands = new HashMap<>();

    public Shell() {
        commands.put("view", null);
        commands.put("report", null);
        commands.put("export", null);
    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");

            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");
                                                    String commandName = parts[0];

            if(commands.containsKey(commandName)){
                if(parts.length != 2){
                    System.out.println("Invalid command");
                    continue;
                }
                String argument = parts[1];
                if(commandName.compareTo("view") == 0) {
                    View command =  new View(argument);
                    System.out.println(command);
                    command.execute();
                }
                else if(commandName.compareTo("report") == 0) {
                    Report command =  new Report();
                    command.setRepository(argument);
                    System.out.println(command);
                    command.execute();
                }
                else if(commandName.compareTo("export") == 0) {
                    Export command =  new Export(argument);
                    System.out.println(command);
                    command.execute();
                }
            }
            else if(commandName.compareTo("quit")==0){
                System.exit(-1);
            }
            else {
                System.out.println("Command not found!");
            }
        }
    }
}

