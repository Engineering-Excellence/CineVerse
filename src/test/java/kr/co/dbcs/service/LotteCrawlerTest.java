package kr.co.dbcs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dbcs.config.RootConfig;
import kr.co.dbcs.config.SecurityConfig;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
class LotteCrawlerTest {

    @Test
    @SneakyThrows
    void testLotteCrawler() {

        JSONObject obj = new JSONObject();
        obj.put("MethodName", "GetPlaySequence");
        obj.put("channelType", "MA");
        obj.put("osVersion", "");
        obj.put("osType", "");
        obj.put("cinemaID", "1|1|9010");
        obj.put("representationMovieCode", "");
        obj.put("playDate", "2023-09-17");

        String url = "https://www.lottecinema.co.kr/LCWS/Ticketing/TicketingData.aspx";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("paramList", obj.toJSONString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        JSONParser parser = new JSONParser();
        JSONObject res = (JSONObject) parser.parse(response.getBody());
        Map<String, Object> ret = new ObjectMapper().readValue(res.get("PlaySeqs").toString(), Map.class);

        log.info("ret: {}", ret);
    }
}
