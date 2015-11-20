<%@ include file="../layout/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title><tiles:insertAttribute name="title"/></title>
<jsp:include page="staticResources.jsp"></jsp:include>
         <style>
             body{
                font:  10.5px Verdana;
             }
         </style>
</head>
<body style="">
    <div id="cuerpo">
        <div id="header">
            <tiles:insertAttribute name="header" />
        </div>
        <div id="contenido">
            <tiles:insertAttribute name="contenido" />
        </div>
        <br>
        <br>
        <div id="footer">
            <tiles:insertAttribute name="footer" />
        </div>
    </div>
</body>
</html>