/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cprigus.services.service;

import com.cprigus.services.god.persistence.hbm.Usuario;
import java.util.List;

/**
 *
 * @author User
 */
public interface UsuarioService {
    List<Usuario> getListaUsuarios();
    Usuario validaUsuario(String email, String password);
    void actulizarConexionUsuario(Usuario usuario);
    int agregaUsuarioNuevo(Usuario usuario);
    Usuario validaEmailSistema(String email);
    void actualizarDatosUsuario(Usuario usuario);
    void deleteUser(Usuario user);
    Usuario byIdUser(int idUser);
}
