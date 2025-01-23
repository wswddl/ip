import java.util.ArrayList;
import java.util.Scanner;

public class MiloIce {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void printStraightLine() {
        int lineLength = 50;
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        String logo = " __    __   __   __        ____       _______    _____   ______        \n"
             + "|  \\  /  | |  | |  |      / __ \\     |__   __|  /   __| |  ____|       \n"
             + "|   \\/   | |  | |  |     | /  \\ |       | |    |   /    | |____        \n"
             + "|   ||   | |  | |  |     | |  | |       | |    |  |     |  ____|       \n"
             + "|   ||   | |  | |  |___  | \\__/ |     __| |__  |   \\__  | |____        \n"
             + "|___||___| |__| |______|  \\____/     |_______|  \\_____| |______|       ";
        System.out.println(logo);
        printStraightLine();
        System.out.println("Hello! I'm Milo Ice\n" + "What can I do for you?");
        printStraightLine();

        // Scanning for input
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (input.equals("bye")) {
                printStraightLine();
                System.out.println("Bye. Hope to see you again soon!");
                printStraightLine();
                break;
            } else if (input.equals("list")) {
                printStraightLine();
                int idx = 1;
                for (Task task : taskList) {
                    System.out.println(idx + "." + task);
                    idx++;
                }
                printStraightLine();
            } else if (parts.length == 2 && parts[0].equals("mark")) {
                int idx = Integer.parseInt(parts[1]);
                if (idx >= 1 && idx <= taskList.size()) {
                    Task task = taskList.get(idx - 1);
                    task.markAsDone();
                    printStraightLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                    printStraightLine();
                } else {
                    System.out.println("Invalid index");
                }

            } else if (parts.length == 2 && parts[0].equals("unmark")) {
                int idx = Integer.parseInt(parts[1]);
                if (idx >= 1 && idx <= taskList.size()) {
                    Task task = taskList.get(idx - 1);
                    task.unMarkAsDone();
                    printStraightLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                    printStraightLine();
                } else {
                    System.out.println("Invalid index");
                }

            } else if (parts.length > 1) {
                if (parts[0].equals("todo")) {
                    String description = input.substring(5).trim();
                    Todo todo = new Todo(description);
                    taskList.add(todo);

                    printStraightLine();
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todo);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list");
                    printStraightLine();
                } else if (parts[0].equals("deadline")) {
                    int byIdx = input.indexOf(" /by ");
                    if (byIdx != -1) {
                        String description = input.substring(9, byIdx).trim();
                        String deadline = input.substring(byIdx + 5).trim();
                        Deadline dl = new Deadline(description, deadline);
                        taskList.add(dl);

                        printStraightLine();
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(dl);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list");
                        printStraightLine();
                    } else {
                        System.out.println("Please specify the deadline after /by");
                    }
                } else if (parts[0].equals("event")) {
                    int fromIdx = input.indexOf(" /from ");
                    int toIdx = input.indexOf(" /to ");
                    if (fromIdx != 1 && toIdx != -1) {
                        String description = input.substring(6, fromIdx).trim();
                        String start = input.substring(fromIdx + 7, toIdx).trim();
                        String end = input.substring(toIdx + 5).trim();
                        Event event = new Event(description, start, end);
                        taskList.add(event);

                        printStraightLine();
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(event);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list");
                        printStraightLine();
                    } else {
                        System.out.println("Please specify the start date using /from and end date using /to");
                    }
                }
            } else {
                taskList.add(new Task(input));
                printStraightLine();
                System.out.println("Sorry, I don't understand your request"); // echo
                printStraightLine();
            }
        }
        scanner.close();
    }
}

