<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:set var="role" value="${not empty sessionScope.userRole?sessionScope.userRole:'GUEST'}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="add_device_name" key="control.devices.add.new.device.name.label"/>
<fmt:message var="add_device_button" key="control.devices.add.new.button.label"/>
<fmt:message var="add_device_name_error" key="control.devices.add.new.device.name.error"/>
<fmt:message var="device_name" key="control.devices.column.name"/>
<fmt:message var="devices_reset_filter" key="control.devices.reset.filter.button.label"/>

<div class="container">
    <div class="row mb-3 p-3">
        <div class="col-6">
            <label for="device-name-input" class="form-label">${add_device_name}:<span
                    class="required-star">&#8432;</span></label>
            <div class="input-group">
                <input type="text" class="form-control add-device"
                       id="device-name-input" name="deviceName"/>
                <button class="btn btn-outline-primary" type="button" id="add-device-button"
                        disabled>${add_device_button}</button>
            </div>
            <div id="device-name-error" class="form-text text-danger"
                 data-error-match="${add_device_name_error}">
            </div>
            <div class="alert alert-warning d-none align-items-center mt-2 p-1"
                 id="alert-new-device-result">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                    <use href="#exclamation-triangle-fill"/>
                </svg>
                <div id="new-device-result-message" class=""></div>
            </div>
        </div>
        <div class="col-6">
            <div class="table-container mb-5">
                <table class="table table-hover table-sm">
                    <thead>
                        <tr class="table-info text-center">
                            <th scope="col">
                                <span>ID</span>
                                <a class="device-sort-link">
                                    <i class="fa fa-sort" data-sort="" data-column="d.device_id"></i>
                                </a>
                            </th>
                            <th scope="col" class="px-4">
                                <span>${device_name}</span>
                                <a class="device-sort-link">
                                    <i class="fa fa-sort" data-sort="" data-column="d.device_name"></i>
                                </a>
                            </th>
                            <th scope="col">
                            </th>
                        </tr>
                        <tr class="device-filter table-secondary">
                            <th>
                                <div class="input-group input-group-sm">
                                    <input class="form-control device-filter-input" type="text"/>
                                </div>
                            </th>
                            <th>
                                <div class="input-group input-group-sm">
                                    <input class="form-control device-filter-input" type="text"/>
                                </div>
                            </th>
                            <th>
                                <div class="btn-group">
                                    <button type="button"
                                            class="btn btn-outline-info clear-devices-filter">${devices_reset_filter}
                                    </button>
                                </div>
                            </th>
                        </tr>
                    </thead>
                    <tbody class="devices-body">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

