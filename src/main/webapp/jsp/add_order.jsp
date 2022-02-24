<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="add_header" key="control.orders.add.header"/>
<fmt:message var="add_order_type" key="control.orders.add.type.title"/>
<fmt:message var="payed_type" key="control.orders.add.type.payed"/>
<fmt:message var="warranty_type" key="control.orders.add.type.warranty"/>
<fmt:message var="add_order_type_error" key="control.orders.add.type.error.match"/>
<fmt:message var="add_device_type" key="control.orders.add.device.type.title"/>
<fmt:message var="choose_button_title" key="control.orders.add.choose.button.title"/>
<fmt:message var="add_device_type_error" key="control.orders.add.device.type.error.match"/>
<fmt:message var="add_company_type" key="control.orders.add.company.type.title"/>
<fmt:message var="add_company_type_error" key="control.orders.add.company.type.error.match"/>
<fmt:message var="add_model" key="control.orders.add.model.title"/>
<fmt:message var="add_model_error" key="control.orders.add.model.error.match"/>
<fmt:message var="add_serial" key="control.orders.add.serial.title"/>
<fmt:message var="add_serial_error" key="control.orders.add.serial.error.match"/>
<fmt:message var="add_client_title" key="control.orders.add.client.title"/>
<fmt:message var="add_client_find_title" key="control.orders.add.client.find.title"/>
<fmt:message var="add_client_find_button_title" key="control.orders.add.client.find.button.title"/>
<fmt:message var="add_client_reset_button_title" key="control.orders.add.client.reset.button.title"/>
<fmt:message var="add_client_find_error" key="control.orders.add.client.find.error"/>
<fmt:message var="add_client_email_title" key="control.orders.add.client.email.title"/>
<fmt:message var="add_client_email_error" key="control.orders.add.client.email.error"/>
<fmt:message var="add_client_names_title" key="control.orders.add.client.names.title"/>
<fmt:message var="add_client_first_name_error" key="control.orders.add.client.first.name.error"/>
<fmt:message var="add_client_second_name_error" key="control.orders.add.client.second.name.error"/>
<fmt:message var="add_client_patronymic_error" key="control.orders.add.client.patronymic.error"/>
<fmt:message var="add_client_first_name_holder" key="control.orders.add.client.first.name.holder"/>
<fmt:message var="add_client_second_name_holder" key="control.orders.add.client.second.name.holder"/>
<fmt:message var="add_client_patronymic_holder" key="control.orders.add.client.patronymic.holder"/>
<fmt:message var="add_client_names_subtitle" key="control.orders.add.client.names.subtitle"/>
<fmt:message var="add_client_phones_title" key="control.orders.add.client.phones.title"/>
<fmt:message var="add_client_phones_subtitle" key="control.orders.add.client.phones.subtitle"/>
<fmt:message var="add_client_phones_match_error" key="control.orders.add.client.phones.match.error"/>
<fmt:message var="add_client_phones_required_error" key="control.orders.add.client.phones.required.error"/>
<fmt:message var="add_client_country_title" key="control.orders.add.client.country.title"/>
<fmt:message var="add_client_country_default" key="control.orders.add.client.country.default"/>
<fmt:message var="add_client_country_error" key="control.orders.add.client.country.error"/>
<fmt:message var="add_client_postcode_title" key="control.orders.add.client.postcode.title"/>
<fmt:message var="add_client_postcode_error" key="control.orders.add.client.postcode.error"/>
<fmt:message var="add_client_state_title" key="control.orders.add.client.state.title"/>
<fmt:message var="add_client_state_error" key="control.orders.add.client.state.error"/>
<fmt:message var="add_client_region_title" key="control.orders.add.client.region.title"/>
<fmt:message var="add_client_region_error" key="control.orders.add.client.region.error"/>
<fmt:message var="add_client_city_title" key="control.orders.add.client.city.title"/>
<fmt:message var="add_client_city_error" key="control.orders.add.client.city.error"/>
<fmt:message var="add_client_street_title" key="control.orders.add.client.street.title"/>
<fmt:message var="add_client_street_error" key="control.orders.add.client.street.error"/>
<fmt:message var="add_client_house_title" key="control.orders.add.client.house.title"/>
<fmt:message var="add_client_house_error" key="control.orders.add.client.house.error"/>
<fmt:message var="add_client_apartment_title" key="control.orders.add.client.apartment.title"/>
<fmt:message var="add_client_apartment_error" key="control.orders.add.client.apartment.error"/>
<fmt:message var="add_client_note_title" key="control.orders.add.client.note.title"/>
<fmt:message var="add_client_note_error" key="control.orders.add.client.note.error"/>
<fmt:message var="close_button_title" key="control.orders.add.close.button.title"/>
<fmt:message var="save_button_title" key="control.orders.add.save.button.title"/>

