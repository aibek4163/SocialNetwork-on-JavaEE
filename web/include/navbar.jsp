<%@include file="session_online.jsp"%>
<div class="container-fluid" style="background-color: #180B7A">
    <div class="container">
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3  " style="background-color: #180B7A">
            <h5 class="my-0 mr-md-auto font-weight-normal text-white">ARALASU KZ</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <%
                    if(isOnline){
                %>
                <i class="far fa-newspaper text-white"></i>
                <a class="p-2 text-white" href="/feed">Feed</a>
                <i class="fas fa-user-friends text-white"></i>
                <a class="p-2 text-white" href="/friends">My Friends</a>
                <i class="fas fa-users text-white"></i>
                <a class="p-2 text-white" href="#">Groups</a>
                <i class="fas fa-comment text-white"></i>
                <a class="p-2 text-white" href="/post?user_id=<%=user.getId()%>">My Posts</a>
                <i class="fab fa-telegram-plane text-white"></i>
                <a class="p-2 text-white" href="/message">Messages +<%=unread_message%></a>
                <i class="fas fa-images text-white"></i>
                <a class="p-2 text-white" href="#">Pictures</a>
                <i class="fas fa-video text-white"></i>
                <a class="p-2 text-white" href="#">Videos</a>
                <%
                    }else {
                %>
                <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-person-plus-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7.5-3a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
                </svg>
                <a class="p-2 text-white" href="/register">Register</a>
                <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-box-arrow-in-right" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
                    <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                </svg>
                <a class="p-2 text-white" href="/login">Login</a>
                <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-question-circle-fill" fill="white" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.496 6.033a.237.237 0 0 1-.24-.247C5.35 4.091 6.737 3.5 8.005 3.5c1.396 0 2.672.73 2.672 2.24 0 1.08-.635 1.594-1.244 2.057-.737.559-1.01.768-1.01 1.486v.105a.25.25 0 0 1-.25.25h-.81a.25.25 0 0 1-.25-.246l-.004-.217c-.038-.927.495-1.498 1.168-1.987.59-.444.965-.736.965-1.371 0-.825-.628-1.168-1.314-1.168-.803 0-1.253.478-1.342 1.134-.018.137-.128.25-.266.25h-.825zm2.325 6.443c-.584 0-1.009-.394-1.009-.927 0-.552.425-.94 1.01-.94.609 0 1.028.388 1.028.94 0 .533-.42.927-1.029.927z"/>
                </svg>
                <a class="p-2 text-white" href="#">FAQ</a>
                <a class="p-2 text-white" href="#">About Aralasu</a>
                <%
                    }
                %>
            </nav>
        </div>
    </div>
</div>

