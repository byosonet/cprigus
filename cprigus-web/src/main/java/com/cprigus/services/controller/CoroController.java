package com.cprigus.services.controller;


import com.cprigus.services.god.persistence.hbm.Coro;
import com.cprigus.services.god.persistence.hbm.PropiedadSistema;
import com.cprigus.services.god.persistence.hbm.TipoMovimientoEnum;
import com.cprigus.services.god.persistence.hbm.Usuario;
import com.cprigus.services.model.ErrorService;
import com.cprigus.services.service.ChangesetService;
import com.cprigus.services.service.CoroService;
import com.cprigus.services.service.PropiedadSistemaService;
import com.cprigus.services.service.UsuarioService;
import com.cprigus.services.util.UtilService;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author User
 */
@Controller
public class CoroController {
    private final Logger log = Logger.getLogger(CoroController.class);
   @RequestMapping(value="/coro/{cifrar}/{idCoro}",method = RequestMethod.GET)
   public String obtenerListaHimnario(Model model, @PathVariable("cifrar") String cifrar, @PathVariable("idCoro") String idCoro) throws SQLException, IOException, Exception {
       
       try{
        String id = idCoro;
        this.log.info(" -- idCoro: "+id);
        this.log.info(" -- Cifrado: "+cifrar);
        String descifrado = UtilService.Desencriptar(cifrar);
        this.log.info(" -- Descifrado: "+descifrado);
        String[] data = descifrado.split(";");

        String userEmail = data[0];
        String userPassword = data[1];
        this.log.info(" -- IdCoro recibido:::: "+id);
        this.log.info(" -- userEmail recibido:::: "+userEmail);
        this.log.info(" -- userPassword recibido:::: "+userPassword);

        Coro coro = this.coroService.obtenerCoro(id);
        if(coro!=null){
            Usuario user = this.usuarioService.validaEmailSistema(userEmail);
            PropiedadSistema ps = this.propiedadSistemaService.obtenerValorPropiedad("mail.admin");
            PropiedadSistema conectado = this.propiedadSistemaService.obtenerValorPropiedad("mail.admin.connect");
            if(user!=null){
                if(ps!=null){
                    if(user.getEmail().equals(ps.getValue()) && conectado.getValue().equals("TRUE")){
                        model.addAttribute("show",true);
                    }
                }
                this.guardarChangeset(TipoMovimientoEnum.CONSULTAR_CORO.getTipo(), user, coro);
            }
            if(String.valueOf(coro.getIdCoro()).equals(id)){
                model.addAttribute("idCoro", id);
                model.addAttribute("nombre", coro.getNombre());

                if(coro.getActivo() == 2){
                model.addAttribute("coro", coro.getDescripcion()!=null?coro.getDataClob(coro.getDescripcion()):"El detalle de este coro no está disponible por el momento.");
                }else if(coro.getActivo() == 1){
                   coro.setDescripcion(null);
                   model.addAttribute("status", "1");
                   model.addAttribute("coro", "El detalle de este coro está en proceso de: VALIDACIÓN.");
                }else{
                   coro.setDescripcion(null);
                   model.addAttribute("status", "0");
                   model.addAttribute("coro", "El detalle de este coro está en proceso de: PENDIENTE.");
                }
                model.addAttribute("numCoro", coro.getNumCoro());

                //model.addAttribute("userEmail", userEmail);
                //model.addAttribute("userPassword", userPassword);
                model.addAttribute("cifrar", cifrar);

                model.addAttribute("statusDescripcion", true);

                if(coro.getDescripcion()==null){
                    model.addAttribute("statusDescripcion", false);
                }else if(coro.getDataClob(coro.getDescripcion()).trim().equals("")){
                    model.addAttribute("statusDescripcion", false);
                    model.addAttribute("coro", "El detalle de este coro no está disponible por el momento.");
                    model.addAttribute("status", "1");
                }
                return "detalle";
            }
        }
       }catch(Exception ex){
           ex.printStackTrace();
       }
    return "notfoundCoro";
   }
   
