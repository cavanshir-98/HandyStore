package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    @Query("select r from Role r where r.name = ?1")
    List<Role> findRolesByName(String role);
}
