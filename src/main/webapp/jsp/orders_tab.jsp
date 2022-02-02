<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="order_status" value="${sessionScope.orderStatus}"/>
<c:set var="repair_level" value="${sessionScope.repairLevel}"/>

<div class="container">
    <div class="row">
        <div class="col-auto ms-auto">
            <div class="btn-group">
                <button type="button" class="btn btn-outline-info clear-filter">Сбросить фильтр</button>
                <button type="button" class="btn btn-outline-success new-order" data-bs-toggle="modal" data-bs-target="#addOrderModal">Новый заказ</button>
            </div>
        </div>
    </div>
    <div class="row table-responsive mt-3">
        <table class="table table-hover table-sm">
            <thead>
                <tr class="table-info">
                    <th scope="col">
                        <span>Номер&nbspзаказа</span>
                        <a class="sort-link" >
                            <i class="fa fa-sort" data-sort="" data-column="o.order_number"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Статус&nbspзаказа</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.order_status"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Создан</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.creation_date"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Клиент</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.first_name, u.second_name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Устройство</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="d.device_name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Производитель</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="co.name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Модель</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.model"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Серийный&nbspномер</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.serial_number"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Принял</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="accepted_names"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Выполнил</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="completed_names"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Завершен</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.completion_date"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Выдан</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.issue_date"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Работы</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="o.work_description"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Сложность</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="repair_level"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Стоимость&nbspработы</span>
                        <a class="sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="repair_cost"></i>
                        </a>
                    </th>
                    <th scope="col">Заметки</th>
                    <th scope="col">Стоимость&nbspдеталей</th>
                    <th scope="col">Запчасти</th>
                    <th scope="col" class="order-action"></th>
                </tr>
            </thead>
            <tbody>
                <tr class="order-filter table-secondary">
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
                    <th>
                    </th>
                    <th></th>
                </tr>
            </tbody>
        </table>
    </div>
</div>
