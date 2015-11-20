package com.cprigus.services.god.persistence.hbm;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.rowset.serial.SerialClob;

/**
 *
 * @author User
 */
public class Coro implements java.io.Serializable{
    private int idCoro;
    private String nombre;
    private String autor;
    private Date fechaAct;
    private Clob descripcion;
    private String numCoro;
    private int activo;
    private String descripcionString;
    
    public Coro(){
        
    }
    
    public Coro(int idcoro, String nombre, String autor, Date fechaAct, Clob descripcion, String numCoro, int activo){
        this.idCoro = idcoro;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaAct = fechaAct;
        this.descripcion = descripcion;
        this.numCoro = numCoro;
        this.activo = activo;
    }

    public int getIdCoro() {
        return idCoro;
    }

    public void setIdCoro(int idCoro) {
        this.idCoro = idCoro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaAct() {
        return fechaAct;
    }

    public void setFechaAct(Date fechaAct) {
        this.fechaAct = fechaAct;
    }

    public Clob getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Clob descripcion) {
        this.descripcion = descripcion;
    }

    
    public String getDataClob(Clob descripcion) throws SQLException, IOException{
        return this.readClob(descripcion);
    }

    public String getNumCoro() {
        return numCoro;
    }

    public void setNumCoro(String numCoro) {
        this.numCoro = numCoro;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public String readClob(Clob clob) throws SQLException, IOException {
        StringBuilder sb = new StringBuilder((int) clob.length());
        Reader r = clob.getCharacterStream();
        char[] cbuf = new char[2048];
        int n;
        while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
            sb.append(cbuf, 0, n);
        }
        return sb.toString();
    }
    
    public Clob covertirStringToClob(String des) throws SQLException{
        Clob myClobFile = new SerialClob(des.toCharArray());
        return myClobFile;
    }

    public String getDescripcionString() {
        return descripcionString;
    }

    public void setDescripcionString(String descripcionString) {
        this.descripcionString = descripcionString;
    }

    @Override
    public String toString() {
        return "Coro{" + "idCoro=" + idCoro + ", nombre=" + nombre + ", autor=" + autor + ", fechaAct=" + fechaAct + ", descripcion=" + descripcion + ", numCoro=" + numCoro + '}';
    }
    
}
