<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="modal fade" id="addOrderModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addOrderModalLabel">Новый заказ</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row mb-3">
                        <div class="col-4">
                            <div>
                                <label for="order-type" class="form-label">Тип заказа:</label>
                                <select class="form-select" id="order-type" name="orderNumber">
                                    <option value="NO" selected></option>
                                    <option value="P">Платный</option>
                                    <option value="S">Гарантийный</option>
                                </select>
                            </div>
                            <div id="order-type-error" class="form-text text-danger"
                                 data-error-match="Тип заказа не выбран">
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <label for="order-device" class="form-label">Тип устройства:</label>
                            <div class="input-group">
                                <input type="text" class="form-control dropdown-input" id="order-device" data-id="0"
                                       name="device">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown">Выбрать...
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end devices"></ul>
                            </div>
                            <div id="order-device-error" class="form-text text-danger"
                                 data-error-match="Название устройства состоит только из букв и имеет до 5 слов">
                            </div>
                        </div>
                        <div class="col-6">
                            <label for="order-company" class="form-label">Производитель:</label>
                            <div class="input-group">
                                <input type="text" class="form-control dropdown-input" id="order-company" data-id="0"
                                       name="company">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown">Выбрать...
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end companies">
                                </ul>
                            </div>
                            <div id="order-company-error" class="form-text text-danger"
                                 data-error-match="Название компании состоит только из букв и имеет до 3 слов">
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <div>
                                <label for="order-model" class="form-label">Модель:</label>
                                <input type="text" class="form-control" id="order-model" name="model"
                                       value=""/>
                            </div>
                            <div id="order-model-error" class="form-text text-danger"
                                 data-error-match="Модель состоит из букв и цифр и имеет длину до 20 знаков">
                            </div>
                        </div>
                        <div class="col-6">
                            <div>
                                <label for="order-serial" class="form-label">Серийный номер:</label>
                                <input type="text" class="form-control" id="order-serial" name="serialNumber"
                                       value=""/>
                            </div>
                            <div id="order-serial-error" class="form-text text-danger"
                                 data-error-match="Серийный номер состоит из букв и цифр и имеет длину до 20 знаков">
                            </div>
                        </div>
                    </div>
                    <hr class="mt-2">
                    <div class="row mb-3">
                        <div class="col-12 my-2">
                            <label class="form-label">Данные клиента:</label>
                        </div>
                        <div class="col-8">
                            <label for="find-phone" class="form-label">Найти клиента:</label>
                            <div class="input-group" id="client-find">
                                <input type="text" class="form-control" placeholder="Номер телефона" id="find-phone"
                                       name="findPhone">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown" id="find-phone-button" disabled>Искать
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end clients"></ul>
                                <button class="btn btn-outline-secondary" type="button">Сбросить</button>
                            </div>
                            <div id="find-phone-error" class="form-text text-danger"
                                 data-error-match="Номер телефона должен соответствовать формату +___-__-_______">
                            </div>
                        </div>
                        <div class="col-4">
                            <div>
                                <label for="order-email" class="form-label">Email:</label>
                                <input type="text" class="form-control" id="order-email" name="email"
                                       value=""/>
                            </div>
                            <div id="order-email-error" class="form-text text-danger"
                                 data-error-match="Адрес должен содержать не менее 8 символов и соответсвовать правилам">
                            </div>
                        </div>
                        <div class="col-12 mt-3">
                            <label for="client-names" class="form-label">Фамилия Имя Отчество:</label>
                            <div class="input-group" id="client-names">
                                <span class="input-group-text">ФИО</span>
                                <input type="text" class="form-control" id="order-first-name" name="firstName">
                                <input type="text" class="form-control" id="order-second-name" name="secondName">
                                <input type="text" class="form-control" id="order-patronymic" name="patronymic">
                            </div>
                            <div id="client-names-error" class="form-text text-danger"
                                 data-error-first="Имя имеет не более 20 букв, возможен знак -"
                                 data-error-second="Фамилия имеет не более 40 букв, возможен знак -"
                                 data-error-patronymic="Отчество имеет не более 20 букв">
                            </div>
                        </div>
                        <div class="col-12 my-3">
                            <label for="client-phones" class="form-label">Контактные телефоны:</label>
                            <div class="input-group" id="client-phones">
                                <span class="input-group-text">Телефоны</span>
                                <input type="text" class="form-control" id="order-phone-1" name="phoneNumber1">
                                <input type="text" class="form-control" id="order-phone-2" name="phoneNumber2">
                                <input type="text" class="form-control" id="order-phone-3" name="phoneNumber3">
                            </div>
                            <div id="client-phones-error" class="form-text text-danger"
                                 data-error-match="Номер телефона должен соответствовать формату +___-__-_______">
                            </div>
                        </div>

                        <div class="col-3">
                            <label for="country-input" class="form-label">Страна:</label>
                            <input type="text" class="form-control" id="country-input" name="country"
                                   value="Беларусь"/>
                            <div id="country-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-3">
                            <label for="postcode-input" class="form-label">Индекс:</label>
                            <input type="text" class="form-control" id="postcode-input" name="country"
                                   value=""/>
                            <div id="postcode-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-3">
                            <label for="state-input" class="form-label">Область:</label>
                            <input type="text" class="form-control" id="state-input" name="state"
                                   value=""/>
                            <div id="state-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-3">
                            <label for="region-input" class="form-label">Район:</label>
                            <input type="text" class="form-control" id="region-input" name="region"
                                   value=""/>
                            <div id="region-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-4">
                            <label for="city-input" class="form-label">Город:</label>
                            <input type="text" class="form-control" id="city-input" name="city"
                                   value=""/>
                            <div id="city-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-4">
                            <label for="street-input" class="form-label">Улица:</label>
                            <input type="text" class="form-control" id="street-input" name="street"
                                   value=""/>
                            <div id="street-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-2">
                            <label for="house-input" class="form-label">Дом:</label>
                            <input type="text" class="form-control" id="house-input" name="house"
                                   value=""/>
                            <div id="house-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-2">
                            <label for="apartment-input" class="form-label">Район:</label>
                            <input type="text" class="form-control" id="apartment-input" name="apartment"
                                   value=""/>
                            <div id="apartment-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>

                    </div>
                    <div class="alert alert-warning ${loginFailed==null?'d-none':'d-flex'} align-items-center mt-2 p-1"
                         id="alert-block">
                        <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                            <use href="#exclamation-triangle-fill"/>
                        </svg>
                        <div id="error-send-code" class="alert-block-message">${loginFailed}</div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                <button type="button" class="btn btn-primary" id="save-button" disabled>Сохранить</button>
            </div>
        </div>
    </div>
</div>