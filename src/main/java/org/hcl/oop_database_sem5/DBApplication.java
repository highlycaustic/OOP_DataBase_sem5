package org.hcl.oop_database_sem5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// TODO: иконка, ксс стиль
public class DBApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DBApplication.class.getResource("db-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 340);
        stage.setTitle("Простая БД");
        stage.setScene(scene);
        stage.show();
    }

    public static void start(String[] args) {
        launch();
    }
}