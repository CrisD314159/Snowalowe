package controllers;

import application.MainSnowAlowe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Administrador;

import java.io.IOException;

public class AdministradorLoginController {

    MainSnowAlowe main;

    Administrador administradorLogeado;

    ModelFactoryController singleton = ModelFactoryController.getInstance();

    @FXML
    private Button backButton;

    @FXML
    private Button logIngAdmin;

    @FXML
    private PasswordField passwoerField;

    @FXML
    private TextField userField;

    @FXML
    private Label welcomebackAdmin;

    @FXML
    void backEvent(ActionEvent event) throws IOException {
        main.inicializarLogin();
    }

    @FXML
    void logInEvent(ActionEvent event) {
        String user = "";
        String password = "";
        user = userField.getText();
        password = passwoerField.getText();
        if(verificarCampos(user, password)){
            boolean verificar = singleton.verificarUserAdministrador(user, password);
            if(verificar){
                administradorLogeado = singleton.obtenerAdministrador(user, password);
                main.abrirPanelAdministrador(administradorLogeado);
            }else {
                Alerta.saltarAlerta("Error", "Verifica los datos ingresados y vuelvelo a intentar");
            }

        }else {
            Alerta.saltarAlerta("Aviso","Completa los campos requeridos");
        }

    }

    private boolean verificarCampos(String user, String password) {
        if(user.equals("")){
            return false;
        }
        if(password.equals("")){
            return false;
        }
        return true;

    }

    public void setMain(MainSnowAlowe mainSnowAlowe) {
        this.main = mainSnowAlowe;
    }
}
