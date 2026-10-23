package com.picture.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.picture.model.dto.picture.PictureQueryRequest;
import com.picture.model.entity.Picture;
import com.picture.model.vo.picture.PictureVO;

import java.util.List;

/**
* @author lanwu
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2026-10-23 10:48:48
*/
public interface PictureService extends IService<Picture> {


    PictureVO getPictureVO(Picture picture);

    List<PictureVO> getPictureVOList(List<Picture> pictureList);

    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

}
