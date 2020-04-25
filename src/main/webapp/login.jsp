<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="http://ua/nure/lesik/SummaryTask4" %>
<html>
<link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
<title><my:locale value="login.title"/></title>
<body style="background-color: lemonchiffon">
<div class="container" style="margin-top: 25px">
    <div id="login-row" class="row justify-content-center align-items-center">
        <div id="login-column" class="col-md-6">
            <div class="box">
                <div class="float">
                    <form class="form" method="post" action="controller">
                        <input type="hidden" name="command" value="login"/>
                        <div class="form-group">
                            <label><my:locale value="login"/> :</label><br>
                            <input type="text" name="login" class="form-control" required
                                   placeholder="<my:locale value="login.placeholder.login"/>"/>
                        </div>
                        <div class="form-group">
                            <label><my:locale value="password"/> :</label><br>
                            <input type="password" name="password" class="form-control" required
                                   placeholder="<my:locale value="login.placeholder.password"/>"/>
                        </div>
                        <div class="form-group">
                            <label><my:locale value="login.captcha"/> :</label><br>
                            <c:if test="${not empty requestScope.errorMessage}">
                                <p class="text-danger">${requestScope.errorMessage}</p>
                            </c:if>
                            <input type="text" name="captchaCode"
                                   placeholder="<my:locale value="login.placeholder.captcha"/>" required/>
                            <img src="captcha.jsp">
                        </div>
                        <div class="form-group">
                            <input type="submit" name="login" class="btn btn-outline-success"
                                   value="<my:locale value="login"/>">
                            <input class="btn btn-outline-secondary" onclick="location.href='controller?command=index'"
                                   value="<my:locale value="back"/>">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>