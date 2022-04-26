package com.onlinestore.onlinestore.service;

import com.onlinestore.onlinestore.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> findMessagesBetween(long loggedUserId, String currentUserId);

    List<Message> findLastMessagesByUser(long loggedUserId);

    void sendMessage(String loggedUserId, String currentUserId, String text);
}