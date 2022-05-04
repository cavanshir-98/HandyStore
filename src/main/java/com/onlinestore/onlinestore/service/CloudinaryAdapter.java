package com.onlinestore.onlinestore.service;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Map;

@Repository
public interface CloudinaryAdapter {

    Map uploadImageCloudinary(File file) throws Exception;
}
