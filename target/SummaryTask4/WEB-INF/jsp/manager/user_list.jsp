<html>
<head>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <title><my:locale value="user_list.title"/></title>
</head>
<body>
<table class="table table-hover" id="myTable">
    <thead>
    <tr>
        <th scope="col"><my:locale value="user_list.login"/></th>
        <th scope="col"><my:locale value="user_list.first_name"/></th>
        <th scope="col"><my:locale value="user_list.last_name"/></th>
        <th scope="col"><my:locale value="user_list.patronymic"/></th>
        <th scope="col"><my:locale value="user_list.email"/></th>
        <th scope="col"><my:locale value="user_list.phone_number"/></th>
        <th scope="col"><my:locale value="do.delete"/></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="oneUser">
        <tr class="success">
            <td scope="row">
                    ${oneUser.login}
            </td>
            <td>${oneUser.first_name}</td>
            <td>${oneUser.last_name}</td>
            <td>${oneUser.patronymic}</td>
            <td>${oneUser.email}</td>
            <td>${oneUser.phone_number}</td>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="user_list"/>
                    <button type="submit" class="btn btn-outline-danger"
                           name="login" value="${oneUser.login}">
                        <my:locale value="delete"/>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
