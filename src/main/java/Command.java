public enum Command {
    LIST,
    BYE,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    UNKNOWN;

    /*
     * Factory method for Command.
     *
     * @return Command.BYE, Command.LIST only if the input is exactly "bye", "list".
     *         return the corresponding Command enum if the first word of the input matches the enums values,
     *         or else return Command.UNKNOWN
     */
    public static Command of(String input) {
        String[] parts = input.split(" ");

        try {
            if (parts[0].equals("bye") || parts[0].equals("list")) {
                return Command.valueOf(input.toUpperCase());
            } else {
                return Command.valueOf(parts[0].toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
