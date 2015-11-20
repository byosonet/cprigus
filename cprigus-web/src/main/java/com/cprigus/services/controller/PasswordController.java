package com.cprigus.services.controller;

import com.cprigus.services.god.persistence.hbm.TipoMovimientoEnum;
import com.cprigus.services.god.persistence.hbm.Usuario;
import com.cprigus.services.model.ErrorService;
import com.cprigus.services.service.ChangesetService;
import com.cprigus.services.service.EmailSendService;
import com.cprigus.services.service.UsuarioService;
import com.cprigus.services.util.UtilService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/password")
public class PasswordController {
    private final Logger log = Logger.getLogger(PasswordController.class);
 
    @RequestMapping(value="/recuperar", method = RequestMethod.GET)
    public String recuperarPassword(Model model){
         //activar menu
        model.addAttribute("menu","smenu");
        return "password";
    }
    
    @RequestMapping(value="/enviar/email", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> enviarPasswordEmail(Model model, HttpServletRequest request){
        String email = request.getParameter("email");
        String confEmail = request.getParameter("confEmail");
        HttpStatus status = HttpStatus.NOT_FOUND;
        this.log.info(" -- Request recibido emai: "+email);
        this.log.info(" -- Request recibido emai: "+confEmail);
        
        ErrorService response = new ErrorService();
        Usuario user = this.usuarioService.validaEmailSistema(email);
        if(user!=null){
            this.guardarChangeset(TipoMovimientoEnum.RECUPERAR_TU_PASSWORD.getTipo(), user);
            
            this.log.info(" -- Enviar email de password a usuario: "+user.getNombre());
            response.setCodigo("202");
            response.setMensaje("Hola "+user.getNombre()+", tu password ha sido enviado a: "+user.getEmail());
            status = HttpStatus.OK;
            
            try
            {
                this.emailSendService.recuperarPassword(email, UtilService.Desencriptar(user.getPassword()),"gtrejo.armenta@gmail.com");
                this.log.info(" -- El correo fue enviado con tu password a: " + email);
            }catch(Exception ex){
                 response.setCodigo("404");
                 response.setMensaje("Por el momento no se pudo enviar tu password a tu correo, intenta más tarde.");
                 status = HttpStatus.NOT_FOUND;
                 this.log.info(" -- El password no pudo ser enviado por el sistema");    
            }
            
        }else{
            this.log.info(" -- Este email no esta registrado en el sistema");
            response.setCodigo("404");
            response.setMensaje("Este email "+email+" no ha sido registrado en el sistema.");
        }
        return new ResponseEntity<ErrorService>(response,status);
    }
    
    private void guardarChangeset(String movement, Usuario user){
        for(TipoMovimientoEnum tipos: TipoMovimientoEnum.values()){
            if(tipos.getTipo().equals(movement)){
                this.changesetService.guardarChangeset(
                tipos.name(),
                new Date(UtilService.getFechaTimeStamp().getTime()), 
                user.getIdUsuario(), null);
                break;
            }
        }
    }
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EmailSendService emailSendService;
    
    @Autowired
    private ChangesetService changesetService;
    
}
