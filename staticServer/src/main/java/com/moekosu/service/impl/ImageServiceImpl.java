package com.moekosu.service.impl;

import com.moekosu.constant.Image;
import com.moekosu.dao.ImageMapper;
import com.moekosu.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public void insert()
    {
        imageMapper.insert();
    }

    @Override
    public Image getImageByKey(Image img)
    {
        return imageMapper.getImageByKey(img);
    }

}
