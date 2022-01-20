<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="main_title" key="main.title"/>

<!DOCTYPE html>
<html>
    <head>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-icons.css" rel="stylesheet">
        <link href="../css/main.css" rel="stylesheet">
        <link href="../css/header.css" rel="stylesheet">
        <title>${main_title}</title>
    </head>
    <body>
        <c:import url="header.jsp"/>
        <div>
            <a href="#">link</a>
        </div>
        <main>
        </main>
        <c:import url="footer.jsp"/>
        <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>
        <script src="../js/header.js" type="text/javascript"></script>
    </body>
</html>