package entry;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建监听器
 */
@Slf4j
public class ExcelStudentDataListener  extends AnalysisEventListener<ExcelStudentData> {

    /***
     *   每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static  final  int BATCH_COUNT = 5;
    List<ExcelStudentData> list = new ArrayList<>();

    /**
     * 这个每一条数据解析都会来调用
     * @param excelStudentData
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelStudentData excelStudentData, AnalysisContext analysisContext) {
        log.info("解析到一条数据",excelStudentData);
        list.add(excelStudentData);
// 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            log.info("save data");
            // 存储完成清理 list
            list.clear();
        }




    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("存储剩余的数据");
        log.info("所有数据解析完成！");
    }
}