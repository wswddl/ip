package milo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import milo.task.Deadline;
import milo.task.Event;
import milo.task.Task;
import milo.task.TaskList;
import milo.task.Todo;

public class Storage {
    private final String FILE_PATH;
    private final File file;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
        this.file = new File(filePath);
        File folder = new File(file.getParent());
        try {
            if (!folder.exists()) {
                folder.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void loadTask(TaskList tasks) {
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
                        tasks.addTask(todo);
                    } catch (MiloIceException e) {
                        System.out.println("Corrupted task detected");
                    }
                } else if (parts[0].equals("D") && parts.length == 4) {
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    String deadline = parts[3];
                    try {
                        Task dl = Deadline.of(description, isDone, deadline);
                        tasks.addTask(dl);
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
                        tasks.addTask(event);
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

    public void updateTask(TaskList tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks.getList()) {
                writer.write(task.toTextFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
