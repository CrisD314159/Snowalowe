package model;

import javafx.scene.image.Image;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Producto {

    private ArrayList<Comentario> comentarios;
    private ArrayList<Interaccion> interacciones;
    private String id;
    private String nombre;
    private String codigo;
    private Image image;
    private EstadoProducto estado;
    private double precio;

    private String categoria;
    private DateTimeFormatter date;
    /*--------------CONSTRUCTOR------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public Producto(ArrayList<Comentario> comentarios, ArrayList<Interaccion> interacciones, String id, String nombre, String codigo, Image imagen, EstadoProducto estado, double precio, DateTimeFormatter date, String categoria) {
        this.comentarios = new ArrayList<>();
        this.interacciones = new ArrayList<>();
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.image = imagen;
        this.estado = estado;
        this.precio = precio;
        this.date = date;
        this.categoria = categoria;
    }

    public Producto() {
    }
    /*----------------METODOS--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/



    /*----------------GETTERS & SETTERS------------------------------------------------------------------------*/

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<Interaccion> getInteracciones() {
        return interacciones;
    }

    public void setInteracciones(ArrayList<Interaccion> interacciones) {
        this.interacciones = interacciones;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
