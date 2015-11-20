package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.hbm.DeliveryFailed;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author User
 */
public class DeliveryFailedDaoImpl extends HibernateDaoSupport implements DeliveryFailedDao{
    private final Logger log = Logger.getLogger(DeliveryFailedDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();
    
    public void guardarDeliveryFailed(DeliveryFailed deliveryFailed) {
        try {
            this.mysql.iniciarOperacion();
            this.mysql.getSesion().save(deliveryFailed);
            this.mysql.getSesion().flush();
            this.mysql.getTx().commit();
            this.log.info(" -- Guardando deliveryFailed en BD: "+deliveryFailed.toString());
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
    }

    public List<DeliveryFailed> getListMailFailed() {
        this.log.info(" -- Buscando por lista de mail failed::");
        return (List<DeliveryFailed>) this
                .getSession()
                .createQuery("FROM DeliveryFailed df " + "ORDER BY df.dateSend DESC").list();
    }

    public void deleteDeliveryFailed(DeliveryFailed df) {
         try {
            this.mysql.iniciarOperacion();
            if(df!=null){
                this.mysql.getSesion().delete(df);
                this.mysql.getSesion().flush();
                this.mysql.getTx().commit();
            }
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
    }

    public DeliveryFailed getById(int idDelivery) {
        this.log.info(" -- Buscando idDelivery by id:: "+idDelivery);
        return (DeliveryFailed) this
                .getSession()
                .createQuery("FROM DeliveryFailed df WHERE df.idFailed = :idDelivery")
                .setParameter("idDelivery", idDelivery)
                .uniqueResult();
    }

}
