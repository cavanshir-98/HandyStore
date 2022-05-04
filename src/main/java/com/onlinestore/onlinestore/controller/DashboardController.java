package com.onlinestore.onlinestore.controller;


import com.onlinestore.onlinestore.model.Post;
import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import com.onlinestore.onlinestore.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {


    private final UserServiceImpl userServiceImpl;
    private final PostService postService;
    private final CityService cityService;


    @RequestMapping()
    public RedirectView redirectView() {
        return new RedirectView("/dashboard/1");
    }

    @GetMapping("/{currentPage}")
    public String dashboard(Model model, Authentication au,
                            @PathVariable("currentPage") int currentPageOp
    ) {

        Page<Post> page = postService.findPage(currentPageOp);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Post> posts = page.getContent();

        model.addAttribute("currentPage", currentPageOp);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("posts", posts);
        model.addAttribute("user", userServiceImpl.findByEmail(getLoggedUser(au).getUsername()));
        model.addAttribute("city", cityService.getAll());
        model.addAttribute("loggedUser", userServiceImpl.findByEmail(getLoggedUser(au).getUsername()));

        return "dashboard";
    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }

}