<div class="modal fade" id="addOrderModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-primary fw-bold" id="addOrderModalLabel">${add_header}</h5>
                <button type="button" class="btn-close close-save-order" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row mb-3">
                        <div class="col-4">
                            <div>
                                <label for="order-type" class="form-label">${add_order_type}:<span
                                        class="required-star">&#8432;</span></label>
                                <select class="form-select required add-order" id="order-type" name="orderNumber">
                                    <option value="NO" selected></option>
                                    <option value="P">${payed_type}</option>
                                    <option value="S">${warranty_type}</option>
                                </select>
                            </div>
                            <div id="order-type-error" class="form-text text-danger"
                                 data-error-match="${add_order_type_error}">
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <label for="order-device" class="form-label">${add_device_type}:<span class="required-star">&#8432;</span></label>
                            <div class="input-group">
                                <input type="text" class="form-control dropdown-input required add-order"
                                       id="order-device"
                                       data-id="0"
                                       name="device">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown">${choose_button_title}...
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end devices"></ul>
                            </div>
                            <div id="order-device-error" class="form-text text-danger"
                                 data-error-match="${add_device_type_error}">
                            </div>
                        </div>
                        <div class="col-6">
                            <label for="order-company" class="form-label">${add_company_type}:</label>
                            <div class="input-group">
                                <input type="text" class="form-control dropdown-input add-order" id="order-company"
                                       data-id="0"
                                       name="company">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown">${choose_button_title}...
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end companies">
                                </ul>
                            </div>
                            <div id="order-company-error" class="form-text text-danger"
                                 data-error-match="${add_company_type_error}">
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <div>
                                <label for="order-model" class="form-label">${add_model}:</label>
                                <input type="text" class="form-control add-order" id="order-model" name="model"
                                       value=""/>
                            </div>
                            <div id="order-model-error" class="form-text text-danger"
                                 data-error-match="${add_model_error}">
                            </div>
                        </div>
                        <div class="col-6">
                            <div>
                                <label for="order-serial" class="form-label">${add_serial}:</label>
                                <input type="text" class="form-control add-order" id="order-serial" name="serialNumber"
                                       value=""/>
                            </div>
                            <div id="order-serial-error" class="form-text text-danger"
                                 data-error-match="${add_serial_error}">
                            </div>
                        </div>
                    </div>
                    <hr class="mt-2">
                    <div class="row mb-3">
                        <div class="col-12 my-2">
                            <label class="form-label">${add_client_title}:</label>
                            <input id="client-id" class="d-none" value="">
                        </div>
                        <div class="col-8">
                            <label for="find-phone" class="form-label">${add_client_find_title}:</label>
                            <div class="input-group" id="client-find">
                                <input type="text" class="form-control add-order" id="find-phone" name="findPhone">
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown" id="find-phone-button" disabled>${add_client_find_button_title}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end clients"></ul>
                                <button class="btn btn-outline-secondary" type="button" id="reset-client-button">
                                    ${add_client_reset_button_title}
                                </button>
                            </div>
                            <div id="find-phone-error" class="form-text text-danger"
                                 data-error-match="${add_client_find_error} +____________">
                            </div>
                        </div>
                        <div class="col-4">
                            <div>
                                <label for="order-email" class="form-label">${add_client_email_title}:</label>
                                <input type="text" class="form-control client-input add-order" id="order-email"
                                       name="email"
                                       value=""/>
                            </div>
                            <div id="order-email-error" class="form-text text-danger"
                                 data-error-match="${add_client_email_error}">
                            </div>
                        </div>
                        <div class="col-12 mt-3">
                            <label for="client-names" class="form-label">${add_client_names_title}:<span
                                    class="required-star">&#8432;</span></label>
                            <div class="input-group" id="client-names">
                                <span class="input-group-text">${add_client_names_subtitle}</span>
                                <input type="text" class="form-control required client-input add-order"
                                       id="order-first-name" placeholder="${add_client_second_name_holder}"
                                       name="firstName">
                                <input type="text" class="form-control required client-input add-order"
                                       id="order-second-name" placeholder="${add_client_first_name_holder}"
                                       name="secondName">
                                <input type="text" class="form-control client-input add-order" id="order-patronymic"
                                       name="patronymic" placeholder="${add_client_patronymic_holder}">
                            </div>
                            <div id=" client-names-error" class="form-text text-danger"
                                data-error-first="${add_client_first_name_error}"
                                data-error-second="${add_client_second_name_error}"
                                data-error-patronymic="${add_client_patronymic_error}">
                            </div>
                        </div>
                        <div class="col-12 my-3">
                            <label for="client-phones" class="form-label">${add_client_phones_title}:<span
                                    class="required-star">&#8432;</span></label>
                            <div class="input-group" id="client-phones">
                                <span class="input-group-text">${add_client_phones_subtitle}</span>
                                <input type="text" class="form-control required client-input add-order"
                                       id="order-phone-1"
                                       name="phoneNumber1">
                                <input type="text" class="form-control client-input add-order" id="order-phone-2"
                                       name="phoneNumber2">
                                <input type="text" class="form-control client-input add-order" id="order-phone-3"
                                       name="phoneNumber3">
                            </div>
                            <div id="client-phones-error" class="form-text text-danger"
                                 data-error-match="${add_client_phones_match_error}  +____________"
                                 data-error-required="${add_client_phones_required_error} +___-__-_______">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="order-country" class="form-label">${add_client_country_title}:</label>
                                <input type="text" class="form-control client-input add-order" id="order-country"
                                       name="country"
                                       value="${add_client_country_default}"/>
                            </div>
                            <div id="client-country-error" class="form-text text-danger"
                                 data-error-match="${add_client_country_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="order-postcode" class="form-label">${add_client_postcode_title}:</label>
                                <input type="text" class="form-control client-input add-order" id="order-postcode"
                                       name="postcode"
                                       value=""/>
                            </div>
                            <div id="client-postcode-error" class="form-text text-danger"
                                 data-error-match="${add_client_postcode_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="order-state" class="form-label">${add_client_state_title}:</label>
                                <input type="text" class="form-control client-input add-order" id="order-state"
                                       name="state"
                                       value=""/>
                            </div>
                            <div id="client-state-error" class="form-text text-danger"
                                 data-error-match="${add_client_state_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="order-region" class="form-label">${add_client_region_title}:</label>
                                <input type="text" class="form-control client-input add-order" id="order-region"
                                       name="region"
                                       value=""/>
                            </div>
                            <div id="client-region-error" class="form-text text-danger"
                                 data-error-match="${add_client_region_error}">
                            </div>
                        </div>
                        <div class="col-4">
                            <div>
                                <label for="order-city" class="form-label">${add_client_city_title}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="text" class="form-control required client-input add-order" id="order-city"
                                       name="city"
                                       value=""/>
                            </div>
                            <div id="client-city-error" class="form-text text-danger"
                                 data-error-match="${add_client_city_error}">
                            </div>
                        </div>
                        <div class="col-4">
                            <div>
                                <label for="order-street" class="form-label">${add_client_street_title}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="text" class="form-control required client-input add-order"
                                       id="order-street"
                                       name="street"
                                       value=""/>
                            </div>
                            <div id="client-street-error" class="form-text text-danger"
                                 data-error-match="${add_client_street_error}">
                            </div>
                        </div>
                        <div class="col-2">
                            <div>
                                <label for="order-house" class="form-label">${add_client_house_title}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="text" class="form-control required client-input add-order" id="order-house"
                                       name="houseNumber"
                                       value=""/>
                            </div>
                            <div id="client-house-error" class="form-text text-danger"
                                 data-error-match="${add_client_house_error}">
                            </div>
                        </div>
                        <div class="col-2">
                            <div>
                                <label for="order-apartment" class="form-label">${add_client_apartment_title}:</label>
                                <input type="text" class="form-control client-input add-order" id="order-apartment"
                                       name="apartmentNumber"
                                       value=""/>
                            </div>
                            <div id="client-apartment-error" class="form-text text-danger"
                                 data-error-match="${add_client_apartment_error}">
                            </div>
                        </div>
                        <div class="col-12">
                            <div>
                                <label for="order-note" class="form-label">${add_client_note_title}:</label>
                                <textarea type="text" class="form-control add-order" id="order-note" name="note"
                                          value="" rows="3"></textarea>
                            </div>
                            <div id="order-note-error" class="form-text text-danger"
                                 data-error-match="${add_client_note_error}">
                            </div>
                        </div>
                        <div class="alert alert-warning d-none align-items-center mt-2 p-1 col-12 save-order-result"
                             id="alert-block">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                                <use href="#exclamation-triangle-fill"/>
                            </svg>
                            <div id="error-send-code" class="alert-block-message save-order-message"></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary close-save-order"
                            data-bs-dismiss="modal">${close_button_title}
                    </button>
                    <button type="button" class="btn btn-primary" id="save-button"
                            disabled>${save_button_title}</button>
                </div>
            </div>
        </div>
    </div>
</div>