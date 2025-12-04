<%-- 
    Document   : exemploLaco
    Created on : 05/05/2020, 21:44:49
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
        
        <%
            for(int i = 1; i <= 6; i++) {
        %>
                <h<% out.print(i); %>>Isso é um header</h<% out.print(i); %>>
        <%
            }
        %>
            
    </body>
</html>
