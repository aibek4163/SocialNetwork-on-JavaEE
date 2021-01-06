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
    <title>Profile Page</title>
</head>
    <body>
        <%@include file="include/navbar.jsp"%>
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <img src="<%=user.getPicture_url()%>" height="200" class="w-100">
                    <div class="card mt-5" style="width: 100%;">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><%=user.getFull_name()%>, <%=user.getAge() + " years"%> </li>
                            <li class="list-group-item"><a href="/profile"><i class="fas fa-id-card"></i>My Profile</a></li>
                            <li class="list-group-item"><a href="#"><i class="fas fa-cogs"></i>Settings</a></li>
                            <li class="list-group-item"><a href="/logout" style="color: #862F3B"><i class="fas fa-sign-out-alt"></i>Log out</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-6">
                    <h4>Edit Profile</h4>
                    <%
                        String email_alert = request.getParameter("same_email");
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
                        String success_up = request.getParameter("updated_success");
                        if(success_up!=null){
                    %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        Your account updated!
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <%
                        String old_password = request.getParameter("old_password");
                        if(old_password!=null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        Old Password Wrong!
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <%
                        String new_password = request.getParameter("new_password");
                        if(new_password!=null){
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
                        Your password changed!
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <form action="/edit_user?btn_update_profile" method="post">
                        <input type="hidden" name="id" value="<%=user.getId()%>">
                        <div class="edit_profile border-bottom mb-3 pb-3">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input type="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" value="<%=user.getEmail()%>" readonly>
                                <small id="emailHelp" class="form-text text-muted">Input your email</small>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputFullName1">Full Name</label>
                                <input type="text" class="form-control" name="full_name" id="exampleInputFullName1" aria-describedby="fullnameHelp" value="<%=user.getFull_name()%>">
                                <small id="fullnameHelp" class="form-text text-muted">Input your full name</small>
                            </div>
                            <div class="form-group">
                                <label for="date">Birthdate</label>
                                <input class="form-control" id="date" name="date" placeholder="YYYY/MM/DD" type="text" value="<%=user.getBirth_date()%>"/>
                                <small id="birthdateHelp1" class="form-text text-muted">Input your birthdate</small>
                            </div>
                            <button class="btn text-white" style="background-color: #180B7A">Update Profile</button>
                        </div>
                    </form>
                    <form action="/edit_user?btn_update_picture" method="post">
                        <input type="hidden" name="id" value="<%=user.getId()%>">
                        <div class="edit_picture border-bottom mb-3 pb-3">
                            <h4>Edit Picture</h4>
                            <div class="form-group">
                                <label for="inputPictureUrl">Edit Picture URL</label>
                                <input type="text" class="form-control" name="picture_url" id="inputPictureUrl" aria-describedby="pictureurl" value="<%=user.getPicture_url()%>">
                                <small id="pictureurl" class="form-text text-muted">Input your picture URL</small>
                            </div>
                            <button  class="btn text-white" style="background-color: #180B7A">Update Picture</button>
                        </div>
                    </form>

                    <form action="/edit_user?btn_update_password" method="post">
                        <input type="hidden" name="id" value="<%=user.getId()%>">
                        <div class="edit_password mb-3 pb-3">
                            <h4>Update Password</h4>
                            <div class="form-group">
                                <label for="oldPassword">Old Password</label>
                                <input type="password" class="form-control" name="old_password" id="oldPassword" aria-describedby="oldPasswordHelp">
                                <small id="oldPasswordHelp" class="form-text text-muted">Input your old password</small>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">New Password</label>
                                <input type="password" class="form-control" name="new_password" id="exampleInputPassword1" aria-describedby="passwordHelp">
                                <small id="passwordHelp" class="form-text text-muted">Input your new password</small>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Re-New Password</label>
                                <input type="password" class="form-control" name="re_new_password" id="exampleInputRePassword1" aria-describedby="repasswordHelp">
                                <small id="repasswordHelp" class="form-text text-muted">Re input your new password again</small>
                            </div>
                            <button  class="btn text-white" style="background-color: #180B7A">Update Password</button>
                        </div>
                    </form>
                </div>
                <div class="col-3">
                    <div class="card bg-light mb-3" style="max-width: 18rem;">
                        <div class="card-header">Header</div>
                        <div class="card-body">
                            <h5 class="card-title">Light card title</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                    <div class="card bg-light mb-3" style="max-width: 18rem;">
                        <div class="card-header">Header</div>
                        <div class="card-body">
                            <h5 class="card-title">Light card title</h5>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
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
