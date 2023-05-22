package controllers;

import application.MainSnowAlowe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RestablecerContraseniaController {

    MainSnowAlowe main;

    ModelFactoryController singleton = ModelFactoryController.getInstance();
    @FXML
    private Button aceptButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField cedulaField;

    @FXML
    void back(ActionEvent event) throws IOException {
        main.inicializarLogin();

    }

    @FXML
    void encontrarCedula(ActionEvent event) throws IOException {
        String cedula = "";
        cedula = cedulaField.getText();
        if(!(cedula.equals(""))){
            String contrasenia = singleton.encontrarContrasenia(cedula);
            if (contrasenia!=null){
                Alerta.saltarAlerta("Listo", "Tu contrasenia es "+ contrasenia);
                cedulaField.setText("");
                main.inicializarLogin();
            }else {
                Alerta.saltarAlerta("Error", "Se produjo un error");
            }
        }else {
           Alerta.saltarAlerta("Aviso", "Rellena los campos requeridos");
        }

    }

    public void setMain(MainSnowAlowe mainSnowAlowe) {
        this.main = mainSnowAlowe;
    }
}
