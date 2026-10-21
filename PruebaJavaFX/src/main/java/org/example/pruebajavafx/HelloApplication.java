
package org.example.pruebajavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource("Welcome-view.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("CMD for Dummies");

        stage.getIcons().add(
                new Image(
                        Objects.requireNonNull(
                                HelloApplication.class.getResourceAsStream("Icono.png")
                        )
                )
        );

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
