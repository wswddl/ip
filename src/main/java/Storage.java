import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    //private final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/savedTasks.txt";
    //private final ArrayList<Task> taskList = new ArrayList<>();
    private final File file;

    public Storage() {
        this.file = new File(FILE_PATH);
        File folder = new File(file.getParent());
        try {
            if (!folder.exists()) {
                folder.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void loadTask(ArrayList<Task> taskList) {
        try {
            Scanner scanner = new Scanner(file);
            while (file.exists() && scanner.hasNextLine()) {
                String savedTextLine = scanner.nextLine();
                String[] parts = savedTextLine.split(" \\| ");

                if (parts[0].equals("T") && parts.length == 3) {
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    try {
                        Task todo = Todo.of(description, isDone);
                        taskList.add(todo);
                    } catch (MiloIceException e) {
                        System.out.println("Corrupted task detected");
                    }
                } else if (parts[0].equals("D") && parts.length == 4) {
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    String deadline = parts[3];
                    try {
                        Task dl = Deadline.of(description, isDone, deadline);
                        taskList.add(dl);
                    } catch (MiloIceException e) {
                        System.out.println("Corrupted task detected");
                    }
                } else if (parts[0].equals("E") && parts.length == 5) {
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    String start = parts[3];
                    String end = parts[4];
                    try {
                        Task event = Event.of(description, isDone, start, end);
                        taskList.add(event);
                    } catch (MiloIceException e) {
                        System.out.println("Corrupted task detected");
                    }

                }
                    // ignore the corrupted task and move to the next one
                    // System.out.println("corrupted");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void updateTask(ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : taskList) {
                writer.write(task.toTextFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        ArrayList<Task> arr = new ArrayList<>();
        Storage storage = new Storage();
        storage.loadTask(arr);
        for (Task t : arr) {
            System.out.println(t);
        }
        System.out.println("----------------");
        arr.remove(0);
        storage.updateTask(arr);
        Task todo = new Todo("Haha", true);
        Task todo1 = new Todo("Haha1", false);
        arr.add(todo);
        arr.add(todo1);

    }
}
