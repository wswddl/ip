package milo;

import milo.task.Task;
import milo.task.TaskList;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /*
     * Print 50 wide underscored dashed line
     */
    public void printStraightLine() {
        int lineLength = 50;
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }
        System.out.println();
    }
    public void showWelcome() {
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
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void sayGoodbye() {
        printStraightLine();
        System.out.println("Bye. Hope to see you again soon!");
        printStraightLine();
    }

    public void listTasksUi (TaskList tasks) {
        printStraightLine();
        int idx = 1;
        for (Task task : tasks.getList()) {
            System.out.println(idx + "." + task);
            idx++;
        }
        printStraightLine();
    }

    public void deleteTaskUi(Task removedTask, TaskList tasks) {
        printStraightLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list");
        printStraightLine();
    }

    public void markTaskUi(Task markTask) {
        printStraightLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + markTask);
        printStraightLine();
    }

    public void unmarkTaskUi(Task unmarkTask) {
        printStraightLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + unmarkTask);
        printStraightLine();
    }

    public void addTaskUi(Task addedTask, TaskList tasks) {
        printStraightLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(addedTask);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list");
        printStraightLine();
    }


    public void showError(String errorMessage) {
        printStraightLine();
        System.out.println(errorMessage);
        printStraightLine();
    }
}
