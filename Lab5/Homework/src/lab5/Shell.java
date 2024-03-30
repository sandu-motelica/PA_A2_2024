package lab5;

import java.util.List;

public class Shell {
    private List<Command> commands;

    public Shell() {
        commands = null;
    }

    public Shell(List<Command> commands) {
        this.commands = commands;
    }

    public void addCommand(Command command){
        this.commands.add(command);
    }
}
