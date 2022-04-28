<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:set var="role" value="${not empty sessionScope.userRole?sessionScope.userRole:'GUEST'}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="select_device_label" key="control.prices.select.device.label"/>
<fmt:message var="select_device_default_title" key="control.prices.select.device.default.title"/>
<fmt:message var="save_prices_title" key="control.prices.save.title"/>
<fmt:message var="save_prices_diagnostic_label" key="control.prices.save.diagnostic.label"/>
<fmt:message var="save_prices_error" key="control.prices.save.cost.error"/>
<fmt:message var="save_prices_maintenance_label" key="control.prices.save.maintenance.label"/>
<fmt:message var="save_prices_repair_level1_label" key="control.prices.save.repair.level1.label"/>
<fmt:message var="save_prices_repair_level2_label" key="control.prices.save.repair.level2.label"/>
<fmt:message var="save_prices_repair_level3_label" key="control.prices.save.repair.level3.label"/>
<fmt:message var="save_prices_technical_conclusion_label" key="control.prices.save.technical.conclusion.label"/>
<fmt:message var="save_prices_button_title" key="control.prices.save.button.title"/>

<div class="container">
    <div class="row mb-3 p-3">
        <div class="col-4">
            <div class="row">
                <div class="col-12">
                    <label for="device-name-select" class="form-label">${select_device_label}:</label>
                    <select class="form-select" id="device-name-select" name="deviceName">
                        <option value="0">- ${select_device_default_title} -</option>
                    </select>
                </div>
                <div class="alert alert-warning d-none align-items-center mt-2 p-1 col-12"
                     id="alert-find-prices-result">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                        <use href="#exclamation-triangle-fill"/>
                    </svg>
                    <div id="find-prices-result-message" class=""></div>
                </div>
            </div>
        </div>
        <div class="col-6 mx-auto">
            <label for="current-prices" class="form-label">${save_prices_title}:<span
                    class="required-star">&#8432;</span></label>
            <div class="row" id="current-prices">
                <div class="col-12 mb-3">
                    <div class="input-group d-flex">
                        <span class="input-group-text flex-grow-1">${save_prices_diagnostic_label}</span>
                        <input type="text" class="form-control edit-price w-25"
                               id="edit-diagnostic-input" name="diagnostic" data-id="0" disabled>
                    </div>
                    <div id="edit-diagnostic-error" class="form-text text-danger"
                         data-error-match="${save_prices_error}">
                    </div>
                </div>
                <div class="col-12 mb-3">
                    <div class="input-group d-flex">
                        <span class="input-group-text flex-grow-1">${save_prices_maintenance_label}</span>
                        <input type="text" class="form-control edit-price w-25"
                               id="edit-maintenance-input" name="maintenance" data-id="0" disabled>
                    </div>
                    <div id="edit-maintenance-error" class="form-text text-danger"
                         data-error-match="${save_prices_error}">
                    </div>
                </div>
                <div class="col-12 mb-3">
                    <div class="input-group d-flex">
                        <span class="input-group-text flex-grow-1">${save_prices_repair_level1_label}</span>
                        <input type="text" class="form-control edit-price w-25"
                               id="edit-repair-level1-input" name="repairLevel1" data-id="0" disabled>
                    </div>
                    <div id="edit-repair-level1-error" class="form-text text-danger"
                         data-error-match="${save_prices_error}">
                    </div>
                </div>
                <div class="col-12 mb-3">
                    <div class="input-group d-flex">
                        <span class="input-group-text flex-grow-1">${save_prices_repair_level2_label}</span>
                        <input type="text" class="form-control edit-price w-25"
                               id="edit-repair-level2-input" name="repairLevel2" data-id="0" disabled>
                    </div>
                    <div id="edit-repair-level2-error" class="form-text text-danger"
                         data-error-match="${save_prices_error}">
                    </div>
                </div>
                <div class="col-12 mb-3">
                    <div class="input-group d-flex">
                        <span class="input-group-text flex-grow-1">${save_prices_repair_level3_label}</span>
                        <input type="text" class="form-control edit-price w-25"
                               id="edit-repair-level3-input" name="repairLevel3" data-id="0" disabled>
                    </div>
                    <div id="edit-repair-level3-error" class="form-text text-danger"
                         data-error-match="${save_prices_error}">
                    </div>
                </div>
                <div class="col-12 mb-3">
                    <div class="input-group d-flex">
                        <span class="input-group-text flex-grow-1">${save_prices_technical_conclusion_label}</span>
                        <input type="text" class="form-control edit-price w-25"
                               id="edit-technical-conclusion-input" name="technicalConclusion" data-id="0" disabled>
                    </div>
                    <div id="edit-technical-conclusion-error" class="form-text text-danger"
                         data-error-match="${save_prices_error}">
                    </div>
                </div>
                <div class="alert alert-warning d-none align-items-center mt-2 p-1 col-12"
                     id="alert-save-prices-result">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                        <use href="#exclamation-triangle-fill"/>
                    </svg>
                    <div id="save-prices-result-message" class=""></div>
                </div>
                <div class="col-auto ms-auto">
                    <button class="btn btn-outline-primary" type="button" id="save-price-button"
                            disabled>${save_prices_button_title}
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
