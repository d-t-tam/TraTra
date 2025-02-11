<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="image/favicon.png" type="image/png">
        <title>Royal Hotel</title>
        <%@include file="components/Style.jsp" %>
    </head>
    <body>
        <!--================Header Area =================-->
        <%@include file="components/Header.jsp" %>
        <!--================Header Area =================-->

        <!--================Breadcrumb Area =================-->
        <section class="breadcrumb_area">
            <div class="overlay bg-parallax" data-stellar-ratio="0.8" data-stellar-vertical-offset="0" data-background=""></div>
            <div class="container">
                <div class="page-cover text-center">
                    <h2 class="page-cover-tittle">Profile</h2>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a></li>
                        <li class="active">Change Password</li>
                    </ol>
                </div>
            </div>
        </section>
        <!--================Breadcrumb Area =================-->

        <!--================Contact Area =================-->
        <section class="contact_area section_gap">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 mb-4">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="profile">View Profile</a>
                            </li>
                            <li class="list-group-item">
                                <a href="update-profile">Update Profile</a>
                            </li>
                            <li class="list-group-item">
                                <a href="change-password">Change Password</a>
                            </li>
                            <li class="list-group-item">
                                <a href="delete-profile">Delete Profile</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-9">
                        <div class="card">
                            <div class="card-body">
                                <form action="change-password" method="post">
                                    <input type="hidden" name="id" value="${sessionScope.customer.id}">
                                    <label>Enter old password</label>
                                    <input type="password" name="oldPassword" >
                                    <label>Enter new password</label>
                                    <input type="password" name="newPassword" >
                                    <button type="submit" name="submit" value="delete">Change Password</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================Contact Area =================-->

    <!--================ start footer Area  =================-->
    <%@include file="components/Footer.jsp" %>
    <!--================ End footer Area  =================-->

    <%@include file="components/Script.jsp" %>
</body>
</html>