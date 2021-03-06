package com.dogpeach.hello.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final Environment env;

    @GetMapping("/profile")
    public String profile() {

//         ㄴㅇㄹㄴㅇㄹㄴㅇㄹ
        List<String> profiles = Arrays.asList(env.getActiveProfiles()); // 현재 실행 중인 activeProfile을 모두 가져옴.
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }

    @GetMapping("/profile2")
    public String testProfile() {
        return "What The hell";
    }
}
