<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="role" value="${not empty sessionScope.userRole?sessionScope.userRole:'GUEST'}"/>
<c:set var="locale" value="${not empty sessionScope.locale?sessionScope.locale:'en_EN'}"/>
<c:url value="/control?command=goto_main_page" var="main_page"/>
<c:url value="/control?command=goto_check_order_page" var="check_order_page"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message var="login" key="header.login"/>
<fmt:message var="signup" key="header.signup"/>
<fmt:message var="address" key="header.address"/>
<fmt:message var="working" key="header.working"/>
<fmt:message var="name" key="header.name"/>
<fmt:message var="home" key="header.nav.home"/>
<fmt:message var="about" key="header.nav.about"/>
<fmt:message var="services" key="header.nav.services"/>
<fmt:message var="check" key="header.nav.services.check"/>
<fmt:message var="request" key="header.nav.services.request"/>
<fmt:message var="blog" key="header.nav.blog"/>
<fmt:message var="contacts" key="header.nav.contacts"/>

<header class="fixed-top">
    <div class="container-fluid bg-secondary text-light px-5">
        <div class="row ">
            <div class="col-12 col-lg-auto order-lg-4 ">
                <ul class="nav justify-content-center">
                    <li class="nav-item align-self-center ">
                        <span class="nav-link p-0 px-1 me-4 bg-danger text-light auth-link " data-role="${role}"
                              id="current-role">${role}</span>
                    </li>
                    <li class="nav-item align-self-center ">
                        <a class="nav-link p-0 pe-1 text-light auth-link " href="#">${login}</a>
                    </li>
                    <span class="">|</span>
                    <li class="nav-item align-self-center">
                        <a class="nav-link p-0 ps-1 text-light auth-link clearable" href="#">${signup}</a>
                    </li>
                    <li class="p-1 ms-3 ">
                        <select class="form-select form-select-sm p-0 pe-3 ps-1 locale-check" id="locale_selector">
                            <option value="ru_RU" ${locale=="ru_RU"?"selected":""}>RU</option>
                            <option value="en_EN" ${locale=="en_EN"?"selected":""}>EN</option>
                            <option value="be_BY" ${locale=="be_BY"?"selected":""}>BY</option>
                        </select>
                    </li>
                </ul>
            </div>
            <div class="col-12 col-sm-6 col-md-4 col-lg-auto order-lg-1 text-center">
                <i class="bi bi-telephone-inbound"></i>
                <span>+375(12)3456789</span>
            </div>
            <div class="col-12 col-sm-6 col-md-4 col-lg-auto order-lg-2 text-center">
                <i class="bi bi-geo-alt"></i>
                <span>${address}</span>
            </div>
            <div class="col-12 col-md-4 col-lg-auto order-lg-3 me-lg-auto text-center">
                <i class="bi bi-clock-history"></i>
                <span>${working}</span>
            </div>
        </div>
    </div>
    <div class="container-fluid bg-primary text-light px-5">
        <div class="row">
            <div class="col-12  col-md-auto align-self-center me-auto fs-1 text-center">
                <h1>${name}</h1>
            </div>
            <div class="col-auto order-md-auto">
                <nav class="navbar navbar-expand-md bg-primary">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarToggler">
                        <i class="bi bi-layout-text-sidebar text-light fs-2"></i>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarToggler">
                        <ul class="navbar-nav ms-auto ">
                            <li class="nav-item">
                                <a class="nav-link clearable" href="${main_page}">${home}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link clearable" href="#">${about}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
                                   role="button">${services}</a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="${check_order_page}">${check}</a></li>
                                    <li><a class="dropdown-item clearable" href="#">${request}</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link clearable" href="#">${blog}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link clearable" href="#">${contacts}</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</header>

