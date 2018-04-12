package com.moekosu.dao;

import com.moekosu.constant.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

    void insert();

    Image getImageByKey(Image img);

}
