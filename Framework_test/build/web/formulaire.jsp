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
        <title>formulaire</title>
        
    </head>
    <body>
        <form action="save" method="GET">
            <input type="number" name="id">
            <input type="text" name="nom">
            <input type="text" name="prenom">
            <input type="submit" value="OK">
        </form>
    </body>
</html>
