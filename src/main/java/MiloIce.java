import java.util.ArrayList;
import java.util.Scanner;

public class MiloIce {

    private static final ArrayList<Task> taskList = new ArrayList<>();

    /*
     * Print 50 wide underscored dashed line
     */
    public static void printStraightLine() {
        int lineLength = 50;
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        String logo = " __    __   __   __        ____       _______    _____   ______ \n"
             + "|  \\  /  | |  | |  |      / __ \\     |__   __|  /   __| |  ____|     \n"
             + "|   \\/   | |  | |  |     | /  \\ |       | |    |   /    | |____      \n"
             + "|   ||   | |  | |  |     | |  | |       | |    |  |     |  ____|       \n"
             + "|   ||   | |  | |  |___  | \\__/ |     __| |__  |   \\__  | |____      \n"
             + "|___||___| |__| |______|  \\____/     |_______|  \\_____| |______|       ";
        System.out.println(logo);
        printStraightLine();
        System.out.println("Hello! I'm Milo Ice\n" + "What can I do for you?");
        printStraightLine();

        // Scanning for input
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            try {
                input = scanner.nextLine();
                String[] parts = input.split(" ");
                Command cmd = Command.of(input);

                if (cmd == Command.BYE) {
                    printStraightLine();
                    System.out.println("Bye. Hope to see you again soon!");
                    printStraightLine();
                    break;
                } else if (cmd == Command.LIST) {
                    printStraightLine();
                    int idx = 1;
                    for (Task task : taskList) {
                        System.out.println(idx + "." + task);
                        idx++;
                    }
                    printStraightLine();
                } else if (cmd == Command.DELETE) {
                    if (parts.length != 2) {
                        throw new MiloIceException("Invalid input, correct example: delete 1");
                    }

                    int idx = Integer.parseInt(parts[1]); // might throw NumberFormatException
                    if (idx < 1 || idx > taskList.size()) {
                        throw new MiloIceException("Invalid index: there are only " + taskList.size() + " task(s)");
                    } else {
                        Task removedTask = taskList.remove(idx - 1);
                        printStraightLine();
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + removedTask);
                        System.out.println("Now you have " + taskList.size() + " task(s) in the list");
                        printStraightLine();
                    }
                } else if (cmd == Command.MARK) {
                    if (parts.length != 2) {
                        throw new MiloIceException("Invalid input, correct example: mark 1");
                    }

                    int idx = Integer.parseInt(parts[1]); // might throw NumberFormatException
                    if (idx < 1 || idx > taskList.size()) {
                        throw new MiloIceException("Invalid index: there are only " + taskList.size() + " task(s)");
                    } else {
                        Task task = taskList.get(idx - 1);
                        if (!task.markAsDone()) {
                            throw new MiloIceException("Task has already been marked before");
                        }
                        printStraightLine();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + task);
                        printStraightLine();
                    }

                } else if (cmd == Command.UNMARK) {
                    if (parts.length != 2) {
                        throw new MiloIceException("Invalid input, correct example: unmark 1");
                    }

                    int idx = Integer.parseInt(parts[1]);
                    if (idx < 1 || idx > taskList.size()) {
                        throw new MiloIceException("Invalid index: there are only " + taskList.size() + " task(s)");
                    } else {
                        Task task = taskList.get(idx - 1);
                        if (!task.unMarkAsDone()) {
                            throw new MiloIceException("Task isn't marked");
                        }
                        printStraightLine();
                        System.out.println("Nice! I've unmarked this task:");
                        System.out.println("  " + task);
                        printStraightLine();
                    }

                } else if (cmd == Command.TODO) {
                    int byIdx = input.indexOf("/by");
                    int fromIdx = input.indexOf("/from");
                    int toIdx = input.indexOf("/to");
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new MiloIceException("Invalid input: description of todo cannot be empty");
                    } // dont allow /by /from /to in To_do
                    else if (byIdx != -1 || fromIdx != -1 || toIdx != -1) {
                        throw new MiloIceException("Invalid input: Todo should not contain '/by', '/from', '/to'");
                    } else {
                        Todo todo = new Todo(description);
                        taskList.add(todo);

                        printStraightLine();
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(todo);
                        System.out.println("Now you have " + taskList.size() + " task(s) in the list");
                        printStraightLine();
                    }
                } else if (cmd == Command.DEADLINE) {
                    int byIdx = input.indexOf(" /by ");

                    if (byIdx != -1) {
                        String description = input.substring(8, byIdx).trim();
                        String deadline = input.substring(byIdx + 4).trim();
                        if (description.isEmpty() || deadline.isEmpty()) {
                            throw new MiloIceException("Invalid input: description and deadline cannot be empty");
                        }
                        Deadline dl = new Deadline(description, deadline);
                        taskList.add(dl);

                        printStraightLine();
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(dl);
                        System.out.println("Now you have " + taskList.size() + " task(s) in the list");
                        printStraightLine();
                    } else {
                        throw new MiloIceException(
                                "Please specify the deadline using '/by'\n" +
                                "Example: deadline finish cs2103t project /by [time]");
                    }
                } else if (cmd == Command.EVENT) {
                    int fromIdx = input.indexOf(" /from ");
                    int toIdx = input.indexOf(" /to ");
                    if (fromIdx != -1 && toIdx != -1) {
                        String description = input.substring(5, fromIdx).trim();
                        String start = input.substring(fromIdx + 6, toIdx).trim();
                        String end = input.substring(toIdx + 4).trim();
                        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                            throw new MiloIceException("Invalid input: description, start and end cannot be empty");
                        }
                        Event event = new Event(description, start, end);
                        taskList.add(event);

                        printStraightLine();
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(event);
                        System.out.println("Now you have " + taskList.size() + " task(s) in the list");
                        printStraightLine();
                    } else {
                        throw new MiloIceException(
                                "Please specify the start date using '/from' and end date using '/to'\n" +
                                        "Example: event [description] /from [start] /to [end]");
                    }

                } else { // Command.UNKNOWN
                    throw new MiloIceException("""
                            Sorry, I don't understand your request
                            Here's what I can do for you:
                            
                            1. list: view all tasks
                            2. bye: Exit chatbot
                            3. mark: mark the task with the given index (mark 2)
                            4. unmark: opposite of mark (unmark 3)
                            5. todo: add todo task (todo finish cs2103t)
                            6. deadline: add a task with a deadline (deadline cs2103t /by [deadline])
                            7. event: add a task with start & end time (event cs2103t /from [start] /to [end])""");
                }
            } catch (MiloIceException e) {
                printStraightLine();
                System.out.println(e.getMessage());
                printStraightLine();
            } catch (NumberFormatException e) {
                printStraightLine();
                System.out.println("Input must be a number (mark 1 / unmark 1)");
                printStraightLine();
            }

        }
        scanner.close();
    }
}

