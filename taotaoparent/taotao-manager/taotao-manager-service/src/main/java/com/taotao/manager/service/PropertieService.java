package com.taotao.manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zjj
 * @date 18-11-3 14:56
 */
@Service
public class PropertieService {

    @Value("${IMAGE_BASE_URL}")
    public String IMAGE_BASE_URL;

    @Value("${REPOSITORY_PATH}")
    public String REPOSITORY_PATH;


}
