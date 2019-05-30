<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!doctype html>
<html lang="en">
    <c:set var="path" scope="session" value="${pageContext.servletContext.contextPath}"/>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v3.8.5">
        <title>Battleship</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/sign-in/">

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">        

    </head>
    <body class="text-center">
        <h1>Hello to a our great game!! Press the link below to start playing</h1>
        <a href="${path}/GameServlet?action=SHOW_GAME_BOARD"> Go ready!!</a>

        <br />
        <br />
        <br />
        <br />
        <br />

        <h3>Hecho por:</h3>
        <h4>Juan Fernando Muñoz Marin - 1036658803</h4>
        <h4>Alexandra Montoya - 1128460273</h4>

    </body>
</html>
