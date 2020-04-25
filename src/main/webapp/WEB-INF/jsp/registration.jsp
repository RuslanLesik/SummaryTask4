<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>registration</title>
</head>
<body style="background-color: lemonchiffon;">
<div class="container" style="margin-top: 25px">
    <div id="login-row" class="row justify-content-center align-items-center">
        <div id="login-column" class="col-md-6">
            <div class="box">
                <div class="float">
                    <form class="form" method="post" action="controller">
                        <input type="hidden" name="command" value="registration"/>
                        <div class="form-group">
                            <label>Login :</label><br>
                            <input type="text" name="login" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Password :</label><br>
                            <input type="password" name="password" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>First name :</label><br>
                            <input type="text" name="first_name" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Last name :</label><br>
                            <input type="text" name="last_name" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Patronymic :</label><br>
                            <input type="text" name="patronymic" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Email :</label><br>
                            <input type="email" name="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Phone number :</label><br>
                            <input type="text" name="phone_number" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-outline-success" name="registration" value="Submit">
                            <input class="btn btn-outline-secondary" onclick="location.href='controller?command=index'"
                                   value="Back">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>