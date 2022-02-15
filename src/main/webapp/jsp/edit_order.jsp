<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:set var="role" value="${sessionScope.userRole}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="edit_header" key="control.orders.edit.header"/>
<fmt:message var="edit_order_number" key="control.orders.edit.order.number.title"/>
<fmt:message var="edit_order_status" key="control.orders.edit.order.status.title"/>
<fmt:message var="edit_creation_date" key="control.orders.edit.creation.date.title"/>
<fmt:message var="edit_creation_date_error" key="control.orders.edit.creation.date.error.match"/>
<fmt:message var="edit_accepted_employee" key="control.orders.edit.accepted.employee.title"/>
<fmt:message var="edit_accepted_employee_error" key="control.orders.edit.creation.date.error.match"/>
<fmt:message var="edit_repair_title" key="control.orders.edit.repair.title"/>
<fmt:message var="edit_completed_employee" key="control.orders.edit.completed.employee.title"/>
<fmt:message var="edit_completed_employee_error" key="control.orders.edit.completed.employee.error.match"/>
<fmt:message var="edit_completion_date" key="control.orders.edit.completion.date.title"/>
<fmt:message var="edit_completion_date_error" key="control.orders.edit.completion.date.error.match"/>
<fmt:message var="edit_issue_date" key="control.orders.edit.issue.date.title"/>
<fmt:message var="edit_issue_date_error" key="control.orders.edit.completion.date.error.match"/>
<fmt:message var="edit_repair_type" key="control.orders.edit.repair.type.title"/>
<fmt:message var="edit_repair_type_error" key="control.orders.edit.repair.type.error.match"/>
<fmt:message var="edit_repair_cost" key="control.orders.edit.repair.cost.title"/>
<fmt:message var="edit_parts_cost" key="control.orders.edit.parts.cost.title"/>
<fmt:message var="edit_total_cost" key="control.orders.edit.total.cost.title"/>
<fmt:message var="edit_work_description" key="control.orders.edit.work.description.title"/>
<fmt:message var="edit_work_description_error" key="control.orders.edit.work.description.error.match"/>
<fmt:message var="edit_parts_title" key="control.orders.edit.parts.title"/>
<fmt:message var="edit_parts_spare_parts" key="control.orders.edit.parts.spare.parts.title"/>
<fmt:message var="edit_parts_add_part" key="control.orders.edit.parts.add.part.title"/>
<fmt:message var="edit_parts_remove_part" key="control.orders.edit.parts.remove.part.title"/>
<fmt:message var="remove_button_title" key="control.orders.edit.parts.remove.button.title"/>
<fmt:message var="update_button_title" key="control.orders.edit.update.button.title"/>
<fmt:message var="edit_device_type" key="control.orders.add.device.type.title"/>
<fmt:message var="choose_button_title" key="control.orders.add.choose.button.title"/>
<fmt:message var="edit_device_type_error" key="control.orders.add.device.type.error.match"/>
<fmt:message var="edit_company_type" key="control.orders.add.company.type.title"/>
<fmt:message var="edit_company_type_error" key="control.orders.add.company.type.error.match"/>
<fmt:message var="edit_model" key="control.orders.add.model.title"/>
<fmt:message var="edit_model_error" key="control.orders.add.model.error.match"/>
<fmt:message var="edit_serial" key="control.orders.add.serial.title"/>
<fmt:message var="edit_serial_error" key="control.orders.add.serial.error.match"/>
<fmt:message var="edit_client_title" key="control.orders.add.client.title"/>
<fmt:message var="edit_client_find_title" key="control.orders.add.client.find.title"/>
<fmt:message var="edit_client_find_button_title" key="control.orders.add.client.find.button.title"/>
<fmt:message var="edit_client_reset_button_title" key="control.orders.add.client.reset.button.title"/>
<fmt:message var="edit_client_find_error" key="control.orders.add.client.find.error"/>
<fmt:message var="edit_client_email_title" key="control.orders.add.client.email.title"/>
<fmt:message var="edit_client_email_error" key="control.orders.add.client.email.error"/>
<fmt:message var="edit_client_names_title" key="control.orders.add.client.names.title"/>
<fmt:message var="edit_client_first_name_error" key="control.orders.add.client.first.name.error"/>-
<fmt:message var="edit_client_second_name_error" key="control.orders.add.client.second.name.error"/>
<fmt:message var="edit_client_patronymic_error" key="control.orders.add.client.patronymic.error"/>
<fmt:message var="edit_client_first_name_holder" key="control.orders.add.client.first.name.holder"/>
<fmt:message var="edit_client_second_name_holder" key="control.orders.add.client.second.name.holder"/>
<fmt:message var="edit_client_patronymic_holder" key="control.orders.add.client.patronymic.holder"/>
<fmt:message var="edit_client_names_subtitle" key="control.orders.add.client.names.subtitle"/>
<fmt:message var="edit_client_phones_title" key="control.orders.add.client.phones.title"/>
<fmt:message var="edit_client_phones_subtitle" key="control.orders.add.client.phones.subtitle"/>
<fmt:message var="edit_client_phones_match_error" key="control.orders.add.client.phones.match.error"/>
<fmt:message var="edit_client_phones_required_error" key="control.orders.add.client.phones.required.error"/>
<fmt:message var="edit_client_country_title" key="control.orders.add.client.country.title"/>
<fmt:message var="edit_client_country_default" key="control.orders.add.client.country.default"/>
<fmt:message var="edit_client_country_error" key="control.orders.add.client.country.error"/>
<fmt:message var="edit_client_postcode_title" key="control.orders.add.client.postcode.title"/>
<fmt:message var="edit_client_postcode_error" key="control.orders.add.client.postcode.error"/>
<fmt:message var="edit_client_state_title" key="control.orders.add.client.state.title"/>
<fmt:message var="edit_client_state_error" key="control.orders.add.client.state.error"/>
<fmt:message var="edit_client_region_title" key="control.orders.add.client.region.title"/>
<fmt:message var="edit_client_region_error" key="control.orders.add.client.region.error"/>
<fmt:message var="edit_client_city_title" key="control.orders.add.client.city.title"/>
<fmt:message var="edit_client_city_error" key="control.orders.add.client.city.error"/>
<fmt:message var="edit_client_street_title" key="control.orders.add.client.street.title"/>
<fmt:message var="edit_client_street_error" key="control.orders.add.client.street.error"/>
<fmt:message var="edit_client_house_title" key="control.orders.add.client.house.title"/>
<fmt:message var="edit_client_house_error" key="control.orders.add.client.house.error"/>
<fmt:message var="edit_client_apartment_title" key="control.orders.add.client.apartment.title"/>
<fmt:message var="edit_client_apartment_error" key="control.orders.add.client.apartment.error"/>
<fmt:message var="edit_client_note_title" key="control.orders.add.client.note.title"/>
<fmt:message var="edit_client_note_error" key="control.orders.add.client.note.error"/>
<fmt:message var="close_button_title" key="control.orders.add.close.button.title"/>


