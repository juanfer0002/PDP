<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <c:set var="path" scope="session" value="${pageContext.servletContext.contextPath}"/>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">                
        <title>List users</title>
        <!-- Custom styles for this template -->
        <link href="${path}/css/album.css" rel="stylesheet">
    </head>
    <body>
        <header>
            <div class="collapse bg-dark" id="navbarHeader">
                <div class="container">
                    <div class="row">

                    </div>
                </div>
            </div>
            <div class="navbar navbar-dark bg-dark shadow-sm">
                <div class="container d-flex justify-content-between">
                    <a href="#" class="navbar-brand d-flex align-items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="mr-2" viewBox="0 0 24 24" focusable="false"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path><circle cx="12" cy="13" r="4"></circle></svg>
                        <strong>Sample Web App</strong>
                    </a> 
                </div>
            </div>
        </header>
        <section class="jumbotron text-center">
            <div class="container">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Doc. ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Last Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${sessionScope.USERS}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.lastName}</td>
                                <td>${user.email}</td>   
                                <td>${user.active ? "Active" : "Inactive"}</td>   
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>        
    </body>
</html>
