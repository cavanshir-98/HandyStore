package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.dto.ChatDto;
import com.onlinestore.onlinestore.model.Message;
import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.MessageService;
import com.onlinestore.onlinestore.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    private final UserServiceImpl userServiceImpl;

    @GetMapping()
    public String getLastMessageByUser(Model model, Authentication au) {
        model.addAttribute("loggedUser", userServiceImpl.findByEmail(getLoggedUser(au).getUsername()));
        model.addAttribute("connections", messageService.findLastMessagesByUser(getLoggedUser(au).getId()));
        return "chat-main";
    }

    @GetMapping("/{id}")
    public String findMessagesBetween(@PathVariable String id, Model model, Authentication au) {
        long loggedUserId = getLoggedUser(au).getId();

        List<Message> m = messageService.findMessagesBetween(loggedUserId, id);

        model.addAttribute("loggedUser", userServiceImpl.findByEmail(getLoggedUser(au).getUsername()));
        model.addAttribute("currentUser", userServiceImpl.findById(id));
        model.addAttribute("messages", m.subList(Math.max(m.size() - 5, 0), m.size()));
        return "chat-private";
    }

    @GetMapping("/all/{id}")
    public String seeAllMessages(@PathVariable String id, Model model, Authentication au) {
        long loggedUserId = getLoggedUser(au).getId();

        model.addAttribute("loggedUser", userServiceImpl.findByEmail(getLoggedUser(au).getUsername()));
        model.addAttribute("currentUser", userServiceImpl.findById(id));
        model.addAttribute("messages", messageService.findMessagesBetween(loggedUserId, id));
        return "chat-private";
    }

    @PostMapping("/{id}")
    public RedirectView sendMessage(ChatDto form, @PathVariable String id, Authentication au) {
        messageService.sendMessage(String.valueOf(getLoggedUser(au).getId()), id, form.getMessage());
        return new RedirectView("{id}");
    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }
}
