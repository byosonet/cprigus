<%@ include file="../layout/taglibs.jsp"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/" style="color:white;">Himnario Presbiteriano</a>
    </div>
    <div style="height: 1px;" aria-expanded="false" class="navbar-collapse collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        
        <c:if test="${menu}">
            <li><a id="perfil" href="#" class="btn btn-primary"><span class="glyphicon glyphicon-user" style="color: white"> Perfil</span></a></li>
            <li><a id="registrar" href="#" class="btn btn-primary"><span class="glyphicon glyphicon-pencil" style="color: white"> Registrar</span></a></li>
            <li><a id="consultar" href="#" class="btn btn-primary"><span class="glyphicon glyphicon-search" style="color: white"> Consultar</span></a></li>
            <li><a id="contacto" href="#" class="btn btn-primary"><span class="glyphicon glyphicon-envelope" style="color: white"> Contacto</span></a></li>
        </c:if>
        <li class="dropdown">
          <a style="color:white;" aria-expanded="true" aria-haspopup="true" role="button" data-toggle="dropdown" class="btn btn-primary dropdown-toggle" href="#"><span class="glyphicon glyphicon-arrow-down" style="color: white"> Opciones</span></a>

        <ul class="dropdown-menu">
                <c:choose>
                        <c:when test="${menu}">
                            <li><a href="#" id="exit">Salir <span class="glyphicon glyphicon-arrow-right"></span></a></li>
                            <!--
                            <li role="separator" class="divider"></li>
                            <li><a href="#" id="perfil"><span class="glyphicon glyphicon-user"></span> Perfil</a></li>
                            <li><a href="#" id="registrar"><span class="glyphicon glyphicon-pencil"></span> Registrar</a></li>
                            <li><a href="#" id="consultar"><span class="glyphicon glyphicon-search"></span> Consultar</a></li>
                            <li><a href="#" id="contacto"><span class="glyphicon glyphicon-envelope"></span> Contacto</a></li>
                            -->
                        </c:when>
                        <c:when test="${menu eq 'smenu'}">
                            <li><a href="/" id="exit">Regresar <span class="glyphicon glyphicon-arrow-left"></span></a></li>
                        </c:when>
                        <c:when test="${menu eq 'detalleMenu'}">
                            <li><a href="#" id="exit">Sin Opciones <span class="glyphicon glyphicon-remove"></span></a></li>
                        </c:when>
                        <c:otherwise>
                           <li><a href="#" id="exit">Salir <span class="glyphicon glyphicon-arrow-right"></span></a></li>
                        </c:otherwise>
                </c:choose>    
        </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
