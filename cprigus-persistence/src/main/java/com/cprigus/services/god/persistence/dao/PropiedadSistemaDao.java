package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.hbm.PropiedadSistema;

/**
 *
 * @author User
 */
public interface PropiedadSistemaDao {
    PropiedadSistema obtenerValorPropiedad(String key);
    void guardarPropiedad (String key, String value);
}
