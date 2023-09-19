<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stat.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<section>
    <div class="stat-container">
        <div class="stat-header">
            <div class="stat-header-wrapper member">
                <div class="stat-title">
                    <img src="${pageContext.request.contextPath}/images/member.png" alt="">
                    회원 수
                </div>
                <div class="stat-count">${memberCount}</div>
            </div>
            <div class="stat-header-wrapper board">
                <div class="stat-title">
                    <img src="${pageContext.request.contextPath}/images/board.png" alt="">
                    게시글 수
                </div>
                <div class="stat-count">${boardCount}</div>
            </div>
            <div class="stat-header-wrapper reply">
                <div class="stat-title">
                    <img src="${pageContext.request.contextPath}/images/reply.png" alt="">
                    댓글 수
                </div>
                <div class="stat-count">${replyCount}</div>
            </div>
        </div>
        <div class="stat-body">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="member-tab">회원 통계</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="board-tab">게시판 통계</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="weekly-tab">주간 통계</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab"
                     tabindex="0">
                    <div class="chart-wrapper">
                        <div id="chart1"></div>
                    </div>
                    <div class="chart-wrapper">
                        <div id="chart2"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    google.charts.load("current", {packages: ["corechart", "bar"]});

    google.charts.setOnLoadCallback(drawGenderChart);
    google.charts.setOnLoadCallback(drawAgeChart);

    function drawGenderChart() {
        let data = google.visualization.arrayToDataTable([
            ['Gender', 'percentage'],
            <c:forEach var="g" items="${genderData}">
            ['${g.get("GENDER")}(${g.get("VALUE")})', ${g.get("VALUE")}],
            </c:forEach>
        ]);

        let options = {
            title: '성별',
            width: 700, // 넓이 옵션
            height: 500
        };

        let chart = new google.visualization.PieChart(document.getElementById('chart1'));
        chart.draw(data, options);
    }

    function drawAgeChart() {
        let data = google.visualization.arrayToDataTable([
            ['Age', 'age'],
            <c:forEach var="a" items="${ageData}">
            ['${a.get("AGE")}(${a.get("VALUE")})', ${a.get("VALUE")}],
            </c:forEach>
        ]);

        let options = {
            title: '나이',
            pieHole: 0.4,
            width: 700, // 넓이 옵션
            height: 500
        };

        let chart = new google.visualization.PieChart(document.getElementById('chart2'));

        chart.draw(data, options);
    }

    function drawBestBoardView() {
        var data = google.visualization.arrayToDataTable([
            ['번호(제목)', '조회수'],
            <c:forEach var="b" items="${boardViewData}">
            ['${b.get("TITLE")}', ${b.get("BOARDVIEW")}],
            </c:forEach>
        ]);

        var options = {
            chart: {
                title: '조회수가 가장 높은 게시글',
            },
            width: 700, // 넓이 옵션
            height: 500,
        };

        var chart = new google.charts.Bar(document.getElementById('chart1'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
    }

    function drawBestBoardReply() {
        var data = google.visualization.arrayToDataTable([
            ['번호(제목)', '댓글'],
            <c:forEach var="b" items="${boardReplyData}">
            ['${b.get("TITLE")}', ${b.get("REPLYCOUNT")}],
            </c:forEach>
        ]);

        var options = {
            chart: {
                title: '댓글이 가장 많은 게시글',
            },
            width: 700, // 넓이 옵션
            height: 500,
        };

        var chart = new google.charts.Bar(document.getElementById('chart2'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
    }

    function drawBoardLastWeek() {
        var data = google.visualization.arrayToDataTable([
            ['일자', '개수'],
            <c:forEach var="b" items="${boardLastWeekData}">
            ['${b.get("WRITEDATE")}'.substring(5), ${b.get("BOARDCOUNT")}],
            </c:forEach>
        ]);

        var options = {
            title: '지난 1주간 작성된 게시글',
            legend: {position: 'bottom'},
            width: 700,
            height: 500
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart1'));

        chart.draw(data, options);
    }

    function drawReplyLastWeek() {
        var data = google.visualization.arrayToDataTable([
            ['일자', '개수'],
            <c:forEach var="b" items="${replyLastWeekData}">
            ['${b.get("WRITEDATE")}'.substring(5), ${b.get("REPLYCOUNT")}],
            </c:forEach>
        ]);

        var options = {
            title: '지난 1주간 작성된 댓글',
            legend: {position: 'bottom'},
            width: 700,
            height: 500
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart2'));

        chart.draw(data, options);
    }

    $("#member-tab").click((e) => {
        $("button").each((i, element) => {
            $(element).removeClass("active");
        })
        $(e.target).addClass("active");
        google.charts.setOnLoadCallback(drawGenderChart);
        google.charts.setOnLoadCallback(drawAgeChart);
    });
    $("#board-tab").click((e) => {
        $("button").each((i, element) => {
            $(element).removeClass("active");
        })
        $(e.target).addClass("active");
        google.charts.setOnLoadCallback(drawBestBoardView);
        google.charts.setOnLoadCallback(drawBestBoardReply);

        // 각각 통계 쿼리 짠다음 데이터 넘겨서 차트 api로 만드는 함수 만들어서 연결
    });
    $("#weekly-tab").click((e) => {
        $("button").each((i, element) => {
            $(element).removeClass("active");
        })
        $(e.target).addClass("active");
        google.charts.setOnLoadCallback(drawBoardLastWeek);
        google.charts.setOnLoadCallback(drawReplyLastWeek);

    });
</script>
