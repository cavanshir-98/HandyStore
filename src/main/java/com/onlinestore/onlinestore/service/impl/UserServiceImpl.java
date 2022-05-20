package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.model.City;
import com.onlinestore.onlinestore.model.Userr;
import com.onlinestore.onlinestore.repository.UserRepo;
import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.CloudinaryAdapter;
import com.onlinestore.onlinestore.service.UserService;
import com.onlinestore.onlinestore.tool.ValidationTool;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final ValidationTool validationTool;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final CloudinaryAdapter cloudinaryAdapter;

    public void register(String email, String pass, String passConfirm) {
        if (email == null || pass == null || passConfirm == null
                || email.isBlank() || pass.isBlank() || passConfirm.isBlank()) ;
        if (!validationTool.isEmailUnique(email)) ;
        if (!validationTool.passMatches(pass, passConfirm)) ;

        Userr user = new Userr(email.toLowerCase(), passwordEncoder.encode(pass), LocalDateTime.now(), true);
        user.setName(null);
        user.setSurname(null);
        user.setImage(null);
        user.setCity(null);
        userRepo.save(user);

        roleService.addRoleToUser(user, "USER");
        log.info("User registered successfully");
    }

    public boolean isInfoFilled(long loggedUserId) {
        Userr user = userRepo.findById(loggedUserId).orElseThrow(RuntimeException::new);
        return user.getName() != null &&
                user.getSurname() != null;
    }

    public void fillInfo(Long id, String name, String surname, City city, String number, MultipartFile file) throws Exception {

        Userr userrByEmail = userRepo.findById(id).orElseThrow(RuntimeException::new);
        userrByEmail.setName(name);
        userrByEmail.setSurname(surname);
        userrByEmail.setCity(city);
        userrByEmail.setPhone(number);

        MultipartFile multipartFile = file;
        File file1 = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        Map<String, String> uploadImage = cloudinaryAdapter.uploadImageCloudinary(file1);
        file1.delete();

        String url = uploadImage.get("url");
        userrByEmail.setImage(url);
        userRepo.save(userrByEmail);
        log.info("User info filled successfully");
    }


    public void updateUser(Authentication authentication, String name, String surname, City city, String number, MultipartFile file) throws Exception {
        if (name == null || surname == null || city == null
                || number == null || name.isBlank()
                || surname.isBlank() || city == null || number.isBlank()
                || file.isEmpty()
        ) ;
        Userr user = userRepo.getById(getLoggedUser(authentication).getId());
        user.setName(name);
        user.setSurname(surname);
        user.setCity(city);
        user.setPhone(number);
        MultipartFile multipartFile = file;
        File file1 = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        Map<String, String> uploadImage = cloudinaryAdapter.uploadImageCloudinary(file1);
        file1.delete();

        String url = uploadImage.get("url");
        user.setImage(url);
        userRepo.save(user);
        log.info("User profile updated successfully");
    }

    public Userr viewUser(String userId, long loggedId) {
        Userr user = findById(userId);
        if (user.getId() == loggedId) throw new RuntimeException("Same Profile");
        return user;
    }

    public Userr findById(String id) {
        if (!validationTool.isParsableToLong(id)) throw new RuntimeException();
        return userRepo.findById(Long.parseLong(id)).orElseThrow(RuntimeException::new);
    }

    public Userr findByIdForImage(Long id) {
        return userRepo.findById(id).orElseThrow();

    }

    public Userr findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    public Optional<Userr> findUserForLogin(String email) {
        return userRepo.findUserrByEmail(email);
    }

    public Optional<Userr> getById(Long id) {
        return userRepo.findById(id);
    }

    public List<Userr> findAll() {
        return userRepo.findAll();
    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }

}
