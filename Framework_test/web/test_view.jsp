<%-- 
    Document   : test_view
    Created on : 30 mars 2023, 16:55:13
    Author     : jessy
--%>

<%@page import="test.Test2"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        ArrayList<Test2> list=(ArrayList<Test2>)request.getAttribute("liste");
        
        %>
    <body>
        <h1>ca marche pas all emp</h1>
        <h2>liste des employes</h2>
        <% 
        for (Test2 emp : list) {
                out.print(emp.getNom()+" " +emp.getPrenom());
                %>
               <br>
               <%
            }
            %>
    </body>
</html>
