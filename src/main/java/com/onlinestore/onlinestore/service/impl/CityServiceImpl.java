package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.dto.CityDto;
import com.onlinestore.onlinestore.model.City;
import com.onlinestore.onlinestore.model.Post;
import com.onlinestore.onlinestore.repository.CityRepository;
import com.onlinestore.onlinestore.repository.PostRepository;
import com.onlinestore.onlinestore.service.CityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    @Override
    public List<CityDto> getAll() {

        List<CityDto> cityDto = cityRepository.findAll().stream().map(city -> {
            CityDto map = modelMapper.map(city, CityDto.class);
            return map;
        }).collect(Collectors.toList());
        return cityDto;
    }

}
