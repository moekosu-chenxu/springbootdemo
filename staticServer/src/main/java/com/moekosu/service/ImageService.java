package com.moekosu.service;

import com.moekosu.constant.Image;

public interface ImageService {

    void insert(Image img);

    Image getImageByKey(Image img);
}
