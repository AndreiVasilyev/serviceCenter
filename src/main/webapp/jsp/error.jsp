<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>SC error page</title>
    </head>
    <body>

        error appear!!!

        <c:if test="${not empty pageContext.exception}">
            <c:set var="exception" value="${pageContext.exception}"/>
            <div class="container payment_window mb-5 pt-3 pb-5">
                <div class="container mt-5">
                    <h1 class="ml-5">${head}</h1>
                    <h3>${error_head} ${exception} </h3>
                    <h3> Stack trace:</h3>
                    <p style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt;">
                        <c:forEach var="element" items="${exception.stackTrace}">
                            ${element}
                            <br/>
                        </c:forEach>
                    </p>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.exception}">
            <c:set var="exception" value="${requestScope.exception}"/>
            <div class="container payment_window mb-5 pt-3 pb-5">
                <div class="container mt-5">
                    <h1 class="ml-5">${head}</h1>
                    <h3>${error_head} ${exception} </h3>
                    <h3> Stack trace:</h3>
                    <p style="font-family: Verdana, Arial, Helvetica, sans-serif;
    font-size: 9pt;">
                        <c:forEach var="element" items="${exception.stackTrace}">
                            ${element}
                            <br/>
                        </c:forEach>
                    </p>
                </div>
            </div>
        </c:if>
        <div>
            <i>no exeptions</i>
        </div>
    </body>
</html>
