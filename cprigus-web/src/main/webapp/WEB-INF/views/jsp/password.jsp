<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Sólo a Dios la Gloria</title>
  <script type="text/javascript">
      $(function(){
          var status;
          $('input#email').focus();
          $('button#enviar').click(function(){
              
              var email = $('input#email');
              var confEmail = $('input#confEmail');
              if(email.val() === ""){
                  muestraMsjSistemaError('El email es requerido.');
                  return false;
              }else if(confEmail.val() === ""){
                  muestraMsjSistemaError('El email es requerido.');
                  return false;
              }else if(email.val() !== confEmail.val()){
                  muestraMsjSistemaError('El email no coincide.');
                  return false;
              }
              
              $.blockUI();
              $.ajax({
	              type: 'POST',
	              url: '${contextpath}'+'/password/enviar/email',
	              data: $('form#recuperar').serialize(),
	                  success: function (data) {
                             $.unblockUI();
                             muestraMsjSistemaSuccess(data.mensaje);
	              },
                         error: function(msj){
                             status = JSON.parse(msj.responseText);
                             $.unblockUI();
                             muestraMsjSistemaError(status.mensaje);
                          }
	        });
          });
          
         $('button#limpiar').click(function(){
              $('form#recuperar')[0].reset();
          });
          
        function muestraMsjSistemaError(msjStatus){
           BootstrapDialog.show({
            size: BootstrapDialog.SIZE_SMALL,
            title: 'Mensaje del Sistema:',
            closable: false,
            message: msjStatus,
            type: BootstrapDialog.TYPE_DANGER,
            cssClass: 'login-dialog',
            buttons: [{
                icon: 'glyphicon glyphicon-check',
                label: 'OK',
                cssClass: 'btn-primary',
                action: function(dialog) {
                    dialog.close();
                }
            }]
        });
        }
        
        function muestraMsjSistemaSuccess(msjStatus){
           BootstrapDialog.show({
            size: BootstrapDialog.SIZE_SMALL,
            title: 'Mensaje del Sistema:',
            closable: false,
            message: msjStatus,
            type: BootstrapDialog.TYPE_SUCCESS,
            cssClass: 'login-dialog',
            buttons: [{
                icon: 'glyphicon glyphicon-check',
                label: 'CONTINUAR',
                cssClass: 'btn-primary',
                action: function(dialog) {
                    dialog.close();
                    $.blockUI();
                    var urlAction = '/';
                    document.getElementById('regresar').action = urlAction;
                    document.getElementById('regresar').method = 'GET';
                    document.getElementById('regresar').submit();
                }
            }]
        });
        }
      });
  </script>
</head>
<body>  
    
    <div class="container-fluid" style="margin-top: 30px;">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-5 col-md-offset-3 main">
            <form id="recuperar" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-4" for="nombre">Email:</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="email" name="email" placeholder="Ingresa tu email">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-4" for="email">Confirmar email:</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="confEmail" name="confEmail" placeholder="Confirma tu email">
                    </div>
                </div>
            </form>
            <form id="regresar"></form>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                <button id="limpiar" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> LIMPIAR</button>
                <button id="enviar" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> ENVIAR</button>
                </div>
            </div>
        </div>
    </div>    
</div>
    
    
    
    
    
</body>
</html>