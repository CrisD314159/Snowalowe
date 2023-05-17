package model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String idAutor;
    private String descripcion;
    private DateTimeFormatter date;
    /*--------------CONSTRUCTOR-----------------------------------------------------------*/
    public Mensaje(String id, String idAutor, String descripcion, DateTimeFormatter date) {
        this.id = id;
        this.idAutor = idAutor;
        this.descripcion = descripcion;
        this.date = date;
    }

    public Mensaje() {
    }
    /*----------------METODOS-------------------------------------------------------------*/

    public String getId() {
        return id;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }
}
