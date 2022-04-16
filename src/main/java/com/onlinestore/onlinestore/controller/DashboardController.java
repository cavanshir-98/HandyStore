package com.onlinestore.onlinestore.controller;


import com.onlinestore.onlinestore.dto.DashBoardDto;
import com.onlinestore.onlinestore.model.DashBoard;
import com.onlinestore.onlinestore.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashBoardService dashBoardService;


    @GetMapping
    public String geTDashBoard(Model model){
        model.addAttribute("name",dashBoardService.getName());

        return "dashboard";
    }
}
