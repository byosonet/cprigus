package com.cprigus.services.service;

import com.cprigus.services.god.persistence.hbm.MailTemplate;

/**
 *
 * @author User
 */
public interface MailTemplateService {
    MailTemplate getEmail (int idMail);
}
