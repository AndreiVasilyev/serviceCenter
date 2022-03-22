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
        <main class="my-5">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center fw-bolder text-secondary mb-3">
                        <h2>${registration_header}</h2>
                    </div>
                    <div class="col-11 mx-auto">
                        <form method="POST"
                              class="needs-validation border border-primary border-2 rounded p-4 container mb-3"
                              action="/control?command=registration_final">
                            <input type="text" class="d-none disabled"
                                   id="registration-id-input" name="userId"
                                   value="${requestScope.registeredEmployee.id}"/>
                            <div class="row">
                                <div class="mb-3 col-3 mx-auto">
                                    <label for="registration-login-input" class="form-label">Логин:<span
                                            class="required-star">&#8432;</span></label>
                                    <div>
                                        <input type="text" class="form-control registration-final is-valid required"
                                               id="registration-login-input"
                                               name="login"
                                               value="${request_parameters.login[0]}"/>
                                    </div>
                                    <div id="registration-login-error" class="form-text text-danger"
                                         data-error-match="Логин содержит недопустимые символы или имеет недопустимое количество знаков">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-3 mx-auto">
                                    <label for="registration-password-input" class="form-label">Пароль:<span
                                            class="required-star">&#8432;</span></label>
                                    <div>
                                        <input type="password" class="form-control registration-final required"
                                               id="registration-password-input"
                                               name="password"
                                               value="${request_parameters.password[0]}"/>
                                    </div>
                                    <div id="registration-password-error" class="form-text text-danger"
                                         data-error-match="Пароль может содержать от 8 до 20 латинских символов и цифр">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-3 mx-auto">
                                    <label for="registration-password-confirm-input" class="form-label">Подтверждение
                                        пароля:<span class="required-star">&#8432;</span></label>
                                    <div>
                                        <input type="password" class="form-control registration-final required"
                                               id="registration-password-confirm-input"
                                               name="passwordConfirm"
                                               value="${request_parameters.passwordConfirm[0]}"/>
                                    </div>
                                    <div id="registration-password-confirm-error" class="form-text text-danger"
                                         data-error-match="Пароль может содержать от 8 до 20 латинских символов и цифр"
                                         data-error-equals="Подтвержденный пароль должен совпадать с введенным">

                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-3">
                                    <label for="registration-role-input" class="form-label">Роль:<span
                                            class="required-star">&#8432;</span></label>
                                    <div>
                                        <input type="text" class="form-control"
                                               id="registration-role-input" name="employeeRole"
                                               value="${request_parameters.employeeRole[0]}" readonly/>
                                    </div>
                                    <div id="registration-role-input-error" class="form-text text-danger"
                                         data-error-match="">
                                    </div>
                                </div>
                                <div class="mb-3 col-3">
                                    <label for="registration-second-name-input" class="form-label">Фамилия:<span
                                            class="required-star">&#8432;</span></label>
                                    <div>
                                        <input type="text" class="form-control registration-final required"
                                               id="registration-second-name-input"
                                               name="secondName"
                                               value="${request_parameters.secondName[0]}"/>
                                    </div>
                                    <div id="registration-second-name-error" class="form-text text-danger"
                                         data-error-match="Фамилия обязательна к заполнению. Содержит от 3 до 40 символов">
                                    </div>
                                </div>
                                <div class="mb-3 col-3">
                                    <label for="registration-first-name-input" class="form-label">Имя:<span
                                            class="required-star">&#8432;</span></label>
                                    <div>
                                        <input type="text" class="form-control registration-final required"
                                               id="registration-first-name-input"
                                               name="firstName"
                                               value="${request_parameters.firstName[0]}"/>
                                    </div>
                                    <div id="registration-first-name-error" class="form-text text-danger"
                                         data-error-match="Имя обязательно к заполнению. Содержит от 3 до 20 символов">
                                    </div>
                                </div>
                                <div class="mb-3 col-3">
                                    <label for="registration-patronymic-input" class="form-label">Отчество:</label>
                                    <div>
                                        <input type="text" class="form-control registration-final"
                                               id="registration-patronymic-input"
                                               name="patronymic"
                                               value="${request_parameters.patronymic[0]}"/>
                                    </div>
                                    <div id="registration-patronymic-error" class="form-text text-danger"
                                         data-error-match="Отчество может содержать от 3 до 20 символов">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-4">
                                    <label for="registration-email-input" class="form-label">Эл. почта:</label>
                                    <div>
                                        <input type="email" class="form-control registration-final"
                                               id="registration-email-input"
                                               name="email"
                                               value="${request_parameters.email[0]}"/>
                                    </div>
                                    <div id="registration-email-error" class="form-text text-danger"
                                         data-error-match="Недопустимый формат записи электронной почты">
                                    </div>
                                </div>
                                <div class="mb-3 col-4">
                                    <label for="registration-postcode-input" class="form-label">Индекс:</label>
                                    <div>
                                        <input type="text" class="form-control registration-final"
                                               id="registration-postcode-input"
                                               name="postcode"
                                               value="${request_parameters.postcode[0]}"/>
                                    </div>
                                    <div id="registration-postcode-error" class="form-text text-danger"
                                         data-error-match="Почтовый индекс должен состоять из 6 цифр">
                                    </div>
                                </div>
                                <div class="mb-3 col-4">
                                    <label for="registration-country-input" class="form-label">Страна:</label>
                                    <div>
                                        <input type="text" class="form-control registration-final"
                                               id="registration-country-input"
                                               name="country"
                                               value="${request_parameters.country[0]}"/>
                                    </div>
                                    <div id="registration-country-error" class="form-text text-danger"
                                         data-error-match="Название страны может содержать от 3 до 30 симоволов латиницы или кириллицы, дефис и пробел">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-4">
                                    <label for="registration-state-input" class="form-label">Область:</label>
                                    <div>
                                        <input type="text" class="form-control registration-final"
                                               id="registration-state-input"
                                               name="state"
                                               value="${request_parameters.state[0]}"/>
                                    </div>
                                    <div id="registration-state-error" class="form-text text-danger"
                                         data-error-match="Область может содержать от 8 до 40 симоволов латиницы или кириллицы, точку и пробел">
                                    </div>
                                </div>
                                <div class="mb-3 col-4">
                                    <label for="registration-region-input" class="form-label">Район:</label>
                                    <div>
                                        <input type="text" class="form-control registration-final"
                                               id="registration-region-input"
                                               name="region"
                                               value="${request_parameters.region[0]}"/>
                                    </div>
                                    <div id="registration-region-error" class="form-text text-danger"
                                         data-error-match="Район может содержать от 8 до 40 симоволов латиницы или кириллицы, точку, дефис и пробел">
                                    </div>
                                </div>
                                <div class="mb-3 col-4">
                                    <label for="registration-city-input" class="form-label">Город:<span
                                            class="required-star">&#8432;</span></label>
                                    <div>
                                        <input type="text" class="form-control registration-final required"
                                               id="registration-city-input"
                                               name="city"
                                               value="${request_parameters.city[0]}"/>
                                    </div>
                                    <div id="registration-city-error" class="form-text text-danger"
                                         data-error-match="Город обязателен к заполнению и может содержать 3 до 30 симоволов латиницы или кириллицы, дефис и пробел">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-4">
                                    <label for="registration-street-input" class="form-label">Улица:<span
                                            class="required-star">&#8432;</span></label>
                                    <div>
                                        <input type="text" class="form-control registration-final required"
                                               id="registration-street-input"
                                               name="street"
                                               value="${request_parameters.street[0]}"/>
                                    </div>
                                    <div id="registration-street-error" class="form-text text-danger"
                                         data-error-match="Улица обязательна к заполнению и может содержать 5 до 40 симоволов латиницы или кириллицы, точку, дефис и пробел">
                                    </div>
                                </div>
                                <div class="mb-3 col-4">
                                    <label for="registration-house-number-input" class="form-label">Дом:<span
                                            class="required-star">&#8432;</span></label>
                                    <div>
                                        <input type="text" class="form-control registration-final required"
                                               id="registration-house-number-input"
                                               name="houseNumber"
                                               value="${request_parameters.houseNumber[0]}"/>
                                    </div>
                                    <div id="registration-house-number-error" class="form-text text-danger"
                                         data-error-match="Номер дома обязателен к заполнению и может содержать до 4 цифр">
                                    </div>
                                </div>
                                <div class="mb-3 col-4">
                                    <label for="registration-apartment-number-input"
                                           class="form-label">Квартира:</label>
                                    <div>
                                        <input type="text" class="form-control registration-final"
                                               id="registration-apartment-number-input"
                                               name="apartmentNumber"
                                               value="${request_parameters.apartmentNumber[0]}"/>
                                    </div>
                                    <div id="registration-apartment-number-error" class="form-text text-danger"
                                         data-error-match="Номер квартиры может содержать до 4 цифр">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-11 mb-3 mx-auto">
                                    <label for="registartion-phones" class="form-label">Контактные телефоны:<span
                                            class="required-star">&#8432;</span></label>
                                    <div class="input-group" id="registartion-phones">
                                        <span class="input-group-text">Телефоны</span>
                                        <input type="text" class="form-control registration-final required"
                                               id="registration-phone-1-input"
                                               name="phoneNumber1" value="${request_parameters.phoneNumber1[0]}">
                                        <input type="text" class="form-control registration-final"
                                               id="registration-phone-2-input"
                                               name="phoneNumber2" value="${request_parameters.phoneNumber2[0]}">
                                        <input type="text" class="form-control registration-final"
                                               id="registration-phone-3-input"
                                               name="phoneNumber3" value="${request_parameters.phoneNumber3[0]}">
                                    </div>
                                    <div id="registration-phones-error" class="form-text text-danger"
                                         data-error-match="Телефонный номер должен содержать 12 цифр и соответствовать формату +____________"
                                         data-error-required="Один контактный номер обязателен к заполнению">
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-2">
                                <button type="submit" class="btn btn-success col-auto ms-auto"
                                        id="registration-save-button"
                                        disabled>
                                    Сохранить
                                </button>
                            </div>
                            <div class="alert alert-warning ${registrationFinalFailed==null?'d-none':'d-flex'} align-items-center mt-2 p-1"
                                 id="alert-block">
                                <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                                    <use href="#exclamation-triangle-fill"/>
                                </svg>
                                <div id="error-registration-first-step"
                                     class="alert-block-message">${registrationFinalFailed}</div>
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
        <script src="../js/registration_final.js" type="text/javascript"></script>
        <script src="../js/ajax_request.js" type="text/javascript"></script>
    </body>
</html>