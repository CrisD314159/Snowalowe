package controllers;

import application.MainSnowAlowe;
import exceptions.VendedorException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Vendedor;

import java.io.IOException;

public class LoginController implements Alerta {

    MainSnowAlowe main;

    ModelFactoryController singleton = ModelFactoryController.getInstance();

    @FXML
    private Label SesionLabel;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button logButton;

    @FXML
    private Label noAccountLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button loginAdminButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button resetpasswordButton;

    @FXML
    private TextField userField;

    @FXML
    private Label userLabel;

    @FXML
    void crearCuentaEvent(ActionEvent event) throws IOException {
        crearCuentaAction();
    }

    private void crearCuentaAction() throws IOException {
        main.abrirCrearCuenta();
    }

    @FXML
    void loginEvent(ActionEvent event) throws VendedorException, IOException {
        loginAction();

    }



    @FXML
    void restablecerCalveEvent(ActionEvent event) {
    main.abrirRecuperarContrasenia();

    }



    public void onCloseAction(ActionEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    private void loginAction() throws IOException {
        String usuario = "";
        String contrasenia = "";
        usuario = userField.getText();
        contrasenia= passwordField.getText();
        if(verificarCampos(usuario, contrasenia)){
            if(singleton.verificarCuenta(usuario, contrasenia)){
                Vendedor vendedorLogeado = singleton.buscarVendedor(usuario, contrasenia) ;
                main.abrirPanelVendedor(vendedorLogeado);
            }else {
                Alerta.saltarAlerta("Error!", "Datos incorrectos, vuelvelo a intentar");
            }

        }else{
            Alerta.saltarAlerta("Error!", "Verifica los campos obligatorios y vuelve a intentar");
        }
    }

    private boolean verificarCampos(String usuario, String contrasenia) {
        if(usuario.equals("")){
            return false;
        }
        if(contrasenia.equals("")){
            return false;
        }
        return true;
    }

    @FXML
    void loginAdmin(ActionEvent event) {
        main.abrirLoginAdministrador();

    }

    public void setMain(MainSnowAlowe mainSnowAlowe) {
        this.main = mainSnowAlowe;
    }

    @FXML
    void initialize() {




    }


}