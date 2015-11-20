package com.cprigus.services.service;

import com.cprigus.services.god.persistence.dao.DeliveryFailedDao;
import com.cprigus.services.god.persistence.hbm.DeliveryFailed;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
public class DeliveryFailedServiceImpl implements DeliveryFailedService{
    private final Logger log = Logger.getLogger(DeliveryFailedServiceImpl.class);
    
    @Transactional
    public void guardarDeliveryFailed(DeliveryFailed deliveryFailed) {
        this.deliveryFailedDao.guardarDeliveryFailed(deliveryFailed);
        this.log.info(" -- Guardando la información del mail no enviado."); 
    }

    @Transactional
    public List<DeliveryFailed> getListMailFailed() {
        this.log.info(" -- Retornado lista de mail failed:");
        return this.deliveryFailedDao.getListMailFailed();
    }

    @Transactional
    public void deleteDeliveryDailed(DeliveryFailed df) {
        this.log.info(" -- Borrar mail failed: "+df.toString());
        this.deliveryFailedDao.deleteDeliveryFailed(df);
    }

    @Transactional
    public DeliveryFailed getById(int id) {
        this.log.info(" -- Buscando DeliveryFailed por id: "+ id);
        return this.deliveryFailedDao.getById(id);
    }

    
    @Autowired
    private DeliveryFailedDao deliveryFailedDao;
    
}
