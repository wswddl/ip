import java.util.ArrayList;
import java.util.Scanner;

public class MiloIce {

    private static ArrayList<String> storedInputsArr = new ArrayList<>();

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
            if (input.equals("bye")) {
                printStraightLine();
                System.out.println("Bye. Hope to see you again soon!");
                printStraightLine();
                break;
            } else if (input.equals("list")) {
                printStraightLine();
                int idx = 1;
                for (String storedInput : storedInputsArr) {
                    System.out.println(idx + ". " + storedInput);
                    idx++;
                }
                printStraightLine();
            } else {
                storedInputsArr.add(input);
                printStraightLine();
                System.out.println("added: " + input); // echo
                printStraightLine();
            }
        }
        scanner.close();
    }
}
