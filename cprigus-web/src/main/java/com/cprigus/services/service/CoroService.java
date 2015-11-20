/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cprigus.services.service;

import com.cprigus.services.god.persistence.hbm.Coro;
import java.util.List;

/**
 *
 * @author User
 */
public interface CoroService {
    List<Coro> obtenerListaCoroActualizada();
    List<Coro> obtenerListaCoroCompleta();
    Coro obtenerCoro(String idCoro);
    int agregarCoro(Coro coro);
    boolean validarNumCoro(String numero);
    boolean validarNombreCoro(String nombre);
    
    List<Coro> getListaPendiente();
    Coro getCoroById(int idCoro);
    void deleteCoro(Coro coro);
    void updateCoro(Coro coro);
    
    Coro getByNumCoro(String numCoro);
}
