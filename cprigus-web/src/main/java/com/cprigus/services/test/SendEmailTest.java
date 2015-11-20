package com.cprigus.services.test;

/**
 *
 * @author Priscila
 */
import com.cprigus.services.service.EmailSendServiceImpl;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
 
public class SendEmailTest {
 
    public static void main(String[] args) throws Exception
    {
        Map<String, File> atachh = new HashMap<String,File>();
        atachh.put("Archivo.png", new File("C:\\temporal\\god\\god-web\\src\\main\\webapp\\resources\\img\\503.png"));
        atachh.put("Archivo.jpg", new File("C:\\temporal\\god\\god-web\\src\\main\\webapp\\resources\\img\\500.jpg"));
        
        ApplicationContext context = new FileSystemXmlApplicationContext("C:\\temporal\\god\\god-web\\src\\main\\java\\com\\rest\\services\\test\\applicationEmail-context.xml");
        EmailSendServiceImpl mailer = (EmailSendServiceImpl) context.getBean("emailSendService");
        mailer.sendEmailRegister("byosonet@gmail.com", "gtrejo.armenta@gmail.com","Gustavo Trejo",atachh);
        //mailer.sendPreConfiguredMail("Exception occurred everywhere.. where are you ????");
    }
}