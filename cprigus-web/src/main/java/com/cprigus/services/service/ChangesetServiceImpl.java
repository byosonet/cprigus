package com.cprigus.services.service;

import com.cprigus.services.god.persistence.dao.ChangesetDao;
import com.cprigus.services.god.persistence.dto.ConsultaCoro;
import com.cprigus.services.god.persistence.hbm.Changeset;
import com.cprigus.services.god.persistence.hbm.TipoMovimientoEnum;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
public class ChangesetServiceImpl implements ChangesetService{
    private final Logger log = Logger.getLogger(ChangesetServiceImpl.class);
    
    @Transactional
    public void guardarChangeset(String tipo, Date date, int idUsuario, String idCoro) {
        this.log.info(" -- ValorTipo: "+tipo);
        try {
              Changeset changeset = new Changeset();
              changeset.setFechaMov(date);
              changeset.setIdCoro(idCoro!=null?idCoro:"NA");
              changeset.setIdUsuario(String.valueOf(idUsuario));
              changeset.setMovement(tipo.replace("_", " "));

            this.log.info(" -- Service Guardando Changeset en BD: "+changeset.toString());
            this.changesetDao.guardarChangeset(changeset);
        } catch (Exception ex) {
            this.log.error(" -- No se puede guardar el changeset en BD: "+ex.getMessage());
        }
    }

    @Transactional
    public List<Changeset> listaChangeset(String idUser) {
        this.log.info(" -- Obteniendo lista de Changeset de Usuario: "+idUser);
        List<Changeset> list = this.changesetDao.getChangesetUser(idUser);
        /*if(list != null){
            for(Changeset ch: list){
                this.log.info(" -- idCh: "+ ch.getIdChangeset()+" Tipo Movimiento: "+ch.getMovement()+" Fecha: "+ch.getFechaMov());
            }
        }*/
        return list;
    }

    @Transactional
    public int totalMovement(TipoMovimientoEnum tipo, String idUser) {
        int total = this.changesetDao.totalMovemente(tipo, idUser);
        this.log.info(" -- Recuperando total de movimientos para: "+tipo.name()+" Total encontrado: "+total);
        return total;
    }

    @Transactional
    public List<ConsultaCoro> getListaCoros(int days) {
        this.log.info(" -- Accediendo service chagenset: getListaCoros");
        return this.changesetDao.getConsultaCoro(days);
    }

    @Autowired
    private ChangesetDao changesetDao;
    
}
