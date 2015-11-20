package com.cprigus.services.controller;

import com.cprigus.services.god.persistence.dto.ConsultaCoro;
import com.cprigus.services.god.persistence.hbm.Changeset;
import com.cprigus.services.god.persistence.hbm.Coro;
import com.cprigus.services.god.persistence.hbm.PropiedadSistema;
import com.cprigus.services.god.persistence.hbm.TipoMovimientoEnum;
import com.cprigus.services.god.persistence.hbm.Usuario;
import com.cprigus.services.service.ChangesetService;
import com.cprigus.services.service.CoroService;
import com.cprigus.services.service.PropiedadSistemaService;
import com.cprigus.services.service.UsuarioService;
import com.cprigus.services.util.UtilService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/lista")
public class ListaHimnarioController {
    private final Logger log = Logger.getLogger(ListaHimnarioController.class);
    @RequestMapping(value="/obtener",method = RequestMethod.POST)
   public String obtenerListaHimnario(Model model, HttpServletRequest request) {
       String email = request.getParameter("idEmail");
       String pass = request.getParameter("idPassword");
       
       this.log.info(" -- email: "+email);
       this.log.info(" -- password: "+pass);
       
       String encriptarPassword = UtilService.Encriptar(pass);
       Usuario user = this.usuarioService.validaUsuario(email, encriptarPassword);
       
       if(user!=null){
           this.guardarChangeset(TipoMovimientoEnum.CONSULTAR_HIMNARIO.getTipo(), user);
            
            //RECUPERANDO INFORMACION DE CONSULTA DE COROS
             int rows=0;
                  PropiedadSistema ps =
                          this.propiedadSistemaService.obtenerValorPropiedad("rows.query.choir");
                  if(ps!=null)
                     rows = Integer.valueOf(ps.getValue());
                  List<ConsultaCoro> cc = this.changesetService.getListaCoros(rows);
                  if(cc!=null){
                      for(int i=0; i<cc.size();i++){
                           model.addAttribute("fecha"+i, cc.get(i).getFecha());
                           model.addAttribute("total"+i, cc.get(i).getTotal());
                      }
                  }
                  
            //DEVOLVIENDO LISTA DE USUARIOS
                  String mailAdmin = this.propiedadSistemaService.obtenerValorPropiedad("mail.admin").getValue();
                  String conectado = this.propiedadSistemaService.obtenerValorPropiedad("mail.admin.connect").getValue();
                  if(mailAdmin.equals(user.getEmail()) && conectado.equals("TRUE")){
                      model.addAttribute("show", true);
                      model.addAttribute("listaUsuario", this.usuarioService.getListaUsuarios());
                  }else{
                      model.addAttribute("show", false);
                  }      

            //Retornando Lista de movimientos de usuario
            List<Changeset> listCh = 
                    this.changesetService.listaChangeset(String.valueOf(user.getIdUsuario()));
            if(listCh != null){
                model.addAttribute("changesetUser", listCh);
            }
        }
       
       try{
           List<Coro> corosActualizados = this.coroService.obtenerListaCoroActualizada();
           List<Coro> corosCompletos = this.coroService.obtenerListaCoroCompleta();
           if(corosActualizados!=null && corosActualizados.size()>0){
               model.addAttribute("coros", corosActualizados);
               model.addAttribute("corosCompletos", corosCompletos);
               model.addAttribute("usuario", user.getNombre());
               
               //model.addAttribute("userEmail", user.getEmail());
               //model.addAttribute("userPassword", pass);
               String cifrar = UtilService.Encriptar(user.getEmail()+";"+pass);
               model.addAttribute("cifrar",cifrar);
               
               //activar menu
               model.addAttribute("menu",true);
               
               //retornando los datos del perfil
               model.addAttribute("nombreUsuario", user.getNombre());
               model.addAttribute("falta", user.getFechaAlta());
               model.addAttribute("fconexion", user.getUltConexion());
               model.addAttribute("emailUsuario", user.getEmail());
               model.addAttribute("actividad", user.getActividad());
               model.addAttribute("sexo", user.getSexo()=='M'?"Masculino":"Femenino");
               model.addAttribute("fnacimiento", user.getFechaNacimiento());
               
               //Dial
               model.addAttribute("accesoSistema", 
                       this.changesetService.totalMovement(TipoMovimientoEnum.ACCESO_AL_SISTEMA, String.valueOf(user.getIdUsuario())));
               model.addAttribute("registroCoro", 
                       this.changesetService.totalMovement(TipoMovimientoEnum.REGISTRO_DE_CORO, String.valueOf(user.getIdUsuario())));
               model.addAttribute("consultarPerfil", 
                       this.changesetService.totalMovement(TipoMovimientoEnum.VALIDAR_USUARIO, String.valueOf(user.getIdUsuario())));
               model.addAttribute("consultarCoro", 
                       this.changesetService.totalMovement(TipoMovimientoEnum.CONSULTAR_CORO, String.valueOf(user.getIdUsuario())));
               model.addAttribute("emailContacto", 
                       this.changesetService.totalMovement(TipoMovimientoEnum.ENVIO_CORREO_CONTACTO, String.valueOf(user.getIdUsuario())));
               model.addAttribute("actUsuario", 
                       this.changesetService.totalMovement(TipoMovimientoEnum.ACTUALIZAR_PERFIL, String.valueOf(user.getIdUsuario())));
               
               //retornando los aviso
               PropiedadSistema tituloAviso = this.propiedadSistemaService.obtenerValorPropiedad("titulo.aviso");
               PropiedadSistema detalleAviso = this.propiedadSistemaService.obtenerValorPropiedad("detalle.aviso");
               
               if(tituloAviso.getActive() == 1){
                model.addAttribute("titulo", tituloAviso.getValue());
                model.addAttribute("detalle", detalleAviso.getValue());
               }else{
                    model.addAttribute("titulo", "<p class=\"alert alert-info\">Por el momento no hay avisos disponibles.</p>");
                    model.addAttribute("detalle", "");
               }
               
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
      return "listaHimnario";
   }
   
   @RequestMapping(value="/obtener",method = RequestMethod.GET)
   public String ingresarGET(Model model, HttpServletRequest request) {
      return "forbidden";
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
   private CoroService coroService;
   
   @Autowired
   private UsuarioService usuarioService;
   
   @Autowired
   private PropiedadSistemaService propiedadSistemaService;
   
   @Autowired
   private ChangesetService changesetService;
}
