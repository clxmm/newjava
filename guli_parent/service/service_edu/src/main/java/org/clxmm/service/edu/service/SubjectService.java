package org.clxmm.service.edu.service;

import org.clxmm.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.service.edu.entity.vo.SubjectVo;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
public interface SubjectService extends IService<Subject> {


    void batchImport(InputStream inputStream);


    List<SubjectVo> nestedList();
}
