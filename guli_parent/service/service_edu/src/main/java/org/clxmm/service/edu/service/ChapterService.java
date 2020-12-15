package org.clxmm.service.edu.service;

import org.clxmm.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 根据章节id删除章节
     * @param id 章节id
     * @return
     */
    boolean deleteChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
