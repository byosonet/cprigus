<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
    <div class="container-fluid">
       <h4 style="text-align: center;" class="sub-header">Movimientos en Sistema</h4>
          <div class="table-responsive">
            <table class="table table-hover" id="listaBitacora">
              <thead>
                <tr>
                  <th style="text-align: center" class="alert alert-dismissible">#</th>
                  <th style="text-align: center" class="alert alert-dismissible"><span class="glyphicon glyphicon-user"></span></th>
                  <th style="text-align: center" class="alert alert-dismissible"><span class="glyphicon glyphicon-tasks"></span></th>
                </tr>
              </thead>
              <tbody>
               <c:forEach items="${changesetUser}" var="ch" varStatus="indice">
               <tr>
                    <td style="text-align: center;width: 2%;">${ch.idChangeset}</td>
                    <c:choose>
                       <c:when test="${ch.idCoro == 'NA'}">
                           <td>${ch.movement}</td>
                       </c:when>
                       <c:when test="${ch.idCoro != 'NA'}">
                           <td>${ch.movement} #${ch.idCoro}</td>
                       </c:when>
                       
                   </c:choose>
                    <td style="text-align: center;">
                        <fmt:formatDate value="${ch.fechaMov}" pattern="dd/MM/yyyy HH:mm:ss" />
                    </td>    
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
    </div>