package com.onlinestore.onlinestore.controller;


import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.dto.UserInfoDto;
import com.onlinestore.onlinestore.model.Userr;
import com.onlinestore.onlinestore.repository.UserRepo;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.UserInfoService;
import com.onlinestore.onlinestore.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@RequestMapping("/info")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final CityService cityService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;

    @GetMapping
    public String getAllUserInfo( ){

        return "anket";

    }

    @PostMapping
    public String createUserInfo( @ModelAttribute @AuthenticationPrincipal Userr userInfoDto) {


        userService.fillInfo(userInfoDto.getEmail(),userInfoDto.getId(),userInfoDto.getName(),userInfoDto.getSurname(),userInfoDto.getCity(),
                userInfoDto.getPhone(),userInfoDto.getImage());

        return "redirect:/dashboard";
    }
}
