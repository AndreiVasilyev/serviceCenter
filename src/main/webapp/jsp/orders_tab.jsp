<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="order_status" value="${sessionScope.orderStatus}"/>
<c:set var="repair_level" value="${sessionScope.repairLevel}"/>

<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="orders_reset_filter" key="control.orders.reset.filter.button"/>
<fmt:message var="orders_new" key="control.orders.new.order.button"/>
<fmt:message var="orders_number" key="control.orders.column.number"/>
<fmt:message var="orders_status" key="control.orders.column.status"/>
<fmt:message var="orders_created" key="control.orders.column.created"/>
<fmt:message var="orders_client" key="control.orders.column.client"/>
<fmt:message var="orders_device" key="control.orders.column.device"/>
<fmt:message var="orders_company" key="control.orders.column.company"/>
<fmt:message var="orders_model" key="control.orders.column.model"/>
<fmt:message var="orders_serial" key="control.orders.column.serial"/>
<fmt:message var="orders_accepted" key="control.orders.column.accepted"/>
<fmt:message var="orders_completed" key="control.orders.column.completed"/>
<fmt:message var="orders_completion_date" key="control.orders.column.completion.date"/>
<fmt:message var="orders_issue_date" key="control.orders.column.issue.date"/>
<fmt:message var="orders_work_description" key="control.orders.column.work.description"/>
<fmt:message var="orders_repair_level" key="control.orders.column.repair.level"/>
<fmt:message var="orders_repair_cost" key="control.orders.column.repair.cost"/>
<fmt:message var="orders_notes" key="control.orders.column.notes"/>
<fmt:message var="orders_parts_cost" key="control.orders.column.parts.cost"/>
<fmt:message var="orders_spare_parts" key="control.orders.column.spare.parts"/>

<div class="container">
    <div class="row">
        <div class="col-auto ms-auto">
            <div class="btn-group">
                <button type="button" class="btn btn-outline-info clear-filter">${orders_reset_filter}</button>
                <button type="button" class="btn btn-outline-success new-order" data-bs-toggle="modal"
                        data-bs-target="#addOrderModal">${orders_new}
                </button>
            </div>
        </div>
    </div>
    <div class="row mt-3 table-container">
        <table class="table table-hover table-sm">
            <thead>
                <tr class="table-info text-center">
                    <th scope="col" class="order-action"></th>
                    <th scope="col">
                        <span>${orders_number}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.order_number"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_status}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.order_status"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_created}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.creation_date"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_client}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.first_name, u.second_name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_device}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="d.device_name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_company}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="co.name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_model}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.model"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_serial}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.serial_number"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_accepted}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="accepted_names"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_completed}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="completed_names"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_completion_date}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.completion_date"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_issue_date}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.issue_date"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_work_description}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.work_description"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_repair_level}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="repair_level"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>${orders_repair_cost}</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="repair_cost"></i>
                        </a>
                    </th>
                    <th scope="col">${orders_notes}</th>
                    <th scope="col">${orders_parts_cost}</th>
                    <th scope="col">${orders_spare_parts}</th>
                </tr>
                <tr class="order-filter table-secondary">
                    <th></th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <select class="form-select filter-input">
                                <option value="" selected></option>
                                <c:forEach var="status" items="${order_status}">
                                    <option value="${status}">${status}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <select class="form-select filter-input">
                                <option value="" selected></option>
                                <c:forEach var="level" items="${repair_level}">
                                    <option value="${level}">${level}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th>
                        <div class="input-group input-group-sm">
                            <input class="form-control filter-input" type="text"/>
                        </div>
                    </th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody class="order-body">

            </tbody>
        </table>
        <div class="col-12 pagination-block my-3">
            <nav>
                <ul class="pagination pagination-sm justify-content-center" data-total-orders="" data-current-page="1"
                    data-is-paginate="">
                    <li class="page-item"><a class="page-link first" href="#"><span>&laquo;</span></a></li>
                    <li class="page-item"><a class="page-link previous" href="#"><span>&lsaquo;</span></a></li>
                    <li class="page-item mx-2"><span class="current-page"></span> of <span class="total-pages"></span>
                    </li>
                    <li class="page-item"><a class="page-link next" href="#"><span>&rsaquo;</span></a></li>
                    <li class="page-item"><a class="page-link last" href="#"><span>&raquo;</span></a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
