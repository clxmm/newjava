package org.clxmm.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.clxmm.service.edu.entity.Subject;
import org.clxmm.service.edu.entity.excel.ExcelSubjectData;
import org.clxmm.service.edu.mapper.SubjectMapper;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/28 8:54 下午
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {

    // 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
    private SubjectMapper subjectMapper;

    /**
     * 遍历每一行的记录
     *
     * @param data
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext analysisContext) {
        log.info("解析到一条记录: {}", data);
        // 处理读取出来的数据
        String levelOneTitle = data.getLevelOneTitle();
        String levelTwoTitle = data.getLevelTwoTitle();
        log.info("levelOneTitle " + levelOneTitle);
        log.info("levelTwoTitle " + levelTwoTitle);

        String parentId = null;
        // 组装数据，存入数据库
        // 一级标题
        Subject subject1 = getByTitle(levelOneTitle);
        if (subject1 == null) {
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            subjectMapper.insert(subject);
            parentId = subject.getId();
        } else {
            parentId = subject1.getId();
        }

        // 二级标题
        if (getSubByTitle(levelTwoTitle,parentId) == null) {
            Subject subject = new Subject();
            subject.setParentId(parentId);
            subject.setTitle(levelTwoTitle);
            subjectMapper.insert(subject);
        }

    }

    // 所有数据解析完成了 都会来调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }

    private Subject getByTitle(String title) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", 0);
        return subjectMapper.selectOne(queryWrapper);
    }

    private Subject getSubByTitle(String title, String parentId) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title)
                .eq("parent_id", parentId);
        return subjectMapper.selectOne(queryWrapper);
    }


}
