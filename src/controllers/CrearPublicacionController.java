
package controllers;


import application.MainSnowAlowe;
import exceptions.ProductoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Vendedor;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class CrearPublicacionController {

    MainSnowAlowe main = new MainSnowAlowe();
    ModelFactoryController singleton = ModelFactoryController.getInstance();

    private Image image;

    private Vendedor vendedorLogeado = null;

    LocalDate date;
    @FXML
    private Button atrasButton;

    @FXML
    private TextField categoriaProductoLabel;

    @FXML
    private TextField codigoProductoLabel;

    @FXML
    private ImageView imagen;

    @FXML
    private TextField nombreProductoLabel;

    @FXML
    private TextField precioProductoLabel;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private Button publicarButton;

    @FXML
    private Button seleccionImagen;

    @FXML
    public void irAtras(ActionEvent event) throws IOException {
        mostrarPanelVendedorAction();


    }

    public void obtenertvendedor(Vendedor vendedor){
        this.vendedorLogeado = vendedor;
    }

    private void mostrarPanelVendedorAction() throws IOException {
        main.abrirPanelVendedor(this.vendedorLogeado);
    }

    @FXML
    void publicarProducto(ActionEvent event) throws ProductoException {
        publicarProductoAction();


    }

    private void publicarProductoAction() throws ProductoException {
        String nombre  ="";
        String codigo ="";
        double precio =0;
        String categoria ="";
        String date = "";
        nombre = nombreProductoLabel.getText();
        codigo = codigoProductoLabel.getText();
        precio = Double.parseDouble(precioProductoLabel.getText());
        categoria = categoriaProductoLabel.getText();
        date = this.date.toString();
        if(vericarCampos(nombre,codigo,precio,categoria)){
            boolean publicacionCreada = singleton.crearPublicacion(nombre, codigo, precio, categoria, this.image, this.vendedorLogeado, date);
            if(publicacionCreada){
                Alerta.saltarAlerta("Listo", "Publicacion Creada");
                limpiarCampos();
            }else{
                Alerta.saltarAlerta("Error", "Hubo un error al crear la publicaión");
            }

        }else{
            Alerta.saltarAlerta("Error", "Verifica los campos obligatorios");
        }
    }

    private void limpiarCampos() {
        precioProductoLabel.clear();
        nombreProductoLabel.clear();
        codigoProductoLabel.clear();
        categoriaProductoLabel.clear();
        imagen.setImage(null);

    }

    private boolean vericarCampos(String nombre, String codigo, double precio, String categoria) {

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


    public void setMain(MainSnowAlowe main) {
        this.main = main;

    }
    @FXML
    void seleccionarImagen(ActionEvent event) {
        seleccionarImagenAction(event);

    }
    @FXML
    void seleccionarImagenAction(ActionEvent event) {

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
            imagen.setImage(image);
        }

    }

    @FXML
    void obtenerFecha(ActionEvent event) {
        LocalDate date = fechaPicker.getValue();
        this.date = date;

    }
}
