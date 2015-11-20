package com.cprigus.services.controller;

import com.cprigus.services.god.persistence.dao.DeliveryFailedDao;
import com.cprigus.services.god.persistence.hbm.DeliveryFailed;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author User
 */
@Controller
public class DetalleMailTemplateController {
     private final Logger log = Logger.getLogger(DetalleMailTemplateController.class);
     @RequestMapping(value="/detalle/mail/{idTemplateFailed}",method = RequestMethod.GET)
     
     public String obtenerDetalleTemplate(Model model, @PathVariable("idTemplateFailed") String idTemplateFailed) throws SQLException, IOException {
       this.log.info(" -- idTemplateFailed Received: "+idTemplateFailed);
         DeliveryFailed mail = this.deliveryFailedDao.getById(Integer.valueOf(idTemplateFailed));
         if(mail!=null){
             this.log.info(" -- Mail found: "+mail.readClob(mail.getBody()));
             model.addAttribute("detalle", mail.readClob(mail.getBody()));
             model.addAttribute("menu", "detalleMenu");
             return "detalleMailTemplateFailed";
         }
       return "forbidden";
    }
     
     
     @Autowired
     private DeliveryFailedDao deliveryFailedDao;
}
