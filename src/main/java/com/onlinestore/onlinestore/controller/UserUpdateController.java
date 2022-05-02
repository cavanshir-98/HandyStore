package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.dto.UserInfoDto;
import com.onlinestore.onlinestore.repository.CityRepository;
import com.onlinestore.onlinestore.repository.PostRepository;
import com.onlinestore.onlinestore.repository.UserRepo;
import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import com.onlinestore.onlinestore.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("update")
@RequiredArgsConstructor
public class UserUpdateController {

    private final UserService userService;
    private final CityService cityService;

    private final UserRepo userRepo;
    private final PostRepository postRepository;

    @GetMapping()
    public String getUser(Model model, Authentication au) {
        model.addAttribute("loggedUser", userService.findByIdForImage(getLoggedUser(au).getId()));
        model.addAttribute("cities", cityService.getAll());
        model.addAttribute("user", userRepo.findById(getLoggedUser(au).getId()).orElseThrow());

        return "update-profile";
    }

    @PostMapping
    public RedirectView updateUser(UserInfoDto form,
                                   @RequestParam("image") MultipartFile file,
                                   Model model,
                                   Authentication au) throws Exception {
        userService.updateUser(au, form.getName(), form.getSurname(), form.getCity(), form.getNumber(), file);

        model.addAttribute("process", "profileupdated");
        return new RedirectView("dashboard/1");
    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }


}
