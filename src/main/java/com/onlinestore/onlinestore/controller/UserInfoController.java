package com.onlinestore.onlinestore.controller;


import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.dto.UserInfoDto;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/info")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final CityService cityService;

    @GetMapping
    public String getAllUserInfo(Model model ){

        model.addAttribute("post",userInfoService.getAll());
        model.addAttribute("city", cityService.getAll());
        return "anket";

    }

    @PostMapping
    public String createUserInfo(@ModelAttribute UserInfoDto userInfoDto) {

        userInfoService.addUserInfo(userInfoDto);

        return "redirect:/info";
    }
}
