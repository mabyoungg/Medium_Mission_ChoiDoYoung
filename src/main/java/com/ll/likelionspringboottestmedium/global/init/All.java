package com.ll.likelionspringboottestmedium.global.init;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.service.MemberService;
import com.ll.likelionspringboottestmedium.global.app.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.File;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class All {
    private final MemberService memberService;

    @Bean
    @Order(1)
    public ApplicationRunner initAll() {
        return args -> {
            if (memberService.findByUsername("system").isPresent()) return;

            new File(AppConfig.getTempDirPath()).mkdirs();

            memberService.join("system", "1234");
            memberService.join("admin", "1234");
        };
    }
}
