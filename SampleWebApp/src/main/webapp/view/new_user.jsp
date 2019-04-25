<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <c:set var="path" scope="session" value="${pageContext.servletContext.contextPath}"/>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">                
        <title>New user</title>
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
                <h4 class="mb-3">New user</h4>
                <form class="needs-validation" novalidate>

                    <div class="mb-3">
                        <label for="documentId">Document ID</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">@</span>
                            </div>
                            <input type="text" class="form-control" name="documentId" placeholder="Document ID" required>
                            <div class="invalid-feedback" style="width: 100%;">
                                Your document ID is required.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" if="name" name="name" placeholder="" value="" required>
                            <div class="invalid-feedback">
                                Valid name is required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Last name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value="" required>
                            <div class="invalid-feedback">
                                Valid last name is required.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="email">Email <span class="text-muted">(Optional)</span></label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com">
                        <div class="invalid-feedback">
                            Please enter a valid email address for shipping updates.
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="address">Password</label>
                            <input type="password" class="form-control" id="address" name="address" required>
                            <div class="invalid-feedback">
                                Please enter your password.
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="status">Status</label>
                            <select class="custom-select d-block w-100" id="status" name="status" required>
                                <option>Active</option>
                                <option>Inactive</option>
                            </select>
                            <div class="invalid-feedback">
                                Please select a valid status.
                            </div>
                        </div>

                    </div>

                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
                </form>

            </div>
        </section>        
    </body>
</html>
