package controllers;


import application.MainSnowAlowe;
import exceptions.CuentaException;
import exceptions.VendedorException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Vendedor;

import java.io.IOException;

public class CrearCuentaController implements Alerta{

    ModelFactoryController singleton;
    MainSnowAlowe main;

    @FXML
    private TextField apellidoField;

    @FXML
    private Label apellidoLabel;

    @FXML
    private Button atrasButton;

    @FXML
    private TextField cedulaField;

    @FXML
    private Label cedulaLabel;

    @FXML
    private PasswordField contraseniaField;

    @FXML
    private Label contraseniaLabel;

    @FXML
    private Button crearCuentaButton;

    @FXML
    private Label crearCuentaLabel;

    @FXML
    private TextField direccionField;

    @FXML
    private Label direccionLabel;

    @FXML
    private TextField nombreField;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label usuarioLabel;

    @FXML
    private TextField ususarioField;




    @FXML
    void IrAtrasEvent(ActionEvent event) throws IOException {
        IrAtrasAction();

    }

    private void IrAtrasAction() throws IOException {
        main.inicializarLogin();
    }

    @FXML
    void crearCuentaEvent(ActionEvent event) throws VendedorException, CuentaException, IOException {
        String nombre = "";
        String apellido = "";
        String direccion = "";
        String cedula = "";
        String usuario = "";
        String contrasenia = "";

        nombre = nombreField.getText();
        apellido = apellidoField.getText();
        cedula = cedulaField.getText();
        direccion = direccionField.getText();
        usuario = ususarioField.getText();
        contrasenia = contraseniaField.getText();
        if(verificarCampos(nombre, apellido, direccion, cedula, usuario, contrasenia)){
            Vendedor crearUsuario = singleton.crearVendedor(nombre, apellido, direccion, cedula, usuario, contrasenia);

            if(crearUsuario != null){
                Alerta.saltarAlerta("Listo!", "La cuenta ha sido creada exitosamente");
                main.inicializarLogin();

            }else{
                Alerta.saltarAlerta("Error", "Hubo un error al crear tu cuenta, intenta nuevamente");
            }

        }else {
            Alerta.saltarAlerta("Error", "Rellena todos los campos requeridos y vuelvelo a intentar");
        }


    }

    private boolean verificarCampos(String nombre, String apellido, String direccion, String cedula, String usuario, String contrasenia) {
        if(nombre.equals("")){
            return false;
        }
        if(apellido.equals("")){
            return false;
        }
        if(direccion.equals("")){
            return false;
        }
        if(cedula.equals("")){
            return false;
        }
        if(usuario.equals("")){
            return false;
        }
        if(contrasenia.equals("")){
            return false;
        }
        return true;
    }


    public void setMain(MainSnowAlowe mainSnowAlowe) {
        this.main = mainSnowAlowe;
        this.singleton =ModelFactoryController.getInstance();
    }
}
