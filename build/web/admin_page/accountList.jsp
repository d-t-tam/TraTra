<%-- 
    Document   : accountList
    Created on : Feb 9, 2025, 12:50:30 AM
    Author     : Aus TUF GAMAING
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Account List</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="adminDashboard.jsp">Welcome, Admin</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="#!">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="AdminDashboard">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                            <a class="nav-link" href="AccountList">
                                <div class="sb-nav-link-icon"><i class="fas fa-user fa-fw"></i></div>
                                Account List
                            </a>
                            <a class="nav-link" href="AdminDashboard">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Revenue Report
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">List of accounts</h1>
                        <div style="margin-bottom: 20px; display: flex; justify-content: space-between">
                            <div>
                                <a class="btn btn-primary" href="AccountList?role=Manager" role="button">Manager</a>
                                <a class="btn btn-primary" href="AccountList?role=Seller" role="button">Seller</a>
                                <a class="btn btn-primary" href="AccountList?role=Staff" role="button">Staff</a>
                                <a class="btn btn-primary" href="AccountList?role=Customer" role="button">Customer</a>    
                            </div>
                            <div>
                                <a class="btn btn-success" href="AddAccount" role="button">Add a new account</a>  
                            </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Full Name</th>
                                            <th>Email</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Full Name</th>
                                            <th>Email</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach items="${sessionScope.acc_list}" var="list">
                                            <tr>
                                                <td><c:out value="${list.getFullName()}"/></td>
                                                <td><c:out value="${list.getEmail()}"/></td>
                                                <c:choose>
                                                    <c:when test="${list.getStatus() eq '1'}">
                                                        <td style="color: green;">Active</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color: red;">Inactive</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td>
                                                    <a class="btn btn-warning" href="EditAccount?id=${list.getId()}&&role=${list.getRole()}" role="button">Edit</a>
                                                    <c:choose>
                                                        <c:when test="${list.getStatus() eq '1'}">
                                                            <a class="btn btn-danger" href="ChangeStatusAccount?id=${list.getId()}&&role=${list.getRole()}" role="button" onclick="return confirm('Are you sure you want to disable this account?');">Disable</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a class="btn btn-success" href="ChangeStatusAccount?id=${list.getId()}&&role=${list.getRole()}" role="button" onclick="return confirm('Are you sure you want to enable this account?');">Enable</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>