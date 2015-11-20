package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.dto.ConsultaCoro;
import com.cprigus.services.god.persistence.hbm.Changeset;
import com.cprigus.services.god.persistence.hbm.TipoMovimientoEnum;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author User
 */
public class ChangesetDaoImpl extends HibernateDaoSupport implements ChangesetDao{
    private final Logger log = Logger.getLogger(ChangesetDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();
    
    public void guardarChangeset(Changeset changeset) {
       try {
            this.mysql.iniciarOperacion();
            this.mysql.getSesion().save(changeset);
            this.mysql.getSesion().flush();
            this.mysql.getTx().commit();
            this.log.info(" -- Guardando Changeset en BD: "+changeset.toString());
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
    }

    public List<Changeset> getChangesetUser(String idUser) {
         List<Changeset> list = (List<Changeset>) this.getSession()
                .createQuery("FROM Changeset ch WHERE ch.idUsuario = :idUser ORDER BY ch.idChangeset DESC")
                .setParameter("idUser", idUser)
                .list();
         return list;
    }

    public int totalMovemente(TipoMovimientoEnum movement, String idUser) {
            Number maximo = (Number) this.getSession().createQuery(
                    "SELECT COUNT(*) "
                    + "FROM Changeset ch WHERE ch.idUsuario = :idUser AND ch.movement = :movement")
                    .setParameter("idUser", idUser)
                    .setParameter("movement", movement.name().replace("_", " "))
                    .uniqueResult();
            return maximo.intValue();
    }

    public List<ConsultaCoro> getConsultaCoro(int days) {
       List<ConsultaCoro> recuperado = new ArrayList<ConsultaCoro>();
       this.log.info(" -- days Solicitados: "+days);
       List<Object[]> listaTemp = (List<Object[]>)this.getSession().createSQLQuery("SELECT count(*) AS TOTAL, "
                + "DATE_FORMAT(FECHA,'%d-%m-%Y') AS FECHA FROM changeset "
                + "WHERE MOVEMENT='CONSULTAR CORO' GROUP BY DATE(FECHA)"
        ).list();
       
       this.log.info(" -- days Encontrados: "+listaTemp.size());
       
       List<Object[]> lista = (List<Object[]>)this.getSession().createSQLQuery("SELECT count(*) AS TOTAL, "
                + "DATE_FORMAT(FECHA,'%d-%m-%Y') AS FECHA FROM changeset "
                + "WHERE MOVEMENT='CONSULTAR CORO' GROUP BY DATE(FECHA)"
        )
        .setFirstResult((listaTemp.size()>0 && listaTemp.size()>days)? listaTemp.size()-days: 0)
        .setMaxResults(days)
        .list();
        if(lista!=null){
            this.log.info(" -- Total Lista: "+lista.size());
            for(int i=0; i<lista.size();i++){
                ConsultaCoro cc = new ConsultaCoro();
                cc.setTotal(lista.get(i)[0].toString());
                cc.setFecha(lista.get(i)[1].toString());
                this.log.info(" Agregando Total: "+cc.getTotal()+" Fecha: "+cc.getFecha());
                recuperado.add(cc);
            }
        }
        return recuperado;
    }
    
    
}
