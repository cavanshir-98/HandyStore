package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.model.Role;
import com.onlinestore.onlinestore.model.Userr;
import com.onlinestore.onlinestore.repository.RoleRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class RoleService {
  private final RoleRepo roleRepo;

  public Role findByRole(String role) {
    return roleRepo.findRolesByName(role).get(0);
  }

  public void addRoleToUser(Userr user, String roleName) {
    Role role = findByRole(roleName);
    role.getUsers().add(user);
    roleRepo.save(role);
    log.info("Role added succesfully");
  }
}
