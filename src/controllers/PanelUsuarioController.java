package controllers;

import application.MainSnowAlowe;
import exceptions.ProductoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Producto;
import model.Vendedor;

import java.io.IOException;

public class PanelUsuarioController {

    ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();

    ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();

    Producto productoSeleccionado = null;

    Vendedor vendedorSeleccionado = null;

    private MainSnowAlowe main;

    ModelFactoryController singleton;

    private Vendedor vendedor;


    @FXML
    private Button actualizarProductoButton;

    @FXML
    private Button mensajesButton;

    @FXML
    private Button cerrarSesionButton;

    @FXML
    private Label likesLabel;

    @FXML
    private TableView<Vendedor> amigosTable;

    @FXML
    private TableColumn<Vendedor, String> apellidosAmigoColum;

    @FXML
    private Label bienvenidaLabel;

    @FXML
    private TableColumn<Producto, String> categoriaProductoColum;

    @FXML
    private Label categoriaProductoLabel;

    @FXML
    private TableColumn<Producto, String> codigoProductoColum;

    @FXML
    private Label codigoProductoLabel;

    @FXML
    private Label fechaLabel;

    @FXML
    private Button crearPublicacionButton;

    @FXML
    private Button eliminarProductoButton;

    @FXML
    private Label estadoProductoLabel;

    @FXML
    private ImageView imagenProducto;

    @FXML
    private Button irMuroButton;

    @FXML
    private TableColumn<Vendedor, String> nombreAmigoColum;

    @FXML
    private TableColumn<Vendedor, String> nombreProductoColum;

    @FXML
    private Label nombreProductoLabel;

    @FXML
    private TableColumn<Producto, String> precioProductoColum;

    @FXML
    private Label precioProductoLabel;

    @FXML
    private Button solicitudesButton;

    @FXML
    private TableView<Producto> tableProductos;

    @FXML
    void actualizarProducto(ActionEvent event) throws IOException {
        main.mostrarActualizarProducto(productoSeleccionado, vendedor);
    }

    @FXML
    void crearPublucacion(ActionEvent event) throws IOException {
        main.mostrarCrearPublicacion(vendedor);

    }

    @FXML
    void eliminarProducto(ActionEvent event) throws ProductoException {
        eliminarProductoAction();
    }

    private void eliminarProductoAction()  {
        String codigo = "";
        codigo = productoSeleccionado.getCodigo();
        if(singleton.eliminarProducto(codigo, vendedor)){
           Alerta.saltarAlerta("Listo", "Producto eliminado");
            listaProductosData.remove(productoSeleccionado);
            limpiarCamposProducto();
        }else{
           Alerta.saltarAlerta("Error", "no fue posible eliminar el producto");
        }
    }

    private void limpiarCamposProducto() {
        nombreProductoLabel.setText("");
        estadoProductoLabel.setText("");
        categoriaProductoLabel.setText("");
        precioProductoLabel.setText("");
        codigoProductoLabel.setText("");
        imagenProducto.setImage(null);
    }

    @FXML
    void irMuroVendedor(ActionEvent event) throws IOException {
        main.mostrarMuroVendedorAliado(vendedor, vendedorSeleccionado);

    }

    @FXML
    void obtenerSolicitudesAmistad(ActionEvent event) throws IOException {
        main.mostrarRecomendaciones(vendedor);
    }

    @FXML
    void initialize(){
        this.nombreProductoColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.categoriaProductoColum.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        this.precioProductoColum.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.codigoProductoColum.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.nombreAmigoColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.apellidosAmigoColum.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        tableProductos.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            mostrarDatosProducto(productoSeleccionado);
        });

        amigosTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            vendedorSeleccionado = newSelection;
        });
    }



    private void mostrarDatosProducto(Producto productoSeleccionado) {
        nombreProductoLabel.setText(productoSeleccionado.getNombre());
        codigoProductoLabel.setText("Codigo: " + productoSeleccionado.getCodigo());
        precioProductoLabel.setText("Precio: " + productoSeleccionado.getPrecio());
        categoriaProductoLabel.setText("Categoria: " + productoSeleccionado.getCategoria());
        estadoProductoLabel.setText("Estado: "+ productoSeleccionado.getEstado());
        imagenProducto.setImage(productoSeleccionado.getImage());
        fechaLabel.setText("Fecha: " + productoSeleccionado.getDate());
        likesLabel.setText("Me gusta: "+ productoSeleccionado.getMeGusta().size());

    }


    public void setMain(MainSnowAlowe main){
        this.main = main;
        this.singleton = ModelFactoryController.getInstance();
        tableProductos.getItems().clear();
        tableProductos.setItems(obtenerListaProductos());
        amigosTable.getItems().clear();
        amigosTable.setItems(obtenerListaVendedoresAliados());

    }

    private ObservableList<Vendedor> obtenerListaVendedoresAliados() {
        listaVendedoresData.addAll(main.obtenerListaVendedoresAliados(vendedor));
        return listaVendedoresData;
    }


    private ObservableList<Producto> obtenerListaProductos() {
       listaProductosData.addAll(main.obtenerListaProductos(vendedor));
        return listaProductosData;
    }

    public void mostrarBienvenida(Vendedor vendedor) {
        this.vendedor = vendedor;
        String nombre ="";
        nombre = vendedor.getNombre();
        bienvenidaLabel.setText("Que bueno verte de nuevo, " + nombre + "!!");

    }


    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        main.inicializarLogin();
    }

    @FXML
    void mostrarMensajes(ActionEvent event) throws IOException {
        main.mostrarVentanaMensajes(this.vendedor);

    }
}
