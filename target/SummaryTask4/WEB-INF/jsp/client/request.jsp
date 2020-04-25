<html>
<head>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <title><my:locale value="request.title"/></title>
</head>
<body>
<br>
<br>
<div class="col-md-4 col-md-push-4">
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#exampleModal">
        <my:locale value="request.create"/>
    </button>

</div>
<c:if test="${not empty requestScope.roomList}">
    <br>
    <h3><my:locale value="request.response"/></h3>
    <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col"><my:locale value="table.view"/></th>
            <th scope="col"><my:locale value="table.room.classes"/></th>
            <th scope="col"><my:locale value="table.number.of.places"/></th>
            <th scope="col"><my:locale value="table.description"/></th>
            <th scope="col"><my:locale value="table.price"/></th>
            <th scope="col"><my:locale value="reservation"/></th>
            <th scope="col"><my:locale value="do.delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roomList}" var="room">
            <tr class="success">
                <td scope="row">
                    <img src="${pageContext.request.contextPath}/images/${room.image_name}" class="rounded-circle"
                         width="185" height="135"/>
                </td>
                <td> ${room.room_classes}</td>
                <td>${room.number_of_places}</td>
                <td>${room.description}</td>
                <td>${room.price}</td>
                <td>
                    <c:if test="${room.room_status != 'NOT_AVAILABLE'}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="reservation"/>
                            <button type="submit" class="btn btn-outline-info"
                                    name="roomId" value="${room.id}">
                                <my:locale value="do.reservation"/>
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${room.room_status == 'NOT_AVAILABLE'}">
                        NOT_AVAILABLE
                        </form>
                    </c:if>
                </td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="delete_answer"/>
                        <button type="submit" class="btn btn-outline-danger"
                                name="roomId" value="${room.id}">
                            <my:locale value="delete"/>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${not empty requestScope.messagesList}">
    <br>
    <h3><my:locale value="request.response"/></h3>
    <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col"><my:locale value="request.fail"/></th>
            <th scope="col"><my:locale value="do.delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${messagesList}" var="oneMessage">
            <tr class="success">
                <td>
                    <p class="text-danger">${oneMessage.message}</p
                </td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="delete_answer"/>
                        <button type="submit" class="btn btn-outline-danger"
                                name="messageId" value="${oneMessage.id}">
                            <my:locale value="delete"/>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
<%@ include file="/WEB-INF/jspf/modal.jspf" %>
</html>

