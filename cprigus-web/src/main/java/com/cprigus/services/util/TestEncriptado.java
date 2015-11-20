package com.cprigus.services.util;

/**
 *
 * @author User
 */
public class TestEncriptado {
    public static void main(String[] args) throws Exception {
 
        String encriptado = UtilService.Encriptar("Esto es una prueba");
        System.out.println(encriptado);
        String desencriptado = UtilService.Desencriptar(encriptado);
        System.out.println(desencriptado);
 
    }
}
