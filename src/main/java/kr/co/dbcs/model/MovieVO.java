package kr.co.dbcs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MovieVO {

    @JsonProperty("movieNm")
    private String movieName;

    @JsonProperty("playStartTime")
    private String playStartTime;

    @JsonProperty("restSeatCnt")
    private int restSeatCnt;

    @JsonProperty("totSeatCnt")
    private int totSeatCnt;
}
