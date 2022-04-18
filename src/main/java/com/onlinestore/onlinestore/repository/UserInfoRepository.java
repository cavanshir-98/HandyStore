package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

}

