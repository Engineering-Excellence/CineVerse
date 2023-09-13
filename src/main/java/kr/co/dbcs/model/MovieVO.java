package kr.co.dbcs.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MovieVO {

    private String company; // 회사
    private String title;   // 영화제목
    private String branch;  // 지점
    private String theater; // 상영관
    private String startTime;   // 상영시작시간
    private int restSeat;   // 잔여좌석
    private int totSeat;    // 전체좌석

    public MovieVO(MegaboxVO megaboxVO) {
        this.company = "메가박스";
        this.title = megaboxVO.getMovieNm();
        this.branch = megaboxVO.getBrchNm();
        this.theater = megaboxVO.getTheabExpoNm();
        this.startTime = megaboxVO.getPlayStartTime();
        this.restSeat = megaboxVO.getRestSeatCnt();
        this.totSeat = megaboxVO.getTotSeatCnt();
    }
}
