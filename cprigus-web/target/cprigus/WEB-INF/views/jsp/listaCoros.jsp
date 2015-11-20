<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<body>
    <div>
       <h4 style="text-align: center;" class="sub-header">Lista de Coros</h4>

          <div class="table-responsive">
            <table class="table table-hover" id="listaCoroCompleta">
              <thead>
                <tr>
                  
                  <th style="text-align: center" class="alert alert-dismissible">N°</th>
                  <th style="text-align: center" class="alert alert-dismissible"><span class="glyphicon glyphicon-music"> NOMBRE DEL CORO</span></th>
                  <th style="text-align: center" class="alert alert-dismissible"><span class="glyphicon glyphicon-user"> AUTOR</span></th>
                  <th style="text-align: center" class="alert alert-dismissible"><span class="glyphicon glyphicon-tasks"> ESTATUS</span></th>
                  
                </tr>
              </thead>
              <tbody>
                
                <c:forEach items="${corosCompletos}" var="corocomp" varStatus="indice">
               <tr>
                
                <td style="text-align: center;width: 2%;"><a id="ref" href="${contextpath}/coro/${cifrar}/${corocomp.idCoro}">${corocomp.numCoro}</a></td>
                <td><a id="ref" href="${contextpath}/coro/${cifrar}/${corocomp.idCoro}">${corocomp.nombre}</a></td>
                <td><a id="ref" href="${contextpath}/coro/${cifrar}/${corocomp.idCoro}">${corocomp.autor}</a></td>
                    <c:choose>
                       <c:when test="${corocomp.activo == 2}">
                           <td style="text-align:center;">
                              <a href="${contextpath}/coro/${cifrar}/${corocomp.idCoro}" class="glyphicon glyphicon-ok alert alert-success" style="margin-top:-4px;margin-bottom: -3px;"> Procesado</a>
                           </td>
                       </c:when>
                       <c:when test="${corocomp.activo == 1}">
                           <td style="text-align:center;">
                              <a href="${contextpath}/coro/${cifrar}/${corocomp.idCoro}" class="glyphicon glyphicon-refresh alert alert-info" style="margin-top:-4px;margin-bottom: -3px;"> Validando</a>
                           </td>
                       </c:when>
                       <c:otherwise>
                          <td style="text-align:center;">
                             <a href="${contextpath}/coro/${cifrar}/${corocomp.idCoro}" class="glyphicon glyphicon-remove alert alert-danger" style="margin-top:-4px;margin-bottom: -3px;"> Pendiente</a>
                           </td>
                       </c:otherwise>
                   </c:choose>
                </tr>    
                </c:forEach>
                
              </tbody>
            </table>
          </div>
       
        <br>
        <h4 class="" style="text-align: center;">Consulta de Coros de los últimos 8 días</h4>
    </div>
    
    <div>
        <canvas id="canvasCoros" height="150" width="400" style="width: 400px; height: 150px;"></canvas>
    </div>
</body>
</html>