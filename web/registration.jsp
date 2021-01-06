<%--
  Created by IntelliJ IDEA.
  User: Aibek
  Date: 04.10.2020
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/head.jsp"%>
    <title>Registration</title>
</head>
    <body>
        <%@include file="include/navbar.jsp"%>
        <div class="container">
            <h1 class="display-4 text-center mx-auto">Create New Account</h1>
            <div class="row mt-5">
                <div class="col-sm-6 offset-3">
                    <%
                        String email_alert = request.getParameter("email_error");
                        if(email_alert!=null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        Email exist!
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <%
                        String password_alert = request.getParameter("password_error");
                        if(password_alert!=null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        Not same passwords!
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <%
                        String success_alert = request.getParameter("success");
                        if(success_alert!=null){
                    %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        Your account created!
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <form action="/add_user" method="post">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" required class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
                            <small id="emailHelp" class="form-text text-muted">Input your email</small>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1" aria-describedby="passwordHelp" name="password">
                            <small id="passwordHelp" class="form-text text-muted">Input your password</small>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Re-Password</label>
                            <input type="password" required class="form-control" id="exampleInputRePassword1" aria-describedby="repasswordHelp" name="re_password">
                            <small id="repasswordHelp" class="form-text text-muted">Re input your password again</small>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputFullName1">Full Name</label>
                            <input type="text" required class="form-control" id="exampleInputFullName1" aria-describedby="fullnameHelp" name="full_name">
                            <small id="fullnameHelp" class="form-text text-muted">Input your full name</small>
                        </div>
                        <div class="form-group">
                            <label for="date">Birthdate</label>
                            <input class="form-control" id="date" name="date" type="text"/>
                            <small id="birthdateHelp1"  class="form-text text-muted">Input your birthdate</small>
                        </div>
                        <button class="btn text-white" style="background-color: #180B7A">Sign Up</button>
                    </form>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function(){
                var date_input=$('input[name="date"]'); //our date input has the name "date"
                var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
                var options={
                    format: 'yyyy/mm/dd',
                    container: container,
                    todayHighlight: true,
                    autoclose: true,
                };
                date_input.datepicker(options);
            })
        </script>
    </body>
</html>
