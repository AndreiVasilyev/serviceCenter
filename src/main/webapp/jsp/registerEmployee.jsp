<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="close_button_title" key="control.orders.add.close.button.title"/>
<fmt:message var="login_error_match" key="login.main.form.login.error.match"/>

<div class="modal fade" id="registerEmployeeModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-primary fw-bold" id="registerEmployeeModalLabel">Регистрация нового
                    сотрудника</h5>
                <button type="button" class="btn-close close-register-employee" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row mb-3">
                        <div class="col-6">
                            <div>
                                <label for="register-user-role" class="form-label">Роль:<span
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
                                 data-error-match="Выберите роль пользователя">
                            </div>
                        </div>
                        <div class="col-6">
                            <label for="register-user-login" class="form-label">Логин:<span
                                    class="required-star">&#8432;</span></label>
                            <div class="input-group">
                                <input type="text" class="form-control register-new-employee" id="register-user-login"
                                       name="login" value=""/>
                                <button class="btn btn-outline-secondary" type="button" id="check-login-button"
                                        disabled>
                                    Проверить
                                </button>
                            </div>
                            <div id="register-user-login-error" class="form-text text-danger"
                                 data-error-match="${login_error_match}" data-error-unique="Логин должен быть
                                уникальный. Это имя уже используется">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary close-register-employee"
                            data-bs-dismiss="modal">${close_button_title}
                    </button>
                    <button type="button" class="btn btn-primary" id="register-employee-button"
                            disabled>Зарегистрировать
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
