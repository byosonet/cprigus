package com.cprigus.services.batch;

import com.cprigus.services.service.PropiedadSistemaService;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author User
 */
public class MonitorStatusImpl implements MonitorStatus{
    private final Logger log = Logger.getLogger(MonitorStatusImpl.class);
    
    public void status() {
        this.log.info(" -- Status del servidor: OK");
        String date = new Date().toString();
        this.propiedadSistemaService.guardarPropiedad("date.system", date);
        this.log.info(" -- DateSystem save: "+date);
    }
    
    public MonitorStatusImpl getInstance(){
        return MonitorStatusImpl.InstanceHolder.SINGLETON;
    }
   
    private static final class InstanceHolder{
        private static final MonitorStatusImpl SINGLETON = new MonitorStatusImpl();
    }
    
    private MonitorStatusImpl(){}
    
   @Autowired
   private PropiedadSistemaService propiedadSistemaService;
}
