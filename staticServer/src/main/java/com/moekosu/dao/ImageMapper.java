package com.moekosu.dao;

import com.moekosu.constant.Image;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {

    void insert(Image image);

    Image getImageByKey(Image img);

}
