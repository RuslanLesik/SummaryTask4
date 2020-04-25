<html>
<head>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><my:locale value="add_room.title"/></title>
</head>
<body>
<br>
<div id="login-row" class="row justify-content-center align-items-center">
    <div id="login-column" class="col-md-3">
        <div class="box">
            <div class="float">

                <form class="form" method="post" enctype="multipart/form-data" action="controller">
                    <input type="hidden" name="command" value="add_room"/>
                    <label class="my-1 mr-2" for="selectNumberOfLaces"><my:locale value="add_room.number_of_places"/>
                        :</label>
                    <select class="custom-select my-1 mr-sm-2" id="selectNumberOfLaces" name="numbers_of_places"
                            required>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                        <option value="4">Four</option>
                        <option value="5">Five</option>
                        <option value="6">Six</option>
                    </select>
                    <label class="my-1 mr-2" for="selectRoomClasses"><my:locale value="add_room.room_classes"/>
                        :</label>
                    <select class="custom-select my-1 mr-sm-2" id="selectRoomClasses" name="room_classes" required>
                        <c:forEach items="${requestScope.roomClasses}" var="roomClass">
                            <option value="${roomClass}">${roomClass}</option>
                        </c:forEach>
                    </select>
                    <div class="form-group">
                        <label><my:locale value="add_room.price"/> :</label><br>
                        <input type="number"
                               name="price"
                               class="form-control" required/>
                    </div>
                    <div class="form-group">
                            <label for="comment"><my:locale value="add_room.description"/> :</label>
                            <textarea class="form-control w-100" rows="3" id="comment" name="description" required maxlength="200"
                            placeholder="<my:locale value="add_room.placeholder_description"/>"></textarea>
                    </div>
                    <input type="file" name="file" required>
                    <br>
                    <br>
                    <div class="form-group">
                        <button type="submit" class="btn btn-outline-primary"><my:locale
                                value="add_room.create_room"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
