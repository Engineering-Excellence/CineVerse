package kr.co.dbcs.controller;

import kr.co.dbcs.model.MemberVO;
import kr.co.dbcs.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final MemberService memberService;

    @GetMapping(value = "/{path1}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String handlePath1() {
        return "/admin/home";
    }

    @GetMapping(value = "/{path1}/{path2}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String handlePath2() {
        return "/admin/home";
    }

    // 경로에 대한 추상화가 이루어지긴 했는데, 이럴 경우 각 관리 페이지에 접근시
    // 이에 해당하는 데이터들은 어떻게 줄 것인지 고민해볼 것
    // 회원 조회일 경우 /admin/member, 상영 조회일 경우 /admin/show 대충 이렇게 간다고 치면
    // 둘 다 /admin/{path1} 에 부합하지만, 가져올 정보는 서로 다르다

    // 이거를 /member, /show 따로 만들자니 추상화한 의미가 없어지고
    // 접근 후 이거를 Restful API를 통해 Ajax로 가져와야 의미가 있을 듯 하다고 예상
    // 따라서 Restful API로 구성할 지를 의논해야함

    // 이 문제는 관리자 뿐 아니라 회원에서도 데이터를 가져와야 하는 경우에 모두 해당
}
