package model;

import java.io.Serializable;

public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;
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
