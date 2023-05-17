package controllers;


import application.MainSnowAlowe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Mensaje;
import model.Vendedor;

import java.io.IOException;

public class MensajesENtrantesController {

    ObservableList<Mensaje> listaMensajesData = FXCollections.observableArrayList();

    Mensaje mensajeSeleccionado;

    Vendedor autor;

    Vendedor vendedorLogeado;

    MainSnowAlowe main;

    ModelFactoryController singleton;
    @FXML
    private TableColumn<Mensaje, String> autorColum;

    @FXML
    private Button irAtras;

    @FXML
    private TableColumn<Mensaje, String> mensajeColum;

    @FXML
    private TextField mensajeField;

    @FXML
    private Button responderButton;

    @FXML
    private TableView<Mensaje> tableMensajes;

    @FXML
    private Label mensajeLabel;


    @FXML
    private Label nombreAutorLabel;


    @FXML
    void irAtras(ActionEvent event) throws IOException {
        main.abrirPanelVendedor(vendedorLogeado);

    }

    @FXML
    void responderMensaje(ActionEvent event) {
        responderMensajeAction();

    }

    private void responderMensajeAction() {
        String mensaje = "";
        mensaje = mensajeField.getText();
        if(verificarCampos(mensaje)){
            boolean enviarMensaje = singleton.respoderMensaje(mensaje, vendedorLogeado, autor);
            if (enviarMensaje){
                Alerta.saltarAlerta("Listo", "Mensaje enviado");
            }else{
               Alerta.saltarAlerta("Error", "Imposible enviar el mensaje");
            }
        }else {
           Alerta.saltarAlerta("Aviso", "Ingresa un mensaje válido para continuar");
        }
    }

    private boolean verificarCampos(String mensaje) {
        if(mensaje.equals("")){
            return false;
        }
        return true;
    }

    @FXML
    void initialize(){
        this.mensajeColum.setCellValueFactory(new PropertyValueFactory<>("contenido"));
        this.autorColum.setCellValueFactory(new PropertyValueFactory<>("autor"));


        tableMensajes.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            mensajeSeleccionado = newSelection;
            mostrarDatosMensaje(mensajeSeleccionado);

        });

    }

    private void mostrarDatosMensaje(Mensaje mensajeSeleccionado) {
        String nombreAutor = mensajeSeleccionado.getIdAutor();
        mensajeLabel.setText(mensajeSeleccionado.getDescripcion());
        nombreAutorLabel.setText(nombreAutor);
    }

    private ObservableList<Mensaje> obtenerListaMensajes() {
        listaMensajesData.addAll(singleton.obtenerListaMensajes(vendedorLogeado));
        return listaMensajesData;
    }


    public void obtenerVendedorLogeado(Vendedor vendedorLogeado) {
        this.vendedorLogeado = vendedorLogeado;
    }

    public void setMain(MainSnowAlowe main) {
        this.main = main;
        this.singleton = ModelFactoryController.getInstance();
        tableMensajes.getItems().clear();
        tableMensajes.setItems(obtenerListaMensajes());

    }
}
