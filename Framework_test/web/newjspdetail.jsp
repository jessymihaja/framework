<%-- 
    Document   : newjspdetail
    Created on : 7 juil. 2023, 15:34:39
    Author     : jessy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="test.Test2"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        
        //Test2 emp=(Test2) request.getAttribute("liste");
        ArrayList<Test2> id=(ArrayList<Test2>)request.getAttribute("listeid");

        
        %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         
<%
         for (Test2 emp : id) {
                 
             
    
                out.print(emp.getId());
                out.print(emp.getNom());
    }
                %>
               <br>
               
                  
            
    </body>
</html>
