<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stat.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    google.charts.setOnLoadCallback(drawChart2);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Gender', 'percentage'],
            ['Male',     60],
            ['Female',      40],
        ]);

        var options = {
            title: '성별',
            width: 700, // 넓이 옵션
            height: 500
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
    }

    function drawChart2() {

        var data = google.visualization.arrayToDataTable([
            ['Age', 'age'],
            ['10', 11],
            ['20', 2],
            ['30', 2],
            ['40', 2],
            ['50', 7]
        ]);

        var options = {
            title: '나이',
            pieHole: 0.4,
            width: 700, // 넓이 옵션
            height: 500
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
    }
</script>
<section>
    <div class="stat-container">
        <div class="stat-header">
            <div class="stat-header-wrapper member">
                <div class="stat-title">
                    <img src="${pageContext.request.contextPath}/images/member.png" alt="">
                    회원 수
                </div>
                <div class="stat-count">1234125</div>
            </div>
            <div class="stat-header-wrapper board">
                <div class="stat-title">
                    <img src="${pageContext.request.contextPath}/images/board.png" alt="">
                    게시글 수
                </div>
                <div class="stat-count">123124136135</div>
            </div>
            <div class="stat-header-wrapper reply">
                <div class="stat-title">
                    <img src="${pageContext.request.contextPath}/images/reply.png" alt="">
                    댓글 수
                </div>
                <div class="stat-count">123135325234</div>
            </div>
        </div>
        <div class="stat-body">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">회원 통계</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">주간 통계</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="contact-tab" data-bs-toggle="tab" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">게시판 통계</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
                    <div class="chart-wrapper">
                        <div id="donutchart"></div>
                    </div>
                    <div class="chart-wrapper">
                        <div id="piechart"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>