<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="vip" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="my" uri="http://ua/nure/lesik/SummaryTask4" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="webjars/jquery/3.4.1/dist/jquery.min.js"></script>
    <script src="webjars/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title><my:locale value="index.title"/></title>
</head>
<body style="background-color: lemonchiffon;">
<div class="card text-center">
    <div class="card-header">
        <my:locale value="index.welcome"/>
    </div>
    <div class="card-body" style="background-color: lemonchiffon;">
        <h5 class="card-title"><my:locale value="index.login"/></h5>
        <p class="card-text"><my:locale value="index.text"/></p>
        <a href="controller?command=login" class="btn btn-outline-success"><my:locale value="login"/></a>
        <a href="controller?command=registration" class="btn btn-outline-info"><my:locale value="registration"/></a>
    </div>
</div>
<div class="card text-center" style="background-color: lemonchiffon;">
    <div class="container mt-3">
        <div id="myCarousel" class="carousel slide">
            <!-- Indicators -->
            <ul class="carousel-indicators">
                <li class="item1 active"></li>
                <li class="item2"></li>
                <li class="item3"></li>
                <li class="item4"></li>
            </ul>
            <!-- The slideshow -->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="${pageContext.request.contextPath}/images/index_1.jpg" width="1100" height="450">
                </div>
                <div class="carousel-item">
                    <img src="${pageContext.request.contextPath}/images/index_2.jpg" width="1100" height="450">
                </div>
                <div class="carousel-item">
                    <img src="${pageContext.request.contextPath}/images/index_3.jpg" width="1100" height="450">
                </div>
                <div class="carousel-item">
                    <img src="${pageContext.request.contextPath}/images/index_4.jpg" width="1100" height="450">
                </div>
            </div>
            <!-- Left and right controls -->
            <a class="carousel-control-prev" href="#myCarousel">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#myCarousel">
                <span class="carousel-control-next-icon"></span>
            </a>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        // Activate Carousel with a specified interval
        $("#myCarousel").carousel({interval: 2000, pause: "hover", keyboard: true});

        // Enable Carousel Indicators
        $(".item1").click(function () {
            $("#myCarousel").carousel(0);
        });
        $(".item2").click(function () {
            $("#myCarousel").carousel(1);
        });
        $(".item3").click(function () {
            $("#myCarousel").carousel(2);
        });
        $(".item4").click(function () {
            $("#myCarousel").carousel(3);
        });

        // Enable Carousel Controls
        $(".carousel-control-prev").click(function () {
            $("#myCarousel").carousel("prev");
        });
        $(".carousel-control-next").click(function () {
            $("#myCarousel").carousel("next");
        });
    });
</script>
<div class="card text-center">
    <div class="card-footer text-muted fixed-bottom">
        <vip:copyright/>
    </div>
</div>
</body>
</html>
