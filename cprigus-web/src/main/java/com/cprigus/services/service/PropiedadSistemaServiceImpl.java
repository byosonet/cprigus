package com.cprigus.services.service;

import com.cprigus.services.god.persistence.dao.PropiedadSistemaDao;
import com.cprigus.services.god.persistence.hbm.PropiedadSistema;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
public class PropiedadSistemaServiceImpl implements PropiedadSistemaService{
    private final Logger log = Logger.getLogger(PropiedadSistemaServiceImpl.class);
    
    @Transactional
    public PropiedadSistema obtenerValorPropiedad(String key) {
        this.log.info(" -- Key recibida: "+key);
        PropiedadSistema ps = this.propiedadSistemaDao.obtenerValorPropiedad(key);
        this.log.info(" -- Propiedad Sistema Devuelto: "+ps.toString());
        return ps;
    }

    @Transactional
    public void guardarPropiedad(String llave, String valor) {
        this.log.info(" -- Guardando propiedad en BD: ");
        this.propiedadSistemaDao.guardarPropiedad(llave, valor);
    }
    

    @Autowired
    private PropiedadSistemaDao propiedadSistemaDao;
    
}
