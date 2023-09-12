package kr.co.dbcs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MovieVO {

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
