package org.clxmm.service.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.common.base.result.R;
import org.clxmm.service.cms.entity.Ad;
import org.clxmm.service.cms.entity.vo.AdVo;
import org.clxmm.service.cms.fegin.OssFileService;
import org.clxmm.service.cms.mapper.AdMapper;
import org.clxmm.service.cms.service.AdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2021-01-09
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {
    @Autowired
    OssFileService ossFileService;

    @Override
    public IPage<AdVo> selectPage(Long page, Long limit) {
        QueryWrapper<AdVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("a.type_id", "a.sort");
        Page<AdVo> pageParam = new Page(page, limit);

        List<AdVo> records = baseMapper.selectPageByQueryWrapper(pageParam, queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }

    @Override
    public boolean removeAdImageById(String id) {
        Ad ad = baseMapper.selectById(id);
        if (ad != null && StringUtils.isNotBlank(ad.getImageUrl())) {
            R r = ossFileService.removeFile(ad.getImageUrl());
            return r.getSuccess();
        }
        return false;
    }

    @Cacheable(value = "index",key = "'selectByAdTypeId'")
    @Override
    public List<Ad> selectByAdTypeId(String adTypeId) {

        QueryWrapper<Ad> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort", "id");
        queryWrapper.eq("type_id", adTypeId);
        return baseMapper.selectList(queryWrapper);
    }
}
