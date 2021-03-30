package FX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Парсер Яндекс Маркет");
        InputStream iconStream = getClass().getResourceAsStream("/icons/icon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/style/darkstyle.css");
        stage.setScene(scene);
        stage.show();

    }
}
