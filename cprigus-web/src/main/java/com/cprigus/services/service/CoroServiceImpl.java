package com.cprigus.services.service;

import com.cprigus.services.god.persistence.dao.CoroDao;
import com.cprigus.services.god.persistence.hbm.Coro;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
public class CoroServiceImpl implements CoroService{
    private final Logger log = Logger.getLogger(CoroServiceImpl.class);

    @Transactional
    public List<Coro> obtenerListaCoroActualizada() {
       this.log.info(" -- Obteniendo Lista de Coros por fechas:::");
       
       List<Coro> coros = this.coroDao.obtenerListaCoroActualizada();
       /*if(coros!=null && coros.size()>0){
           for(Coro c : coros){
               try {
                   this.log.info(" -- idCoro: "+c.getIdCoro()+" Nombre: "+c.getNombre());
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
       }*/
       return coros;
    }

    @Transactional
    public List<Coro> obtenerListaCoroCompleta() {
       this.log.info(" -- Obteniendo Lista de Coros completa :::");
       
       List<Coro> coros = this.coroDao.obtenerListacCompletaCoro();
       /*if(coros!=null && coros.size()>0){
           for(Coro c : coros){
               try {
                   this.log.info(" -- idCoro: "+c.getIdCoro()+" Nombre del Coro: "+c.getNombre());
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
       }*/
       return coros;
    }
    
    

    @Transactional
    public Coro obtenerCoro(String idCoro) {
        this.log.info(" -- Obteniendo Coro");
        Coro coro = this.coroDao.obtenerCoro(idCoro);
        if(coro!=null){
            this.log.info(" -- Coro encontrado: "+coro.getNombre());
            return coro;
        }
        return coro;
    }

    @Transactional
    public int agregarCoro(Coro coro) {
        int idCoro = 0;
        this.log.info(" -- Agregando coro al sistema: " + coro.toString());
        try {
            idCoro = this.coroDao.agregarCoro(coro);
        } catch (Exception ex) {
            this.log.error(" -- El coro no se ha podido dar de alta en el sistema: " + ex.getMessage());
        }
        return idCoro;
    }

    @Transactional
    public boolean validarNumCoro(String numero) {
        this.log.info(" -- ServiceCoro recibiendo y validando numero de coro: "+numero);
        return this.coroDao.validarNumeroCoro(numero);
    }

    @Transactional
    public boolean validarNombreCoro(String nombre) {
        this.log.info(" -- ServiceCoro recibiendo y validando nombre de coro: "+nombre);
        return this.coroDao.validarNombreCoro(nombre);
    }

    @Transactional
    public List<Coro> getListaPendiente() {
       this.log.info(" -- Obteniendo la lista de coros pendientes: ");
       return this.coroDao.getListaCorosPendientes();
    }

    @Transactional
    public Coro getCoroById(int idCoro) {
        this.log.info(" -- Obteniendo coro por id: "+idCoro);
        return this.coroDao.getCoroById(idCoro);
    }

    @Transactional
    public void deleteCoro(Coro coro) {
        this.log.info(" -- Eliminar coro: "+coro.toString());
        this.coroDao.deleteCoro(coro);
    }

    @Transactional
    public void updateCoro(Coro coro) {
       this.log.info(" -- Actualizar coro: "+coro.toString());
       this.coroDao.updateCoro(coro);
    }

    @Transactional
    public Coro getByNumCoro(String numCoro) {
        this.log.info(" -- Buscando por numCoro: "+numCoro);
        return this.coroDao.getByNumCoro(numCoro);
    }

    @Autowired
    private CoroDao coroDao;
}
