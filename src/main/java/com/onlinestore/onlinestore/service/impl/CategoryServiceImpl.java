package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.dto.CategoryDto;
import com.onlinestore.onlinestore.model.Category;
import com.onlinestore.onlinestore.repository.CategoryRepo;
import com.onlinestore.onlinestore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAll() {

        List<CategoryDto> category = categoryRepo.findAll().stream().map(category1 -> {
            CategoryDto map = modelMapper.map(category1, CategoryDto.class);
            return map;
        }).collect(Collectors.toList());
        return category;

    }

}