<div class="modal fade" id="editOrderModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-xl modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-primary fw-bold" id="editOrderModalLabel">${edit_header}</h5>
                <button type="button" class="btn-close close-edit-order" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row mb-3">
                        <input type="text" class="form-control edit-order d-none" id="edit-id" name="orderId"
                               value=""/>
                        <div class="col-2">
                            <div>
                                <label for="edit-number" class="form-label">${edit_order_number}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="text" class="form-control edit-order" id="edit-number" name="orderNumber"
                                       value="" disabled/>
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="edit-status" class="form-label">${edit_order_status}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="text" class="form-control edit-order" id="edit-status" name="orderStatus"
                                       value="" disabled/>
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="edit-creation-date" class="form-label">${edit_creation_date}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="datetime-local" class="form-control edit-order" id="edit-creation-date"
                                       name="creationDate" data-sec="" ${role ne 'ADMIN'?'disabled':''}
                                       value=""/>
                            </div>
                            <div id="edit-creation-date-error" class="form-text text-danger"
                                 data-error-match="${edit_creation_date_error}">
                            </div>
                        </div>
                        <div class="col-4">
                            <div>
                                <label for="edit-accepted-employee" class="form-label">${edit_accepted_employee}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="text" class="d-none" id="accepted-employee-id"/>
                                <select class="form-select edit-order" id="edit-accepted-employee"
                                        name="acceptedEmployee" ${role ne 'ADMIN'?'disabled':''}>
                                    <option value=""></option>
                                </select>
                            </div>
                            <div id="edit-accepted-employee-error" class="form-text text-danger"
                                 data-error-match="${edit_accepted_employee_error}">
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-4">
                            <label for="edit-device" class="form-label">${edit_device_type}:<span
                                    class="required-star">&#8432;</span></label>
                            <div class="input-group">
                                <input type="text" class="form-control dropdown-input required edit-order"
                                       id="edit-device" data-id="0" name="device" disabled>
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}>${choose_button_title}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end edit-devices"></ul>
                            </div>
                            <div id="edit-device-error" class="form-text text-danger"
                                 data-error-match="${edit_device_type_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <label for="edit-company" class="form-label">${edit_company_type}:</label>
                            <div class="input-group">
                                <input type="text" class="form-control dropdown-input edit-order" id="edit-company"
                                       data-id="0" name="company" disabled>
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}>${choose_button_title}...
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end edit-companies">
                                </ul>
                            </div>
                            <div id="edit-company-error" class="form-text text-danger"
                                 data-error-match="${edit_company_type_error}">
                            </div>
                        </div>
                        <div class="col-2">
                            <div>
                                <label for="edit-model" class="form-label">${edit_model}:</label>
                                <input type="text" class="form-control edit-order" id="edit-model" name="model"
                                       value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}/>
                            </div>
                            <div id="edit-model-error" class="form-text text-danger"
                                 data-error-match="${edit_model_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="edit-serial" class="form-label">${edit_serial}:</label>
                                <input type="text" class="form-control edit-order" id="edit-serial" name="serialNumber"
                                       value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}/>
                            </div>
                            <div id="edit-serial-error" class="form-text text-danger"
                                 data-error-match="${edit_serial_error}">
                            </div>
                        </div>
                    </div>
                    <hr class="mt-2">
                    <div class="row mb-3">
                        <input id="edit-client-id" class="d-none edit-client no-validate" value="" data-old-value="">
                        <input id="edit-address-id" class="d-none  edit-client no-validate" value="" data-old-value="">
                        <div class="col-12 my-2">
                            <label class="form-label text-success fw-bold">${edit_client_title}:</label>
                        </div>
                        <div class="col-4">
                            <label for="edit-find-phone" class="form-label">${edit_client_find_title}:</label>
                            <div class="input-group" id="edit-client-find">
                                <input type="text" class="form-control edit-order" id="edit-find-phone"
                                       name="findPhone"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}>
                                <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                        data-bs-toggle="dropdown" id="edit-find-phone-button"
                                        disabled>${edit_client_find_button_title}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end edit-clients"></ul>
                                <button class="btn btn-outline-secondary" type="button"
                                        id="edit-reset-client-button" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}>
                                    ${edit_client_reset_button_title}
                                </button>
                            </div>
                            <div id="edit-phone-error" class="form-text text-danger"
                                 data-error-match="${edit_client_find_error} +____________">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="edit-email" class="form-label">${edit_client_email_title}:</label>
                                <input type="text" class="form-control edit-client edit-order" id="edit-email"
                                       name="email"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}
                                       value=""/>
                            </div>
                            <div id="edit-email-error" class="form-text text-danger"
                                 data-error-match="${edit_client_email_error}">
                            </div>
                        </div>
                        <div class="col-2">
                            <div>
                                <label for="edit-country" class="form-label">${edit_client_country_title}:</label>
                                <input type="text" class="form-control edit-client edit-order" id="edit-country"
                                       name="country"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}
                                       value="${edit_client_country_default}"/>
                            </div>
                            <div id="edit-country-error" class="form-text text-danger"
                                 data-error-match="${edit_client_country_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="edit-state" class="form-label">${edit_client_state_title}:</label>
                                <input type="text" class="form-control edit-client edit-order" id="edit-state"
                                       name="state"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}
                                       value=""/>
                            </div>
                            <div id="edit-state-error" class="form-text text-danger"
                                 data-error-match="${edit_client_state_error}">
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6 mt-3">
                            <label for="edit-names" class="form-label">${edit_client_names_title}:<span
                                    class="required-star">&#8432;</span></label>
                            <div class="input-group" id="edit-names">
                                <span class="input-group-text">${edit_client_names_subtitle}</span>
                                <input type="text" class="form-control required edit-client edit-order"
                                       id="edit-first-name" placeholder="${edit_client_second_name_holder}"
                                       name="firstName"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}>
                                <input type="text" class="form-control required edit-client edit-order"
                                       id="edit-second-name" placeholder="${edit_client_first_name_holder}"
                                       name="secondName"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}>
                                <input type="text" class="form-control edit-client edit-order" id="edit-patronymic"
                                       name="patronymic" placeholder="${edit_client_patronymic_holder}"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}>
                            </div>
                            <div id="edit-names-error" class="form-text text-danger"
                                 data-error-first="${edit_client_first_name_error}"
                                 data-error-second="${edit_client_second_name_error}"
                                 data-error-patronymic="${edit_client_patronymic_error}">
                            </div>
                        </div>
                        <div class="col-6 my-3">
                            <label for="edit-client-phones" class="form-label">${edit_client_phones_title}:<span
                                    class="required-star">&#8432;</span></label>
                            <div class="input-group" id="edit-client-phones">
                                <span class="input-group-text">${edit_client_phones_subtitle}</span>
                                <input type="text" class="form-control required edit-client edit-order"
                                       id="edit-phone-1"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}
                                       name="phoneNumber1">
                                <input type="text" class="form-control edit-client edit-order" id="edit-phone-2"
                                       name="phoneNumber2"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}>
                                <input type="text" class="form-control edit-client edit-order" id="edit-phone-3"
                                       name="phoneNumber3"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}>
                            </div>
                            <div id="edit-phones-error" class="form-text text-danger"
                                 data-error-match="${edit_client_phones_match_error}  +____________"
                                 data-error-required="${edit_client_phones_required_error} +___-__-_______">
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-2">
                            <div>
                                <label for="edit-postcode" class="form-label">${edit_client_postcode_title}:</label>
                                <input type="text" class="form-control edit-client edit-order" id="edit-postcode"
                                       name="postcode"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}
                                       value=""/>
                            </div>
                            <div id="edit-postcode-error" class="form-text text-danger"
                                 data-error-match="${edit_client_postcode_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="edit-region" class="form-label">${edit_client_region_title}:</label>
                                <input type="text" class="form-control edit-client edit-order" id="edit-region"
                                       name="region"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}
                                       value=""/>
                            </div>
                            <div id="edit-region-error" class="form-text text-danger"
                                 data-error-match="${edit_client_region_error}">
                            </div>
                        </div>
                        <div class="col-2">
                            <div>
                                <label for="edit-city" class="form-label">${edit_client_city_title}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="text" class="form-control required edit-client edit-order" id="edit-city"
                                       name="city"
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}
                                       value=""/>
                            </div>
                            <div id="edit-city-error" class="form-text text-danger"
                                 data-error-match="${edit_client_city_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="edit-street" class="form-label">${edit_client_street_title}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="text" class="form-control required edit-client edit-order"
                                       id="edit-street" data-old-value="" name="street"
                                       value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}/>
                            </div>
                            <div id="edit-street-error" class="form-text text-danger"
                                 data-error-match="${edit_client_street_error}">
                            </div>
                        </div>
                        <div class="col-1">
                            <div>
                                <label for="edit-house" class="form-label">${edit_client_house_title}:<span
                                        class="required-star">&#8432;</span></label>
                                <input type="text" class="form-control required edit-client edit-order"
                                       id="edit-house" data-old-value="" name="houseNumber"
                                       value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}/>
                            </div>
                            <div id="edit-house-error" class="form-text text-danger"
                                 data-error-match="${edit_client_house_error}">
                            </div>
                        </div>
                        <div class="col-1">
                            <div>
                                <label for="edit-apartment" class="form-label">${edit_client_apartment_title}:</label>
                                <input type="text" class="form-control edit-client edit-order" id="edit-apartment"
                                       name="apartmentNumber" value=""
                                       data-old-value="" ${role ne 'ADMIN' and role ne 'MANAGER'?'disabled':''}/>
                            </div>
                            <div id="edit-apartment-error" class="form-text text-danger"
                                 data-error-match="${edit_client_apartment_error}">
                            </div>
                        </div>
                    </div>
                    <hr class="mt-2">
                    <div class="row mb-3">
                        <div class="col-12 my-2">
                            <label class="form-label text-success fw-bold">${edit_repair_title}:</label>
                        </div>
                        <div class="col-4">
                            <div>
                                <label for="edit-completed-employee"
                                       class="form-label">${edit_completed_employee}:</label>
                                <input type="text" class="d-none" id="completed-employee-id"/>
                                <select class="form-select edit-order" id="edit-completed-employee"
                                        name="completedEmployee" ${role ne 'ADMIN'?'disabled':''}>
                                    <option value=""></option>
                                </select>
                            </div>
                            <div id="edit-completed-employee-error" class="form-text text-danger"
                                 data-error-match="${edit_completed_employee_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="edit-completion-date" class="form-label">${edit_completion_date}:</label>
                                <input type="datetime-local" class="form-control edit-order" id="edit-completion-date"
                                       name="creationDate" data-sec="" ${role ne 'ADMIN'?'disabled':''}
                                       value=""/>
                            </div>
                            <div id="edit-completion-date-error" class="form-text text-danger"
                                 data-error-match="${edit_completion_date_error}">
                            </div>
                        </div>
                        <div class="col-3">
                            <div>
                                <label for="edit-issue-date" class="form-label">${edit_issue_date}:</label>
                                <input type="datetime-local" class="form-control edit-order" id="edit-issue-date"
                                       name="creationDate" data-sec="" ${role ne 'ADMIN'?'disabled':''}
                                       value=""/>
                            </div>
                            <div id="edit-issue-date-error" class="form-text text-danger"
                                 data-error-match="${edit_issue_date_error}">
                            </div>
                        </div>
                    </div>
                    <div class="row mb-4">
                        <input type="text" class="d-none" id="edit-price-info-id" value=""/>
                        <div class="col-4">
                            <div>
                                <label for="edit-repair-level" class="form-label">${edit_repair_type}:</label>
                                <select class="form-select edit-order" id="edit-repair-level"
                                        name="repairLevel" ${role ne 'ADMIN' and role ne 'ENGINEER'?'disabled':''}>
                                    <option value=""></option>
                                </select>
                            </div>
                            <div id="edit-repair-level-error" class="form-text text-danger"
                                 data-error-match="${edit_repair_type_error}">
                            </div>
                        </div>
                        <div class="col-2">
                            <div>
                                <label for="edit-work-price" class="form-label">${edit_repair_cost}:</label>
                                <input type="text" class="form-control edit-order" id="edit-work-price"
                                       name="workPrice" value="" disabled/>
                            </div>
                            <div id="edit-work-price-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-2">
                            <div>
                                <label for="edit-parts-cost" class="form-label">${edit_parts_cost}:</label>
                                <input type="text" class="form-control edit-order" id="edit-parts-cost"
                                       value="" disabled/>
                            </div>
                            <div id="edit-parts-cost-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-2">
                            <div>
                                <label for="edit-total-cost" class="form-label">${edit_total_cost}:</label>
                                <input type="text" class="form-control edit-order" id="edit-total-cost"
                                       value="" disabled/>
                            </div>
                            <div id="edit-total-cost-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <div>
                                <label for="edit-work-description" class="form-label">${edit_work_description}:</label>
                                <textarea type="text" class="form-control edit-order" id="edit-work-description"
                                          name="workDescription" ${role ne 'ADMIN' and role ne 'ENGINEER'?'disabled':''}
                                          value="" rows="3"></textarea>
                            </div>
                            <div id="edit-work-description-error" class="form-text text-danger"
                                 data-error-match="${edit_work_description_error}">
                            </div>
                        </div>
                        <div class="col-6">
                            <div>
                                <label for="edit-note" class="form-label">${edit_client_note_title}:</label>
                                <textarea type="text" class="form-control edit-order" id="edit-note" name="note"
                                          value="" rows="3"></textarea>
                            </div>
                            <div id="edit-note-error" class="form-text text-danger"
                                 data-error-match="${edit_client_note_error}">
                            </div>
                        </div>
                    </div>
                    <hr class="mt-2">
                    <div class="row mb-3">
                        <div class="col-12 my-2">
                            <label class="form-label text-success fw-bold">${edit_parts_title}:</label>
                        </div>
                        <div class="col-6">
                            <div>
                                <label for="edit-spare-parts" class="form-label">${edit_parts_spare_parts}:</label>
                                <select class="form-select edit-order no-validate" size="5" id="edit-spare-parts"
                                        name="spareParts" ${role ne 'ADMIN' and role ne 'ENGINEER'?'disabled':''}>
                                </select>
                            </div>
                            <div id="edit-spare-parts-error" class="form-text text-danger"
                                 data-error-match="">
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="row mb-3">
                                <div class="col-8">
                                    <label for="edit-find-part" class="form-label">${edit_parts_add_part}:</label>
                                    <div class="input-group" id="part-find">
                                        <input type="text" class="form-control edit-order no-validate"
                                               id="edit-find-part" ${role ne 'ADMIN' and role ne 'ENGINEER'?'disabled':''}>
                                        <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                                data-bs-toggle="dropdown" id="edit-find-part-button"
                                        ${role ne 'ADMIN' and role ne 'ENGINEER'?'disabled':''}
                                        >${edit_client_find_button_title}
                                        </button>
                                        <ul class="dropdown-menu dropdown-menu-end edit-parts"></ul>
                                    </div>
                                    <div id="edit-find-part-error" class="form-text text-danger"
                                         data-error-match="">
                                    </div>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col">
                                    <label for="edit-remove-part-button"
                                           class="form-label">${edit_parts_remove_part}:</label>
                                    <div class="input-group">
                                        <button class="btn btn-outline-secondary" type="button"
                                                id="edit-remove-part-button" disabled>${remove_button_title}
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="alert alert-warning d-none align-items-center mt-2 p-1 col-12 update-order-result"
                             id="alert-block">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                                <use href="#exclamation-triangle-fill"/>
                            </svg>
                            <div id="error-edit-order" class="alert-block-message update-order-message"></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary close-save-order"
                            data-bs-dismiss="modal">${close_button_title}
                    </button>
                    <button type="button" class="btn btn-primary" id="edit-save-button"
                    >${update_button_title}</button>
                </div>
            </div>
        </div>
    </div>
</div>