package org.clxmm.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.entity.Teacher;
import org.clxmm.service.edu.entity.vo.TeacherQueryVo;
import org.clxmm.service.edu.fegin.OssFileService;
import org.clxmm.service.edu.mapper.TeacherMapper;
import org.clxmm.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Autowired
    OssFileService ossFileService;

    @Override
    public Page<Teacher> pageSelect(Page<Teacher> page, TeacherQueryVo teacherQueryVo) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");

        if (teacherQueryVo == null) {
            return baseMapper.selectPage(page,queryWrapper);
        }


        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDataEnd = teacherQueryVo.getJoinDateEnd();

        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name",name);
        }

        if (level != null) {
            queryWrapper.eq("level",level);
        }

        if (StringUtils.isNotBlank(joinDateBegin)) {
            queryWrapper.gt("join_date",joinDateBegin);
        }

        if (StringUtils.isNotBlank(joinDataEnd)) {
            queryWrapper.le("join_date",joinDataEnd);
        }

//        String sqlSelect = queryWrapper.getSqlSelect();
        return baseMapper.selectPage(page,queryWrapper);
    }


    @Override
    public List<Map<String, Object>> selectNamesByKey(String key) {

        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.select("name");
        wrapper.likeRight("name",key);

        List<Map<String, Object>> list = baseMapper.selectMaps(wrapper);
        return list;
    }

    @Override
    public boolean removeAvatarBtId(String id) {

        Teacher teacher = baseMapper.selectById(id);
        if ( teacher != null) {
            if (StringUtils.isNotBlank(teacher.getAvatar())) {
                R r = ossFileService.removeFile(teacher.getAvatar());
                return r.getSuccess();
            }
        }

        return false;
    }
}
