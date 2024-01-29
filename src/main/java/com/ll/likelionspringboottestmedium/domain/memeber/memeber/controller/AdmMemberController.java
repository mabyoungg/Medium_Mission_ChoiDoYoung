package com.ll.likelionspringboottestmedium.domain.memeber.memeber.controller;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.domain.memeber.memeber.service.MemberService;
import com.ll.likelionspringboottestmedium.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/adm/member")
@RequiredArgsConstructor
public class AdmMemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/list")
    public String showList(
            @RequestParam(defaultValue = "") String kw,
            @RequestParam(defaultValue = "1") int page
    ) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(sorts));

        Page<Member> itemPage = memberService.search(kw, pageable);
        rq.attr("itemPage", itemPage);
        rq.attr("page", page);

        return "domain/member/member/adm/list";
    }
}
