<html>
<head>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <title><my:locale value="reservation.title"/></title>
</head>

<body>
<br>
<form action="controller" method="post">
    <input type="hidden" name="command" value="submit_reservation"/>
    <input type="hidden" name="user_id" value="${user.id}"/>
    <input type="hidden" name="price" value="${room.price}"/>
    <table class="table table-hover">
        <thead>
        <tr>
            <th style="width: 10%"><my:locale value="table.view"/></th>
            <th style="width: 10%"><my:locale value="table.room.classes"/></th>
            <th style="width: 15%"><my:locale value="table.number.of.places"/></th>
            <th style="width: 25%"><my:locale value="table.description"/></th>
            <th style="width: 7%"><my:locale value="table.price"/></th>
            <th style="width: 15%"><my:locale value="reservation.check_in"/></th>
            <th style="width: 15%"><my:locale value="reservation.check_out"/></th>
            <th style="width: 15%"><my:locale value="reservation"/></th>
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
            <td><input type="text" id="from" name="check_in" readonly/></td>
            <td><input type="text" id="to" name="check_out" readonly/></td>
            <td>
                <button type="submit" class="btn btn-outline-primary"
                        name="room_id" value="${room.id}">
                    <my:locale value="do.reservation"/>
                </button>
            </td>
        </tr>
        </tbody>
    </table>
</form>
<div class="col-md-4 col-md-push-4">
    <button type="button" class="btn btn-lg btn-danger" data-placement="bottom" data-toggle="popover"
            title="List of booked dates :"
            data-content="
<c:forEach items="${reserveList}" var="oneReserve">
            From : ${oneReserve.check_in} to : ${oneReserve.check_out}&nbsp;

</c:forEach>">
        <my:locale value="reservation.booked_dates"/>
    </button>
</div>
<script>
    $(function() {
    $("#from").datepicker(
        {
            showOn: 'button',
            dateFormat: "yy-mm-dd",
            minDate: new Date(),
            maxDate: '+3M',
            onClose: function (selectedDate) {
                $("#to").datepicker("option", "minDate", selectedDate);
            }
        });
    $("#to").datepicker(
        {
            showOn: 'button',
            dateFormat: "yy-mm-dd",
            maxDate: '+3M',
            onClose: function (selectedDate) {
                $("#from").datepicker("option", "maxDate", selectedDate);
            }
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        $('[data-toggle="popover"]').popover()
    })
</script>
</body>
</html>
