<html>
<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <script type="text/javascript" src="webjars/datatables/1.10.20/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="webjars/datatables/1.10.20/css/jquery.dataTables.min.css">
    <title><my:locale value="profile.title"/></title>
</head>
<body>
<br>
<br>
<br>
<table class="table table-hover" id="myTable">
    <thead>
    <tr>
        <th style="width:  10%" class="no-sort"><my:locale value="table.view"/></th>
        <th style="width:  10%"><my:locale value="table.room.classes"/></th>
        <th style="width:  15%"><my:locale value="table.number.of.places"/></th>
        <th style="width:  25%" class="no-sort"><my:locale value="table.description"/></th>
        <th style="width:  8%"><my:locale value="table.price"/></th>
        <th style="width:  8%"><my:locale value="table.status"/></th>
        <c:if test="${user.role == 'MANAGER'}">
            <th style="width:  15%" class="no-sort"><my:locale value="lock.unlock"/></th>
            <th style="width:  15%" class="no-sort"><my:locale value="do.delete"/></th>
        </c:if>
        <c:if test="${user.role == 'CLIENT'}">
            <th style="width:  15%" class="no-sort"><my:locale value="reservation"/></th>
        </c:if>
        <th style="width:  15%" class="no-sort"><my:locale value="profile.comments"/></th>
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
            <td>${room.room_status}</td>
            <c:if test="${user.role == 'MANAGER'}">
                <td>
                    <c:if test="${room.room_status != 'NOT_AVAILABLE'}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="change_room"/>
                            <button type="submit" class="btn btn-outline-secondary"
                                    name="disable_room_id" value="${room.id}">
                                <my:locale value="lock"/>
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${room.room_status == 'NOT_AVAILABLE'}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="change_room"/>
                            <button type="submit" class="btn btn-outline-success"
                                    name="enable_room_id" value="${room.id}">
                                <my:locale value="unlock"/>
                            </button>
                        </form>
                    </c:if>
                </td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="change_room"/>
                        <button type="submit" class="btn btn-outline-danger"
                             name="delete_room_id"   value="${room.id}">
                            <my:locale value="delete"/>
                        </button>
                    </form>
                </td>
            </c:if>
            <c:if test="${user.role == 'CLIENT'}">
                <td>
                    <c:if test="${room.room_status != 'NOT_AVAILABLE'}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="reservation"/>
                            <button type="submit" class="btn btn-outline-info"
                                 name="roomId"   value="${room.id}">
                                <my:locale value="do.reservation"/>
                            </button>
                        </form>
                    </c:if>
                </td>
            </c:if>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="comment"/>
                    <button type="submit" class="btn btn-outline-warning"
                            name="roomId"   value="${room.id}">
                        <my:locale value="profile.comment"/>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="/WEB-INF/jspf/datatable_script.jspf" %>
</body>
</html>
