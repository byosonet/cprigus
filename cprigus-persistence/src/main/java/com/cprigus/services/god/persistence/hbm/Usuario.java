/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cprigus.services.god.persistence.hbm;

import java.util.Date;

/**
 *
 * @author User
 */
public class Usuario implements java.io.Serializable{
    private int idUsuario;
    private String nombre;
    private char sexo;
    private Date fechaAlta;
    private Date ultConexion;
    private String notificaciones;
    private String email;
    private String password;
    private Date fechaNacimiento;
    private String actividad;
    
    public Usuario(){
    }
    
    public Usuario(int idUsuario, String nommbre, char sexo, Date fechaAlta, Date ultConexion, String notificaciones,
            String email, String password, Date fechaNacimiento, String actividad){
        this.idUsuario = idUsuario;
        this.nombre = nommbre;
        this.sexo = sexo;
        this.fechaAlta = fechaAlta;
        this.ultConexion = ultConexion;
        this.notificaciones = notificaciones;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.actividad = actividad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getUltConexion() {
        return ultConexion;
    }

    public void setUltConexion(Date ultConexion) {
        this.ultConexion = ultConexion;
    }

    public String getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(String notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", sexo=" + sexo + ", fechaAlta=" + fechaAlta + ", ultConexion=" + ultConexion + ", notificaciones=" + notificaciones + ", email=" + email + ", password=" + password + ", fechaNacimiento=" + fechaNacimiento + ", actividad=" + actividad + '}';
    }

}
