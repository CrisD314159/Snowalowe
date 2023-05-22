package application;


import controllers.*;
import exceptions.VendedorException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainSnowAlowe extends Application {
    Red snowalowe = new Red("Snowalowe");

    private Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        inicializarLogin();
    }

    public void inicializarLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        LoginController controller = fxmlLoader.getController();
        controller.setMain(this);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    public void abrirPanelVendedor(Vendedor vendedor) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/PanelUsuario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        PanelUsuarioController controller= fxmlLoader.getController();
        controller.mostrarBienvenida(vendedor);
        controller.setMain(this);


        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        stage.setTitle("Bienvenid@ a tu panel principal de Snowalowe");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }

    public void abrirCrearCuenta() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/CrearCuentaView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        CrearCuentaController controller = fxmlLoader.getController();
        controller.setMain(this);

        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        stage.setTitle("Bienvenid@ a Snowalowe");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }



    public void mostrarActualizarProducto(Producto productoSeleccionado, Vendedor vendedor ) {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(MainSnowAlowe.class.getResource("../views/ActualizarProductoView.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores
            ActualizarProductoController controller = loader.getController();
            controller.obtenerProducto(productoSeleccionado);
            controller.obtenerVendedor(vendedor);
            controller.setMain(this);
            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().clear();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
            stage.setTitle("Crea tu producto");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarCrearPublicacion(Vendedor vendedorLogeado) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/CrearPublicacionView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        CrearPublicacionController controller = fxmlLoader.getController();
        controller.setMain(this);
        controller.obtenertvendedor(vendedorLogeado);

        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        stage.setTitle("Crea tu producto");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }

    public ArrayList<Vendedor> obtenerListaVendedoresAliados(Vendedor vendedorLogeado) {
        return snowalowe.obtenerListaAmigos(vendedorLogeado);
    }

    public ArrayList<Producto> obtenerListaProductos(Vendedor vendedorLogeado) {
        return snowalowe.obtenerListaProductos(vendedorLogeado);
    }

    public void mostrarVentanaMensajes(Vendedor vendedorLogeado) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/MensajesEntrantes.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MensajesENtrantesController controller = fxmlLoader.getController();
        controller.obtenerVendedorLogeado(vendedorLogeado);
        controller.setMain(this);


        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        stage.setTitle("Crea tu producto");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void mostrarMuroVendedorAliado(Vendedor vendedorLogeado, Vendedor vendedorSeleccionado) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/VendedorAmigoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        VendedorAmigoController controller = fxmlLoader.getController();
        controller.obtenerVendedorLogeado(vendedorLogeado);
        controller.obtenerVendedorSeleccionado(vendedorSeleccionado);
        controller.setMain(this);


        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        stage.setTitle("Crea tu producto");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void mostrarRecomendaciones(Vendedor vendedorLogeado) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/RecomendacionesVenedoresViews.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        RecomendacionVendedoresAliadosController controller = fxmlLoader.getController();
        controller.obtenerVendedorLogeado(vendedorLogeado);
        try {
            controller.setMain(this);
        } catch (VendedorException e) {
            throw new RuntimeException(e);
        }


        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        stage.setTitle("Crea tu producto");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }


    public Stage getPrimaryStage() {
        return  stage;
    }





    public ArrayList<Mensaje> obtenerListaMensajes(Vendedor vendedorLogeado) {
        return vendedorLogeado.getMensajes();
    }





    public void mostrarChat(Vendedor vendedorLogeado, Vendedor vendedorAliado) throws IOException, VendedorException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/ChatView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ChatController controller = fxmlLoader.getController();
        controller.obtenerVendedorLogeado(vendedorLogeado);
        controller.obtenerVendedorAliado(vendedorAliado);
        controller.setMain(this);


        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        stage.setTitle("Crea tu producto");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }

    public void mostrarSolicitudes(Vendedor vendedorLogeado) throws IOException, VendedorException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSnowAlowe.class.getResource("../views/SolicitudesViews.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        SolicitudesController controller = fxmlLoader.getController();
        controller.obtenerVendedorLogeado(vendedorLogeado);
        controller.setMain(this);


        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        stage.setTitle("Crea tu producto");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }

    public void abrirLoginAdministrador() {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(MainSnowAlowe.class.getResource("../views/LoginAdministrador.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores
            AdministradorLoginController controller = loader.getController();
            controller.setMain(this);
            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().clear();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
            stage.setTitle("Bienvenido de vuelta");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void abrirPanelAdministrador(Administrador administradorLogeado) {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(MainSnowAlowe.class.getResource("../views/PanelAdministrador.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores
            PanelAdministradorController controller = loader.getController();
            controller.setMain(this);
            controller.setAdministrador(administradorLogeado);
            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().clear();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
            stage.setTitle("Bienvenido de vuelta");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void abrirRecuperarContrasenia() {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(MainSnowAlowe.class.getResource("../views/ReestablecerContrasenia.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores
            RestablecerContraseniaController controller = loader.getController();
            controller.setMain(this);

            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().clear();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
            stage.setTitle("Recupera tu contraseña");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}