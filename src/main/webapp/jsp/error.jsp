<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="error_title" key="error.title"/>

<!DOCTYPE html>
<html>
    <head>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-icons.css" rel="stylesheet">
        <link href="../css/header.css" rel="stylesheet">
        <title>${error_title}</title>
    </head>
    <body class="min-vh-100">
        <c:import url="header.jsp"/>
        <div class="mt-3">
            <c:if test="${not empty pageContext.exception}">
                <c:set var="exception" value="${pageContext.exception}"/>
                <div class="container mb-5 pt-3 pb-5">
                    <div class="container mt-5">
                        <h1 class="ml-5">${head}</h1>
                        <h3>${error_head} ${exception} </h3>
                        <h3> Stack trace:</h3>
                        <p style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9pt;">
                            <c:forEach begin="0" end="10" varStatus="loop">
                                <c:if test="${!loop.first and exception!=null}">
                                    Caused By:<br/>
                                </c:if>
                                <c:if test="${exception != null}">
                                    <span class="ms-2 fw-bold">Message: ${exception.message}</span>
                                    <br/>
                                    <c:forEach items="${exception.stackTrace}" var="trace">
                                        <span class="ms-3">${trace}</span>
                                        <br/>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${loop.last && exception != null}">
                                    More causes not listed...
                                </c:if>
                                <c:set var="exception" value="${exception.cause}"/>
                            </c:forEach>
                        </p>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.exception}">
                <c:set var="exception" value="${requestScope.exception}"/>
                <div class="container mb-5 pt-3 pb-5">
                    <div class="container mt-5">
                        <h1 class="ml-5">${head}</h1>
                        <h3>${error_head} ${exception} </h3>
                        <h3> Stack trace:</h3>
                        <p style="font-family: Verdana, Arial, Helvetica, sans-serif;
    font-size: 9pt;">
                            <c:forEach begin="0" end="10" varStatus="loop">
                                <c:if test="${!loop.first and exception!=null}">
                                    Caused By:<br/>
                                </c:if>
                                <c:if test="${exception != null}">
                                    <span class="ms-2 fw-bold">Message: ${exception.message}</span>
                                    <br/>
                                    <c:forEach items="${exception.stackTrace}" var="trace">
                                        <span class="ms-3">${trace}</span>
                                        <br/>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${loop.last && exception != null}">
                                    More causes not listed...
                                </c:if>
                                <c:set var="exception" value="${exception.cause}"/>
                            </c:forEach>
                        </p>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.exception}">
                <c:set var="exception" value="${sessionScope.exception}"/>
                <div class="container mb-5 pt-3 pb-5">
                    <div class="container">
                        <h1 class="ml-5">${head}</h1>
                        <h3>${error_head}${exception}</h3>
                        <h3> Stack trace:</h3>
                        <p style="font-family: Verdana, Arial, Helvetica, sans-serif;
    font-size: 9pt;">
                            <c:forEach begin="0" end="10" varStatus="loop">
                                <c:if test="${!loop.first and exception!=null}">
                                    Caused By:<br/>
                                </c:if>
                                <c:if test="${exception != null}">
                                    <span class="ms-2 fw-bold">Message: ${exception.message}</span>
                                    <br/>
                                    <c:forEach items="${exception.stackTrace}" var="trace">
                                        <span class="ms-3">${trace}</span>
                                        <br/>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${loop.last && exception != null}">
                                    More causes not listed...
                                </c:if>
                                <c:set var="exception" value="${exception.cause}"/>
                            </c:forEach>
                        </p>
                    </div>
                </div>
            </c:if>
        </div>
        <c:import url="footer.jsp"/>
        <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>
        <script src="../js/header.js" type="text/javascript"></script>
    </body>
</html>
