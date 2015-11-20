<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sólo a Dios la Gloria</title>
        <script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/model/ModelUser.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                
                $('#listaCoroPendiente').footable();
                $('#listaMailFailed').footable();
                
                $('#summernote').summernote({
                    height: 300,
                    lang: 'es-ES' // default: 'en-US'
                  });
                
                //GRAFICANDO CONSULTA DE COROS
                var barChartDataCoros = {
                        labels : ['${fecha0}','${fecha1}','${fecha2}','${fecha3}','${fecha4}','${fecha5}','${fecha6}','${fecha7}'],
                        datasets : [
                                {
                                        label: "My First dataset",
					fillColor : "rgba(220,220,220,0.2)",
					strokeColor : "rgba(220,220,220,1)",
					pointColor : "rgba(220,220,220,1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(220,220,220,1)",
					data : ['${total0}','${total1}','${total2}','${total3}','${total4}','${total5}','${total6}','${total7}']
                                }
                        ]
                }
		var ctxCoros = document.getElementById("canvasCoros").getContext("2d");
		window.myBarCoros = new Chart(ctxCoros).Line(barChartDataCoros, {
			responsive : true
		});
                
	
                //OCULTANDO LOS DIVS DE LOS JSP
                var status;
                $('div#contactoJSP').hide();
                $('div#agregarCoroJSP').hide();
                $('div#perfilJSP').hide();
                $('div#avisoJSP').hide();
                
                      var listaCoroCompleta = $('#listaCoroCompleta')
                          .dataTable(
                              {
                              "aoColumns": [ {"bSearchable": true}, {"bSearchable": false},{"bSearchable": false}, {"bSearchable": false}],
                              "sPaginationType": "full_numbers",
                              "bPaginate": true,
                              "bLengthChange" : false,
                              "pageLength": 10,
                              "oLanguage" : {
                                    "sProcessing":     "Procesando...",
                                    "sLengthMenu":     "Mostrar _MENU_ registros",
                                    "sZeroRecords":    "No se encontraron resultados",
                                    "sEmptyTable":     "Ningún dato disponible en esta tabla",
                                    "sInfo":           "",
                                    "sInfoEmpty":      "",
                                    "sInfoFiltered":   "",
                                    "sInfoPostFix":    "",
                                    "sSearch":         "<span class='glyphicon glyphicon-search'></span>",
                                    "sUrl":            "",
                                    "sInfoThousands":  ",",
                                    "sLoadingRecords": "Cargando...",
                                    "oPaginate": {
                                        "sFirst":    "",
                                        "sLast":     "",
                                        "sNext":     "",
                                        "sPrevious": ""
                                    },
                                    "oAria": {
                                        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                                        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                                    }
                              }
                              });
                              
                              
                      var listaBitacora = $('#listaBitacora')
                          .dataTable(
                              {
                              "aoColumns": [ {"bSearchable": false}, {"bSearchable": true}, {"bSearchable": false}],
                              "sPaginationType": "full_numbers",
                              "bPaginate": true,
                              "bLengthChange" : false,
                              "pageLength": 10,
                              "bSort" : false,
                              "oLanguage" : {
                                    "sProcessing":     "Procesando...",
                                    "sLengthMenu":     "Mostrar _MENU_ registros",
                                    "sZeroRecords":    "No se encontraron resultados",
                                    "sEmptyTable":     "Ningún dato disponible en esta tabla",
                                    "sInfo":           "",
                                    "sInfoEmpty":      "",
                                    "sInfoFiltered":   "",
                                    "sInfoPostFix":    "",
                                    "sSearch":         "<span class='glyphicon glyphicon-search'></span>",
                                    "sUrl":            "",
                                    "sInfoThousands":  ",",
                                    "sLoadingRecords": "Cargando...",
                                    "oPaginate": {
                                        "sFirst":    "",
                                        "sLast":     "",
                                        "sNext":     "",
                                        "sPrevious": ""
                                    }
                              }
                              });
                      
                     var listaUsuario = $('#listaUsuario')
                          .dataTable(
                              {
                              "aoColumns": [ {"bSearchable": true}, {"bSearchable": false}, {"bSearchable": false},{"bSearchable": false},{"bSearchable": false},{"bSearchable": false}],
                              "sPaginationType": "full_numbers",
                              "bPaginate": false,
                              "bLengthChange" : false,
                              "pageLength": 10,
                              "bSort" : false,
                              "oLanguage" : {
                                    "sProcessing":     "Procesando...",
                                    "sLengthMenu":     "Mostrar _MENU_ registros",
                                    "sZeroRecords":    "No se encontraron resultados",
                                    "sEmptyTable":     "Ningún dato disponible en esta tabla",
                                    "sInfo":           "",
                                    "sInfoEmpty":      "",
                                    "sInfoFiltered":   "",
                                    "sInfoPostFix":    "",
                                    "sSearch":         "<span class='glyphicon glyphicon-search'></span>",
                                    "sUrl":            "",
                                    "sInfoThousands":  ",",
                                    "sLoadingRecords": "Cargando...",
                                    "oPaginate": {
                                        "sFirst":    "",
                                        "sLast":     "",
                                        "sNext":     "",
                                        "sPrevious": ""
                                    }
                              }
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
                              
                    $('a#aviso').click(function(){
                            $('div#contenidoDinamico').hide();
                            $('div#agregarCoroJSP').hide();
                            $('div#perfilJSP').hide();
                            $('div#contactoJSP').hide();
                            $('div#avisoJSP').show();
                    }); 
                    
                    $('a#contacto').click(function(){
                            $('div#contenidoDinamico').hide();
                             $('div#agregarCoroJSP').hide();
                             $('div#perfilJSP').hide();
                            $('div#contactoJSP').show();
                            $('div#avisoJSP').hide();
                    }); 
                    
                    $('a#registrar').click(function(){
                            $('div#contenidoDinamico').hide();
                             $('div#agregarCoroJSP').show();
                            $('div#contactoJSP').hide();
                            $('div#perfilJSP').hide();
                            $('div#avisoJSP').hide();
                    });  
                    
                    $('a#consultar').click(function(){
                           $('div#contenidoDinamico').show();
                           $('div#contactoJSP').hide();
                           $('div#agregarCoroJSP').hide();
                           $('div#perfilJSP').hide();
                           $('div#avisoJSP').hide();
                    });  
                    
                    $('a#perfil').click(function(){
                           $('div#contenidoDinamico').hide();
                           $('div#contactoJSP').hide();
                           $('div#agregarCoroJSP').hide();
                           $('div#perfilJSP').show();
                           $('div#avisoJSP').hide();
                    });  
                    
                    $('button#limpiar').click(function(){
                        $('#formContacto')[0].reset();
                        $('#formRegistrar')[0].reset();
                        $('#formPerfil')[0].reset();
                    });
                    
                    //OPCION CONTACTO
                    $('button#bcontacto').click(function(){
                        var asunto = $('input#asunto');
                        var nombre = $('input#nombre');
                        var email = $('input#emailContacto');
                        var text = $('textArea#textarea');
                        if(asunto.val() === ""){
                            muestraMsjSistemaError('El asunto es requerido.');
                            return false;
                        }else if(nombre.val() === ""){
                            muestraMsjSistemaError('Tu nombre es requerido.');
                            return false;
                        }else if(email.val() === ""){
                            muestraMsjSistemaError('Tu email es requerido.');
                            return false;
                        }else if(text.val() === ""){
                            muestraMsjSistemaError('No puedes enviar tus comentarios en blanco.');
                            return false;
                        }
                        
                        $.blockUI();
                        $.ajax({
                                type: 'POST',
                                url:  '${contextpath}'+'/contacto/sistema',
                                data: $('form#formContacto').serialize(),
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
                    
                    
                        //OPCION REGISTRAR
                        $('button#bregistrar').click(function(){
                        var nombreCoro = $('input#nombreCoro');
                        var autor = $('input#autor');
                        var numCoro = $('input#numCoro');
                        var desccoro = $('textArea#desccoro');
                        if(nombreCoro.val() === ""){
                            muestraMsjSistemaError('El nombre del coro es requerido.');
                            return false;
                        }else if(autor.val() === ""){
                            muestraMsjSistemaError('El nombre del autor es requerido.');
                            return false;
                        }else if(numCoro.val() === ""){
                            muestraMsjSistemaError('El número del coro es requerido.');
                            return false;
                        }else if(desccoro.val() === ""){
                            muestraMsjSistemaError('Debes agregar el contenido del coro.');
                            return false;
                        }
                        
                        $.blockUI();
                        var texto = $('.note-editable').html();
                        $('input#summernoteText').val(texto);
                        $.ajax({
                                type: 'POST',
                                url:  '${contextpath}'+'/agregar/coro',
                                data: $('form#formRegistrar').serialize(),
                                    success: function (data) {
                                       $.unblockUI();
                                       muestraMsjSistemaSuccessRegistrarCoro(data.mensaje);
                                },
                                   error: function(msj){
                                       status = JSON.parse(msj.responseText);
                                       $.unblockUI();
                                       muestraMsjSistemaError(status.mensaje);
                                    }
                          });
                    });
                    
                    
                    
                        //OPCION PERFIL
                        $('button#bperfil').click(function(){
                        var passwordUsuario = $('input#passwordUsuario');
                       
                        if(passwordUsuario.val() === ""){
                            muestraMsjSistemaError('Escribe tu password para actualizar.');
                            return false;
                        }
                        
                        $.blockUI();
                        $.ajax({
                                type: 'POST',
                                url:  '${contextpath}'+'/actualizar/usuario',
                                data: $('form#formPerfil').serialize(),
                                    success: function (data) {
                                       $.unblockUI();
                                       muestraMsjSistemaSuccessIndex(data.mensaje);
                                },
                                   error: function(msj){
                                       status = JSON.parse(msj.responseText);
                                       $.unblockUI();
                                       muestraMsjSistemaError(status.mensaje);
                                    }
                          });
                    });
                    
                    
                    
                    
                    //FUNCIONES DE DIALOGS DE SUCCESS Y ERROR
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
                                 label: 'OK',
                                 cssClass: 'btn-primary',
                                 action: function(dialog) {
                                     dialog.close();
                                 }
                             }]
                         });
                         }
                         
                         function muestraMsjSistemaSuccessRegistrarCoro(msjStatus){
                            BootstrapDialog.show({
                             size: BootstrapDialog.SIZE_SMALL,
                             title: 'Mensaje del Sistema:',
                             closable: false,
                             message: msjStatus,
                             type: BootstrapDialog.TYPE_SUCCESS,
                             cssClass: 'login-dialog',
                             buttons: [{
                                 icon: 'glyphicon glyphicon-check',
                                 label: 'OK',
                                 cssClass: 'btn-primary',
                                 action: function(dialog) {
                                     dialog.close();
                                        $.blockUI();
                                        var urlAction = '${contextpath}' + '/ingresar';
                                        document.getElementById('regresar').action = urlAction;
                                        document.getElementById('regresar').method = 'POST';
                                        document.getElementById('regresar').submit();
                                 }
                             }]
                         });
                         }
                         
                         
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
                         
                         $('a#menuSpan').click(function(){
                            if( $('div#menuPrincipal').is(":visible") ){
                                $('div#menuPrincipal').css('display','none');
                            }else{
                               $('div#menuPrincipal').css('display','block');
                            }
                         });
                         
                         //ELIMINAR USUARIO
                        $('a#deleteUser').click(function(){
                            var tr = $(this);
                            var value = $(this).attr('href');
                            value = value.split("#")[1];
                            var idUser = value.split(";")[0];
                            var nombre = value.split(";")[1];
                            muestraMensajedeConfirmacion('¿Estas seguro de eliminar a:', idUser,nombre, tr);
                        });
                        
                        function muestraMensajedeConfirmacion(msjStatus, idUser,nombre, tr){
                            BootstrapDialog.show({
                             size: BootstrapDialog.SIZE_SMALL,
                             title: 'Mensaje del Sistema:',
                             closable: false,
                             message: msjStatus+" "+'<b>'+nombre+'</b>'+"?",
                             type: BootstrapDialog.TYPE_DANGER,
                             cssClass: 'login-dialog',
                             buttons: [{
                                 icon: 'glyphicon glyphicon-remove',
                                 label: 'CANCELAR',
                                 cssClass: 'btn-default',
                                 action: function(dialog) {
                                     dialog.close();
                                 }
                             },{
                                 icon: 'glyphicon glyphicon-ok',
                                 label: 'ACEPTAR',
                                 cssClass: 'btn-primary',
                                 action: function(dialog) {
                                     dialog.close();
                                     $('input#idUsuario').val(idUser);
                                        $.blockUI();
                                        $.ajax({
                                                type: 'POST',
                                                url:  '${contextpath}'+'/eliminar/usuario',
                                                data: $('form#eliminarUsuario').serialize(),
                                                    success: function (data) {
                                                       $.unblockUI();
                                                       tr.closest('tr').remove();
                                                       muestraMsjSistemaSuccess(data.mensaje);
                                                },
                                                   error: function(msj){
                                                       status = JSON.parse(msj.responseText);
                                                       $.unblockUI();
                                                       muestraMsjSistemaError(status.mensaje);
                                                    }
                                          });
                                 }
                             }]
                         });
                         }
                        
                         //ELIMINAR MAIL FALLIDO
                         $('a#deleteMailFailed').click(function(){
                            var tr = $(this);
                            var value = $(this).attr('href');
                            value = value.split("#")[1];
                            muestraMensajedeConfirmacionEliminarMailFallido('¿Estas seguro de eliminar este registro?',value, tr);
                        });
                        
                        function muestraMensajedeConfirmacionEliminarMailFallido(msjStatus, value, tr){
                            BootstrapDialog.show({
                             size: BootstrapDialog.SIZE_SMALL,
                             title: 'Mensaje del Sistema:',
                             closable: false,
                             message: msjStatus,
                             type: BootstrapDialog.TYPE_DANGER,
                             cssClass: 'login-dialog',
                             buttons: [{
                                 icon: 'glyphicon glyphicon-remove',
                                 label: 'CANCELAR',
                                 cssClass: 'btn-default',
                                 action: function(dialog) {
                                     dialog.close();
                                 }
                             },{
                                 icon: 'glyphicon glyphicon-ok',
                                 label: 'ACEPTAR',
                                 cssClass: 'btn-primary',
                                 action: function(dialog) {
                                     dialog.close();
                                     $('input#idMailFailed').val(value);
                                        $.blockUI();
                                        $.ajax({
                                                type: 'POST',
                                                url:  '${contextpath}'+'/eliminar/mail/failed',
                                                data: $('form#eliminarEmail').serialize(),
                                                    success: function (data) {
                                                       $.unblockUI();
                                                       tr.closest('tr').remove();
                                                       $('.footable-row-detail').remove();
                                                       muestraMsjSistemaSuccess(data.mensaje);
                                                },
                                                   error: function(msj){
                                                       status = JSON.parse(msj.responseText);
                                                       $.unblockUI();
                                                       muestraMsjSistemaError(status.mensaje);
                                                    }
                                          });
                                 }
                             }]
                         });
                         }
                         
                         
                         //ELIMINAR CORO PENDIENTE
                         $('a#deleteCoro').click(function(){
                            var tr = $(this);
                            var value = $(this).attr('href');
                            value = value.split("#")[1];
                            muestraMensajedeConfirmacionEliminarActualizarCoro('¿Estas seguro de eliminar este coro?',value, tr, 'eliminar');
                        });
                        
                         //ACTUALIZAR CORO PENDIENTE
                         $('a#activeCoro').click(function(){
                            var tr = $(this);
                            var value = $(this).attr('href');
                            value = value.split("#")[1];
                            muestraMensajedeConfirmacionEliminarActualizarCoro('¿Deseas activar este coro?',value, tr, 'actualizar');
                        });
                        
                        function muestraMensajedeConfirmacionEliminarActualizarCoro(msjStatus, value, tr, ruta){
                            var dialogo;
                            if(ruta === 'eliminar'){
                                dialogo = BootstrapDialog.TYPE_DANGER;
                            }else{
                                dialogo = BootstrapDialog.TYPE_SUCCESS;
                            }
                            
                            BootstrapDialog.show({
                             size: BootstrapDialog.SIZE_SMALL,
                             title: 'Mensaje del Sistema:',
                             closable: false,
                             message: msjStatus,
                             type: dialogo,
                             cssClass: 'login-dialog',
                             buttons: [{
                                 icon: 'glyphicon glyphicon-remove',
                                 label: 'CANCELAR',
                                 cssClass: 'btn-default',
                                 action: function(dialog) {
                                     dialog.close();
                                 }
                             },{
                                 icon: 'glyphicon glyphicon-ok',
                                 label: 'ACEPTAR',
                                 cssClass: 'btn-primary',
                                 action: function(dialog) {
                                     dialog.close();
                                     $('input#idCoroPendiente').val(value);
                                        $.blockUI();
                                        $.ajax({
                                                type: 'POST',
                                                url:  '${contextpath}'+'/'+ruta+'/coro/pendiente',
                                                data: $('form#eliminarActualizarCoro').serialize(),
                                                    success: function (data) {
                                                       $.unblockUI();
                                                       tr.closest('tr').remove();
                                                       $('.footable-row-detail').remove();
                                                       muestraMsjSistemaSuccess(data.mensaje);
                                                },
                                                   error: function(msj){
                                                       status = JSON.parse(msj.responseText);
                                                       $.unblockUI();
                                                       muestraMsjSistemaError(status.mensaje);
                                                    }
                                          });
                                 }
                             }]
                         });
                         }
                         
                }          
            );
        </script>
    </head>
    <body>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-10 col-md-offset-1 main">
          <div id="contenidoDinamico"><jsp:include page="listaCoros.jsp"></jsp:include></div>
          <div id="contactoJSP"><jsp:include page="contacto.jsp"></jsp:include></div>
          <div id="agregarCoroJSP"><jsp:include page="agregarCoro.jsp"></jsp:include></div>
          <div id="perfilJSP"><jsp:include page="perfil.jsp"></jsp:include></div>
          <div id="avisoJSP"><jsp:include page="aviso.jsp"></jsp:include></div>
          
        </div>
      </div>
    </div>
    
    <form id="regresar">
        <input type="hidden" id="cifrar" name="cifrar" value="${cifrar}">
    </form>
    
    <form id="eliminarUsuario">
        <input type="hidden" id="idUsuario" name="idUsuario" value="">
    </form>
    
    <form id="eliminarEmail">
        <input type="hidden" id="idMailFailed" name="idMailFailed" value="">
        <input type="hidden" id="idMailUsuarioTemp" name="idMailUsuarioTemp" value="${emailUsuario}">
    </form>
    
    <form id="eliminarActualizarCoro">
        <input type="hidden" id="idCoroPendiente" name="idCoroPendiente" value="">
        <input type="hidden" id="idMailUsuario" name="idMailUsuario" value="${emailUsuario}">
        
    </form>
    
    <form id="index">
    </form>
    </body>
</html>
