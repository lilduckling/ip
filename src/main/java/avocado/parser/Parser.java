package avocado.parser;

import avocado.command.*;
import avocado.exception.AvocadoException;
import avocado.task.*;

public class Parser {
    public static Command parse(String fullCommand) throws AvocadoException {
        if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark ")) {
            return new MarkCommand(Integer.parseInt(fullCommand.split(" ")[1]) - 1);
        } else if (fullCommand.startsWith("unmark ")) {
            return new UnmarkCommand(Integer.parseInt(fullCommand.split(" ")[1]) - 1);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteCommand(Integer.parseInt(fullCommand.split(" ")[1]) - 1);
        } else if (fullCommand.startsWith("todo ")) {
            String description = fullCommand.substring(5).trim();
            if (description.isEmpty()) {
                throw new AvocadoException("Oops! The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(description));
        } else if (fullCommand.startsWith("deadline ")) {
            String[] parts = fullCommand.split(" /by ", 2);
            if (parts.length < 2) {
                throw new AvocadoException("Oops! Deadline format should be: deadline <task> /by yyyy-MM-dd");
            }
            try {        
                return new AddCommand(new Deadline(parts[0].substring(9).trim(), parts[1]));
            } catch (Exception e) {
                throw new AvocadoException("Oops! Date format should be: yyyy-MM-dd");
            }
        } else if (fullCommand.startsWith("event ")) {
            String[] parts = fullCommand.split(" /from | /to ", 3);
            if (parts.length < 3) {
                throw new AvocadoException("Oops! Event format should be: event <task> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            }
            try {
                return new AddCommand(new Event(parts[0].substring(6).trim(), parts[1], parts[2]));
            } catch (Exception e) {
                throw new AvocadoException("Oops! Date format should be: yyyy-MM-dd HHmm");
            }
        } else {
            return new InvalidCommand();
        }
    }
}
