package controllers;

import application.MainSnowAlowe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Administrador;
import model.Cuenta;
import model.Vendedor;

public class PanelAdministradorController {

    MainSnowAlowe main;

    ModelFactoryController singleton = ModelFactoryController.getInstance();

    ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();

    Administrador administradorLogeado;

    Vendedor vendedorSeleccionado;


    @FXML
    private Label bienvenidaLabel;


    @FXML
    private Button cerrarSesionButton;

    @FXML
    private Button eliminarvendedorButton;

    @FXML
    private Label friendsLable;

    @FXML
    private Label idLabel;

    @FXML
    private Label likesLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TableColumn<Vendedor, String> nombreColum;

    @FXML
    private Label productsLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private TableColumn<Vendedor, String> surnameColum;

    @FXML
    private Label userLable;

    @FXML
    private Tab vendedoresTab;

    @FXML
    private TableView<Vendedor> vendedoresTable;

    @FXML
    void cerrarSesionEvent(ActionEvent event) {
        main.abrirLoginAdministrador();
    }
    @FXML
    void initialize(){
        this.nombreColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.surnameColum.setCellValueFactory(new PropertyValueFactory<>("apellido"));


        vendedoresTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            vendedorSeleccionado = newSelection;
            mostrarDatosVendedor(vendedorSeleccionado);
        });


    }

    @FXML
    void eliminarVendedorEvent(ActionEvent event) {
        String cedula = "";
        cedula = vendedorSeleccionado.getCedula();
        if(!(cedula.equals(""))){

            if (singleton.eliminarVendedor(cedula)){
                Alerta.saltarAlerta("Listo", "Vendedor eliminado");
                listaVendedoresData.remove(vendedorSeleccionado);
                limpiarCampos();
            }else {
                Alerta.saltarAlerta("Error", "Se produjo un error");
            }
        }else {
            Alerta.saltarAlerta("Aviso", "Selecciona un vendedor para continuar");
        }



    }

    private void limpiarCampos() {
        nameLabel.setText("");
        surnameLabel.setText("");
        userLable.setText("");
        idLabel.setText("");
        likesLabel.setText("");
        productsLabel.setText("");
        friendsLable.setText("");
    }

    public void setMain(MainSnowAlowe mainSnowAlowe) {
        this.main = mainSnowAlowe;
        vendedoresTable.getItems().clear();
        vendedoresTable.setItems(obtenerListaVendedores());
    }

    private void mostrarDatosVendedor(Vendedor vendedor){
        nameLabel.setText( vendedor.getNombre());
        surnameLabel.setText(vendedor.getApellido());
        userLable.setText(vendedor.getCuenta().getUsuario());
        idLabel.setText("Cédula: " + vendedor.getCedula());
        likesLabel.setText("Total de me gusta "+vendedor.getNumeroMegusta());
        productsLabel.setText("Cantidad de productos: "+ vendedor.getProductos().size());
        friendsLable.setText("Cantidad de amigos: "+ vendedor.getListaAmigos().size());

    }

    public void setAdministrador(Administrador administradorLogeado) {
        bienvenidaLabel.setText("Bienvenid@ de nuevo, " +administradorLogeado.getNombre());
        this.administradorLogeado = administradorLogeado;
    }

    private ObservableList<Vendedor> obtenerListaVendedores() {
        listaVendedoresData.addAll(singleton.obtenerListaVendedoresAliados());
        return listaVendedoresData;
    }
}