   @RequestMapping(value="/agregar/coro",method = RequestMethod.POST)
   public ResponseEntity<ErrorService> agregarCoro(Model model, HttpServletRequest request) throws SQLException, Exception {
       HttpStatus status = HttpStatus.NOT_FOUND;
       
       
       String cifrar = request.getParameter("cifrar");
       String descifrado = UtilService.Desencriptar(cifrar);
       this.log.info(" -- Descifrado: "+descifrado);
       String[] data = descifrado.split(";");
       String userEmail = data[0];
       Usuario user = this.usuarioService.validaEmailSistema(userEmail);
       
       String nombreCoro = request.getParameter("nombreCoro");
       String autor = request.getParameter("autor");
       String numCoro = request.getParameter("numCoro");
       String desccoro = request.getParameter("summernoteText");
       
       this.log.info(" -- Nombre del Coro: "+nombreCoro);
       this.log.info(" -- Autor del Coro: "+autor);
       this.log.info(" -- # del Coro: "+numCoro);
       this.log.info(" -- Descripción del Coro: "+desccoro);
       
       ErrorService response = new ErrorService();
       response.setCodigo("404");
       response.setMensaje("Por el momento no es posible agregar el coro al sistema, intente más tarde.");
       
       Timestamp stamp = new Timestamp(System.currentTimeMillis());
       this.log.info(" -- Fecha de Alta::: "+stamp);
       Date fechaAlta = new Date(stamp.getTime());

       Coro coro = new Coro();
       coro.setActivo(0);
       coro.setAutor(autor);
       coro.setNumCoro(numCoro);
       coro.setFechaAct(fechaAlta);
       coro.setDescripcion(coro.covertirStringToClob(desccoro));
       coro.setNombre(nombreCoro.toUpperCase());

       int longNombre = coro.getNombre().length();
       if (longNombre > 99) {
           this.log.info(" -- Longitud del nombre del coro: "+longNombre);
           response.setMensaje("La longitud del nombre del coro es demasiado larga.");
           response.setCodigo("404");
           return new ResponseEntity<ErrorService>(response, status);
       }
       this.log.info(" -- Longitud del nombre del coro: "+longNombre);
           
       //VALIDANDO CORO EN BASE DE DATOS
       boolean nCoro = this.coroService.validarNumCoro(coro.getNumCoro());
       boolean nomCoro = this.coroService.validarNombreCoro(coro.getNombre());
       if (nCoro && nomCoro) {
           try {
               int idCoro = this.coroService.agregarCoro(coro);
               this.log.info(" -- Coro Agregado con el id: " + idCoro);
               response.setCodigo("200");
               response.setMensaje("El coro ha sido agregado exitosamente al sistema.");
               status = HttpStatus.OK;
               if(user!=null){
                    this.guardarChangeset(TipoMovimientoEnum.REGISTRO_DE_CORO.getTipo(), user, coro);
                }
           } catch (Exception ex) {
               this.log.error(" -- No se pudo agregar el coro al sistema: " + ex.getMessage());
           }
       } else {
           if (nCoro == false) 
               response.setMensaje("Este número de coro "+coro.getNumCoro()+" ya existe en sistema.");
            else if (nomCoro == false) 
               response.setMensaje("Este nombre de coro ya se encuentra registrado en sistema: "+coro.getNombre());
           response.setCodigo("404");
       }
    return new ResponseEntity<ErrorService>(response, status);
   }
   
