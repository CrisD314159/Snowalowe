package controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.util.Objects;

public interface Alerta {
    static void saltarAlerta(String header, String contenido){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(Alerta.class.getResource("../stylesheets/AlertStyles.css")).toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        alert.showAndWait();
    }
}
