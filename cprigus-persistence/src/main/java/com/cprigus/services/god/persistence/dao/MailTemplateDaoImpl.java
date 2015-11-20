package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.hbm.MailTemplate;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author User
 */
public class MailTemplateDaoImpl extends HibernateDaoSupport implements MailTemplateDao{
    private final Logger log = Logger.getLogger(MailTemplateDaoImpl.class);
    TransacctionMySQL mysql = new TransacctionMySQL();
    public MailTemplate getMail(int idMail) {
        this.log.info(" -- Obteniendo mail para el Id: "+idMail);
        return (MailTemplate) this.getSession()
                .createQuery("FROM MailTemplate mt WHERE mt.idMailTemplate = :idMail")
                .setParameter("idMail", idMail).uniqueResult();
    }
}
    

