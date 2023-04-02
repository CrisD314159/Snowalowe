package model;

import java.util.Objects;

public class Cuenta {
    private String usuario;
    private String contrasenia;

    public Cuenta(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Cuenta() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(usuario, cuenta.usuario) && Objects.equals(contrasenia, cuenta.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, contrasenia);
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
