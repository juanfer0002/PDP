<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <c:set var="path" scope="session" value="${pageContext.servletContext.contextPath}"/>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">      

        <title>Messages</title>
    </head>
    <body>
        <div class="container mt-5"> 
            <div class="alert alert-${sessionScope.TYPE.code}" role="alert">
                <h4 class="alert-heading">
                    <c:out value="${sessionScope.TYPE.title}"/>
                </h4>
                <p><c:out value="${sessionScope.MSG}"/></p>
                <hr>
                <p class="mb-0"><a href="${path}${sessionScope.ROUTE}" />Go back!</p>
            </div>
        </div>



    </body>
</html>
