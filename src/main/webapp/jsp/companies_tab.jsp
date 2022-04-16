<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:set var="role" value="${not empty sessionScope.userRole?sessionScope.userRole:'GUEST'}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="add_company_name" key="control.companies.add.new.company.name.label"/>
<fmt:message var="add_company_button" key="control.companies.add.new.button.label"/>
<fmt:message var="add_company_name_error" key="control.companies.add.new.company.name.error"/>
<fmt:message var="add_company_contract" key="control.companies.add.new.company.is.contract.label"/>
<fmt:message var="company_name" key="control.companies.column.name"/>
<fmt:message var="company_contract" key="control.companies.column.is.contract"/>
<fmt:message var="companies_reset_filter" key="control.companies.reset.filter.button.label"/>

<div class="container">
    <div class="row mb-3 p-3">
        <div class="col-5">
            <div class="row">
                <div class="col-12">
                    <label for="company-name-input" class="form-label">${add_company_name}:<span
                            class="required-star">&#8432;</span></label>
                    <div class="input-group">
                        <input type="text" class="form-control add-company"
                               id="company-name-input" name="companyName"/>
                    </div>
                    <div id="company-name-error" class="form-text text-danger"
                         data-error-match="${add_company_name_error}">
                    </div>
                </div>
                <div class="col-12 mt-3">
                    <label for="company-contract-input" class="form-label">${add_company_contract}:<span
                            class="required-star">&#8432;</span></label>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="company-contract-input" value="0">
                    </div>
                    <div id="company-contract-error" class="form-text text-danger"
                         data-error-match="">
                    </div>
                </div>
                <div class="col-12 mt-3">
                    <button class="btn btn-outline-primary" type="button" id="add-company-button"
                            disabled>${add_company_button}
                    </button>
                </div>
            </div>
            <div class="alert alert-warning d-none align-items-center mt-2 p-1"
                 id="alert-new-company-result">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                    <use href="#exclamation-triangle-fill"/>
                </svg>
                <div id="new-company-result-message" class=""></div>
            </div>
        </div>
        <div class="col-7">
            <div class="table-container mb-5">
                <table class="table table-hover table-sm">
                    <thead>
                        <tr class="table-info text-center">
                            <th scope="col">
                                <span>ID</span>
                                <a class="company-sort-link">
                                    <i class="fa fa-sort" data-sort="" data-column="co.company_id"></i>
                                </a>
                            </th>
                            <th scope="col">
                                <span>${company_name}</span>
                                <a class="company-sort-link">
                                    <i class="fa fa-sort" data-sort="" data-column="co.name"></i>
                                </a>
                            </th>
                            <th scope="col">
                                <span>${company_contract}</span>
                                <a class="company-sort-link">
                                    <i class="fa fa-sort" data-sort="" data-column="co.is_service_contract"></i>
                                </a>
                            </th>
                            <th scope="col">
                            </th>
                        </tr>
                        <tr class="company-filter table-secondary">
                            <th class="ps-2">
                                <div class="input-group input-group-sm">
                                    <input class="form-control company-filter-input" type="text"/>
                                </div>
                            </th>
                            <th>
                                <div class="input-group input-group-sm">
                                    <input class="form-control company-filter-input" type="text"/>
                                </div>
                            </th>
                            <th class="align-middle">
                                <div class="input-group input-group-sm">
                                    <input class="form-check-input company-filter-input mx-auto"
                                           id="is-contract-filter-input" type="checkbox" value=""/>
                                </div>
                            </th>
                            <th class="pe-2">
                                <div class="btn-group">
                                    <button type="button"
                                            class="btn btn-outline-info clear-companies-filter">${companies_reset_filter}
                                    </button>
                                </div>
                            </th>
                        </tr>
                    </thead>
                    <tbody class="companies-body">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
