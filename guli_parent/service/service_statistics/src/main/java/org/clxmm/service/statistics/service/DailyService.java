package org.clxmm.service.statistics.service;

import org.clxmm.service.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author clxmm
 * @since 2021-02-27
 */
public interface DailyService extends IService<Daily> {


    void createStatisticsByDay(String day);

    Map<String, Map<String, Object>> getChartData(String begin, String end);
}
