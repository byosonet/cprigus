package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.hbm.Coro;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author User
 */
public class CoroDaoImpl extends HibernateDaoSupport implements CoroDao{
    private final Logger log = Logger.getLogger(CoroDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();
    
    public List<Coro> obtenerListaCoroActualizada() {
        this.log.info(" -- Buscando por lista de coros por fecha::");
        return (List<Coro>) this
                .getSession()
                .createQuery("FROM Coro c " + "ORDER BY c.fechaAct DESC")
                .setMaxResults(2)
                .list();
    }

    public List<Coro> obtenerListacCompletaCoro() {
        this.log.info(" -- Buscando por lista de coros::");
        return (List<Coro>) this
                .getSession()
                .createQuery("FROM Coro c " + "ORDER BY c.nombre ASC")
                .list();
    }
    
    public Coro obtenerCoro(String idCoro) {
        this.log.info(" -- Buscando por coro por id ::"+idCoro);
        int coro = Integer.valueOf(idCoro);
        return (Coro) this
                .getSession()
                .createQuery("FROM Coro c WHERE c.idCoro = :id")
                .setParameter("id", coro)
                .uniqueResult();
    }

    public int agregarCoro(Coro coro) {
        try {
            this.mysql.iniciarOperacion();
            Number maximo = (Number) this.getSession().createQuery(
                    "SELECT MAX(c.idCoro) "
                    + "FROM Coro c")
                    .uniqueResult();

            long id = maximo == null ? 1 : maximo.longValue() + 1;
            coro.setIdCoro(Integer.parseInt(String.valueOf(id)));
            this.mysql.getSesion().save(coro);
            this.mysql.getSesion().flush();

            this.mysql.getTx().commit();
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
        return coro.getIdCoro();
    }

    public boolean validarNumeroCoro(String numCoro) {
        boolean statusNumeroCoro = false;
        this.log.info(" -- Buscando por número de coro ::" + numCoro);
        Coro coro = (Coro) this
                .getSession()
                .createQuery("FROM Coro c WHERE c.numCoro = :numCoro")
                .setParameter("numCoro", numCoro)
                .uniqueResult();
        if (coro == null)
            statusNumeroCoro = true;
        else
            this.log.info(" -- Este número de coro ya exite y esta asociado con el nombre de: "+coro.getNombre());
        return statusNumeroCoro;
    }

    public boolean validarNombreCoro(String nombre) {
       boolean statusNombreCoro = false;
        this.log.info(" -- Buscando por nombre de coro ::" + nombre);
        List<Coro> listCoro = (List<Coro>) this
                .getSession()
                .createQuery("FROM Coro c")   
                .list();
        
        for(Coro c : listCoro){
            if(c.getNombre().toUpperCase().trim().equals(nombre.toUpperCase().trim())){
                this.log.info(" -- Ya existe un nombre de coro asociado al id: "+c.getIdCoro());
                return statusNombreCoro;
            }
        }
        this.log.info(" -- Este nombre de coro es valido: "+nombre);
        statusNombreCoro = true;
        return statusNombreCoro;
    }

    public List<Coro> getListaCorosPendientes() {
        this.log.info(" -- Buscando por lista de coros pendientes::");
        return (List<Coro>) this
                .getSession()
                .createQuery("FROM Coro c WHERE c.activo != 2 " + "ORDER BY c.fechaAct DESC")
                .list();
    }

    public Coro getCoroById(int idCoro) {
        this.log.info(" -- Buscando por idCoro ::" + idCoro);
        return (Coro) this
                .getSession()
                .createQuery("FROM Coro c WHERE c.idCoro = :idCoro")
                .setParameter("idCoro", idCoro)
                .uniqueResult();
        
    }

    public void deleteCoro(Coro coro) {
        try {
            this.mysql.iniciarOperacion();
            if(coro!=null){
                this.mysql.getSesion().delete(coro);
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

    public void updateCoro(Coro coro) {
       try {
            this.mysql.iniciarOperacion();
            if(coro!=null){
                this.mysql.getSesion().update(coro);
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

    public Coro getByNumCoro(String numCoro) {
        this.log.info(" -- Buscando por numCoro ::" + numCoro);
        return (Coro) this
                .getSession()
                .createQuery("FROM Coro c WHERE c.numCoro = :numCoro")
                .setParameter("numCoro", numCoro)
                .uniqueResult();
    }

}
