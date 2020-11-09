package org.clxmm.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.service.edu.entity.vo.TeacherQueryVo;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
public interface TeacherService extends IService<Teacher> {

    Page<Teacher> pageSelect(Page<Teacher> page, TeacherQueryVo teacherQueryVo);
}
