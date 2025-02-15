package milo;

import java.util.ArrayList;
import java.util.Scanner;

import milo.task.Task;
import milo.task.TaskList;

/**
 * Represents the user interface for interacting with the MiloIce application.
 * It handles the display of messages and the reading of user input.
 */
public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    /**
     * Print 50 wide underscored dashed line
     */
    public void printStraightLine() {
        int lineLength = 50;
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }
        System.out.println();
    }
    /**
     * Displays the welcome message and introductory information about the application.
     */
    public void showWelcome() {
        String logo = " __    __   __   __        ____       _______    _____   ______    \n"
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
    /**
     * Reads and returns a line of user input from the console.
     *
     * @return The input line from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
    /**
     * Displays a goodbye message when the application exits.
     */
    public void sayGoodbye() {
        printStraightLine();
        System.out.println("Bye. Hope to see you again soon!");
        printStraightLine();
    }
    /**
     * Displays the current list of tasks in the TaskList.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void listTasksUi(TaskList tasks) {
        printStraightLine();
        int idx = 1;
        for (Task task : tasks.getList()) {
            System.out.println(idx + "." + task);
            idx++;
        }
        printStraightLine();
    }
    /**
     * Displays a message when a task is deleted from the TaskList.
     *
     * @param removedTask The task that was removed.
     * @param tasks The TaskList after the task has been removed.
     */
    public void deleteTaskUi(Task removedTask, TaskList tasks) {
        printStraightLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list");
        printStraightLine();
    }
    /**
     * Displays a message when a task is marked as done.
     *
     * @param markTask The task that was marked as done.
     */
    public void markTaskUi(Task markTask) {
        printStraightLine();
        System.out.println("  " + markTask);
        printStraightLine();
    }
    /**
     * Displays a message when a task is unmarked.
     *
     * @param unmarkTask The task that was unmarked.
     */
    public void unmarkTaskUi(Task unmarkTask) {
        printStraightLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + unmarkTask);
        printStraightLine();
    }
    /**
     * Displays a message when a task is added to the TaskList.
     *
     * @param addedTask The task that was added.
     * @param tasks The TaskList after the task has been added.
     */
    public void addTaskUi(Task addedTask, TaskList tasks) {
        printStraightLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(addedTask);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list");
        printStraightLine();
    }
    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        printStraightLine();
        System.out.println(errorMessage);
        printStraightLine();
    }
    /**
     * Displays the tasks that match the specified keyword.
     * If no matching tasks are found, displays a message indicating so.
     *
     * @param listOfTasksWithKeyword The list of tasks that contain the keyword.
     */
    public void findKeywordUi(ArrayList<Task> listOfTasksWithKeyword) {
        if (!listOfTasksWithKeyword.isEmpty()) {
            printStraightLine();
            System.out.println("Here are the matching tasks in your list:");
            int idx = 1;
            for (Task task : listOfTasksWithKeyword) {
                System.out.println(idx + "." + task);
                idx++;
            }
            printStraightLine();
        } else {
            printStraightLine();
            System.out.println("Sorry, there is no matching tasks in your list");
            printStraightLine();
        }
    }
}
