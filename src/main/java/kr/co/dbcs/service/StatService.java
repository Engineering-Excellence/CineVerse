package kr.co.dbcs.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StatService {

    public int getMemberCount();

    public int getBoardCount();

    public int getReplyCount();

    List<Map<String, BigDecimal>> getGenderData();

    List<Map<String, Object>> getAgeData();

    List<Map<String, Object>> getBoardViewData();

    List<Map<String, Object>> getBoardReplyData();

    List<Map<String, Object>> getBoardLastWeekData();

    List<Map<String, Object>> getReplyLastWeekData();
}
