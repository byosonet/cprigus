<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<script type="text/javascript">
$(function(){ 
    $('input#acceso').knob();
});
</script>
<body>
    <div>
        <h4 class="sub-header" style="text-align: center;">Perfil</h4>
        <form id="formPerfil" class="form-horizontal" method="POST">
            
             <div class="form-group">
             <label class="control-label col-sm-2" for="nombreusuario">Nombre Completo:</label>
              <div class="col-sm-5">
                <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" placeholder="Ingresa tu nombre" value="${nombreUsuario}">
                <input type="hidden" id="nombreUsuario" name="nombreUsuario" value="${nombreUsuario}">
              </div>
             
             <label class="control-label col-sm-2" for="falta">Fecha de Alta:</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="falta" name="falta" value="<fmt:formatDate value="${falta}" pattern="dd/MM/yyyy HH:mm:ss" />" disabled>
                <input type="hidden" id="falta" name="falta" value="${falta}">
              </div>
             </div><br>
              
              <div class="form-group">
                <label class="control-label col-sm-2" for="fconexion">Ultima conexión:</label>
                <div class="col-sm-3">
                   <input type="text" class="form-control" id="fconexion" name="fconexion" value="<fmt:formatDate value="${fconexion}" pattern="dd/MM/yyyy HH:mm:ss" />" disabled>
                   <input type="hidden" id="fconexion" name="fconexion" value="${fconexion}">
                </div>


                <label class="control-label col-sm-1" for="email">Email:</label>
                <div class="col-sm-3">
                   <input type="email" class="form-control" id="emailUsuario" name="emailUsuario" value="${emailUsuario}" disabled>
                   <input type="hidden" id="emailUsuario" name="emailUsuario" value="${emailUsuario}">
                </div>

                <label class="control-label col-sm-1" for="actividad">Actividad:</label>
                <div class="col-sm-2">
                   <input type="text" class="form-control" id="actividad" name="actividad" value="${actividad}" disabled>
                   <input type="hidden" id="actividad" name="actividad" value="${actividad}">
                </div> 
              </div><br>
                
              <div class="form-group">
                    <label class="control-label col-sm-2" for="fnacimiento">Fecha de nacimiento:</label>
                    <div class="col-sm-2">
                       <input type="text" class="form-control" id="fnacimiento" name="fnacimiento" value="<fmt:formatDate value="${fnacimiento}" pattern="dd/MM/yyyy" />" disabled>
                       <input type="hidden" id="fnacimiento" name="fnacimiento" value="${fnacimiento}">
                    </div>


                    <label class="control-label col-sm-1" for="sexo">Sexo:</label>
                    <div class="col-sm-2">
                       <input type="text" class="form-control" id="sexo" name="sexo" value="${sexo}" disabled>
                       <input type="hidden" id="sexo" name="sexo" value="${sexo}">
                    </div>

                    <label class="control-label col-sm-2" for="passwordUsuario">Password:</label>
                    <div class="col-sm-3">
                       <input type="password" class="form-control" id="passwordUsuario" name="passwordUsuario" value="${passwordUsuario}">
                    </div>
              </div><br>
        </form>
        
        
        <div class="row">
              <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                  <button id="limpiar" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> LIMPIAR</button>
                  <button id="bperfil" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> ACTUALIZAR</button>
              </div>
            <center>
            <div style="width: 100%;text-align: justify;">
                <font face="Verdana" color="gray">
                    <hr>
                        <blockquote style="font-size: 12px;">                          
                         En esta sección unicamente puedes visualizar la información de tu perfil, más adelante podrás actualizar tus datos completos, 
                         por el momento solo puedes cambiar tu password.
                        </blockquote>
                    <hr>
                </font>
            </div>
            </center>
            
            <div id="bitacora"><jsp:include page="bitacora.jsp"></jsp:include></div>
            
            <c:if test="${show}">
                <div id="usuario"><jsp:include page="usuario.jsp"></jsp:include></div>
                <div id="mailFailed"><jsp:include page="mailFailed.jsp"></jsp:include></div>
                <div id="mailFailed"><jsp:include page="listaCoroPendientes.jsp"></jsp:include></div>
            </c:if>
                
        </div>
        
        
        <br>
        <h4 class="sub-header" style="text-align: center;"></h4>
        <div class="row col-sm-12">
            
            <div class="col-sm-2 col-xs-6" style="text-align: center">
                <label>Accesos a Sistema</label>
                <br>
                <input disabled data-step="1" data-min="0" data-max="500" data-width="50" data-height="50" data-fgColor="#000000" id="acceso" type="text" value="${accesoSistema}" class="dial">
            </div>
            
           
            <div class="col-sm-2 col-xs-6" style="text-align: center">
                 <label>Registros de Coros</label>
                 <br>
                <input disabled data-step="1" data-min="0" data-max="500" data-width="50" data-height="50" data-fgColor="#FF0000" id="acceso" type="text" value="${registroCoro}" class="dial">
            </div>
            
            
            <div class="col-sm-2 col-xs-6" style="text-align: center">
                <label>Consulta de Perfil</label>
                <br>
                <input disabled data-step="1" data-min="0" data-max="500" data-width="50" data-height="50" data-fgColor="#FF00FF" id="acceso" type="text" value="${consultarPerfil}" class="dial">
            </div>
            
            
            <div class="col-sm-2 col-xs-6" style="text-align: center">
                <label>Consulta de Coros</label>
                <br>
                <input disabled data-step="1" data-min="0" data-max="500" data-width="50" data-height="50" data-fgColor="#000000" id="acceso" type="text" value="${consultarCoro}" class="dial">
            </div>
            
            
            <div class="col-sm-2 col-xs-6" style="text-align: center">
                <label>Email de Contacto</label>
                <br>
                <input disabled data-step="1" data-min="0" data-max="500" data-width="50" data-height="50" data-fgColor="#FF0000" id="acceso" type="text" value="${emailContacto}" class="dial">
            </div>
            
            
            <div class="col-sm-2 col-xs-6" style="text-align: center">
                 <label>Actualizar Usuario</label>
                 <br>
                <input disabled data-step="1" data-min="0" data-max="500" data-width="50" data-height="50" data-fgColor="#FF00FF" id="acceso" type="text" value="${actUsuario}" class="dial">
            </div>
        </div>
    </div>    
</body>
</html>