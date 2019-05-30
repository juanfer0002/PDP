<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Battleship</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/sign-in/">

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">    
        <style>
            body, html {
                height: 100%
            }
            
            .btn {
                min-height: 50px
            }
        </style>
    </head>
    <body class="align-middle">

        <div class="container">


            <div class="container">

                <c:forEach var="gameRow" items="${sessionScope.GAME_BOARD}" varStatus="rowStatus">
                    <div class="row">
                        <c:forEach var="gameCol" items="${gameRow}" varStatus="colStatus">
                            <div class="col w-100 p-0">
                                <c:choose>

                                    <c:when test = "${gameCol < 0}">
                                        <button type="button" class="btn btn-light btn-block "></button>
                                    </c:when>

                                    <c:when test = "${gameCol == 0}">
                                        <a href="${path}/GameServlet?action=PLAY&x=${rowStatus.index}&y=${colStatus.index}" 
                                           type="button" class="btn btn-primary btn-block"></a>
                                    </c:when>

                                    <c:otherwise>
                                        <button type="button" class="btn btn-danger btn-block"></button>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
            <br />
            <div class="container alert alert-${MSG_TYPE != null ? MSG_TYPE : 'primary'}">
                ${sessionScope.MSG}

            </div>

            <div class="container">
                <a href="${path}/GameServlet?action=RESET" type="button" class="btn btn-warning btn-block">
                    <h3>Restart</h3>
                </a>
            </div>
        </div>

    </body>
</html>
