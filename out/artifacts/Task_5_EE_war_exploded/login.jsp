<%--
  Created by IntelliJ IDEA.
  User: Aibek
  Date: 04.10.2020
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/head.jsp"%>
    <title>Log In</title>
</head>
    <body>
        <%@include file="include/navbar.jsp"%>
        <div class="container">
            <h1 class="display-4 text-center mx-auto">Sign In</h1>
            <div class="row mt-5">
                <div class="col-sm-6 offset-3">
                    <%
                        String email_alert = request.getParameter("email_error");
                        if(email_alert!=null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        Email does not exist!
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
                        Wrong password!
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <form action="/sign_in" method="post">
                            <%
                                String email_cookie = (String)request.getAttribute("email_field");
                                String password_cookie = (String)request.getAttribute("password_field");
                            %>

                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input type="email" required class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" value="<%=email_cookie%>">
                                <small id="emailHelp" class="form-text text-muted">Input your email</small>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="password" required class="form-control" id="exampleInputPassword1" aria-describedby="passwordHelp" name="password" value="<%=password_cookie%>">
                                <small id="passwordHelp" class="form-text text-muted">Input your password</small>
                            </div>
                            <div class="form-check mb-4">
                                <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" name="is_check">
                                <label class="form-check-label" for="defaultCheck1">
                                    Remember me
                                </label>
                            </div>
                        <button class="btn text-white" style="background-color: #180B7A">Sign In</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
