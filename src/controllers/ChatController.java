package controllers;


import application.MainSnowAlowe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import model.Vendedor;

import java.io.IOException;

public class ChatController {
    private Vendedor vendedorLogeado;
    private Vendedor vendedorAliado;
    MainSnowAlowe main;

    ModelFactoryController singleton;



    @FXML
    private Button btnEnviarMensaje;

    @FXML
    private Button btnRegresar;


    @FXML
    private TextField txtMensaje;

    @FXML
    void enviarMensaje(ActionEvent event) {
        String mensaje = "";
        mensaje = txtMensaje.getText();
        if(verificarCampos(mensaje)){
            boolean enviarMensaje = singleton.enviarMensaje(mensaje, vendedorLogeado, vendedorAliado);
            if (enviarMensaje){
              Alerta.saltarAlerta("Listo", "Mensaje Enviado");
              txtMensaje.clear();
            }else{
               Alerta.saltarAlerta("Error", "Imposible enviar el mensaje, intenta de nuevo más tarde");
            }
        }else {
            Alerta.saltarAlerta("Aviso", "Ingresa un mensaje válido antes de continuar");
        }

    }

    private boolean verificarCampos(String mensaje) {
        if(mensaje.equals("")){
            return false;
        }
        return true;
    }

    @FXML
    void regresar(ActionEvent event) throws IOException {
        main.mostrarMuroVendedorAliado(vendedorLogeado,vendedorAliado);

    }


    public void setMain(MainSnowAlowe main) {
        this.main = main;
        this.singleton= ModelFactoryController.getInstance();
    }

    public void obtenerVendedorLogeado(Vendedor vendedorLogeado) {
        this.vendedorLogeado=vendedorLogeado;
    }

    public void obtenerVendedorAliado(Vendedor vendedorAliado) {
        this.vendedorAliado=vendedorAliado;
    }
}
