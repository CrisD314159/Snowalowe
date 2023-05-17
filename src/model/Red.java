package model;


import exceptions.CuentaException;
import exceptions.ProductoException;
import exceptions.VendedorException;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Red implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private ArrayList<Vendedor> listaUsuarios;
    private ArrayList<Cuenta> listaCuentas;

    /*----------------CONSTRUCTOR--------------------------------------------------*/



    public Red(String nombre) {
        this.listaUsuarios = new ArrayList<Vendedor>();
        this.listaCuentas = new ArrayList<Cuenta>();
        this.nombre = nombre;
        //inicializarDatos();
    }

    private void inicializarDatos() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre("pepe");
        vendedor.setApellido("Martinez");
        vendedor.setCedula("123");
        vendedor.setDireccion("en una casa");
        Cuenta cuenta = new Cuenta("pepito","123");
        vendedor.setCuenta(cuenta);
        listaCuentas.add(cuenta);
        listaUsuarios.add(vendedor);
    }

    public Red() {
        this.listaUsuarios = new ArrayList<Vendedor>();
        this.listaCuentas = new ArrayList<Cuenta>();
    }

    /*----------------METODOS-------------------------------------------------------*/
    public void buscarVendedor(String id ){

    }
    public void buscarVendedorNom(String nombre ){

    }
    public void sugerirVendedor(String id ){

    }


    /*----------------GETTERS & SETTERS-----------------------------------------------*/


    public void setUsuarios(ArrayList<Vendedor> usuarios) {
        this.listaUsuarios = usuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Vendedor> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Vendedor> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    /*------------METODOS CREATE--------------*/
    public Vendedor nuevoVendedor(String nombre, String apellido, String cedula, String direccion, String user, String password) throws VendedorException {

        Vendedor vendedor = null;

        boolean verificarVendedor = false;
        verificarVendedor = existeVendedor(cedula);

        if(verificarVendedor){
            throw new VendedorException("El vededor ya se encuentra registrado");
        }else {
            vendedor = new Vendedor();
            ArrayList<Producto> listaProductos = new ArrayList<Producto>();
            ArrayList<Vendedor> listaVendedoresAliados = new ArrayList<Vendedor>();
            ArrayList<Mensaje> listaMensajes = new ArrayList<Mensaje>();
            Cuenta cuenta = new Cuenta(user, password);
            vendedor.setMensajes(listaMensajes);
            vendedor.setNombre(nombre);
            vendedor.setApellido(apellido);
            vendedor.setCedula(cedula);
            vendedor.setDireccion(direccion);
            vendedor.setProductos(listaProductos);
            vendedor.setListaAmigos(listaVendedoresAliados);
            vendedor.setCuenta(cuenta);
            getListaUsuarios().add(vendedor);

        }
        return vendedor;
    }

    private boolean existeVendedor(String cedula) {
        for (Vendedor vendedor : listaUsuarios) {

            if(vendedor.getCedula().equals(cedula)){
                return true;
            }
        }
        return false;
    }

    public boolean crearProducto(String nombre, String codigo , String categoria, double precio, Image image, Vendedor vendedor, String date) throws ProductoException {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCodigo(codigo);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setEstado(EstadoProducto.PUBLICADO);
        producto.setImage(image);
        producto.setDate(date);
        if(existeProducto(codigo, vendedor)){
            throw new ProductoException("El producto ya existe");
        }
        vendedor.getProductos().add(producto);

        return true;
    }

    private boolean existeProducto(String codigo, Vendedor vendedor) {
        for (Producto producto:vendedor.getProductos()) {
            if(producto.equals(codigo)){
                return true;
            }

        }
        return false;
    }

    public Cuenta crearCuenta(String user, String password) throws CuentaException, IOException {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(user);
        cuenta.setContrasenia(password);

        if(!existeCuenta(user)){
            throw new CuentaException("la cuenta ya existe");
        }

        return cuenta;
    }

    private boolean existeCuenta(String user) {
        for (Cuenta cuenta:listaCuentas) {
            if(cuenta.getUsuario().equals(user)){
                return false;
            }
        }
        return true;
    }


    /*---------------METODOS READ-----------------*/

    public Vendedor buscarVendeor(String codigo) throws VendedorException {
        Vendedor vendedorEncontrado=null;
        for (Vendedor vendedor:listaUsuarios) {
            if(vendedor.getCedula().equals(codigo)){
                return vendedor;
            }
        }if (vendedorEncontrado == null){
            throw new VendedorException("EL vendedor no se encontro");
        }
        return vendedorEncontrado;
    }

    public Producto buscarProducto(String codigo, Vendedor vendedor) throws ProductoException {
        Producto productoEncontrado=null;
        for (Producto producto:vendedor.getProductos()
        ) {
            if(producto.getCodigo().equals(codigo)){
                productoEncontrado = producto;
                return productoEncontrado;
            }
        }if(productoEncontrado == null){
            throw new ProductoException("El producto no se encuentra");
        }
        return productoEncontrado;

    }

    public Cuenta buscarCuenta(String user) throws CuentaException {
        Cuenta cuentaEncontrada= null;
        for (Cuenta cuenta:listaCuentas) {
            if(cuenta.getUsuario().equals(user)){
                cuentaEncontrada = cuenta;
                return cuentaEncontrada;
            }

        }if(cuentaEncontrada==null){
            throw new CuentaException("la cuenta ya se encuentra realizada");
        }
        return cuentaEncontrada;
    }



    /*--------METODOS UPDATE---------*/

    public boolean actualizarVendedor(String nombre, String apellido, String cedula, String direccion){
        for (Vendedor vendedor:listaUsuarios) {
            if (vendedor.getCedula().equals(cedula)){
                vendedor.setNombre(nombre);
                vendedor.setApellido(apellido);
                vendedor.setDireccion(direccion);
                return true;
            }
        }
        return false;
    }

    public boolean actualizarProducto(String nombre, String codigo , String categoria, double precio, EstadoProducto estado, Vendedor vendedor, Image image){
        for (Producto producto:vendedor.getProductos()) {
            if(producto.getCodigo().equals(codigo)){
                producto.setNombre(nombre);
                producto.setCategoria(categoria);
                producto.setPrecio(precio);
                producto.setEstado(estado);
                producto.setImage(image);
                return true;
            }

        }
        return false;
    }

    public boolean actualizarCuenta(String user, String contrasenia){
        for (Cuenta cuenta:listaCuentas) {
            if (cuenta.getUsuario().equals(user)){
                cuenta.setContrasenia(contrasenia);
                return true;
            }
        }
        return false;

    }

    /*-------METODOS DE DELETE------*/

    public boolean eliminarVendedor(String codigo){
        for (Vendedor vendedor:listaUsuarios
        ) {
            if (vendedor.getCedula().equals(codigo)){
                listaUsuarios.remove(vendedor);
                return true;
            }

        }
        return false;
    }

    public boolean eliminarProducto(String codigo, Vendedor vendedor){
        for (Producto producto:vendedor.getProductos()
        ) {
            if (producto.getCodigo().equals(codigo)){
                vendedor.getProductos().remove(producto);
                return true;
            }

        }
        return false;
    }

    public boolean eliminarCuenta(String user){
        for (Cuenta cuenta:listaCuentas) {
            if (cuenta.getUsuario().equals(user)){
                listaCuentas.remove(cuenta);
                return true;
            }
        }
        return false;
    }

    /*-----------METODOS----------------*/
    public boolean enviarMensaje(Vendedor vendedorLogeado, Vendedor vendedorAliado, String contenido) {
        Mensaje mensaje = new Mensaje();
        mensaje.setIdAutor(vendedorLogeado.getNombre());
        mensaje.setDescripcion(contenido);
        vendedorAliado.getMensajes().add(mensaje);
        return true;
    }

    public boolean responderMensaje(String mensaje, Vendedor autor, Vendedor vendedorLogeado) {
        Mensaje mensaje1 = new Mensaje();
        mensaje1.setIdAutor(vendedorLogeado.getNombre());
        mensaje1.setDescripcion(mensaje);
        autor.getMensajes().add(mensaje1);
        return true;
    }


    public boolean AgregarComentario(String mensaje, Vendedor vendedorLogeado, Vendedor vendedorAliado, String codigoProducto) {
        Comentario comentario = new Comentario();
        comentario.setAutor(vendedorLogeado);
        comentario.setContenido(mensaje);
        for (Producto producto:vendedorAliado.getProductos()) {
            if(producto.getCodigo().equals(codigoProducto)){
                producto.getComentarios().add(comentario);
                return true;
            }

        }
        return false;

    }







    public Boolean verificarUsuario(String usuario, String contrasenia) {
        for (Vendedor vendedor:listaUsuarios) {
            if (vendedor.verificarCuenta(usuario, contrasenia)){
                return true;
            }

        }
        return false;
    }

    public ArrayList<Vendedor> obtenerListaAmigos(Vendedor vendedorLogeado) {
        return vendedorLogeado.getListaAmigos();
    }

    public ArrayList<Producto> obtenerListaProductos(Vendedor vendedorLogeado) {
        return vendedorLogeado.getProductos();
    }

    public Vendedor obtenerVendedor(String usuario, String contrasenia) {
        Vendedor vendedorEncontrado = null;
        for (Vendedor vendedor:listaUsuarios) {
            if (vendedor.verificarCuenta(usuario, contrasenia)){
                vendedorEncontrado = vendedor;
                return vendedorEncontrado;
            }
        }
        return vendedorEncontrado;
    }

    public Comentario agregarComentario(Vendedor vendedorLogeado, Vendedor vendedorAliado, String mensaje, Producto productoS) {
        ArrayList<Producto> productos = vendedorAliado.getProductos();
        Comentario comentario = new Comentario();
        comentario.setAutor(vendedorLogeado);
        comentario.setContenido(mensaje);
        for (Producto producto:productos) {
            if(producto.getCodigo().equals(productoS.getCodigo())){
                producto.getComentarios().add(comentario);
            }

        }

        return comentario;
    }


    public ArrayList<Vendedor> obtenerListaRecomendados(Vendedor vendedorLogeado) {
       return vendedorLogeado.getListaRecomendados();
    }

    public ArrayList<Vendedor> actualizarTablaRecomendaciones(Vendedor vendedorLogeado) {

        for (Vendedor vendedor1: listaUsuarios) {
            if(verificarVendedorRepetido(vendedor1, vendedorLogeado)){
                if(verificarVendedorRepetidoAliados(vendedor1, vendedorLogeado)){
                    if(verificarVendedorRepetidoAliados(vendedorLogeado, vendedor1)){
                        vendedorLogeado.agregarVendedorRecomendado(vendedor1);
                    }
                }
            }
        }
        return vendedorLogeado.getListaRecomendados();
    }

    private boolean verificarVendedorRepetido(Vendedor vendedor, Vendedor vendedorAux) {
        if(vendedor.getCedula().equals(vendedorAux.getCedula())){
            return false;
        }
        return true;
    }

    private boolean verificarVendedorRepetidoAliados(Vendedor vendedor, Vendedor vendedorAux) {
        for (Vendedor vendedor1 : vendedorAux.getListaRecomendados()) {
            if(vendedor1.getCedula().equals(vendedor.getCedula())){
                return false;
            }
        }
        return true;
    }


    public boolean enviarSolucitudAmistad(Vendedor vendedorLogeado, Vendedor vendedorSeleccionado) {
        return vendedorSeleccionado.anadirSolicitud(vendedorLogeado);
    }

    public void aceptarSolicitud(Vendedor vendedorLogeado, Vendedor vendedorSeleccionado) {
        vendedorLogeado.aceptarSolicitud(vendedorSeleccionado);
        vendedorSeleccionado.aceptarSolicitud(vendedorLogeado);

    }

    public void rechazarSolicitud(Vendedor vendedorLogeado, Vendedor vendedorSeleccionado) {
        vendedorLogeado.rechazarSolicitud(vendedorSeleccionado);
        vendedorSeleccionado.rechazarSolicitud(vendedorLogeado);
    }

    public ArrayList<Vendedor> obtenerListaSolicitudes(Vendedor vendedorLogeado) {
        return vendedorLogeado.getListaSolicitudes();
    }

    public int contarMegustas(Producto productoSeleccionado) {
        return productoSeleccionado.getMeGusta().size();
    }

    public boolean verificarMeGusta(Vendedor vendedorAliado, Vendedor vendedorLogeado, Producto productoSeleccionado) {
        ArrayList<Producto> productos = vendedorAliado.getProductos();
        for (Producto producto:productos) {
            if(producto.getCodigo().equals(productoSeleccionado.getCodigo())){
                if (productoSeleccionado.verificarMegusta(vendedorLogeado)){
                    return true;
                }

            }

        }
        return false;
    }

    public boolean anadirMeGusta(Vendedor vendedorAliado, Vendedor vendedorLogueado, Producto productoSeleccionado) {
        ArrayList<Producto> productos = vendedorAliado.getProductos();
        for (Producto producto:productos) {
            if(producto.getCodigo().equals(productoSeleccionado.getCodigo())){
                producto.getMeGusta().add(vendedorLogueado);
                return true;
            }

        }
        return false;
    }

    public void quitarMeGusta(Vendedor vendedorAliado, Vendedor vendedorLogeado, Producto productoSeleccionado) {
        ArrayList<Producto> productos = vendedorAliado.getProductos();
        for (Producto producto:productos) {
            if(producto.getCodigo().equals(productoSeleccionado.getCodigo())){
                producto.getMeGusta().remove(vendedorLogeado);
            }

        }
    }

    public ArrayList<Mensaje> obtenerListaMensajes(Vendedor vendedorLogeado) {
        return vendedorLogeado.getMensajes();
    }
}
