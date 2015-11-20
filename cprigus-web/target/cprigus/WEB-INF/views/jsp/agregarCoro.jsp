<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<body>
    <div>
        <h4 class="sub-header" style="text-align: center;">Registrar</h4>
        <form id="formRegistrar" class="form-horizontal" method="POST">
            
             <div class="form-group">
              
             <label class="control-label col-sm-2" for="coro">Nombre del Coro:</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="nombreCoro" name="nombreCoro" placeholder="Ingresa el nombre del coro">
              </div>
             
             <label class="control-label col-sm-1" for="autor">Autor:</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="autor" name="autor" placeholder="Ingresa el nombre del autor">
              </div>
              
              <label class="control-label col-sm-1" for="coro"># Coro:</label>
              <div class="col-sm-2">
                 <input type="number" class="form-control" id="numCoro" name="numCoro" placeholder="Ingesa el número del coro">
              </div>
              
              
            </div>

            <div class="form-group">
                <label class="control-label col-sm-12" for="desc" style="text-align: left;">Descripci&oacute;n del Coro:</label>
            </div>
            
            <div class="form-group">
              <div class="col-sm-12">
                  <div id="summernote">Pega aquí el contenido del coro por favor...</div>
                  <input type="hidden" id="summernoteText" name="summernoteText">
              </div>
            </div>
            
            <input type="hidden" id="cifrar" name="cifrar" value="${cifrar}">
        </form>
        
        
        <div class="row">
              <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                  <button id="limpiar" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> LIMPIAR</button>
                  <button id="bregistrar" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> AGREGAR</button>
              </div>
            <center>
            <div style="width: 100%;text-align: justify;">
                <font face="Verdana" color="gray">
                    <hr>
                        <blockquote style="font-size: 12px;">                          
                         Puedes agregar nuevos himnos, pero recuerda que estos primero tendrán que ser validados por el administrador antes de
                         ser publicados, si tienes dudas puedes ponerte en contacto con el Administrador del Sistema.
                        </blockquote>
                    <hr>
                </font>
            </div>
            </center>
        </div>
        
        
    </div>
</body>
</html>