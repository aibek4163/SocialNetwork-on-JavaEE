<%@ page import="java.util.ArrayList" %>
<%@ page import="aralasu.kz.db.Post" %><%--
  Created by IntelliJ IDEA.
  User: Aibek
  Date: 06.10.2020
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="include/head.jsp"%>
    <title>Posts</title>
</head>
    <body>
    <%@include file="include/navbar.jsp"%>
    <div class="container">
        <%
            String success = request.getParameter("success");
            if(success!=null){
        %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            New post added!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%
            }
        %>
        <%
            String error = request.getParameter("error");
            if(error!=null){
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            Error!
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%
            }
        %>
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
                <!-- Button trigger modal -->
                <button type="button" class="btn text-white mb-4" data-toggle="modal" data-target="#addPublication" style="background-color: #180B7A">
                    ADD NEW
                </button>
                <div class="modal fade" id="addPublication" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <form action="/add_post" method="post">
                            <input type="hidden" name="user_id" value="<%=user.getId()%>">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="staticBackdropLabel">Add New Post</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label>Title </label>
                                        <input type="text" class="form-control" name="post_title">
                                    </div>
                                    <div class="form-group">
                                        <label>Short Content</label>
                                    </div>
                                    <textarea name="post_short_content"></textarea>
                                    <div class="form-group">
                                        <label>Content</label>
                                    </div>
                                    <textarea name="post_content"></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-success">Add</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <%
                    ArrayList<Post> posts = (ArrayList<Post>)request.getAttribute("post_user");
                    if(posts!=null){
                        for(Post p:posts){
                %>
                <form action="/details_post" method="get">
                    <input type="hidden" name="id" value="<%=p.getId()%>">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title"><%=p.getTitle()%></h5>
                            <p class="card-text"><%=p.getShort_content()%></p>
                            <button type="button" class="btn btn-sm" data-toggle="modal" style="border-color: #180B7A;color:#180B7A;" data-target="#detailsModal<%=p.getId()%>">
                                More-->
                            </button>
                        </div>
                        <div class="card-footer text-muted d-flex">
                            Posted on <%=p.getPost_date()%> by <p style="color: #180B7A">  <%=p.getUser_id().getFull_name()%></p>
                        </div>
                    </div>
                </form>
                <!-- Modal details -->
                <div class="modal fade" id="detailsModal<%=p.getId()%>" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <form action="/edit_post" method="post">
                            <input type="hidden" name="post_id" value="<%=p.getId()%>">
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
                                        <input type="text" class="form-control" name="post_title" value="<%=p.getTitle()%>">
                                    </div>
                                    <div class="form-group">
                                        <label>Short Content</label>
                                        <textarea name="post_short_content" class="form-control" cols="62" rows="10"><%=p.getShort_content()%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label>Content</label>
                                        <textarea name="post_content" class="form-control" cols="62" rows="10"><%=p.getContent()%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label>Post date</label>
                                        <label>Date: <%=p.getPost_date()%></label>
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
