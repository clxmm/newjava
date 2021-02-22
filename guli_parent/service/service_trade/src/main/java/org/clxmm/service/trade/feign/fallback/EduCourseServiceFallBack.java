package org.clxmm.service.trade.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.base.dto.CourseDto;
import org.clxmm.service.trade.feign.EduCourseService;
import org.springframework.stereotype.Service;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/13 2:19 下午
 */
@Service
@Slf4j
public class EduCourseServiceFallBack implements EduCourseService {
    @Override
    public CourseDto getCourseDtoById(String courseId) {
        log.info("熔断保护");
        return null;
    }
    @Override
    public R updateBuyCountById(String id) {
        log.error("熔断器被执行");
        return R.error();
    }

}
