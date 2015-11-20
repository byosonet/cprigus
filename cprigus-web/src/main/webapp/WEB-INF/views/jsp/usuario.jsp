<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
    <div class="container-fluid">
       <h4 style="text-align: center;" class="sub-header">Usuarios Registrados</h4>
          <div class="table-responsive">
            <table class="table table-hover" id="listaUsuario">
              <thead>
                <tr>
                  <th style="text-align: center" class="alert alert-dismissible">NOMBRE</th>
                  <th style="text-align: center" class="alert alert-dismissible">FECHA ALTA</th>
                  <th style="text-align: center" class="alert alert-dismissible">ULT. CONEXION</th>
                  <th style="text-align: center" class="alert alert-dismissible">CORREO</th>
                  <th style="text-align: center" class="alert alert-dismissible">PROFESI&Oacute;N</th>
                  <th style="text-align: center" class="alert alert-dismissible">ELIMINAR</th>
                </tr>
              </thead>
              <tbody>
               <c:forEach items="${listaUsuario}" var="user" varStatus="indice">
               <tr>
                    <td style="text-align: left;width: 2%;">${user.nombre}</td>
                    <td style="text-align: center;">
                        <fmt:formatDate value="${user.fechaAlta}" pattern="dd/MM/yyyy HH:mm:ss" />
                    </td>    
                    <td style="text-align: center;">
                        <fmt:formatDate value="${user.ultConexion}" pattern="dd/MM/yyyy HH:mm:ss" />
                    </td>
                    <td style="text-align: left;width: 2%;">${user.email}</td>
                    <td style="text-align: left;width: 2%;">${user.actividad}</td>
                    <td style="text-align: center;width: 2%;">
                        <a href="#${user.idUsuario};${user.nombre}" id="deleteUser" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>
                    </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
    </div>