package com.onlinestore.onlinestore.service.impl;


import com.onlinestore.onlinestore.exception.user.UserNotFoundEx;
import com.onlinestore.onlinestore.model.Userr;
import com.onlinestore.onlinestore.repository.UserRepo;
import com.onlinestore.onlinestore.tool.FileTool;
import com.onlinestore.onlinestore.tool.ValidationTool;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
public class UserService {
    private final ValidationTool validationTool;
    private final FileTool fileTool;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
//    private final CloudinaryService cloudinaryService;

    public void register(String email, String pass, String passConfirm) {
        if (email == null || pass == null || passConfirm == null
                || email.isBlank() || pass.isBlank() || passConfirm.isBlank()) ;
        if (!validationTool.isEmailUnique(email)) ;
        if (!validationTool.passMatches(pass, passConfirm)) ;

        Userr user = new Userr(email.toLowerCase(), passwordEncoder.encode(pass),LocalDateTime.now(),true);
        user.setName("Name");
        user.setSurname("Surname");
        user.setImage(null);
        user.setCity("Sheki");
        userRepo.save(user);

        roleService.addRoleToUser(user, "USER");
        log.info("User registered successfully");
    }

    public void login(String login, String pass) {
        if (!validationTool.isLoginCorrect(login, pass))
            ;
    }

//    public boolean isInfoFilled(long loggedUserId) {
//        Userr user = findById(String.valueOf(loggedUserId));
//        return user.getCity() != null && user.getName() != null &&
//                user.getSurname() != null && !user.getSurname().isBlank();
//    }


    public void fillInfo(String email,Long id, String name, String surname, String city, String number, MultipartFile file) {

        Userr userrByEmail = userRepo.findByEmail(email).orElseThrow(RuntimeException::new);
        userrByEmail.setName(name);
        userrByEmail.setSurname(surname);
        userrByEmail.setCity(city);
        userrByEmail.setImage(file);
        userrByEmail.setPhone(number);
        userRepo.save(userrByEmail);
        log.info("User info filled successfully");
    }


//    public void updateUser(String id, String name, String surname, String city, String number, MultipartFile file) {
//        if (name == null || surname == null || city == null
//                || number == null || id.isBlank() || name.isBlank()
//                || surname.isBlank() || city.isBlank() || number.isBlank()
//                || file.isEmpty()
//        ) ;
//
//        if (validationTool.isPhoneValid(number)) ;
//
////        String image = cloudinaryService.uploadFile(file);
//
//        Userr user = findById(id);
//        user.setName(name);
//        user.setSurname(surname);
//        user.setCity(city);
//        user.setPhone(number);
////        user.setImage(image);
//        userRepo.save(user);
//        log.info("User profile updated successfully");
//    }

    public void updatePassword(String email, String pass, String conPass) {
        if (email == null || pass == null || conPass == null
                || email.isBlank() || pass.isBlank() || conPass.isBlank()) ;

        if (!pass.equals(conPass)) ;

        Userr user = findByEmail(email);
        user.setPassword(passwordEncoder.encode(pass));
        userRepo.save(user);
    }

    public Userr findById(Long id) {
//        if (!validationTool.isParsableToLong(String.valueOf(id))) throw new RuntimeException();
        return userRepo.findById(id).orElseThrow(UserNotFoundEx::new);
    }

    public Userr findByEmail(String email) {
        return userRepo.findUserrByEmail(email).orElseThrow(RuntimeException::new);
    }

    public Optional<Userr> findUserForLogin(String email) {
        return userRepo.findUserrByEmail(email);
    }

//    public Userr viewUser(String userId, long loggedId) {
//        Userr user = findById(userId);
//        if (user.getId() == loggedId) throw new RuntimeException();
//        return user;
//    }

    public boolean isUserExistByEmail(String mail) {
        return userRepo.findUserrByEmail(mail).isPresent();
    }


}
