<%-- 
    Document   : ExemploLoop
    Created on : 05/05/2020, 18:24:11
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
        <%
            for (int i = 2; i <= 6; i++) {
        %>
            <h<% out.print(i); %>> Isso é um header </h<% out.print(i); %>>
        <%
            }
        %>
            </select>
         </form>
    </body>
</html>
