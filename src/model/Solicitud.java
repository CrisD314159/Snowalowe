package model;

import java.time.format.DateTimeFormatter;

public class Solicitud {

    private String id;
    private String idRemitente;
    private String idDestinatario;
    private DateTimeFormatter date;
    private EstadoSolicitud estado;
    /*--------------CONSTRUCTOR--------------------------------------------------------------------------------------------*/
    public Solicitud(String id, String idRemitente, String idDestinatario, DateTimeFormatter date, EstadoSolicitud estado) {
        this.id = id;
        this.idRemitente = idRemitente;
        this.idDestinatario = idDestinatario;
        this.date = date;
        this.estado = estado;
    }
    /*----------------METODOS-----------------------------------------------------------------------------------------------*/

    /*----------------GETTERS & SETTERS-------------------------------------------------------------------------------------*/
    public String getId() {
        return id;
    }

    public String getIdRemitente() {
        return idRemitente;
    }

    public String getIdDestinatario() {
        return idDestinatario;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }
}
