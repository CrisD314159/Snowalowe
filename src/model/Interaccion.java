package model;

public class Interaccion {

    private String id;
    private String idAutor;
    private TipoInteraccion tipo;
    /*--------------CONSTRUCTOR-------------------------------------*/
    public Interaccion(String id, String idAutor, TipoInteraccion tipo) {
        this.id = id;
        this.idAutor = idAutor;
        this.tipo = tipo;
    }

    public Interaccion() {
    }
    /*----------------METODOS---------------------------------------*/

    /*----------------GETTERS & SETTERS-----------------------------*/

    public String getId() {
        return id;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public TipoInteraccion getTipo() {
        return tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public void setTipo(TipoInteraccion tipo) {
        this.tipo = tipo;
    }
}
