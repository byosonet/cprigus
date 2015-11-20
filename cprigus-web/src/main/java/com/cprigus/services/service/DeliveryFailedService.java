package com.cprigus.services.service;

import com.cprigus.services.god.persistence.hbm.DeliveryFailed;
import java.util.List;

/**
 *
 * @author User
 */
public interface DeliveryFailedService {
    void guardarDeliveryFailed(DeliveryFailed deliveryFailed);
    List<DeliveryFailed> getListMailFailed();
    void deleteDeliveryDailed(DeliveryFailed df);
    DeliveryFailed getById(int id);
}
