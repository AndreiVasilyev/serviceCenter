<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="role" value="${not empty sessionScope.userRole?sessionScope.userRole:'GUEST'}"/>
<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:set var="request_parameters" value="${sessionScope.requestData.requestParameters}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="registration_title" key="registration.title"/>
<fmt:message var="registration_header" key="registration.main.header"/>
<fmt:message var="registration_role" key="registration.main.role"/>
<fmt:message var="registration_role_error" key="registration.main.role.error.match"/>
<fmt:message var="registration_login" key="registration.main.login"/>
<fmt:message var="registration_login_label" key="registration.main.login.label"/>
<fmt:message var="registration_login_error" key="registration.main.login.error.match"/>
<fmt:message var="registration_next_button" key="registration.main.next.button.label"/>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-icons.css" rel="stylesheet">
        <link href="../css/header.css" rel="stylesheet">
        <link href="../css/registration.css" rel="stylesheet">
        <title>${registration_title}</title>
    </head>
    <body class="min-vh-100">
        <c:import url="icon_sprite.jsp"/>
        <c:import url="header.jsp"/>
        <main class="mt-5">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center fw-bolder text-secondary mb-3">
                        <h2>${registration_header}</h2>
                    </div>
                    <div class="col-4 mx-auto">
                        <form method="POST"
                              class="form-floating needs-validation border border-primary border-2 rounded p-4"
                              action="/control?command=registration_first_step">
                            <div class="mb-3">
                                <div>
                                    <label for="registration-employee-role"
                                           class="form-label">${registration_role}:<span
                                            class="required-star">&#8432;</span></label>
                                    <select class="form-select registration-first-step" id="registration-employee-role"
                                            name="employeeRole">
                                        <option value="" ${request_parameters.employeeRole eq null?'selected':''}></option>
                                        <option value="ADMIN" ${request_parameters.employeeRole[0] eq 'ADMIN'?'selected':''}>
                                            ADMIN
                                        </option>
                                        <option value="MANAGER" ${request_parameters.employeeRole[0] eq 'MANAGER'?'selected':''}>
                                            MANAGER
                                        </option>
                                        <option value="ENGINEER" ${request_parameters.employeeRole[0] eq 'ENGINEER'?'selected':''}>
                                            ENGINEER
                                        </option>
                                    </select>
                                </div>
                                <div id="registration-employee-role-error" class="form-text text-danger"
                                     data-error-match="${registration_role_error}">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="registration-login-input" class="form-label">${registration_login}:<span
                                        class="required-star">&#8432;</span></label>
                                <div class="form-floating">
                                    <input type="text" class="form-control registration-first-step"
                                           id="registration-login-input"
                                           name="login"
                                           value="${request_parameters.login[0]}"/>
                                    <label for="registration-login-input"
                                           class="input-field-tip">${registration_login_label}</label>
                                </div>
                                <div id="registration-login-error" class="form-text text-danger"
                                     data-error-match="${registration_login_error}">
                                </div>
                            </div>
                            <div class="row">
                                <button type="submit" class="btn btn-success col-auto ms-auto"
                                        id="registration-next-button"
                                        disabled>${registration_next_button}
                                </button>
                            </div>
                            <div class="alert alert-warning ${registrationFirstStepFailed==null?'d-none':'d-flex'} align-items-center mt-2 p-1"
                                 id="alert-block">
                                <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                                    <use href="#exclamation-triangle-fill"/>
                                </svg>
                                <div id="error-registration-first-step"
                                     class="alert-block-message">${registrationFirstStepFailed}
                                </div>
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
        <script src="../js/registration.js" type="text/javascript"></script>
        <script src="../js/ajax_request.js" type="text/javascript"></script>
    </body>
</html>