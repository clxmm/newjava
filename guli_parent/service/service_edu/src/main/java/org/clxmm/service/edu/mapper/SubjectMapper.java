package org.clxmm.service.edu.mapper;

import org.clxmm.service.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.clxmm.service.edu.entity.vo.SubjectVo;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    List<SubjectVo> nestedListByParentId(String parentId);


}
