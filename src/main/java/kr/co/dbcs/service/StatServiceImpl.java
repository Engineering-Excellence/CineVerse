package kr.co.dbcs.service;

import kr.co.dbcs.mapper.StatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatServiceImpl implements StatService {
    private final StatMapper statMapper;

    @Override
    public int getMemberCount() {
        return statMapper.getMemberCount();
    }

    @Override
    public int getBoardCount() {
        return statMapper.getBoardCount();
    }

    @Override
    public int getReplyCount() {
        return statMapper.getReplyCount();
    }

    @Override
    public List<Map<String, BigDecimal>> getGenderData() {
        return statMapper.getGenderData();
    }

    @Override
    public List<Map<String, Object>> getAgeData() {
        List<Map<String, BigDecimal>> data = statMapper.getAgeData();

        Map<String, Integer> map = new HashMap<>();
        for (Map<String, BigDecimal> m : data) {
            if (m.get("AGE").intValue() <= 1) {
                if (!map.containsKey("10대 이하")) map.put("10대 이하", 0);
                map.put("10대 이하", map.get("10대 이하") + m.get("VALUE").intValue());
            } else if (m.get("AGE").intValue() == 2)
                map.put("20대", m.get("VALUE").intValue());
            else if (m.get("AGE").intValue() == 3)
                map.put("30대", m.get("VALUE").intValue());
            else if (m.get("AGE").intValue() == 4)
                map.put("40대", m.get("VALUE").intValue());
            else if (m.get("AGE").intValue() >= 5) {
                if (!map.containsKey("50대 이상")) map.put("50대 이상", 0);
                map.put("50대 이상", map.get("50대 이상") + m.get("VALUE").intValue());
            }
        }

        List<Map<String, Object>> ret = new ArrayList<>();

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            Map<String, Object> m = new HashMap<>();
            m.put("AGE", e.getKey());
            m.put("VALUE", e.getValue());
            ret.add(m);
        }
        ret.sort((m1, m2) -> {
            return m1.get("AGE").toString().compareTo(m2.get("AGE").toString());
        });

        log.info("{}", ret);
        return ret;
    }

    @Override
    public List<Map<String, Object>> getBoardViewData() {
        return statMapper.getBoardViewData();
    }

    @Override
    public List<Map<String, Object>> getBoardReplyData() {
        return statMapper.getBoardReplyData();
    }

    @Override
    public List<Map<String, Object>> getBoardLastWeekData() {
        return statMapper.getBoardLastWeekData();
    }

    @Override
    public List<Map<String, Object>> getReplyLastWeekData() {
        return statMapper.getReplyLastWeekData();
    }
}
