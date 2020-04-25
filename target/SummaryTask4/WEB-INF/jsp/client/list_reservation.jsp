<html>
<head>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <title><my:locale value="list_reservation.title"/></title>
</head>
<body>
<table class="table table-hover" id="myTable">
    <thead>
    <tr>
        <th style="width: 10%"><my:locale value="table.view"/></th>
        <th style="width: 10%"><my:locale value="table.room.classes"/></th>
        <th style="width: 15%"><my:locale value="table.number.of.places"/></th>
        <th style="width: 25%"><my:locale value="table.description"/></th>
        <th style="width: 15%"><my:locale value="table.price"/></th>
        <th style="width: 15%"><my:locale value="list_reservation.info"/></th>
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
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="list_reservation"/>
                    <button type="submit" class="btn btn-outline-primary"
                            name="roomId" value="${room.id}">
                        <my:locale value="list_reservation.more"/>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
