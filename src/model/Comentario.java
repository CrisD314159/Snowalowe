package model;

import java.io.Serializable;
import java.util.Objects;

public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    String contenido;
    Vendedor autor;
    Producto producto;

    public Comentario() {
    }

    public Comentario(String contenido, Vendedor autor, Producto producto) {
        this.contenido = contenido;
        this.autor = autor;
        this.producto = producto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Vendedor getAutor() {
        return autor;
    }

    public void setAutor(Vendedor autor) {
        this.autor = autor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return Objects.equals(contenido, that.contenido) && Objects.equals(autor, that.autor) && Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contenido, autor, producto);
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "contenido='" + contenido + '\'' +
                ", autor=" + autor +
                ", producto=" + producto +
                '}';
    }
}
