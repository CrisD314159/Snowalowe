package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSnowAlowe extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bienvenid@ a Snowalowe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Snowalowe snowalowe1 = Snowalowe.getSnowalowe();
        Snowalowe snowalowe2 = Snowalowe.getSnowalowe();
        System.out.println(snowalowe1.hashCode());
        System.out.println(snowalowe2.hashCode());
    }


}