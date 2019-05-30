<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <c:set var="path" scope="session" value="${pageContext.servletContext.contextPath}"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">        
    </head>
    <body>
    <body>
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
            <h5 class="my-0 mr-md-auto font-weight-normal">App cells</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="#">Support</a>
            </nav>
            <a class="btn btn-outline-primary" href="#">Sign in</a>
        </div>

        <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
            <h1 class="display-4">Dashboard cells</h1>
        </div>

        <div class="container">


            <div class="card-deck mb-4 text-center">
                <c:forEach var="cell" items="${sessionScope.CELLS}">
                    <div class="col-lg-4 col-md-6">
                        <form class="needs-validation" data-toggle="validator" role="form" method="POST" action="${path}/CellServlet">
                            <div class="card mb-4 shadow-sm">
                                <div class="card-header text-white bg-${cell.reserved ? 'success' : 'dark'}">
                                    <h4 class="my-0 font-weight-normal">Cell ${cell.id}</h4>
                                </div>
                                <div class="card-body">
                                    <img src="${cell.photo}" class="card-img-top mb-4">

                                    <input type="hidden" name="id" value="${cell.id}"/>

                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">Document ID</span>
                                        </div>
                                        <input type="text" class="form-control" name="userId" 
                                               required value="${cell.userId}">
                                        <div class="invalid-feedback" style="width: 100%;">
                                            Your document ID is required.
                                        </div>
                                    </div>

                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">Date</span>
                                        </div>
                                        <input type="text" class="form-control" name="date" 
                                               value="${cell.date}" required>
                                        <div class="invalid-feedback">
                                            Valid name is required.
                                        </div>
                                    </div>

                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon1">Photo</span>
                                        </div>
                                        <input type="text" class="form-control" name="photo" 
                                               value="${cell.photo}" required>
                                        <div class="invalid-feedback">
                                            Valid name is required.
                                        </div>
                                    </div>

                                </div>

                                <div class="btn-group btn-group-lg btn-block" role="group" aria-label="Basic example">
                                    <button type="submit" name="action" value="RESERVE_CELL" class="btn btn-success">Reserve</button>
                                    <button type="submit" name="action" value="UPDATE_CELL" class="btn btn-primary">Update</button>
                                    <button type="submit" name="action" value="FREE_CELL" class="btn btn-dark">Free</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:forEach>
            </div>

        </div>

    </body>
</html>
