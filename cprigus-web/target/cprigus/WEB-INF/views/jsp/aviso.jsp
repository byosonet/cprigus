<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<body>
    <div>
        <h3 class="alert alert-info" style="text-align: center;">Avisos</h3>
        
        <center>
        <div style="width: 100%;text-align: justify;">
            <font face="Arial" color="gray">
                <hr>
                    
                        <c:out value="${detalle}" escapeXml="false"/>
                     
                <hr>                          
            </font>
        </div>
        </center>
                
        
        
        
    </div>
</body>
</html>