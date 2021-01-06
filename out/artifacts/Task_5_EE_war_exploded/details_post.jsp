<%@ page import="aralasu.kz.db.Post" %><%--
  Created by IntelliJ IDEA.
  User: Aibek
  Date: 06.10.2020
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/head.jsp"%>
    <title>Details Post</title>
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
                    <%
                        Post posts = (Post)request.getAttribute("post");
                        if(posts!=null){
                    %>
                        <input type="hidden" name="id" value="<%=posts.getId()%>">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title"><%=posts.getTitle()%></h5>
                                <p class="card-text"><%=posts.getShort_content()%></p>
                                <p class="card-text"><%=posts.getContent()%></p>
                            </div>
                            <%
                                if(user.getId().equals(posts.getUser_id().getId())){
                            %>
                            <button type="button" class="btn btn-sm" data-toggle="modal" style="border-color: #180B7A;color:#180B7A;" data-target="#detailsModal<%=posts.getId()%>">
                                Edit post
                            </button>
                            <%
                                }
                            %>
                            <div class="card-footer text-muted d-flex">
                                Posted on <%=posts.getPost_date()%> by <p style="color: #180B7A">  <%=posts.getUser_id().getFull_name()%></p>
                            </div>
                        </div>
                    <div class="modal fade" id="detailsModal<%=posts.getId()%>" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <form action="/edit_post?own_post" method="post">
                                <input type="hidden" name="post_id" value="<%=posts.getId()%>">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel_detail">POST DETAILS</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label>Title</label>
                                            <input type="text" class="form-control" name="post_title" value="<%=posts.getTitle()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Short Content</label>
                                            <textarea name="post_short_content" class="form-control" cols="62" rows="10"><%=posts.getShort_content()%></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Content</label>
                                            <textarea name="post_content" class="form-control" cols="62" rows="10"><%=posts.getContent()%></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Date: <%=posts.getPost_date()%></label>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button  class="btn btn-success" name="edit_btn">Edit</button>
                                        <button  class="btn btn-danger" name="delete_btn">Delete</button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%
                        }
                    %>
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
    </body>
</html>
