<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="role" value="${sessionScope.userRole}"/>
<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:set var="request_parameters" value="${sessionScope.requestData.requestParameters}"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="control_title" key="control.title"/>
<fmt:message var="page_header" key="control.main.header"/>


<fmt:message var="login_label" key="login.main.form.login.label"/>
<fmt:message var="login_tip" key="login.main.form.login.tip"/>
<fmt:message var="login_error_match" key="login.main.form.login.error.match"/>
<fmt:message var="password_label" key="login.main.form.password.label"/>
<fmt:message var="password_tip" key="login.main.form.password.tip"/>
<fmt:message var="password_error_match" key="login.main.form.password.error.match"/>
<fmt:message var="form_submit" key="login.main.form.submit"/>
<fmt:message var="remember" key="login.main.form.remember.label"/>
<fmt:message var="registration" key="login.main.form.link.registration"/>

<fmt:message var="order_number" key="checkOrder.main.table.order.number"/>
<fmt:message var="order_status" key="checkOrder.main.table.status"/>
<fmt:message var="order_creation" key="checkOrder.main.table.creation"/>
<fmt:message var="order_device" key="checkOrder.main.table.device"/>
<fmt:message var="order_company" key="checkOrder.main.table.company"/>
<fmt:message var="order_model" key="checkOrder.main.table.model"/>
<fmt:message var="order_serial" key="checkOrder.main.table.serial"/>
<fmt:message var="order_completion" key="checkOrder.main.table.completion"/>
<fmt:message var="order_issue" key="checkOrder.main.table.issue"/>
<fmt:message var="order_work_description" key="checkOrder.main.table.work.description"/>
<fmt:message var="order_work_cost" key="checkOrder.main.table.work.cost"/>
<fmt:message var="order_total_cost" key="checkOrder.main.table.total.cost"/>
<fmt:message var="order_part_caption" key="checkOrder.main.table.part.caption"/>
<fmt:message var="order_part_number" key="checkOrder.main.table.part.number"/>
<fmt:message var="order_part_name" key="checkOrder.main.table.part.name"/>
<fmt:message var="order_part_description" key="checkOrder.main.table.part.description"/>
<fmt:message var="order_part_cost" key="checkOrder.main.table.part.cost"/>

<!DOCTYPE html>
<html>
    <head>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-icons.css" rel="stylesheet">
        <link href="../css/font-awesome.css" rel="stylesheet">
        <link href="../css/header.css" rel="stylesheet">
        <link href="../css/control.css" rel="stylesheet">
        <title>${control_title}</title>
    </head>
    <body class="min-vh-100">
        <c:import url="icon_sprite.jsp"/>
        <c:import url="registerEmployee.jsp"/>
        <c:import url="add_order.jsp"/>
        <c:import url="edit_order.jsp"/>
        <c:import url="header.jsp"/>
        <main class="my-5">
            <div class="d-flex align-items-start">
                <div class="nav flex-column nav-pills mx-3" id="v-pills-tab">
                    <button class="nav-link active" id="orders-tab" data-bs-toggle="pill"
                            data-bs-target="#orders-content"
                            type="button">Заказы
                    </button>
                    <button class="nav-link" id="employees-tab" data-bs-toggle="pill"
                            data-bs-target="#employees-content" type="button">Сотрудники
                    </button>
                    <button class="nav-link" id="parts-tab" data-bs-toggle="pill" data-bs-target="#parts-content"
                            type="button">Запчасти
                    </button>
                    <button class="nav-link" id="devices-tab" data-bs-toggle="pill" data-bs-target="#devices-content"
                            type="button">Устройства
                    </button>
                    <button class="nav-link" id="producers-tab" data-bs-toggle="pill"
                            data-bs-target="#producers-content"
                            type="button">Производители
                    </button>
                    <button class="nav-link" id="prices-tab" data-bs-toggle="pill" data-bs-target="#prices-content"
                            type="button">Цены
                    </button>
                </div>
                <div class="tab-content" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="orders-content">
                        <c:import url="orders_tab.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="employees-content">
                        <c:import url="employees_tab.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="parts-content">
                        <c:import url="parts_tab.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="devices-content">
                        <c:import url="devices_tab.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="producers-content">
                        <c:import url="companies_tab.jsp"/>
                    </div>
                    <div class="tab-pane fade" id="prices-content">prices</div>
                </div>
            </div>
        </main>
        <c:import url="footer.jsp"/>
        <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>
        <script src="../js/header.js" type="text/javascript"></script>
        <script src="../js/custom_validation.js" type="text/javascript"></script>
        <script src="../js/control.js" type="text/javascript"></script>
        <script src="../js/add_order.js" type="text/javascript"></script>
        <script src="../js/edit_order.js" type="text/javascript"></script>
        <script src="../js/employees.js" type="text/javascript"></script>
        <script src="../js/parts.js" type="text/javascript"></script>
        <script src="../js/devices.js" type="text/javascript"></script>
        <script src="../js/companies.js" type="text/javascript"></script>
        <script src="../js/ajax_request.js" type="text/javascript"></script>
    </body>
</html>