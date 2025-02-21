package milo;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private MiloIce miloIce = new MiloIce("./data/savedTasks.txt");

    @Override
    public void start(Stage stage) {
        final int minHeight = 220;
        final int minWidth = 417;
        try {
            // change icon to milo ice image
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/DaMiloIce.png")));
            stage.setMinHeight(minHeight);
            stage.setMinWidth(minWidth);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Milo Ice");
            fxmlLoader.<MainWindow>getController().setMiloIce(miloIce);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeApplication() {
        Platform.exit();
    }
}

