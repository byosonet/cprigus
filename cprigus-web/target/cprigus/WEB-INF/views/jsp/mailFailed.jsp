<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
    <div class="container-fluid">
       <h4 style="text-align: center;" class="sub-header">Mails fallidos</h4>
          <div class="table-responsive">
            <table class="footable" id="listaMailFailed">
              <thead>
                <tr>
                  <th data-class="expand" style="text-align: center" class="alert alert-dismissible">DE</th>
                  <th style="text-align: center" class="alert alert-dismissible">PARA</th>
                  <th style="text-align: center" class="alert alert-dismissible">QUIEN ENVIA</th>
                  <th style="text-align: center" class="alert alert-dismissible">TIPO FALLO</th>
                  <th style="text-align: center" class="alert alert-dismissible">FECHA DE ENVIO</th>
                  <th style="text-align: center" class="alert alert-dismissible">ELIMINAR</th>
                  <th data-hide="all" style="text-align: center" class="alert alert-dismissible">CORREO</th>
                </tr>
              </thead>
              <tbody>
               <c:forEach items="${listaMail}" var="mail" varStatus="indice">
               <tr>
                    <td style="text-align: left;">${mail.mailTo}</td>
                    <td style="text-align: left;">${mail.mailFrom}</td>
                    <td style="text-align: left;">${mail.name}</td>
                    <td style="text-align: left;">${mail.typeFailed}</td>
                    <td style="text-align: center;">
                        <fmt:formatDate value="${mail.dateSend}" pattern="dd/MM/yyyy HH:mm:ss" />
                    </td>  
                    <td style="text-align: center;">
                        <a href="#${mail.idFailed}" id="deleteMailFailed" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
                    </td>
                    <td>
                        <br>
                        <br>
                        <div style="text-align:center">
                            <a href="/detalle/mail/${mail.idFailed}" class="btn btn-info" target="_blank">
                                <span class="glyphicon glyphicon-envelope" ></span></a>
                        </div>
                        <br>
                        <br>
                        <div style="text-align: justify;">
                            <c:out value="${mail.detalleClob}" escapeXml="true"/>
                        </div>
                    </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
    </div>