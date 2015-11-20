package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.hbm.Usuario;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author User
 */
public class UsuarioDaoImpl extends HibernateDaoSupport implements UsuarioDao{
    private final Logger log = Logger.getLogger(UsuarioDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();

    public List<Usuario> getUser() {
       this.log.info(" -- Buscando por lista de usuarios::");
        return (List<Usuario>) this
                .getSession()
                .createQuery("FROM Usuario u " + "ORDER BY u.idUsuario ASC").list();
    }

    public Usuario validaUsuario(String email, String password) {
        this.log.info(" -- Buscando usuario by email:: "+email);
        return (Usuario) this
                .getSession()
                .createQuery("FROM Usuario u WHERE u.email = :email AND u.password = :password")
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
    }

    public Usuario validaEmailSistema(String email) {
        this.log.info(" -- Verificando email en BD:: "+email);
        return (Usuario) this
                .getSession()
                .createQuery("FROM Usuario u WHERE u.email = :email")
                .setParameter("email", email)
                .uniqueResult();
    }
    
    public void actualizarConexionUsuario(Usuario usuario) {
         try {
            this.mysql.iniciarOperacion();
            if(usuario!=null){
                Timestamp stamp = new Timestamp(System.currentTimeMillis());
                this.log.info("-- Datetime::: "+stamp);
                Date date = new Date(stamp.getTime());
                this.log.info("-- Date::: "+date);
                usuario.setUltConexion(date);
                this.mysql.getSesion().saveOrUpdate(usuario);
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

    public int agregarUsuario(Usuario usuario) {
        try {
            this.mysql.iniciarOperacion();
            Number maximo = (Number) this.getSession().createQuery(
                    "SELECT MAX(u.idUsuario) "
                    + "FROM Usuario u")
                    .uniqueResult();

            long id = maximo == null ? 1 : maximo.longValue() + 1;
            usuario.setIdUsuario(Integer.parseInt(String.valueOf(id)));
            this.mysql.getSesion().save(usuario);
            this.mysql.getSesion().flush();

            this.mysql.getTx().commit();
        } catch (HibernateException he) {
            this.mysql.manejarException(he);
            throw he;
        } finally {
            this.mysql.getSesion().close();
        }
        return usuario.getIdUsuario();
    }

    public void actualizarDatosUsuario(Usuario user) {
        try {
            this.mysql.iniciarOperacion();
            if(user!=null){
                this.mysql.getSesion().saveOrUpdate(user);
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

    public void deleteUser(Usuario usuario) {
        try {
            this.mysql.iniciarOperacion();
            if(usuario!=null){
                this.mysql.getSesion().delete(usuario);
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

    public Usuario byId(int idUser) {
        this.log.info(" -- Buscando usuario by id:: "+idUser);
        return (Usuario) this
                .getSession()
                .createQuery("FROM Usuario u WHERE u.idUsuario = :idUser")
                .setParameter("idUser", idUser)
                .uniqueResult();
    }

    public List<Usuario> getListaEmailNotificaciones(String notificar) {
        return (List<Usuario>) this.getSession().createQuery
        ("FROM Usuario u WHERE u.notificaciones = :tipo")
                .setParameter("tipo", notificar)
                .list();
    }
    
    
}