   @RequestMapping(value = "/eliminar/coro/pendiente", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> eliminarCoroPendiente(HttpServletRequest request) throws IOException, Exception {
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        String idCoroPendiente = request.getParameter("idCoroPendiente");
        String mailUser = request.getParameter("idMailUsuario");
        this.log.info(" -- Request de mail usuario: "+mailUser);

        ErrorService response = new ErrorService();
        response.setCodigo("404");
        response.setMensaje("Los datos no se pudieron eliminar.");
        
        Coro coro = this.coroService.getCoroById(Integer.valueOf(idCoroPendiente));
        if(coro!=null){
            this.guardarChangeset(TipoMovimientoEnum.ELIMINAR_CORO_PENDIENTE.getTipo(), 
                    this.usuarioService.validaEmailSistema(mailUser), coro);
            this.coroService.deleteCoro(coro);
            this.log.info(" -- El coro fue eliminado");
            response.setCodigo("200");
            response.setMensaje("Los datos del coro fueron eliminados con éxito.");
            status = HttpStatus.OK;
        }
        return new ResponseEntity<ErrorService>(response, status);
    }
    
    @RequestMapping(value = "/actualizar/coro/pendiente", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> actualizarCoroPendiente(HttpServletRequest request) throws IOException, Exception {
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        String idCoroPendiente = request.getParameter("idCoroPendiente");
        String mailUser = request.getParameter("idMailUsuario");
        this.log.info(" -- Request de mail usuario: "+mailUser);

        ErrorService response = new ErrorService();
        response.setCodigo("404");
        response.setMensaje("Los datos no se pudieron eliminar.");
        
        Coro coro = this.coroService.getCoroById(Integer.valueOf(idCoroPendiente));
        if(coro!=null){
            this.guardarChangeset(TipoMovimientoEnum.ACTIVAR_CORO_PENDIENTE.getTipo(), 
                    this.usuarioService.validaEmailSistema(mailUser), coro);
            coro.setActivo(2);
            this.coroService.updateCoro(coro);
            this.log.info(" -- El coro fue actualizado");
            response.setCodigo("200");
            response.setMensaje("El coro fue activado con éxito.");
            status = HttpStatus.OK;
        }
        return new ResponseEntity<ErrorService>(response, status);
    }
    
    @RequestMapping(value = "/actualizar/coro/detalle", method = RequestMethod.POST)
    public ResponseEntity<ErrorService> actualizarCoroDetalle(HttpServletRequest request) throws IOException, Exception {
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        String detalleCoroActualizar = request.getParameter("detalleCoroActualizar");
        String numIdCoroActualizar = request.getParameter("numIdCoroActualizar");
        String res = request.getParameter("cifrarTemp");
        String mail = "";
        if(res!=null){
          this.log.info(" -- El cifrado es enviado: "+res);
          String descifrar = UtilService.Desencriptar(res);
          String[] dataEnviada = descifrar.split(";");
          mail = dataEnviada[0];     
      }
        
        this.log.info(" -- numIdCoroActualizar: "+numIdCoroActualizar);
        this.log.info(" -- detalleCoroActualizar: "+detalleCoroActualizar);

        ErrorService response = new ErrorService();
        response.setCodigo("404");
        response.setMensaje("El coro no pudo ser actualizado.");
        
        Coro coro = this.coroService.getByNumCoro(numIdCoroActualizar);
        if(coro!=null){
            this.guardarChangeset(TipoMovimientoEnum.ACTUALIZAR_CORO.getTipo(), 
                    this.usuarioService.validaEmailSistema(mail), coro);
            coro.setDescripcion(coro.covertirStringToClob(detalleCoroActualizar));
            this.coroService.updateCoro(coro);
            this.log.info(" -- El coro fue actualizado.");
            response.setCodigo("200");
            response.setMensaje("El coro fue actualizado con éxito.");
            status = HttpStatus.OK;
        }
        return new ResponseEntity<ErrorService>(response, status);
    }

   private void guardarChangeset(String movement, Usuario user, Coro coro){
        for(TipoMovimientoEnum tipos: TipoMovimientoEnum.values()){
            if(tipos.getTipo().equals(movement)){
                this.changesetService.guardarChangeset(
                tipos.name(),
                new Date(UtilService.getFechaTimeStamp().getTime()), 
                user.getIdUsuario(), String.valueOf(coro.getNumCoro()));
                break;
            }
        }
    }
   
   @Autowired
   private CoroService coroService;
   
   @Autowired
   private ChangesetService changesetService;
   
   @Autowired
   private UsuarioService usuarioService;
   
   @Autowired
   private PropiedadSistemaService propiedadSistemaService;
    
}
