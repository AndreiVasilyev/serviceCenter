<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="employees_reset_filter" key="control.employees.reset.filter.button"/>
<fmt:message var="employees_register_employee" key="control.employees.register.employee.button"/>
<fmt:message var="employees_id" key="control.employees.column.user.id"/>
<fmt:message var="employees_role" key="control.employees.column.user.role"/>
<fmt:message var="employees_login" key="control.employees.column.login"/>
<fmt:message var="employees_second_name" key="control.employees.column.second.name"/>
<fmt:message var="employees_first_name" key="control.employees.column.first.name"/>
<fmt:message var="employees_patronymic" key="control.employees.column.patronymic"/>
<fmt:message var="employees_email" key="control.employees.column.email"/>
<fmt:message var="employees_postcode" key="control.employees.column.postcode"/>
<fmt:message var="employees_country" key="control.employees.column.country"/>
<fmt:message var="employees_state" key="control.employees.column.state"/>
<fmt:message var="employees_region" key="control.employees.column.region"/>
<fmt:message var="employees_city" key="control.employees.column.city"/>
<fmt:message var="employees_street" key="control.employees.column.street"/>
<fmt:message var="employees_house_number" key="control.employees.column.house.number"/>
<fmt:message var="employees_apartment_number" key="control.employees.column.apartment.number"/>
<fmt:message var="employees_phone_number" key="control.employees.column.phone.number"/>

<div class="container">
    <div class="row">
        <div class="col-auto ms-auto">
            <div class="btn-group">
                <button type="button"
                        class="btn btn-outline-info clear-employees-filter">${employees_reset_filter}</button>
                <button type="button" class="btn btn-outline-success register-employee" data-bs-toggle="modal"
                        data-bs-target="#registerEmployeeModal">${employees_register_employee}
                </button>
            </div>
        </div>
    </div>
    <div class="row mt-3 table-container">
        <table class="table table-hover table-sm">
            <thead>
                <tr class="table-info text-center">
                    <th scope="col">
                        <span>${employees_id}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.user_id"></i>
                        </a>
                    </th>
                    <th scope="col" class="px-4">
                        <span>${employees_role}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.user_role"></i>
                        </a>
                    </th>
                    <th scope="col" class="px-4">
                        <span>${employees_login}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="e.login"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_second_name}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.second_name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_first_name}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.first_name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_patronymic}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.patronymic"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_email}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.email"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_postcode}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.postcode"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_country}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.country"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_state}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.state"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_region}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.region"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_city}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.city"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_street}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.street"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_house_number}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.house_number"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_apartment_number}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.apartment_number"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${employees_phone_number}</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="phone_number"></i>
                        </a>
                    </th>
                </tr>
                <tr class="employees-filter table-secondary">
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <select class="form-select employees-filter-input role-select">
                                <option value="" selected></option>
                                <option value="ADMIN">ADMIN</option>
                                <option value="MANAGER">MANAGER</option>
                                <option value="ENGINEER">ENGINEER</option>
                            </select>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control employees-filter-input" type="text"/>
                        </div>
                    </th>
                </tr>
            </thead>
            <tbody class="employees-body">

            </tbody>
        </table>
    </div>
</div>
