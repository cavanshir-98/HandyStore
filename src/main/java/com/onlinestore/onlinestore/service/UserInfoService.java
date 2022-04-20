package com.onlinestore.onlinestore.service;

import com.onlinestore.onlinestore.dto.UserInfoDto;
import com.onlinestore.onlinestore.model.UserInfo;

import java.util.List;

public interface UserInfoService {

   List<UserInfoDto> getAll();

   void addUserInfo(UserInfoDto userInfoDto);
}
