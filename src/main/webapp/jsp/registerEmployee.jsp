<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="close_button_title" key="control.orders.add.close.button.title"/>
<fmt:message var="login_error_match" key="login.main.form.login.error.match"/>
<fmt:message var="register_title" key="control.employees.register.modal.title"/>
<fmt:message var="register_role" key="control.employees.register.modal.role.title"/>
<fmt:message var="register_role_error" key="control.employees.register.modal.role.error"/>
<fmt:message var="register_login" key="control.employees.register.modal.login.title"/>
<fmt:message var="register_login_error" key="control.employees.register.modal.login.error"/>
<fmt:message var="register_check_login" key="control.employees.register.modal.check.login.button"/>
<fmt:message var="register_button" key="control.employees.register.modal.button"/>

<div class="modal fade" id="registerEmployeeModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-primary fw-bold" id="registerEmployeeModalLabel">${register_title}</h5>
                <button type="button" class="btn-close close-register-employee" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row mb-3">
                        <div class="col-6">
                            <div>
                                <label for="register-user-role" class="form-label">${register_role}:<span
                                        class="required-star">&#8432;</span></label>
                                <select class="form-select register-new-employee" id="register-user-role"
                                        name="userRole">
                                    <option value="" selected></option>
                                    <option value="ADMIN">ADMIN</option>
                                    <option value="MANAGER">MANAGER</option>
                                    <option value="ENGINEER">ENGINEER</option>
                                </select>
                            </div>
                            <div id="register-user-role-error" class="form-text text-danger"
                                 data-error-match="${register_role_error}">
                            </div>
                        </div>
                        <div class="col-6">
                            <label for="register-user-login" class="form-label">${register_login}:<span
                                    class="required-star">&#8432;</span></label>
                            <div class="input-group">
                                <input type="text" class="form-control register-new-employee" id="register-user-login"
                                       name="login" value="" data-checked=""/>
                                <button class="btn btn-outline-secondary" type="button" id="check-login-button"
                                        disabled>
                                    ${register_check_login}
                                </button>
                            </div>
                            <div id="register-user-login-error" class="form-text text-danger"
                                 data-error-match="${login_error_match}" data-error-unique="${register_login_error}">
                            </div>
                        </div>
                    </div>
                    <div class="alert alert-warning d-none align-items-center mt-2 p-1"
                         id="alert-register-result">
                        <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                            <use href="#exclamation-triangle-fill"/>
                        </svg>
                        <div id="register-result-message" class=""></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary close-register-employee"
                            data-bs-dismiss="modal">${close_button_title}
                    </button>
                    <button type="button" class="btn btn-primary" id="register-employee-button"
                            disabled>${register_button}
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
