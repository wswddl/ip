package milo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import milo.task.Task;
import milo.task.TaskList;

/**
 * Handles loading and saving tasks to a file. It ensures that the file structure
 * is created and maintained, and that tasks can be read from or written to a storage file.
 */
public class Storage {
    private final String file_Path;
    private final File file;

    /**
     * Constructs a Storage object with the given file path. Ensures that the necessary
     * folder and file exist, creating them if necessary.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.file_Path = filePath;
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

    /**
     * Loads tasks from the storage file into the given TaskList.
     * It processes each line in the file and adds corresponding tasks (Todo, Deadline, Event) to the TaskList.
     * Corrupted files are ignored, and the number of corrupted tasks is printed.
     *
     * @param tasks The TaskList to load tasks into.
     */
    public void loadTask(TaskList tasks) {
        int numOfCorruptedFiles = Parser.parseFileAndLoadTask(this.file, tasks);
        if (numOfCorruptedFiles <= 1) {
            System.out.println("There is " + numOfCorruptedFiles + " corrupted file");
        } else {
            System.out.println("There are " + numOfCorruptedFiles + " corrupted file(s)");
        }
    }

    /**
     * Updates the storage file with the current state of tasks in the TaskList.
     *
     * @param tasks The TaskList containing the current tasks to be saved.
     */
    public void updateTask(TaskList tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_Path))) {
            for (Task task : tasks.getList()) {
                writer.write(task.toTextFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

}
