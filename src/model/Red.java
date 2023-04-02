package model;


import exceptions.CuentaException;
import exceptions.ProductoException;
import exceptions.VendedorException;
import javafx.scene.image.Image;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Red {
    private ArrayList<Vendedor> listaUsuarios;
    private ArrayList<Cuenta> listaCuentas;

    /*----------------CONSTRUCTOR--------------------------------------------------*/

    public Red(ArrayList<Vendedor> usuarios) {
        this.listaUsuarios = new ArrayList<>();
    }

    /*----------------METODOS-------------------------------------------------------*/
    public void buscarVendedor(String id ){

    }
    public void buscarVendedorNom(String nombre ){

    }
    public void sugerirVendedor(String id ){

    }


    /*----------------GETTERS & SETTERS-----------------------------------------------*/

    public ArrayList<Vendedor> getUsuarios() {
        return listaUsuarios;
    }

    public void setUsuarios(ArrayList<Vendedor> usuarios) {
        this.listaUsuarios = usuarios;
    }


    /*------------METODOS CREATE--------------*/
    public Vendedor nuevoVendedor(String nombre, String apellido, String cedula, String direccion, Cuenta cuenta) throws VendedorException {
        Vendedor vendedor = new Vendedor();
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        ArrayList<Vendedor> listaVendedoresAliados = new ArrayList<Vendedor>();
        vendedor.setNombre(nombre);
        vendedor.setApellido(apellido);
        vendedor.setCedula(cedula);
        vendedor.setDireccion(direccion);
        vendedor.setProductos(listaProductos);
        vendedor.setListaAmigos(listaVendedoresAliados);
        vendedor.setCuenta(cuenta);

        if(existeVendedor(cedula) == true){
            throw new VendedorException("El vendedor ya existe");
        }
        listaUsuarios.add(vendedor);
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

    public boolean crearProducto(String nombre, String codigo , String categoria, double precio, Image image, Vendedor vendedor, DateTimeFormatter date) throws ProductoException {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCodigo(codigo);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setEstado(EstadoProducto.PUBLICADO);
        producto.setImage(image);
        producto.setDate(date);
        if(existeProducto(codigo, vendedor) == true){
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

    public Cuenta crearCuenta(String user, String password) throws CuentaException {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(user);
        cuenta.setContrasenia(password);

        if(existeCuenta(user)==false){
            throw new CuentaException("la cuenta ya existe");
        }
        listaCuentas.add(cuenta);
        return cuenta;
    }

    private boolean existeCuenta(String user) {
        for (Cuenta cuenta:listaCuentas
             ) {
            if(cuenta.getUsuario().equals(user)){
                return true;
            }
        }
        return false;
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
        for (Cuenta cuenta:listaCuentas
             ) {
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
            if(producto.equals(codigo)){
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
    public Mensaje enviarMensaje(Vendedor vendedorLogeado, Vendedor vendedorAliado, String contenido) {

        Mensaje mensaje = new Mensaje();
        mensaje.setIdAutor(vendedorLogeado.getNombre());
        mensaje.setDescripcion(contenido);
        vendedorAliado.getMensajes().add(mensaje);
        return mensaje;
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

    public boolean agregarInteraccion(Vendedor vendedorLogeado, Vendedor amigo, String codigoProducto, TipoInteraccion interaccion ){
        Interaccion interaccion1= new Interaccion();
        interaccion1.setIdAutor(vendedorLogeado.getNombre());
        interaccion1.setTipo(interaccion);
        for (Producto producto:amigo.getProductos()
             ) {
            if (producto.getCodigo().equals(codigoProducto)){
                producto.getInteracciones().add(interaccion1);
                return true;
            }

        }
        return false;
    }

    public int contarInteracciones(Vendedor vendedroLogeado, Vendedor amigo, String codigoProducto){
        for (Producto producto:amigo.getProductos()
        ) {
            if (producto.getCodigo().equals(codigoProducto)){
                return producto.getInteracciones().size();

            }
        }
        return 0;

    }



}
