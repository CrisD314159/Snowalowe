package controllers;

import application.MainSnowAlowe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Alerta {

    MainSnowAlowe main;
    @FXML
    private Label SesionLabel;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button logButton;

    @FXML
    private Label noAccountLabel;

    @FXML
    private Label passwordField;

    @FXML
    private TextField passwordLabel;

    @FXML
    private Button resetpasswordButton;

    @FXML
    private TextField userField;

    @FXML
    private Label userLabel;

    @FXML
    void crearCuentaEvent(ActionEvent event) {
        crearCuentaAction();

    }

    private void crearCuentaAction() {
        main.abrirCrearCuenta();
    }

    @FXML
    void loginEvent(ActionEvent event) {
        loginAction();

    }

    private void loginAction() {
        String usuario = "";
        usuario = userField.getText();
        String contrasenia = "";
        contrasenia= passwordField.getText();

        if(verificarCampos(usuario,contrasenia)){
            boolean acceso = main.verificarUsuario(usuario, contrasenia);
            if(acceso){
                main.abrirPanelVendedor(usuario);
            }else {
                Alerta.saltarAlerta("Error!", "Datos incorrectos, vuelvelo a intentar");
            }
        }else{
            Alerta.saltarAlerta("Error!", "Datos incompletos, vuelvelo a intentar");
        }
    }

    private boolean verificarCampos(String usuario, String contrasenia) {
        if(usuario == "" && contrasenia== ""){
            return false;
        }
        return false;
    }

    @FXML
    void restablecerCalveEvent(ActionEvent event) {

    }


}