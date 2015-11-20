<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
    <div class="container-fluid">
       <h4 style="text-align: center;" class="sub-header">Lista de Coros Pendientes</h4>
          <div class="table-responsive">
            <table class="footable" id="listaCoroPendiente">
              <thead>
                <tr>
                  <th data-class="expand" style="text-align: center" class="alert alert-dismissible">NOMBRE</th>
                  <th style="text-align: center" class="alert alert-dismissible">AUTOR</th>
                  <th style="text-align: center" class="alert alert-dismissible">FECHA ACTUALIZACI&Oacute;N.</th>
                  <th style="text-align: center" class="alert alert-dismissible">N°CORO</th>
                  <th style="text-align: center" class="alert alert-dismissible">ESTATUS</th>
                  <th style="text-align: center" class="alert alert-dismissible">ELIMINAR/ACTIVAR</th>
                  <th data-hide="all" style="text-align: center" class="alert alert-dismissible">DESCRIPCI&Oacute;N</th>
                </tr>
              </thead>
              <tbody>
               <c:forEach items="${listaCoroPendiente}" var="pen" varStatus="indice">
               <tr>
                    <td style="text-align: left;">${pen.nombre}</td>
                    <td style="text-align: left;">${pen.autor}</td>
                    <td style="text-align: center;">
                        <fmt:formatDate value="${pen.fechaAct}" pattern="dd/MM/yyyy HH:mm:ss" />
                    </td>
                    <td style="text-align: center;">${pen.numCoro}</td>
                    <td style="text-align: center;">
                     <c:choose>
                       <c:when test="${pen.activo == 1}">
                        <label class="btn alert-info">Validando</label>
                       </c:when>
                       <c:when test="${pen.activo == 0}">
                           <label class="btn alert-danger">Pendiente</label>
                       </c:when>
                    </c:choose>
                    </td>
                    <td style="text-align: center;">
                       <a class="btn btn-danger" id="deleteCoro" href="#${pen.idCoro}"><span class="glyphicon glyphicon-trash"></span></a>
                       <a class="btn btn-success" id="activeCoro" href="#${pen.idCoro}"><span class="glyphicon glyphicon-ok"></span></a>
                    </td>
                    <td style="text-align: justify;"><br><br>
                        <c:out value="${pen.descripcionString}" escapeXml="false"/>
                    </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
    </div>