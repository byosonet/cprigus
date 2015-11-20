package com.cprigus.services.service;

import com.cprigus.services.god.persistence.hbm.PropiedadSistema;

/**
 *
 * @author User
 */
public interface PropiedadSistemaService {
    PropiedadSistema obtenerValorPropiedad (String key);
    void guardarPropiedad(String llave, String valor);
}
