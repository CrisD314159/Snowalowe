package model;

import java.time.format.DateTimeFormatter;

public class Log {
    private DateTimeFormatter date;
    private String interfaz;
    private String accion;
    /*-------------------CONSTRUCTOR---------------------------------------*/

    public Log(DateTimeFormatter date, String interfaz, String accion) {
        this.date = date;
        this.interfaz = interfaz;
        this.accion = accion;
    }
    /*--------------------METODOS------------------------------------------*/



    /*----------------------GETTERS & SETTERS------------------------------*/

    public DateTimeFormatter getDate() {
        return date;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }

    public String getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(String interfaz) {
        this.interfaz = interfaz;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
