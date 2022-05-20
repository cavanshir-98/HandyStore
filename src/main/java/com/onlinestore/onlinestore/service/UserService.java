package com.onlinestore.onlinestore.service;

import com.onlinestore.onlinestore.model.City;
import com.onlinestore.onlinestore.model.Userr;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void register(String email, String pass, String passConfirm);

    boolean isInfoFilled(long loggedUserId);

    void fillInfo(Long id, String name, String surname, City city, String number, MultipartFile file) throws Exception;

    void updateUser(Authentication authentication, String name, String surname, City city, String number, MultipartFile file) throws Exception;

    Userr viewUser(String userId, long loggedId);

    Userr findById(String id);

    Userr findByIdForImage(Long id);

    Userr findByEmail(String email);

    Optional<Userr> findUserForLogin(String email);

    Optional<Userr> getById(Long id);

}
