/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.hbm.Usuario;
import java.util.List;

/**
 *
 * @author User
 */
public interface UsuarioDao {
    List<Usuario> getUser();
    Usuario validaUsuario(String email, String password);
    void actualizarConexionUsuario(Usuario usuario);
    int agregarUsuario(Usuario usuario);
    Usuario validaEmailSistema(String email);
    void actualizarDatosUsuario(Usuario user);
    void deleteUser(Usuario usuario);
    Usuario byId(int idUser);
    List<Usuario> getListaEmailNotificaciones(String notificar);
}
