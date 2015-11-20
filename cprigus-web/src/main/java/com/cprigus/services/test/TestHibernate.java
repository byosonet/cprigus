package com.cprigus.services.test;

/**
 *
 * @author Priscila
 */
import com.cprigus.services.god.persistence.dao.MailTemplateDaoImpl;
import com.cprigus.services.god.persistence.dao.UsuarioDaoImpl;
import com.cprigus.services.god.persistence.hbm.Usuario;
import com.cprigus.services.util.UtilService;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
 
public class TestHibernate {
 
    public static void main(String[] args) throws Exception
    {
        ApplicationContext context = new FileSystemXmlApplicationContext("C:\\temporal\\god\\god-web\\src\\main\\java\\com\\cprigus\\services\\test\\applicationContextHibernate.xml");
        UsuarioDaoImpl usuario = (UsuarioDaoImpl) context.getBean("usuarioDao");
        MailTemplateDaoImpl mailTemplateDaoImpl = (MailTemplateDaoImpl) context.getBean("mailTemplateDao");
        
        for(Usuario user : usuario.getListaEmailNotificaciones("NO")){
            System.err.println(" -- Mail: "+user.getEmail()+" -- Status: "+user.getNotificaciones());
        }
        
        System.err.println(" -- Mail: "+mailTemplateDaoImpl.getMail(1).readClob(mailTemplateDaoImpl.getMail(1).getMailTemplate()));
        System.err.println(" -- Mail: "+mailTemplateDaoImpl.getMail(2).readClob(mailTemplateDaoImpl.getMail(2).getMailTemplate()));
        System.err.println(" -- Mail: "+mailTemplateDaoImpl.getMail(3).readClob(mailTemplateDaoImpl.getMail(3).getMailTemplate()));
        
        
        Usuario u = new Usuario();
        u.setNombre("GUSTAVO2");
        u.setEmail("gtrejo.armenta@gmail.com");
        u.setPassword("holamundo");
        u.setNotificaciones("SI");
        u.setSexo('M');
        u.setFechaNacimiento(new Date());
        u.setActividad("ESTUDIANTE");
        int idUsuario = usuario.agregarUsuario(u);
        System.err.println(" -- idUsuario agregado: "+idUsuario);
        
    }
}
