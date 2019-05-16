<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <c:set var="path" scope="session" value="${pageContext.servletContext.contextPath}"/>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">      

        <title>No valid action</title>
    </head>
    <body>
        <div class="container mt-5"> 
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">
                    This isn't a place you should be. Please leave.
                </h4>
            </div>
        </div>

    </body>
</html>
