package controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public interface Alerta {
    static void saltarAlerta(String header, String contenido){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        //DialogPane dialogPane = alert.getDialogPane();
        //dialogPane.getStylesheets().add(getClass().getResource("../stylesheets/AlertsStylesheets.css").toExternalForm());
        //dialogPane.getStyleClass().add("dialog");
        alert.showAndWait();
    }
}
