package com.onlinestore.onlinestore.service;

import com.onlinestore.onlinestore.dto.CityDto;
import com.onlinestore.onlinestore.model.City;
import com.onlinestore.onlinestore.model.Post;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<CityDto> getAll();

}
