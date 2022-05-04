package com.onlinestore.onlinestore.tool;

import com.onlinestore.onlinestore.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
public class ValidationTool {

    private final UserRepo userRepo;

    public boolean isEmailUnique(String email) {
        return userRepo.findUserrByEmail(email.toLowerCase())
                .equals(Optional.empty());
    }

    public boolean passMatches(String pass, String passConfirm) {
        return pass.equals(passConfirm);
    }

    public boolean isParsableToLong(String source) {
        try {
            Long.parseLong(source);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.warn("Value could not be parsed to Long: from ValidationTool.isParsableToLong()");
            return false;
        }
    }

}