package command;

import task.Task;

import java.util.ArrayList;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(ArrayList<Task> todoList) throws DukeException;
}
