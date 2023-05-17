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


public class SolicitudesController {

    MainSnowAlowe main = new MainSnowAlowe();
    ObservableList<Vendedor> listaVendedorData= FXCollections.observableArrayList();
    Vendedor vendedorSeleccionado = null;

    ModelFactoryController singleton= ModelFactoryController.getInstance();

    private Vendedor vendedorLogeado;

    @FXML
    private Button btnAceptarSolicitud;

    @FXML
    private Button btnRechazarSolicitud;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableColumn<Vendedor, String> columnApellido;

    @FXML
    private TableColumn<Vendedor, String> columnNombre;

    @FXML
    private TableView<Vendedor> tableListaSolicitudes;

    @FXML
    void aceptarSolicitud(ActionEvent event) {
        aceptarSolicitudAction();
    }

    private void aceptarSolicitudAction() {
        if(vendedorSeleccionado == null){
            Alerta.saltarAlerta("Aviso", "Debes seleccionar un vendedor válido para continuar");
        }else{
            singleton.aceptarSolicitud(vendedorLogeado, vendedorSeleccionado);
            Alerta.saltarAlerta("Listo", "La solicitud fue aceptada");
            listaVendedorData.remove(vendedorSeleccionado);
            tableListaSolicitudes.refresh();
        }
    }

    @FXML
    void rechazarSolicitud(ActionEvent event) {
        if(vendedorSeleccionado == null){
            Alerta.saltarAlerta("Aviso", "Debes seleccionar un vendedor para continuar");
        }else{
            singleton.rechazarSolicitud(vendedorLogeado, vendedorSeleccionado);
            Alerta.saltarAlerta("Listo", "Se ha rechazado la solicitud exitosamente");
            listaVendedorData.remove(vendedorSeleccionado);
            tableListaSolicitudes.refresh();
        }
    }

    @FXML
    void regresar(ActionEvent event) throws IOException {
        main.mostrarRecomendaciones(vendedorLogeado);
    }

    @FXML
    void initialize(){
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tableListaSolicitudes.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            vendedorSeleccionado = newSelection;
        });
    }

    public void setMain(MainSnowAlowe main) throws VendedorException {
        this.main = main;
        tableListaSolicitudes.getItems().clear();
        tableListaSolicitudes.setItems(obtenerListaSolicitudes());
    }

    private ObservableList<Vendedor> obtenerListaSolicitudes() throws VendedorException {
        listaVendedorData.addAll(singleton.obtenerListaSolicitudes(vendedorLogeado));
        return listaVendedorData;
    }


    public void obtenerVendedorLogeado(Vendedor vendedorLogeado) {
        this.vendedorLogeado = vendedorLogeado;
    }





}
