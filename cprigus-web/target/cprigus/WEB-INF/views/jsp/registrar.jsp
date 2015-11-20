<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sólo a Dios la Gloria</title>
         <script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/model/ModelNacimiento.js"></script>
  
  <script type="text/javascript">
      $(function(){
          
        $('select#dia').select2();
        $('select#anio').select2();
        $('select#mes').select2();
        $('select#actividad').select2();
          
        var status;
        $('input#masculino').click(function(){
            $('input#femenino').attr('checked',false);
        });

        $('input#femenino').click(function(){
            $('input#masculino').attr('checked',false);
        });
          
          
        $('input#nombre').focus();
        $('button#registrar').click(function(){
            
                var nombre = $('input#nombre');
                var email = $('input#email');
                var passw = $('input#password');
                var confPassword = $('input#confPassword');
                if(nombre.val() === ""){
                    nombre.focus();
                    muestraMsjSistemaError('El nombre es requerido.');
                    return false;
                }else if(email.val() === ""){
                    muestraMsjSistemaError('El email es requerido.');
                    return false;
                }else if(passw.val() === "" || confPassword.val() === ""){
                    muestraMsjSistemaError('El password es requerido.');
                    return false;
                }else if(passw.val() != confPassword.val()){
                    muestraMsjSistemaError('El password no coincide.');
                    return false; 
                }
                var m = $('input#masculino').filter(":checked").val();
                var f = $('input#femenino').filter(":checked").val();
		if(m === undefined && f === undefined){
			  muestraMsjSistemaError('El sexo es un campo requerido.');
			  return false;
		}
                $('input#idEmail').val(email.val());
                $('input#idPassword').val(passw.val());
		$.blockUI();
                $.ajax({
	              type: 'POST',
	              url: '${contextpath}'+'/usuario/nuevo',
	              data: $('form#formRegistrar').serialize(),
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
              $('form#formRegistrar')[0].reset();
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
                    var urlAction = '${contextpath}' + '/lista/obtener';
                    document.getElementById('ingresar').action = urlAction;
                    document.getElementById('ingresar').method = 'POST';
                    document.getElementById('ingresar').submit();
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
        <div class="col-sm-12 col-sm-offset-0 col-md-10 col-md-offset-1 main">
            <form class="form-horizontal" id="formRegistrar" method="post" action="${contextpath}/registrar">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="nombre">Nombre completo:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresa tu nombre">
                    </div>
                    
                    <label class="control-label col-sm-1" for="email">Email:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="email" name="email" placeholder="Ingesa tu email">
                    </div>
                </div>

                <div class="form-group">
                   <label class="control-label col-sm-2" for="email">Password:</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Ingesa tu password">
                    </div>
                   
                   <label class="control-label col-sm-2" for="email">Confirmar password:</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="confPassword" name="confPassword" placeholder="Confirma tu password">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-sm-12" style="text-align: center;">Información Personal:</label>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-sm-2">Día:</label>
                    <div class="col-sm-1">
                        <select class="form-control" id="dia" name="dia" data-bind="foreach: days, visible: days().length > 0">
                        <option data-bind="value: id,text:day"></option></select>
                    </div>
                    
                    <label class="control-label col-sm-1">Mes:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="mes" name="mes" data-bind="foreach: months, visible: months().length > 0">
                        <option data-bind="value: id,text:mes"></option></select>
                    </div>
                    
                    <label class="control-label col-sm-1">Año:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="anio" name="anio" data-bind="foreach: years, visible: years().length > 0">
                        <option data-bind="value: year,text:year"></option></select>
                    </div>
                   
                    
                    <label class="control-label col-sm-1">Actividad:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="actividad" name="actividad" data-bind="foreach: activities, visible: activities().length > 0">
                        <option data-bind="value: activity,text:activity"></option></select>
                    </div>
                </div>
                
                
                 <div class="form-group">
                    <label class="control-label col-sm-2">Sexo:</label>
                    <div class="col-sm-2">
                       <div class="radio radio-info radio-inline">
                            <input type="radio" id="masculino" name="sexo" value="M">
                            <label> Masculino </label>
                        </div>
                    </div>

                    <div class="col-sm-2">
                       <div class="radio radio-info radio-inline">
                            <input type="radio" id="femenino" name="sexo" value="F">
                            <label> Femenino </label>
                        </div>
                    </div>
     
                    <label class="control-label col-sm-3">Deseo recibir notificaciones:</label>
                    <div class="col-sm-3">
                       <div class="checkbox checkbox-primary">
                        <input type="checkbox" value="SI" name="notificar" id="notificar">
                        <label>
                            Sí
                        </label>
                    </div>
                    </div>
                </div>
            </form>
                
            <form id="ingresar">
                <input type="hidden" id="idEmail" name="idEmail" value=""/>
                <input type="hidden" id="idPassword" name="idPassword" value=""/>
            </form>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                <button id="limpiar" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> LIMPIAR</button>
                <button id="registrar" class="btn btn-primary"><span class="glyphicon glyphicon-user"></span> REGISTRAR</button>
                </div>
            </div>
        </div>
    </div>    
</div>
    
    
    </body>
</html>
