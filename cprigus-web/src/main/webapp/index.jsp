<%@ include file="./WEB-INF/views/layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>CPrigus Panel</title>
  <jsp:include page="./WEB-INF/views/layout/staticResources.jsp"></jsp:include>
  <style>
      
  </style>
  
  <script type="text/javascript">
      $(function(){
          var status;
          $('input#login').focus();
          $('button#acceder').click(function(){
              
              var email = $('input#login');
              var password = $('input#password');
              if(email.val() === ""){
                  muestraMsjSistemaError('El email es requerido.');
                  return false;
              }else if(password.val() === ""){
                  muestraMsjSistemaError('El password es requerido.');
                  return false;
              }
              
              $.blockUI();
              $.ajax({
	              type: 'POST',
	              url: '${contextpath}'+'/validar/usuario',
	              data: $('form#ingresar').serialize(),
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
          
          $('button#registrar').click(function(){
                $.blockUI();
                var urlAction = '${contextpath}' + '/registrar/usuario';
		document.getElementById('ingresar').action = urlAction;
                document.getElementById('ingresar').method = 'GET';
		document.getElementById('ingresar').submit();
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
                icon: 'glyphicon glyphicon-ok',
                label: 'CONTINUAR',
                cssClass: 'btn-primary',
                action: function(dialog) {
                    dialog.close();
                    $.blockUI();
                    var urlAction = '${contextpath}' + '/ingresar';
                    document.getElementById('ingresar').action = urlAction;
                    document.getElementById('ingresar').method = 'POST';
                    document.getElementById('ingresar').submit();
                }
            }]
        });
        }
      });
  </script>
  <style>
             body{
                font:  10.5px Verdana;
             }
  </style>
</head>
<body style="">
     <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/" style="color:white;">CPrigus Panel Administrador</a>
          </div>
          <div style="height: 1px;" aria-expanded="false" class="navbar-collapse collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                  <a style="color:white;" aria-expanded="true" aria-haspopup="true" role="button" data-toggle="dropdown" class="navbar-brand dropdown-toggle" href="#"></a>
                </li>
              </ul>
          </div>
        </div>
      </nav>

<div class="container-fluid" style="margin-top: 30px;">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-5 col-md-offset-3 main">
            <form id="ingresar" class="form-horizontal" method="POST" action="${contextpath}/ingresar">
                <div class="form-group">
                    <label class="control-label col-sm-3" for="nombre"></label>
                    <div class="col-sm-9">
                        <div style="text-align:center"><label>Bienvenido a CPrigus.</label><br><img width="150px" height="150px" src="${contextpath}/static/resources/img/cpanel_user.png">
                      </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="nombre">Email:</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="login" name="user" placeholder="Ingresa tu email">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3" for="email">Password:</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Ingesa tu password">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-12">¿Olvidé mi contraseña? <a href="${contextpath}/password/recuperar">Recuperar contraseña</a></label>
                </div>
            </form>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                <button id="registrar" class="btn btn-default"><span class="glyphicon glyphicon-user"></span> REGISTRAR</button>
                <button id="acceder" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> ACCEDER</button>
                </div>
            </div>
        </div>
    </div>    
</div>
         

  <nav class="navbar navbar-inverse navbar-fixed-bottom">
          <div class="container-fluid">
            <div class="navbar-header navbar-right">
              <a class="navbar-brand" href="https://www.linkedin.com/pub/gustavo-ulises-trejo-armenta/94/aa6/262" style="font-size: 1em;color:white;">by Ulysses M@c</a>
            </div>
          </div>
</nav>
</body>
</html>
