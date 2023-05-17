package controllers;

import application.MainSnowAlowe;
import exceptions.VendedorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Vendedor;

import java.io.IOException;
import java.util.Objects;


public class RecomendacionVendedoresAliadosController {

    MainSnowAlowe main = new MainSnowAlowe();
    ObservableList<Vendedor> listaVendedorData= FXCollections.observableArrayList();
    Vendedor vendedorSeleccionado = null;

    ModelFactoryController singleton = ModelFactoryController.getInstance();
    private Vendedor vendedorLogeado;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnCambiarPagina;

    @FXML
    private Button btnEnviarSolicitud;

    @FXML
    private TableColumn<Vendedor, String> columnApellidoRecomendacion;

    @FXML
    private TableColumn<Vendedor, String> columnNombreRecomendacion;

    @FXML
    private TableView<Vendedor> tableRecomendaciones;


    @FXML
    private TextField txtFiltroMeGusta;

    @FXML
    void cambiarSolicitudes(ActionEvent event) throws IOException, VendedorException {
        main.mostrarSolicitudes(vendedorLogeado);
    }
    @FXML
    void buscarVendedor(ActionEvent event) {
        buscarVendedorAction();
    }


    private void buscarVendedorAction() {
        singleton.actualizarTablaRecomendados(vendedorLogeado);
        listaVendedorData.removeAll(singleton.obtenerListaVendedoresRecomendados(vendedorLogeado));
        tableRecomendaciones.refresh();
        aniadirTabla();
        tableRecomendaciones.refresh();
    }

    private void aniadirTabla() {
        listaVendedorData.addAll(singleton.obtenerListaVendedoresRecomendados(vendedorLogeado));
        tableRecomendaciones.setItems(listaVendedorData);
    }


    @FXML
    void enviarSolicitud(ActionEvent event) {
        enviarSolicitudAction();
    }

    private void enviarSolicitudAction() {
        if(vendedorSeleccionado == null){
            Alerta.saltarAlerta("Aviso", "Debes seleccionar un vendedor válido");
        }else{
            boolean enviarSolicitud =  singleton.enviarSolicitud(vendedorLogeado, vendedorSeleccionado);

            if(!enviarSolicitud){
                Alerta.saltarAlerta("Aviso", "El vendedor ya cuenta con una solicitud");
            }else{
                Alerta.saltarAlerta("Listo", "La solicitud fue enviada");
                listaVendedorData.remove(vendedorSeleccionado);
                tableRecomendaciones.refresh();
            }
        }
    }

    @FXML
    void regresar(ActionEvent event) throws IOException {
        main.abrirPanelVendedor(vendedorLogeado);

    }

    @FXML
    void initialize(){
        this.columnNombreRecomendacion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnApellidoRecomendacion.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tableRecomendaciones.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            vendedorSeleccionado = newSelection;
        });
    }

    public void setMain(MainSnowAlowe main) throws VendedorException {
        this.main = main;
        tableRecomendaciones.getItems().clear();
        tableRecomendaciones.setItems(obtenerListaVendedoresRecomendados());
    }

    private ObservableList<Vendedor> obtenerListaVendedoresRecomendados() throws VendedorException {
        listaVendedorData.addAll(singleton.obtenerListaVendedoresRecomendados(vendedorLogeado));
        return listaVendedorData;
    }


    public void obtenerVendedorLogeado(Vendedor vendedorLogeado) {
        this.vendedorLogeado = vendedorLogeado;
    }


}
