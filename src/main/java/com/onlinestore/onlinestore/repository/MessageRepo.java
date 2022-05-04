package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.Message;
import com.onlinestore.onlinestore.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    List<Message> findAllByFromAndTo(Userr sender, Userr receiver);

    List<Message> findAllByFromOrTo(Userr sender, Userr receiver);

}
