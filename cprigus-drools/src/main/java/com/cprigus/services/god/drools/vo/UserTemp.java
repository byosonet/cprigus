package com.cprigus.services.god.drools.vo;

/**
 *
 * @author User
 */
public class UserTemp {
    private String nombre;
    private int edad;
    private String email;
    private boolean validado;
    private String mensaje;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "UserTemp{" + "nombre=" + nombre + ", edad=" + edad + ", email=" + email + ", validado=" + validado + ", mensaje=" + mensaje + '}';
    }

}
