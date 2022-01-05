<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-icons.css" rel="stylesheet">
        <link href="../css/main.css" rel="stylesheet">
        <title>ServiceCenter main page</title>
    </head>
    <body>
        <header class="fixed-top">
            <div class="container-fluid bg-secondary text-light px-5">
                <div class="row ">
                    <div class="col-12 col-lg-auto order-lg-4 ">
                        <ul class="nav justify-content-center">
                            <li class="nav-item align-self-center ">
                                <a class="nav-link p-0 pe-1 text-light auth-link " href="#">LOGIN</a>
                            </li>
                            <span class="">|</span>
                            <li class="nav-item align-self-center">
                                <a class="nav-link p-0 ps-1 text-light auth-link" href="#">SIGN UP</a>
                            </li>
                            <li class="p-1 ms-3 ">
                                <select class="form-select form-select-sm p-0 pe-3 ps-1 locale-check">
                                    <option selected>RU</option>
                                    <option value="1">EN</option>
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
                        <span>Street, Town</span>
                    </div>
                    <div class="col-12 col-md-4 col-lg-auto order-lg-3 me-lg-auto text-center">
                        <i class="bi bi-clock-history"></i>
                        <span>Mon-Fri: 9:00 - 18:00</span>
                    </div>
                </div>
            </div>
            <div class="container-fluid bg-primary text-light px-5">
                <div class="row">
                    <div class="col-12  col-md-auto align-self-center me-auto fs-1 text-center">
                        <h1>Service Center</h1>
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
                                        <a class="nav-link" href="#">HOME</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">ABOUT US</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
                                           role="button">SERVICES</a>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" href="#">Check order</a></li>
                                            <li><a class="dropdown-item" href="#">Request call</a></li>
                                        </ul>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">BLOG</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">CONTACTS</a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </header>
        <main>
            <img src="../img/main-repair-md.jpg" class="img-fluid" alt="#" >
        </main>
        <footer>
            <div class="container-fluid bg-secondary text-light px-5">
                <div class="row">
                    <div class="col-auto me-auto align-self-center">
                        <span class="">&#169; Copyright</span>
                    </div>
                    <div class="col-auto">
                        <nav class="navbar ">
                            <div>
                                <a class="navbar-brand text-light" href="#">
                                    <i class="bi bi-instagram"></i>
                                </a>
                                <a class="navbar-brand text-light" href="#">
                                    <i class="bi bi-telegram"></i>
                                </a>
                                <a class="navbar-brand text-light" href="#">
                                    <i class="bi bi-facebook"></i>
                                </a>
                                <a class="navbar-brand text-light" href="#">
                                    <i class="bi bi-twitter"></i>
                                </a>
                                <a class="navbar-brand text-light" href="#">
                                    <i class="bi bi-skype"></i>
                                </a>
                            </div>
                        </nav>
                    </div>

                </div>
            </div>
        </footer>
        <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>
    </body>
</html>