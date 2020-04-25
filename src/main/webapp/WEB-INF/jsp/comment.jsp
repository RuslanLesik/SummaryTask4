<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="my" uri="http://ua/nure/lesik/SummaryTask4" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="../../css/st.css"/>
    <link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.4.1/dist/jquery.min.js"></script>
    <title><my:locale value="comment.title"/></title>
</head>
<body>
<table class="table table-hover">
    <thead>
    <tr>
        <th style="width: 10%"><my:locale value="table.view"/></th>
        <th style="width: 10%"><my:locale value="table.room.classes"/></th>
        <th style="width: 15%"><my:locale value="table.number.of.places"/></th>
        <th style="width: 25%"><my:locale value="table.description"/></th>
        <th style="width: 7%"><my:locale value="table.price"/></th>
    </tr>
    </thead>
    <tbody>
    <tr class="success">
        <td scope="row">
            <img src="${pageContext.request.contextPath}/images/${room.image_name}" class="rounded-circle"
                 width="185" height="135"/>
        </td>
        <td> ${room.room_classes}</td>
        <td>${room.number_of_places}</td>
        <td>${room.description}</td>
        <td>${room.price}</td>
    </tr>
    </tbody>
</table>

<div class="row d-flex justify-content-center mt-100 mb-100">
    <div class="col-lg-6">
        <div class="card">
            <div class="card-body text-center">
                <h4 class="card-title"><my:locale value="comment.comments"/></h4>
            </div>
            <div class="comment-widgets">
                <!-- Comment Row -->
                <c:if test="${not empty requestScope.commentList}">
                    <c:forEach items="${commentList}" var="comment">
                        <div class="d-flex flex-row comment-row m-t-0">
                            <div class="p-2"><img src="${pageContext.request.contextPath}/images/avatar.jpg" alt="user"
                                                  width="50" class="rounded-circle"></div>
                            <div class="comment-text w-100">
                                <h6 class="font-medium">${comment.user_login}</h6> <span
                                    class="m-b-15 d-block">${comment.comment}</span>
                                <div class="comment-footer"><span
                                        class="text-muted float-right">${comment.date_create}</span>
                                </div>
                                <c:if test="${user.role == 'MANAGER'}">
                                    <form method="post" action="controller">
                                        <input type="hidden" name="command" value="comment"/>
                                        <input type="hidden" name="commentId" value="${comment.id}"/>
                                        <input type="hidden" name="roomId" value="${room.id}"/>
                                        <button type="submit" class="btn btn-outline-danger" name="delete_comment" value="delete_comment">
                                            <my:locale value="delete"/>
                                        </button>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <div class="form-group">
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="comment"/>
                        <input type="hidden" name="roomId" value="${room.id}"/>
                        <label for="comment"><my:locale value="comment.add.text"/></label>
                        <textarea class="form-control" rows="3" id="comment" name="message"></textarea>
                        <br>
                        <input class="btn btn-outline-secondary" onclick="location.href='controller?command=profile'"
                               value="<my:locale value="back"/>">
                        <button type="submit" class="btn btn-outline-primary" name="add_comment" value="add_comment">
                            <my:locale value="comment.add_comment"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
