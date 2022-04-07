<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:set var="role" value="${not empty sessionScope.userRole?sessionScope.userRole:'GUEST'}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="add_part_header" key="control.parts.add.new.header"/>
<fmt:message var="add_part_number" key="control.parts.add.new.part.number.label"/>
<fmt:message var="add_part_number_error" key="control.parts.add.new.part.number.error"/>
<fmt:message var="add_part_name" key="control.parts.add.new.part.name.label"/>
<fmt:message var="add_part_name_error" key="control.parts.add.new.part.name.error"/>
<fmt:message var="add_part_description" key="control.parts.add.new.part.description.label"/>
<fmt:message var="add_part_description_error" key="control.parts.add.new.part.description.error"/>
<fmt:message var="add_part_cost" key="control.parts.add.new.part.cost.label"/>
<fmt:message var="add_part_cost_error" key="control.parts.add.new.part.cost.error"/>
<fmt:message var="add_part_button" key="control.parts.add.new.button.label"/>
<fmt:message var="parts_header" key="control.parts.table.header"/>
<fmt:message var="parts_reset_filter" key="control.parts.reset.filter.button.label"/>
<fmt:message var="part_number" key="control.parts.column.part.number"/>
<fmt:message var="part_name" key="control.parts.column.name"/>
<fmt:message var="part_description" key="control.parts.column.description"/>
<fmt:message var="part_cost" key="control.parts.column.cost"/>

<div class="container parts-tab-container">
    <div class="row mb-3 border p-3">
        <div class="col-12">
            <h3 class="text-primary">${add_part_header}</h3>
        </div>
        <div class="col-6">
            <label for="part-number-input" class="form-label">${add_part_number}:</label>
            <div>
                <input type="text" class="form-control add-part"
                       id="part-number-input" name="partNumber"/>
            </div>
            <div id="part-number-error" class="form-text text-danger"
                 data-error-match="${add_part_number_error}">
            </div>
        </div>
        <div class="col-6">
            <label for="part-name-input" class="form-label">${add_part_name}:<span
                    class="required-star">&#8432;</span></label>
            <div>
                <input type="text" class="form-control add-part"
                       id="part-name-input" name="name"/>
            </div>
            <div id="part-name-error" class="form-text text-danger"
                 data-error-match="${add_part_name_error}">
            </div>
        </div>
        <div class="col-6">
            <label for="part-description-input" class="form-label">${add_part_description}:</label>
            <div>
                <input type="text" class="form-control add-part"
                       id="part-description-input" name="description"/>
            </div>
            <div id="part-description-error" class="form-text text-danger"
                 data-error-match="${add_part_description_error}">
            </div>
        </div>
        <div class="col-6">
            <label for="part-cost-input" class="form-label">${add_part_cost}:<span
                    class="required-star">&#8432;</span></label>
            <div>
                <input type="text" class="form-control add-part"
                       id="part-cost-input" name="cost"/>
            </div>
            <div id="part-cost-error" class="form-text text-danger"
                 data-error-match="${add_part_cost_error}">
            </div>
        </div>
        <div class="alert alert-warning d-none align-items-center mt-2 p-1"
             id="alert-new-part-result">
            <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                <use href="#exclamation-triangle-fill"/>
            </svg>
            <div id="new-part-result-message" class=""></div>
        </div>
        <div class="col-auto ms-auto mt-2">
            <div class="btn-group">
                <button type="button" disabled
                        class="btn btn-outline-info save-new-part">${add_part_button}
                </button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <h3 class="text-primary">${parts_header}</h3>
        </div>
        <div class="col-auto ms-auto">
            <div class="btn-group">
                <button type="button"
                        class="btn btn-outline-info clear-parts-filter">${parts_reset_filter}
                </button>
            </div>
        </div>
    </div>
    <div class="row mt-3 table-container mb-5">
        <table class="table table-hover table-sm">
            <thead>
                <tr class="table-info text-center">
                    <th scope="col">
                        <span>ID</span>
                        <a class="part-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="s.id"></i>
                        </a>
                    </th>
                    <th scope="col" class="px-4">
                        <span>${part_number}</span>
                        <a class="part-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="s.part_number"></i>
                        </a>
                    </th>
                    <th scope="col" class="px-4">
                        <span>${part_name}</span>
                        <a class="part-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="s.name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${part_description}</span>
                        <a class="part-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="s.description"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${part_cost}</span>
                        <a class="part-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="s.cost"></i>
                        </a>
                    </th>
                    <th scope="col">

                    </th>
                </tr>
                <tr class="part-filter table-secondary">
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control part-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control part-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control part-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control part-filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control part-filter-input" type="text"/>
                        </div>
                    </th>
                    <th></th>
                </tr>
            </thead>
            <tbody class="parts-body">

            </tbody>
        </table>
    </div>
</div>
