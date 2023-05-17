package controllers;


import application.MainSnowAlowe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.EstadoProducto;
import model.Producto;
import model.Vendedor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActualizarProductoController implements Initializable {
    private EstadoProducto[] listaEstados = {EstadoProducto.PUBLICADO, EstadoProducto.CANCELADO, EstadoProducto.VENDIDO};



    Vendedor vendedor;

    Producto producto;
    private MainSnowAlowe main = new MainSnowAlowe();

    private ModelFactoryController singleton = ModelFactoryController.getInstance();

    private Image image;

    @FXML
    private Button actualizarButton;

    @FXML
    private TextField categoriaField;

    @FXML
    private TextField codigoField;

    @FXML
    private ChoiceBox<EstadoProducto> estadoChoice;

    @FXML
    private ImageView imagenProducto;

    @FXML
    private Button irAtrasButton;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField precioField;

    @FXML
    private Button seleccionarImagenButton;

    @FXML
    void actualizarProducto(ActionEvent event) {
        actualizarButtonAction();

    }

    private void actualizarButtonAction() {

        EstadoProducto estado = estadoChoice.getValue();
        String nombre ="";
        String codigo ="";
        String categoria ="";
        double precio = 0;
        nombre = nombreField.getText();
        codigo = codigoField.getText();
        categoria = categoriaField.getText();
        precio = Double.parseDouble(precioField.getText());

        if(verificarCampos(nombre, codigo, categoria, precio)){
            boolean actualizarProducto = singleton.actualizarProducto(nombre, codigo, estado, categoria, precio, this.image, this.vendedor);
            if(actualizarProducto){
                Alerta.saltarAlerta("Listo", "Producto actualizado");
            }else {
               Alerta.saltarAlerta("Error", "Imposible actualizar el producto");
            }
        }else{
            Alerta.saltarAlerta("Aviso", "Hay campos obligatorios vacíos");
        }


    }



    private boolean verificarCampos(String nombre, String codigo,  String categoria, double precio) {
        if(nombre.equals("")){
            return false;

        }
        if(codigo.equals("")){
            return false;

        }
        if(precio == 0){
            return false;

        }
        if(categoria.equals("")){
            return false;

        }


        return true;
    }

    @FXML
    void irAtras(ActionEvent event) throws IOException {
        main.abrirPanelVendedor(vendedor);

    }

    @FXML
    void seleccionarImagen(ActionEvent event) {
        seleccionarImagenAction();

    }

    private void seleccionarImagenAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(main.getPrimaryStage());

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            this.image = image;
            imagenProducto.setImage(image);
        }
    }


    public void setMain(MainSnowAlowe main) {
        this.main = main;
    }

    public void obtenerProducto(Producto productoSeleccionado) {
        this.producto = productoSeleccionado;
        nombreField.setText(productoSeleccionado.getNombre());
        codigoField.setText(productoSeleccionado.getCodigo());
        categoriaField.setText(productoSeleccionado.getCategoria());
        precioField.setText(String.valueOf(productoSeleccionado.getPrecio()));

    }

    public void obtenerVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estadoChoice.getItems().addAll(listaEstados);
    }
}