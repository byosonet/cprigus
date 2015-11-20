package com.cprigus.services.god.persistence.dto;

/**
 *
 * @author User
 */
public class ConsultaCoro {
    private String total;
    private String fecha;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ListaConsultaCoro{" + "total=" + total + ", fecha=" + fecha + '}';
    }
    
    
    
}
