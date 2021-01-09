package org.clxmm.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.service.edu.entity.vo.TeacherQueryVo;

import java.util.List;
import java.util.Map;

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

    List<Map<String, Object>> selectNamesByKey(String key);

    /**
     * 删除头像
     * @param id   讲师的id， 根据讲师的id产看url地址
     * @return
     */
    boolean removeAvatarBtId(String id);

    /**
     *
     * @param teacherId 讲师id
     * @return
     */
    Map<String, Object> getTeacherInfoById(String teacherId);
}
