package org.clxmm.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.clxmm.service.edu.entity.Subject;
import org.clxmm.service.edu.entity.excel.ExcelSubjectData;
import org.clxmm.service.edu.entity.vo.SubjectVo;
import org.clxmm.service.edu.listener.ExcelSubjectDataListener;
import org.clxmm.service.edu.mapper.SubjectMapper;
import org.clxmm.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2020-11-04
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {


    /**
     * 读取excel 文件
     * @param inputStream
     */
    @Override
    public void batchImport(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectDataListener(baseMapper))
                .excelType(ExcelTypeEnum.XLS)
                .sheet().doRead();
    }

    @Override
    public List<SubjectVo> nestedList() {
        return baseMapper.nestedListByParentId("0");
    }
}
