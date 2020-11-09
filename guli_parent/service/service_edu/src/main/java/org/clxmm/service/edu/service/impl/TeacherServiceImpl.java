package org.clxmm.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.clxmm.service.edu.entity.Teacher;
import org.clxmm.service.edu.entity.vo.TeacherQueryVo;
import org.clxmm.service.edu.mapper.TeacherMapper;
import org.clxmm.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<Teacher> pageSelect(Page<Teacher> page, TeacherQueryVo teacherQueryVo) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByAsc("sort");

        if (teacherQueryVo == null) {
            return baseMapper.selectPage(page,queryWrapper);
        }


        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDataEnd = teacherQueryVo.getJoinDataEnd();

        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name",name);
        }

        if (level != null) {
            queryWrapper.eq("level",level);
        }

        if (StringUtils.isNotBlank(joinDateBegin)) {
            queryWrapper.gt("join_date",joinDataEnd);
        }

        if (StringUtils.isNotBlank(joinDataEnd)) {
            queryWrapper.le("join_date",joinDataEnd);
        }

        String sqlSelect = queryWrapper.getSqlSelect();

        return baseMapper.selectPage(page,queryWrapper);
    }
}
