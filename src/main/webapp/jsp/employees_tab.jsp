<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <div class="col-auto ms-auto">
            <div class="btn-group">
                <button type="button" class="btn btn-outline-info clear-employees-filter">Сбросить фильтр</button>
                <button type="button" class="btn btn-outline-success register-employee" data-bs-toggle="modal"
                        data-bs-target="#registerEmployeeModal">Новый сотрудник
                </button>
            </div>
        </div>
    </div>
    <div class="row mt-3 table-container">
        <table class="table table-hover table-sm">
            <thead>
                <tr class="table-info text-center">
                    <th scope="col">
                        <span>userID</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.user_id"></i>
                        </a>
                    </th>
                    <th scope="col" class="px-4">
                        <span>User Role</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.user_role"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Фамилия</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.second_name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Имя</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.first_name"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Отчество</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.patronymic"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Почта</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="u.email"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Индекс</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.postcode"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Страна</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.country"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Область</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.state"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Район</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.region"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Город</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.city"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Улица</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.street"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Дом</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.house_number"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Квартира</span>
                        <a class="employee-sort-link">
                            <i class="fa fa-sort" data-sort="" data-column="a.apartment_number"></i>
                        </a>
                    </th>
                    <th scope="col">
                        <span>Телефоны</span>
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
                </tr>
            </thead>
            <tbody class="employees-body">

            </tbody>
        </table>
    </div>
</div>
