package kr.co.dbcs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MegaboxVO {

    @JsonProperty("movieNm")
    private String movieNm; // 영화제목

    @JsonProperty("brchNm")
    private String brchNm;  // 지점

    @JsonProperty("theabExpoNm")
    private String theabExpoNm; // 상영관

    @JsonProperty("playStartTime")
    private String playStartTime;   // 시작시간

    @JsonProperty("restSeatCnt")
    private int restSeatCnt;    // 잔여좌석

    @JsonProperty("totSeatCnt")
    private int totSeatCnt; // 전체좌석
}
