<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="http://ua/nure/lesik/SummaryTask4"%>
<html>
<head>
    <script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
    <title><my:locale value="info_reservation.title"/></title>
</head>
<body>
<table class="table table-hover" id="myTable">
    <thead>
    <tr>
        <th scope="col"><my:locale value="info_reservation.from"/></th>
        <th scope="col"><my:locale value="info_reservation.to"/></th>
        <th scope="col"><my:locale value="do.delete"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${reserveList}" var="reserve">
    <tr class="success">
        <td scope="row">${reserve.check_in}</td>
        <td> ${reserve.check_out}</td>
        <td>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="list_reservation"/>
                <input type="hidden" name="roomId" value="${reserve.room_id}"/>
                <button type="submit" class="btn btn-outline-danger"
                        name="reserveId" value="${reserve.id}">
                    <my:locale value="delete"/>
                </button>
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>
<div class="col-md-4 col-md-push-4">
<div class="form-group">
    <input class="btn btn-outline-secondary" onclick="location.href='controller?command=list_reservation'"
           value="<my:locale value="back"/>">
</div>
</div>
</body>
</html>
