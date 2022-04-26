package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.model.Message;
import com.onlinestore.onlinestore.model.Userr;
import com.onlinestore.onlinestore.repository.MessageRepo;
import com.onlinestore.onlinestore.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;
    private final UserService userService;

    @Override
    public List<Message> findMessagesBetween(long loggedUserId, String currentUserId) {
        Userr loggedUser = userService.findById(String.valueOf(loggedUserId));
        Userr currentUser = userService.findById(currentUserId);

        List<Message> allMessages = new ArrayList<>();

        allMessages.addAll(messageRepo.findAllByFromAndTo(loggedUser, currentUser));
        allMessages.addAll(messageRepo.findAllByFromAndTo(currentUser, loggedUser));
        allMessages.sort(Comparator.comparing(Message::getId));

        return allMessages;
    }

    @Override
    public List<Message> findLastMessagesByUser(long loggedUserId) {
        Userr loggedUser = userService.findById(String.valueOf(loggedUserId));

        return messageRepo.findAllByFromOrTo(loggedUser, loggedUser)
                .stream()
                .map(m -> m.getFrom().equals(loggedUser) ? m.getTo() : m.getFrom())
                .distinct()
                .map(c -> findMessagesBetween(loggedUserId, String.valueOf(c.getId())))
                .map(messages -> messages.get(messages.size() - 1))
                .collect(Collectors.toList());
    }

    @Override
    public void sendMessage(String loggedUserId, String currentUserId, String text) {
        if (currentUserId == null || text == null || text.isBlank()) throw new RuntimeException();

        messageRepo.save(
                new Message(
                        userService.findById(loggedUserId),
                        userService.findById(currentUserId),
                        text,
                        LocalDateTime.now()));
    }
}
