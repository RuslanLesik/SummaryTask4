
<html>
<head>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <title><my:locale value="request_list.title"/></title>
</head>
<body>
<h3><my:locale value="request_list.page"/></h3>
<table class="table table-hover" id="myTable">
    <thead>
    <tr>
        <th scope="col"><my:locale value="table.number.of.places"/></th>
        <th scope="col"><my:locale value="table.room.classes"/></th>
        <th scope="col"><my:locale value="request_list.number_of_days"/></th>
        <th scope="col"><my:locale value="request_list.user_login"/></th>
        <th scope="col"><my:locale value="request_list.select_room"/></th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col"></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestList}" var="oneRequest">
        <tr class="success">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="request_list"/>
                <input type="hidden" name="user_login" value="${oneRequest.user_login}"/>
                <td scope="row">${oneRequest.numbers_of_places}</td>
                <td>${oneRequest.room_classes}</td>
                <td>${oneRequest.number_of_days}</td>
                <td>${oneRequest.user_login}</td>
                <td><select name="room_id" class="form-control"
                            id="exampleFormControlSelect1">
                    <c:forEach items="${listRooms}" var="oneRoom">
                        <c:if test="${oneRequest.room_classes == oneRoom.room_classes}">
                            <option value="${oneRoom.id}">
                                Class : ${oneRoom.room_classes},
                                Number of places : ${oneRoom.number_of_places},
                                Price : ${oneRoom.price}
                            </option>
                        </c:if>
                    </c:forEach>
                </select></td>
                <td>
                <td>
                    <button type="submit" class="btn btn-outline-success" name="sendAnswer" value="${oneRequest.id}">
                        <my:locale value="request_list.send"/>
                    </button>
                </td>
                <td>
                    <button type="submit" class="btn btn-outline-danger" name="notFound" value="${oneRequest.id}">
                        <my:locale value="request_list.not_found"/>
                    </button>
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
