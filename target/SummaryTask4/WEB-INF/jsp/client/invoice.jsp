<html>
<head>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <title><my:locale value="invoice.title"/></title>
</head>
<body>
<br>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col"><my:locale value="invoice.reckoning"/></th>
        <th scope="col"><my:locale value="invoice.status"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${invoiceList}" var="oneInvoice">
        <tr class="success">

            <td><my:locale value="invoice.bill"/> ${oneInvoice.days_count} <my:locale value="invoice.days"/> ${oneInvoice.reckoning}</td>
            <c:if test="${oneInvoice.active != true}">
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="invoice"/>
                        <input type="hidden" name="reserve_id" value="${oneInvoice.reserve_id}"/>
                        <button type="submit" class="btn btn-outline-warning btn-lg btn-block"
                                name="invoice_id" value="${oneInvoice.id}">
                            <my:locale value="invoice.pay"/>
                        </button>
                    </form>
                </td>
            </c:if>
            <c:if test="${oneInvoice.active == true}">
                <td>
                    <div class="alert alert-success text-center" role="alert">
                        <my:locale value="invoice.paid"/>
                    </div>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
