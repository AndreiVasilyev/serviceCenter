<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="role" value="${not empty sessionScope.userRole?sessionScope.userRole:'GUEST'}"/>
<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:set var="request_parameters" value="${sessionScope.requestData.requestParameters}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="check_order_title" key="checkOrder.title"/>
<fmt:message var="page_header" key="checkOrder.main.header"/>
<fmt:message var="number_label" key="checkOrder.main.form.number.label"/>
<fmt:message var="number_tip" key="checkOrder.main.form.number.tip"/>
<fmt:message var="number_error_match" key="checkOrder.main.form.number.error.match"/>
<fmt:message var="email_label" key="checkOrder.main.form.email.label"/>
<fmt:message var="email_tip" key="checkOrder.main.form.email.tip"/>
<fmt:message var="email_error_match" key="checkOrder.main.form.email.error.match"/>
<fmt:message var="email_error_size" key="checkOrder.main.form.email.error.size"/>
<fmt:message var="email_button" key="checkOrder.main.form.email.button"/>
<fmt:message var="code_label" key="checkOrder.main.form.code.label"/>
<fmt:message var="code_tip" key="checkOrder.main.form.code.tip"/>
<fmt:message var="code_error_match" key="checkOrder.main.form.code.error.match"/>
<fmt:message var="form_submit" key="checkOrder.main.form.submit"/>
<fmt:message var="alert_note" key="checkOrder.main.alert.note.send.code"/>
<fmt:message var="alert_result" key="checkOrder.main.alert.result.send.code"/>
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
        <link href="../css/header.css" rel="stylesheet">
        <link href="../css/check_order.css" rel="stylesheet">
        <title>${check_order_title}</title>
    </head>
    <body class="min-vh-100">
        <c:import url="icon_sprite.jsp"/>
        <c:import url="header.jsp"/>
        <main class="mt-5">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center fw-bolder text-secondary">
                        <h2>${page_header}</h2>
                    </div>
                    <div class="col-6 mx-auto">
                        <form method="POST" name="order" class="needs-validation">
                            <div class="mb-3">
                                <label for="order-number-input" class="form-label">${number_label}</label>
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="order-number-input" name="orderNumber"
                                           value="${request_parameters.orderNumber[0]}"/>
                                    <label for="order-number-input" class="input-field-tip">${number_tip}</label>
                                </div>
                                <div id="order-number-error" class="form-text text-danger"
                                     data-error-match="${number_error_match}">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="email-input" class="form-label">${email_label}</label>
                                <div class="form-floating input-group">
                                    <input type="email" class="form-control" name="email" id="email-input"
                                           value="${request_parameters.email[0]}" ${role eq 'GUEST'?'':'disabled'}/>
                                    <label for="email-input" class="input-field-tip">${email_tip}</label>
                                    <button class="btn btn-outline-success" type="button" id="send-code-button"
                                            disabled> ${email_button}</button>
                                </div>
                                <div id="email-error" class="form-text text-danger"
                                     data-error-match="${email_error_match}"
                                     data-error-size="${email_error_size}"></div>
                            </div>
                            <div class="mb-3">
                                <label for="code-input" class="form-label">${code_label}</label>
                                <div class="form-floating">
                                    <input type="text" class="form-control" id="code-input" disabled name="code"
                                           value="${request_parameters.code[0]}" ${role eq 'GUEST'?'':'disabled'}/>
                                    <label for="code-input" class="input-field-tip">${code_tip}</label>
                                </div>
                                <div id="code-error" class="form-text text-danger"
                                     data-error-match="${code_error_match}"></div>
                            </div>
                            <button type="button" class="btn btn-success" id="check-order-button" disabled>
                                ${form_submit}</button>
                            <div class="alert alert-warning d-none align-items-center mt-2 p-1" id="alert-block">
                                <svg class="bi flex-shrink-0 me-2" width="24" height="24">
                                    <use href="#exclamation-triangle-fill"/>
                                </svg>
                                <div id="error-send-code" class="d-none alert-block-message"></div>
                                <div id="note-input-code" class="d-none alert-block-message">${alert_note}</div>
                                <div id="result-search-order" class="d-none alert-block-message">${alert_result}</div>
                            </div>
                        </form>
                    </div>
                    <div class="col-10 table-responsive mx-auto d-none results">
                        <table class="table table-bordered table-sm">
                            <thead>
                                <tr class="table-primary">
                                    <th scope="col">${order_number}</th>
                                    <th scope="col">${order_status}</th>
                                    <th scope="col">${order_creation}</th>
                                    <th scope="col">${order_device}</th>
                                    <th scope="col">${order_company}</th>
                                    <th scope="col">${order_model}</th>
                                    <th scope="col">${order_serial}</th>
                                    <th scope="col">${order_completion}</th>
                                    <th scope="col">${order_issue}</th>
                                    <th scope="col">${order_work_description}</th>
                                    <th scope="col">${order_work_cost}</th>
                                    <th scope="col">${order_total_cost}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row" class="td-number td-order"></th>
                                    <td class="td-status td-order"></td>
                                    <td class="td-creation td-order"></td>
                                    <td class="td-device td-order"></td>
                                    <td class="td-company td-order"></td>
                                    <td class="td-model td-order"></td>
                                    <td class="td-serial td-order"></td>
                                    <td class="td-completion td-order"></td>
                                    <td class="td-issue td-order"></td>
                                    <td class="td-work td-order"></td>
                                    <td class="td-work-cost td-order"></td>
                                    <td class="td-total-cost td-order"></td>
                                </tr>
                                <tr>
                                    <td colspan="4"></td>
                                    <td colspan="8">
                                        <table class="table table-sm table-borderless mb-0 caption-top">
                                            <caption>${order_part_caption}</caption>
                                            <thead>
                                                <th scope="col">${order_part_number}</th>
                                                <th scope="col">${order_part_name}</th>
                                                <th scope="col">${order_part_description}</th>
                                                <th scope="col">${order_part_cost}</th>
                                            </thead>
                                            <tbody class="body-parts">
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        <c:import url="footer.jsp"/>
        <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>
        <script src="../js/header.js" type="text/javascript"></script>
        <script src="../js/custom_validation.js" type="text/javascript"></script>
        <script src="../js/check_order.js" type="text/javascript"></script>
        <script src="../js/ajax_request.js" type="text/javascript"></script>
    </body>
</html>