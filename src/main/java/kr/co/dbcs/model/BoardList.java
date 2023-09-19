package kr.co.dbcs.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class BoardList {

    private final List<BoardVO> list = new ArrayList<>();
    private int pageSize;   // 한 페이지 당 보여줄 일반글 개수
    private int totalCount;
    private int currentPage = 1;
    private int totalPage;
    private int startNo;
    private int endNo;
    private int startPage;
    private int endPage;
    private final int BLOCK = 10;   // 페이지 네비게이션에서 한 번에 표시되는 페이지 수

    public void initBoardList(int pageSize, int totalCount, int currentPage) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        calculator();
    }

    private void calculator() {
        totalPage = (totalCount - 1) / pageSize + 1;
        currentPage = Math.min(currentPage, totalPage);
        startNo = (currentPage - 1) * pageSize + 1;
        endNo = startNo + pageSize - 1;
        endNo = Math.min(endNo, totalCount);
        startPage = (currentPage - 1) / BLOCK * BLOCK + 1;
        endPage = startPage + 9;
        endPage = Math.min(endPage, totalPage);
    }
}
