package model;

import java.util.ArrayList;

public class Vendedor {



    private ArrayList<Mensaje> mensajes;
    private ArrayList<Vendedor> listaAmigos;
    private ArrayList<Producto> productos;
    private ArrayList<Solicitud> solicitudesEnviadas;
    private ArrayList<Solicitud> solicitudesRecibidas;
    private ArrayList<Contacto> contactos;
    private String id ;
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private Cuenta cuenta;
    /*---------------------------CONSTRUCTOR--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public Vendedor(ArrayList<Producto> productos, ArrayList<Solicitud> solicitudesEnviadas, ArrayList<Solicitud> solicitudesRecibidas, ArrayList<Contacto> contactos, String id, String nombre, String apellido, String cedula, String direccion, ArrayList<Mensaje> mensajes) {
        this.productos = new ArrayList<>();
        this.solicitudesEnviadas = new ArrayList<>();
        this.solicitudesRecibidas = new ArrayList<>();
        this.contactos = new ArrayList<>();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.mensajes = mensajes;
    }

    public Vendedor() {
    }
    /*------------------------METODOS-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    public void enviarSolicitud(String id){

    }
    public void aceptarSolicitud(String id){

    }
    public void obtenerProductos(){

    }
    /*-----------------------GETTERS & SETERS -------------------------------------------*/


    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Solicitud> getSolicitudesEnviadas() {
        return solicitudesEnviadas;
    }

    public void setSolicitudesEnviadas(ArrayList<Solicitud> solicitudesEnviadas) {
        this.solicitudesEnviadas = solicitudesEnviadas;
    }

    public ArrayList<Solicitud> getSolicitudesRecibidas() {
        return solicitudesRecibidas;
    }

    public void setSolicitudesRecibidas(ArrayList<Solicitud> solicitudesRecibidas) {
        this.solicitudesRecibidas = solicitudesRecibidas;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Vendedor> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(ArrayList<Vendedor> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
