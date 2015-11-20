package com.cprigus.services.god.persistence.hbm;

import java.util.Date;

/**
 *
 * @author User
 */
public class Changeset implements java.io.Serializable{
    
    private int idChangeset;
    private String idUsuario;
    private String movement;
    private String idCoro;
    private Date fechaMov;
    
    public Changeset(){
    }
    
    public Changeset(int idChangeset, String idUsuario, String idMovement, String idCoro, Date fechaMov){
        this.idChangeset = idChangeset;
        this.idUsuario = idUsuario;
        this.movement = idMovement;
        this.idCoro = idCoro;
        this.fechaMov = fechaMov;
    }

    public int getIdChangeset() {
        return idChangeset;
    }

    public void setIdChangeset(int idChangeset) {
        this.idChangeset = idChangeset;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getIdCoro() {
        return idCoro;
    }

    public void setIdCoro(String idCoro) {
        this.idCoro = idCoro;
    }

    public Date getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(Date fechaMov) {
        this.fechaMov = fechaMov;
    }

    @Override
    public String toString() {
        return "Changeset{" + "idChangeset=" + idChangeset + ", idUsuario=" + idUsuario + ", movement=" + movement + ", idCoro=" + idCoro + ", fechaMov=" + fechaMov + '}';
    }
  
}
