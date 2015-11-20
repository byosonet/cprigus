package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.hbm.DeliveryFailed;
import java.util.List;

/**
 *
 * @author User
 */
public interface DeliveryFailedDao {
    void guardarDeliveryFailed(DeliveryFailed deliveryFailed);
    List<DeliveryFailed> getListMailFailed();
    void deleteDeliveryFailed(DeliveryFailed df);
    DeliveryFailed getById(int idDelivery);
}
