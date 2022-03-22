<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="role" value="${not empty sessionScope.userRole?sessionScope.userRole:'GUEST'}"/>
<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:set var="request_parameters" value="${sessionScope.requestData.requestParameters}"/>

<c:url value="/control?command=goto_registration_page" var="registration_page"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="login_title" key="login.title"/>
<fmt:message var="page_header" key="login.main.header"/>
<fmt:message var="login_label" key="login.main.form.login.label"/>
<fmt:message var="login_tip" key="login.main.form.login.tip"/>
<fmt:message var="login_error_match" key="login.main.form.login.error.match"/>
<fmt:message var="password_label" key="login.main.form.password.label"/>
<fmt:message var="password_tip" key="login.main.form.password.tip"/>
<fmt:message var="password_error_match" key="login.main.form.password.error.match"/>
<fmt:message var="form_submit" key="login.main.form.submit"/>
<fmt:message var="remember" key="login.main.form.remember.label"/>
<fmt:message var="registration" key="login.main.form.link.registration"/>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-icons.css" rel="stylesheet">
        <link href="../css/header.css" rel="stylesheet">
        <title>${login_title}</title>
    </head>
    <body class="min-vh-100">
        <c:import url="icon_sprite.jsp"/>
        <c:import url="header.jsp"/>
        <main class="mt-5">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center fw-bolder text-secondary">
                        <h2>${page_header}</h2>
                    </div>
                    <div class="col-4 mx-auto">
                        <form method="POST" name="login-form"
                              class="needs-validation border border-primary border-2 rounded p-4"
                              action="/control?command=login">
                            <div class="mb-3">
                                <label for="login-input" class="form-label">${login_label}</label>
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="login-input" name="login"
                                           value="${request_parameters.login[0]}"/>
                                    <label for="login-input" class="input-field-tip">${login_tip}</label>
                                </div>
                                <div id="login-error" class="form-text text-danger"
                                     data-error-match="${login_error_match}">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="password-input" class="form-label">${password_label}</label>
                                <div class="form-floating">
                                    <input type="password" class="form-control" id="password-input" name="password"
                                           value="${request_parameters.password[0]}" autocomplete="on"/>
                                    <label for="password-input" class="input-field-tip">${password_tip}</label>
                                </div>
                                <div id="password-error" class="form-text text-danger"
                                     data-error-match="${password_error_match}"></div>
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="remember-input" name="remember"
                                ${request_parameters.remember[0]=='on'?'checked':''}>
                                <label class="form-check-label" for="remember-input">${remember}</label>
                            </div>
                            <div class="row">
                                <button type="submit" class="btn btn-success col-auto" id="login-button" disabled>
                                    ${form_submit}</button>
                                <div class="ms-auto col-auto">
                                    <a href="${registration_page}" class="text-decoration-none">${registration}</a>
                                </div>
                            </div>
                            <div class="alert alert-warning ${loginFailed==null?'d-none':'d-flex'} align-items-center mt-2 p-1"
                                 id="alert-block">
                                <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                                    <use href="#exclamation-triangle-fill"/>
                                </svg>
                                <div id="error-send-code" class="alert-block-message">${loginFailed}</div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </main>
        <c:import url="footer.jsp"/>
        <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>
        <script src="../js/header.js" type="text/javascript"></script>
        <script src="../js/custom_validation.js" type="text/javascript"></script>
        <script src="../js/login.js" type="text/javascript"></script>
        <script src="../js/ajax_request.js" type="text/javascript"></script>
    </body>
</html>