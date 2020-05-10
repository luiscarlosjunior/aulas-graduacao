<%-- 
    Document   : index
    Created on : 10/05/2020, 20:37:39
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="validarIdade.jsp">
        <div class="input date required">
            <jsp:include page="utilizadades/util.jsp" />
        </div>
        </form>
    </body>
</html>
