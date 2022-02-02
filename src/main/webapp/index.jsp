<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <c:set var="role" value="${sessionScope.userRole}"/>
        <c:set var="page_path" value="${role eq null or role eq 'GUEST' or role eq 'CLIENT'?'/control?command=goto_main_page':'/control?command=goto_control_page'}"/>
        <c:redirect url="${page_path}"/>
    </body>
</html>