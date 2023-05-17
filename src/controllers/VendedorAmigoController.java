package controllers;


import application.MainSnowAlowe;
import exceptions.VendedorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Comentario;
import model.Producto;
import model.Vendedor;

import java.io.IOException;

public class VendedorAmigoController {
    ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();
    ObservableList<Comentario> listaComentariosDate = FXCollections.observableArrayList();

    MainSnowAlowe main;

    ModelFactoryController singleton = ModelFactoryController.getInstance();

    Comentario comentarioSeleccionado;
    Producto productoSeleccionado;

    Vendedor vendedorSeleccionado;

    Vendedor vendedorLogeado;

    Vendedor vendedorAliado;

    @FXML
    private Label ApellidoLabel;

    @FXML
    private TableColumn<Producto, String> categoriaColum;

    @FXML
    private Label categoriaProductolabel;

    @FXML
    private Button chatButton;

    @FXML
    private TextField comentarioField;

    @FXML
    private Label numeroMeGusta;

    @FXML
    private ImageView imagenProducto;

    @FXML
    private TableColumn<Producto, String> codigoColum;

    @FXML
    private Label codigoProductoLabel;

    @FXML
    private Button comentarButton;

    @FXML
    private TableColumn<Comentario, String> comentariosColum;

    @FXML
    private Label estadoProductoLabel;


    @FXML
    private TableColumn<Producto, String> nombreColum;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label fechaLabel;

    @FXML
    private Label nombreProductoLabel;

    @FXML
    private TableColumn<Vendedor, String> precioColum;

    @FXML
    private Label precioProductoLabel;

    @FXML
    private TableView<Comentario> tableComentarios;

    @FXML
    private TableView<Producto> tableProductos;

    @FXML
    private Button volverButton;

    @FXML
    void irChat(ActionEvent event) throws VendedorException, IOException {
        main.mostrarChat(this.vendedorLogeado,this.vendedorAliado);
    }

    @FXML
    void meGusta(ActionEvent event) {
        meGustaAction();
    }


    private void meGustaAction() {
        if(!(singleton.verificarExisteMeGusta(vendedorAliado, vendedorLogeado, productoSeleccionado))){
            boolean meGustaAgregado = singleton.agregarMeGusta(vendedorAliado, vendedorLogeado, productoSeleccionado);
            if(meGustaAgregado){
                int cantidadMeGusta = singleton.contarMeGustas(productoSeleccionado);
                numeroMeGusta.setText("" + cantidadMeGusta);
            }
        }else{
            singleton.quitarMeGusta(vendedorAliado, vendedorLogeado, productoSeleccionado);
            int cantidadMeGusta = singleton.contarMeGustas(productoSeleccionado);
            numeroMeGusta.setText("" + cantidadMeGusta);
        }
    }



    @FXML
    void volverAtras(ActionEvent event) throws IOException {
        main.abrirPanelVendedor(vendedorLogeado);
    }

    public void setMain(MainSnowAlowe main) {
        this.main = main;
        tableProductos.getItems().clear();
        tableProductos.setItems(obtenerListaProductos());
        tableComentarios.getItems().clear();
    }

    public void obtenerVendedorLogeado(Vendedor vendedorLogeado) {
        this.vendedorLogeado = vendedorLogeado;

    }


    public void obtenerVendedorSeleccionado(Vendedor vendedorSeleccionado) {
              this.vendedorAliado = vendedorSeleccionado;
              String nombre = vendedorSeleccionado.getNombre();
              String apellido = vendedorSeleccionado.getApellido();
             nombreLabel.setText("Nombre: " + nombre);
              ApellidoLabel.setText("Apellido: " + apellido);
    }



    @FXML
    void initialize(){
        this.nombreColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.categoriaColum.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        this.precioColum.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.codigoColum.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.comentariosColum.setCellValueFactory(new PropertyValueFactory<>("mensaje"));

        tableProductos.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            mostrarDatosProducto(productoSeleccionado);
        });

        tableComentarios.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            comentarioSeleccionado = newSelection;
        });
    }

    private void mostrarDatosProducto(Producto productoSeleccionado) {
        nombreProductoLabel.setText(productoSeleccionado.getNombre());
        codigoProductoLabel.setText("Codigo: " + productoSeleccionado.getCodigo());
        precioProductoLabel.setText("Precio: " + productoSeleccionado.getPrecio());
        estadoProductoLabel.setText("Estado: " + productoSeleccionado.getEstado());
        categoriaProductolabel.setText("Categoria: " + productoSeleccionado.getCategoria());
        fechaLabel.setText("Fecha: " + productoSeleccionado.getDate());
        imagenProducto.setImage(productoSeleccionado.getImage());
    }

    private ObservableList<Producto> obtenerListaProductos() {
        listaProductosData.addAll(main.obtenerListaProductos(vendedorAliado));
        return listaProductosData;
    }




    @FXML
    void agregarComentario(ActionEvent event) {
        agregarComentarioAction();

    }



    private void agregarComentarioAction() {
        String mensaje = "";
        mensaje = comentarioField.getText();
        if (mensaje.equals("")) {
            Alerta.saltarAlerta("Advertencia", "Ingresa algún comentario válido");
        } else {
            Comentario comentarioAgregado = singleton.agregarComenterio(vendedorLogeado, vendedorAliado, mensaje, productoSeleccionado);
            listaComentariosDate.add(comentarioAgregado);
            tableComentarios.refresh();
            Alerta.saltarAlerta("Listo", "Mensaje enviado");

        }
    }

    private boolean verificarTexto (String mensaje){
        if (mensaje.equals("")) {
            return false;
        }
        return true;
    }


}
