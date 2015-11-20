package com.cprigus.services.god.persistence.dao;

import com.cprigus.services.god.persistence.hbm.MailTemplate;

/**
 *
 * @author User
 */
public interface MailTemplateDao {
    MailTemplate getMail(int idMail);
}
