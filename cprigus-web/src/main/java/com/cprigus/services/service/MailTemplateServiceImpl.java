package com.cprigus.services.service;

import com.cprigus.services.god.persistence.dao.MailTemplateDao;
import com.cprigus.services.god.persistence.hbm.MailTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
public class MailTemplateServiceImpl implements MailTemplateService{
    private final Logger log = Logger.getLogger(MailTemplateServiceImpl.class);
    @Transactional
    public MailTemplate getEmail(int idMail) {
        MailTemplate mail = this.mailTemplateDao.getMail(idMail);
        try{
        this.log.info(" -- Mail Obtenido: "+mail.readClob(mail.getMailTemplate()));
        }catch(Exception ex){
            this.log.error(" -- Error: "+ex.getMessage());
        }
        return mail;
    }
    
    
    @Autowired
    private MailTemplateDao mailTemplateDao;
    
}
