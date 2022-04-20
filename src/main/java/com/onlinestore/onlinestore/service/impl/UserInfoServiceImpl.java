package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.dto.UserInfoDto;
import com.onlinestore.onlinestore.model.UserInfo;
import com.onlinestore.onlinestore.repository.UserInfoRepository;
import com.onlinestore.onlinestore.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserInfoDto> getAll() {

        List<UserInfoDto> all = userInfoRepository.findAll().stream().map(dto -> {
            UserInfoDto userInfoDto = modelMapper.map(dto, UserInfoDto.class);
            return userInfoDto;
        }).collect(Collectors.toList());
        return all;
    }

    @Override
    public void addUserInfo(UserInfoDto userInfoDto) {
    UserInfo userInfo =new UserInfo();
    userInfo.setCities(userInfoDto.getCity());
    userInfo.setId(userInfoDto.getId());
    userInfo.setName(userInfoDto.getName());
    userInfo.setNumber(userInfoDto.getNumber());
    userInfo.setSurname(userInfoDto.getSurname());
    userInfoRepository.save(userInfo);


    }
}
