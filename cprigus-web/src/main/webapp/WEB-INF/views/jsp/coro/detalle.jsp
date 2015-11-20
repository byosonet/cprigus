<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sólo a Dios la Gloria</title>
    <script type="text/javascript">
      $(function(){
          var status;
          $('button#editarCoro').click(function(){
                $('button#editarCoro').hide();
                $('button#guardarCoro').show();
                $('#summernote').summernote({
                    height: 300,
                    lang: 'es-ES' // default: 'en-US'
                  });
          });
          
          $('button#guardarCoro').click(function(){
              muestraMensajedeConfirmacionActualizarCoro('¿Deseas aplicar y guardar los cambios?');
          });
          
          
          function muestraMsjSistemaSuccessActualizarCoro(msjStatus, ruta){
                            var dialogo;
                            if(ruta === '200'){
                                dialogo = BootstrapDialog.TYPE_SUCCESS;
                            }else{
                                dialogo = BootstrapDialog.TYPE_DANGER;
                            }
                            BootstrapDialog.show({
                             size: BootstrapDialog.SIZE_SMALL,
                             title: 'Mensaje del Sistema:',
                             closable: false,
                             message: msjStatus,
                             type: dialogo,
                             cssClass: 'login-dialog',
                             buttons: [{
                                 icon: 'glyphicon glyphicon-check',
                                 label: 'OK',
                                 cssClass: 'btn-primary',
                                 action: function(dialog) {
                                     $('#summernote').destroy();
                                     dialog.close();
                                 }
                             }]
                         });
                }
            
            
             function muestraMensajedeConfirmacionActualizarCoro(msjStatus){
                            BootstrapDialog.show({
                             size: BootstrapDialog.SIZE_SMALL,
                             title: 'Mensaje del Sistema:',
                             closable: false,
                             message: msjStatus,
                             type: BootstrapDialog.TYPE_SUCCESS,
                             cssClass: 'login-dialog',
                             buttons: [{
                                 icon: 'glyphicon glyphicon-remove',
                                 label: 'CANCELAR',
                                 cssClass: 'btn-default',
                                 action: function(dialog) {
                                     dialog.close();
                                     $('#summernote').destroy();
                                     $('button#guardarCoro').hide();
                                     $('button#editarCoro').show();
                                 }
                             },{
                                 icon: 'glyphicon glyphicon-ok',
                                 label: 'ACEPTAR',
                                 cssClass: 'btn-primary',
                                 action: function(dialog) {
                                    dialog.close();
                                    $('button#guardarCoro').hide();
                                    $('button#editarCoro').show();
                                    $.blockUI();
                                    //var texto = $('.note-editable').html();
                                    var texto = $('#summernote').code();
                                    $('input#detalleCoroActualizar').val(texto);
                                    $.ajax({
                                            type: 'POST',
                                            url:  '${contextpath}'+'/actualizar/coro/detalle',
                                            data: $('form#actualizarCoro').serialize(),
                                                success: function (data) {
                                                   $.unblockUI();
                                                   muestraMsjSistemaSuccessActualizarCoro(data.mensaje, '200');
                                            },
                                               error: function(msj){
                                                   status = JSON.parse(msj.responseText);
                                                   $.unblockUI();
                                                   muestraMsjSistemaSuccessActualizarCoro(status.mensaje, '404');
                                                }
                                      });
                                 }
                             }]
                         });
            }
          
          $('button#back').click(function(){
              $.blockUI();
                var urlAction = '${contextpath}' + '/ingresar';
                document.getElementById('regresar').action = urlAction;
                document.getElementById('regresar').method = 'POST';
                document.getElementById('regresar').submit();
          });
          
          $('a#exit').click(function(){
                $.blockUI();
                $.ajax({
                        type: 'POST',
                        url:  '${contextpath}'+'/sistema/salir',
                        data: $('form#regresar').serialize(),
                         success: function (data) {
                               $.unblockUI();
                               muestraMsjSistemaSuccessIndex("Gracias por tu visita, Dios te bendiga.");
                        }  
                  });
            });
            
            function muestraMsjSistemaSuccessIndex(msjStatus){
                BootstrapDialog.show({
                 size: BootstrapDialog.SIZE_SMALL,
                 title: 'Mensaje del Sistema:',
                 closable: false,
                 message: msjStatus,
                 type: BootstrapDialog.TYPE_SUCCESS,
                 cssClass: 'login-dialog',
                 buttons: [{
                     icon: 'glyphicon glyphicon-ok',
                     label: 'ACEPTAR',
                     cssClass: 'btn-primary',
                     action: function(dialog) {
                         dialog.close();
                        $.blockUI();
                        var urlAction = '/';
                        document.getElementById('index').action = urlAction;
                        document.getElementById('index').method = 'GET';
                        document.getElementById('index').submit();
                     }
                 }]
             });
             }  
      });
  </script>
    </head>
    <body>
    <div class="container-fluid">
        <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-8 col-md-offset-2 main">
            <br>
            <center><h4 class="page-header">"<c:out value="${nombre}"/>"<br><u>(CORO <c:out value="${numCoro}"/>)</u></h4></center>
            <br>
        <center>
        
        <c:choose>
            <c:when test="${statusDescripcion}">
               <div style="width: 100%;text-align: justify;">
                   <font size="3" face="Verdana" color="black">
                    <strong>
                        <div id="summernote"><c:out value="${coro}" escapeXml="false"/></div>
                        <br><br>
                    </strong>
                    </font>
                </div>
            </c:when>
            <c:when test="${status == 1}">
              <div class="row alert alert-info" style="width: 100%;text-align: center;">
                   <strong><c:out value="${coro}"/></strong>
                </div>
            </c:when>
            <c:otherwise>
               <div class="row alert alert-danger" style="width: 100%;text-align: center;">
                   <strong><c:out value="${coro}"/></strong>
                </div>
            </c:otherwise>
        </c:choose>
        
                
        </center>
        <center>
            <h4 class="page-header">
                <c:if test="${show && statusDescripcion}">
                    <button id="guardarCoro" style="display: none;" class="btn btn-default"><span class="glyphicon glyphicon-floppy-disk"></span> GUARDAR</button>
                    <button id="editarCoro" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span> EDITAR</button>
                </c:if>
                <button id="back" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> REGRESAR</button>
            </h4>
        </center>
        </div>
    </div>
  </div>
    <form id="regresar">
        <input type="hidden" id="cifrar" name="cifrar" value="${cifrar}">
    </form>
    <form id="index">
    </form>
    <form id="actualizarCoro">
        <input type="hidden" id="detalleCoroActualizar" name="detalleCoroActualizar" value="">
        <input type="hidden" id="numCoroActualizar" name="numIdCoroActualizar" value="${numCoro}">
        <input type="hidden" id="cifrarTemp" name="cifrarTemp" value="${cifrar}">
    </form>
    </body>
</html>