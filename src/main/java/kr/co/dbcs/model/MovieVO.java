package kr.co.dbcs.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MovieVO {

    private String company; // 회사
    private String title;   // 영화제목
    private String branch;  // 지점
    private String theater; // 상영관
    private String startTime;   // 상영시작시간
    private int restSeat;   // 잔여좌석
    private int totSeat;    // 전체좌석

    public MovieVO(@NonNull MegaboxVO megaboxVO) {
        this.company = "메가박스";
        this.title = megaboxVO.getMovieNm();
        this.branch = megaboxVO.getBrchNm();
        this.theater = megaboxVO.getTheabExpoNm();
        this.startTime = megaboxVO.getPlayStartTime();
        this.restSeat = megaboxVO.getRestSeatCnt();
        this.totSeat = megaboxVO.getTotSeatCnt();
    }

    public MovieVO(@NonNull LotteCinemaVO lotteCinemaVO) {
        this.company = "롯데시네마";
        this.title = lotteCinemaVO.getMovieNameKR();
        this.branch = lotteCinemaVO.getCinemaNameKR();
        this.theater = lotteCinemaVO.getScreenNameKR();
        this.startTime = lotteCinemaVO.getStartTime();
        this.restSeat = lotteCinemaVO.getBookingSeatCount();
        this.totSeat = lotteCinemaVO.getTotalSeatCount();
    }
}
