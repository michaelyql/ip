package terminator.command;

import terminator.task.DeadlineTask;
import terminator.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Concrete class representing a command to create a DeadlineTask.
 */
public class DeadlineCommand extends Command {
    private final String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    private static final String ERR_MSG = """
            Deadline description cannot be empty. 
            
            Usage: deadline <description> /by dd/MM/yyyy HHmm""";

    /**
     * Creates a new DeadlineTask with the given input and adds it to the task list.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the description of the deadline task is empty.
     */
    @Override
    public void execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) {
            throw new TerminatorException(ERR_MSG);
        }

        // Parse description and date
        int byIdx = input.indexOf("/by");
        String description = input.substring(0, byIdx).trim();
        if (description.isEmpty()) {
            throw new TerminatorException(ERR_MSG);
        }
        String byDateString = input.substring(byIdx + 4);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime byDate = LocalDateTime.parse(byDateString, dateTimeFormatter);

        // Add to TaskList
        Task t = new DeadlineTask(description, byDate);
        todoList.add(t);
        System.out.println("Mission parameters updated. Added new objective:\n\n" + t);
    }
}
