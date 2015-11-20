package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.hbm.PropiedadSistema;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author User
 */
public class PropiedadSistemaDaoImpl extends HibernateDaoSupport implements PropiedadSistemaDao{
     private final Logger log = Logger.getLogger(PropiedadSistemaDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();
    public PropiedadSistema obtenerValorPropiedad(String key) {
        this.log.info(" -- Buscando Propiedad by key:: "+key);
        return (PropiedadSistema) this
                .getSession()
                .createQuery("FROM PropiedadSistema p WHERE p.key = :key")
                .setParameter("key", key)
                .uniqueResult();
    }

    public void guardarPropiedad(String key, String value) {
        try {
            this.mysql.iniciarOperacion();
            this.log.info(" -- Buscando Propiedad by key:: "+key);
            PropiedadSistema ps = (PropiedadSistema)this
                .getSession()
                .createQuery("FROM PropiedadSistema p WHERE p.key = :key")
                .setParameter("key", key)
                .uniqueResult();
            ps.setValue(value);
            this.mysql.getSesion().saveOrUpdate(ps);
            this.mysql.getSesion().flush();
            this.mysql.getTx().commit();
            this.log.info(" -- Guardando Propiedad en BD: "+ps.toString());
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
    }
}
