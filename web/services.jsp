<%-- 
    Document   : try
    Created on : Aug 21, 2020, 12:16:29 AM
    Author     : David Guntoro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script>
            $(function(){
            $("#nav-placeholder").load("navigationbar.jsp");
             });
        </script>
    </head>
    <body bgcolor="white">
        <!--Navigation bar, right after <body> tag-->
        <div id="nav-placeholder">
            
        </div>
        <h1> This is the SERVICES PAGE</h1>
    </body>
</html>