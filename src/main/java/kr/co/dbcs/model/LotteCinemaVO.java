package kr.co.dbcs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LotteCinemaVO {

    @JsonProperty("MovieNameKR")
    private String movieNameKR; // 영화제목

    @JsonProperty("CinemaNameKR")
    private String cinemaNameKR;  // 지점

    @JsonProperty("ScreenNameKR")
    private String screenNameKR; // 상영관

    @JsonProperty("StartTime")
    private String startTime;   // 시작시간

    @JsonProperty("BookingSeatCount")
    private int bookingSeatCount;    // 잔여좌석

    @JsonProperty("TotalSeatCount")
    private int totalSeatCount; // 전체좌석
}
