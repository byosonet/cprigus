package com.cprigus.services.service;

/**
 *
 * @author Priscila
 */
import com.cprigus.services.god.persistence.hbm.DeliveryFailed;
import com.cprigus.services.god.persistence.hbm.MailTemplate;
import com.cprigus.services.god.persistence.hbm.TipoEnvioEmail;
import java.io.BufferedReader;
import java.io.File;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
@Component("emailSendService")
public class EmailSendServiceImpl implements EmailSendService
{
    @Autowired
    JavaMailSender mailSender;
 
    @Transactional
    public void sendEmailRegister(final String toEmail,final String bccEmail, final String user, final Map<String, File> attach) throws Exception {
        final MailTemplate mail = this.mailTemplateService.getEmail(1);
        final StringWriter swRegistro = new StringWriter();

        VelocityContext context = new VelocityContext();
        context.put("nombre", user);
        Velocity.evaluate(
                context,
                swRegistro,
                "velocity-mail-registro",
                new BufferedReader(mail.getMailTemplate().getCharacterStream()));
        final String actualMessageRegistro = swRegistro.toString();
        this.log.info(" -- Merge Template: " + actualMessageRegistro);

        try {
        final boolean multipart = attach != null && attach.size() > 0;
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(
                        mimeMessage, multipart, "UTF-8");
                messageHelper.setSubject(mail.getSubject());
                messageHelper.setTo(toEmail);
                messageHelper.setBcc(bccEmail);
                messageHelper.setText(actualMessageRegistro, true);
                if (attach != null) {
                    for (Map.Entry<String, File> entry : attach.entrySet()) {
                        messageHelper.addAttachment(entry.getKey(), entry.getValue());
                    }
                }
            }
        };
            this.mailSender.send(preparator);
            this.log.info(" -- Correo enviado a: "+toEmail);
        } catch (MailException e) {
            DeliveryFailed failed = new DeliveryFailed();
            failed.setMailTo(toEmail);
            failed.setMailFrom("godweb.mx@gmail.com");
            failed.setSubject(mail.getSubject());
            failed.setName(user);
            failed.setBody(failed.covertirStringToClob(actualMessageRegistro));
            failed.setTypeFailed(TipoEnvioEmail.MAIL_REGISTRO.name());
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
                this.log.info("-- Datetime::: "+stamp);
                Date date = new Date(stamp.getTime());
                this.log.info("-- Date::: "+date);
            failed.setDateSend(date);
            this.deliveryFailedService.guardarDeliveryFailed(failed);
            
            log.error(" -- Correo no pudo ser enviado: ", e);
            //throw new RuntimeException("No se pudo enviar mensaje de registro");
            
        }
    }

    @Transactional
    public void recuperarPassword(final String email, final String password, final String bccEmail) throws Exception {
        final MailTemplate mail = this.mailTemplateService.getEmail(2);
        final StringWriter swPassword = new StringWriter();

        VelocityContext context = new VelocityContext();
        context.put("tuclave", password);
        Velocity.evaluate(
                context,
                swPassword,
                "velocity-mail-password",
                new BufferedReader(mail.getMailTemplate().getCharacterStream()));
        final String actualMessagePassword = swPassword.toString();
        this.log.info(" -- Merge Template: " + actualMessagePassword);
        
        try {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(
                        mimeMessage, "UTF-8");
                messageHelper.setSubject(mail.getSubject());
                messageHelper.setTo(email);
                messageHelper.setBcc(bccEmail);
                messageHelper.setText(actualMessagePassword, true);
            }
        };
            this.mailSender.send(preparator);
            this.log.info(" -- Correo enviado a: "+email);
        } catch (MailException e) {
            DeliveryFailed failed = new DeliveryFailed();
            failed.setMailTo(email);
            failed.setMailFrom("godweb.mx@gmail.com");
            failed.setSubject(mail.getSubject());
            failed.setName(email);
            failed.setBody(failed.covertirStringToClob(actualMessagePassword));
            failed.setTypeFailed(TipoEnvioEmail.MAIL_PASSWORD.name());
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
                this.log.info("-- Datetime::: "+stamp);
                Date date = new Date(stamp.getTime());
                this.log.info("-- Date::: "+date);
            failed.setDateSend(date);
            this.deliveryFailedService.guardarDeliveryFailed(failed);

            log.error(" -- Correo no pudo ser enviado: ", e);
            throw new RuntimeException("No se pudo enviar mensaje de password");
            
        }
    }

    @Transactional
    public void contactoSistema(final String emailSistema, final String asunto, final String nombre, final String emailCliente, final String boydAsunto) throws Exception{
       final MailTemplate mail = this.mailTemplateService.getEmail(3);
       final StringWriter swContacto = new StringWriter();

        VelocityContext context = new VelocityContext();
        context.put("nombre", nombre.toUpperCase());
        context.put("asunto", asunto.toUpperCase());
        context.put("emailUsuario", emailCliente);
        context.put("body", boydAsunto);
        Velocity.evaluate(
                context,
                swContacto,
                "velocity-mail-password",
                new BufferedReader(mail.getMailTemplate().getCharacterStream()));
        final String actualMessageContacto = swContacto.toString();
        this.log.info(" -- Merge Template: " + actualMessageContacto);
        
        try {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(
                        mimeMessage, "UTF-8");
                messageHelper.setSubject(asunto);
                messageHelper.setTo(emailSistema);
                messageHelper.setText(actualMessageContacto, true);
            }
        };
            this.mailSender.send(preparator);
            this.log.info(" -- Correo enviado a: "+emailSistema);
        } catch (MailException e) {
            DeliveryFailed failed = new DeliveryFailed();
            failed.setMailTo("godweb.mx@gmail.com");
            failed.setMailFrom(emailCliente);
            failed.setSubject(asunto);
            failed.setName(nombre);
            failed.setBody(failed.covertirStringToClob(actualMessageContacto));
            failed.setTypeFailed(TipoEnvioEmail.MAIL_CONTACTO.name());
            Timestamp stamp = new Timestamp(System.currentTimeMillis());
                this.log.info("-- Datetime::: "+stamp);
                Date date = new Date(stamp.getTime());
                this.log.info("-- Date::: "+date);
            failed.setDateSend(date);
            this.deliveryFailedService.guardarDeliveryFailed(failed);
            
            log.error(" -- Correo no pudo ser enviado: ", e);
            throw new RuntimeException("No se pudo enviar mensaje de contacto");
        }
    }
    
    @Autowired
    private DeliveryFailedService deliveryFailedService;
    
    @Autowired
    private MailTemplateService mailTemplateService;
    
    private final Logger log = Logger.getLogger(EmailSendServiceImpl.class);
}