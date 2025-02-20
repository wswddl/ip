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
    GREET,
    RESCHEDULE;

    /**
     * Factory method for Command.
     *
     * @return Command.BYE, Command.LIST only if the input is exactly "bye", "list".
     *         return the corresponding Command enum if the first word of the input matches the enums values,
     *         or else return Command.UNKNOWN.
     */
    public static Enum of(String input) {
        String[] parts = input.split(" ");
        String firstWord = parts[0];
        try {
            if (firstWord.equalsIgnoreCase("hi")
                    || firstWord.equalsIgnoreCase("hello")
                    || firstWord.equalsIgnoreCase("nihao")
                    || firstWord.equalsIgnoreCase("helo")
                    || firstWord.equalsIgnoreCase("wassup")
                    || firstWord.equalsIgnoreCase("hei")
                    || firstWord.equalsIgnoreCase("heii")) {
                return Enum.GREET;
            } else if (firstWord.equalsIgnoreCase("res")
                    || firstWord.equalsIgnoreCase("reschedule")) {
                return Enum.RESCHEDULE;
            } else if (firstWord.equalsIgnoreCase("ls")
                    || firstWord.equalsIgnoreCase("list")) {
                return Enum.LIST;
            } else {
                return Enum.valueOf(firstWord.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}

