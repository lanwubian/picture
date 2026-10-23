package com.picture.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.picture.model.dto.picture.PictureQueryRequest;
import com.picture.model.entity.Picture;
import com.picture.model.vo.picture.PictureVO;
import com.picture.service.PictureService;
import com.picture.mapper.PictureMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author lanwu
* @description 针对表【picture(图片)】的数据库操作Service实现
* @createDate 2026-10-23 10:48:48
*/
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService{

    @Override
    public PictureVO getPictureVO(Picture picture) {
        if (picture == null) {
            return null;
        }
        PictureVO pictureVO = new PictureVO();
        BeanUtils.copyProperties(picture,pictureVO);
        return pictureVO;
    }

    @Override
    public List<PictureVO> getPictureVOList(List<Picture> pictureList) {
        if (CollUtil.isEmpty(pictureList)) {
            return new ArrayList<>();
        }
        return pictureList.stream()
                .map(this::getPictureVO)
                .collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest) {
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        if (pictureQueryRequest == null) {
            return queryWrapper;
        }
        Long id = pictureQueryRequest.getId();
        String name = pictureQueryRequest.getName();
        String introduction = pictureQueryRequest.getIntroduction();
        String category = pictureQueryRequest.getCategory();
        List<String> tags = pictureQueryRequest.getTags();
        Long picSize = pictureQueryRequest.getPicSize();
        Integer picWidth = pictureQueryRequest.getPicWidth();
        Integer picHeight = pictureQueryRequest.getPicHeight();
        Double picScale = pictureQueryRequest.getPicScale();
        String picFormat = pictureQueryRequest.getPicFormat();
        String searchText = pictureQueryRequest.getSearchText();
        Long userId = pictureQueryRequest.getUserId();
        String sortField = pictureQueryRequest.getSortField();
        String sortOrder = pictureQueryRequest.getSortOrder();
        // 从多字段中检索
        if (StrUtil.isNotBlank(searchText)){
            // 需要拼接查询条件
            queryWrapper.and(qw -> qw.like("name",searchText)
                    .or()
                    .like("introduction",searchText)
            );
        }
        queryWrapper.eq(ObjUtil.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjUtil.isNotEmpty(userId),"userId",userId);
        queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        queryWrapper.like(StrUtil.isNotBlank(introduction),"introduction",introduction);
        queryWrapper.like(StrUtil.isNotBlank(picFormat),"picFormat",picFormat);
        queryWrapper.eq(StrUtil.isNotBlank(category),"category",category);
        queryWrapper.eq(ObjUtil.isNotEmpty(picWidth),"picWidth",picWidth);
        queryWrapper.eq(ObjUtil.isNotEmpty(picHeight),"picHeight",picHeight);
        queryWrapper.eq(ObjUtil.isNotEmpty(picSize),"picSize",picSize);
        queryWrapper.eq(ObjUtil.isNotEmpty(picScale),"picScale",picScale);

        // JSON 数组查询
        if (CollUtil.isNotEmpty(tags)){
            for (String tag : tags) {
                queryWrapper.like("tags","\" + tag + \"");
            }
        }
        // 排序
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField),sortOrder.equals("ascend"),sortField);
        return queryWrapper;
    }
}




