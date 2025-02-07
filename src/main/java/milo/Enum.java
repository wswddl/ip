package milo;

/**
 * Enum representing commands in the application.
 * Commands include listing, marking, unmarking, adding Todo task,
 * add Deadline task, Event task, deleting task, finding keyword and exiting the application.
 * The Enum also provides factory method to determine the correct command from a user input.
 */
public enum Enum {

    LIST,
    BYE,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    FIND,
    UNKNOWN,
    GREET;

    /**
     * Factory method for Command.
     *
     * @return Command.BYE, Command.LIST only if the input is exactly "bye", "list".
     *         return the corresponding Command enum if the first word of the input matches the enums values,
     *         or else return Command.UNKNOWN.
     */
    public static Enum of(String input) {
        String[] parts = input.split(" ");

        try {
            if (parts[0].equals("bye") || parts[0].equals("list")) {
                return Enum.valueOf(input.toUpperCase());
            } else if (parts[0].equalsIgnoreCase("hi") || parts[0].equalsIgnoreCase("hello")) {
                return Enum.GREET;
            } else {
                return Enum.valueOf(parts[0].toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}

