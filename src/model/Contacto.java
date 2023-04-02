package model;

public class Contacto {

    private String id;
    private String idVendedor;
    /*--------------CONSTRUCTOR-------------------------------------*/
    public Contacto(String id, String idVendedor) {
        this.id = id;
        this.idVendedor = idVendedor;
    }
    /*----------------METODOS---------------------------------------*/


    /*----------------GETTERS & SETTERS-----------------------------*/

    public String getId() {
        return id;
    }

    public String getIdVendedor() {
        return idVendedor;
    }
}
