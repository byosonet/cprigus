package com.cprigus.services.batch;

import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author User
 */
public class MonitorFechaImpl implements MonitorFecha{
    private final Logger log = Logger.getLogger(MonitorFechaImpl.class);
    public void fecha() {
        this.log.info(" -- Fecha de Sistema: "+new Date());
    }
    
    public static MonitorFechaImpl getInstance(){
        return MonitorFechaImpl.InstanceHolder.SINGLETON;
    }
    private static final class InstanceHolder{
        private static final MonitorFechaImpl SINGLETON=
            new MonitorFechaImpl();
    }
    private MonitorFechaImpl(){}
}
