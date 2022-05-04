package com.onlinestore.onlinestore.controller;


import com.onlinestore.onlinestore.dto.UserInfoDto;
import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/info")
@RequiredArgsConstructor
public class UserInfoController {

    private final CityService cityService;
    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public String getAllUserInfo(Model model, Authentication authentication) {

        if (userServiceImpl.isInfoFilled(getLoggedUser(authentication).getId())) {
            return "redirect:/dashboard/1";
        }
        model.addAttribute("city", cityService.getAll());

        return "anket";

    }

    @PostMapping
    public String createUserInfo(@ModelAttribute UserInfoDto formInfo,
                                 @RequestParam("image") MultipartFile file,
                                 Authentication au) throws Exception {

        userServiceImpl.fillInfo(getLoggedUser(au).getId(), formInfo.getName(), formInfo.getSurname(), formInfo.getCity(),
                formInfo.getNumber(), file);

        return "redirect:/dashboard";


    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }
}
