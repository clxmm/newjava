package org.clxmm.service.trade.feign;

import org.clxmm.common.base.result.R;
import org.clxmm.service.base.dto.CourseDto;
import org.clxmm.service.trade.feign.fallback.EduCourseServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author clxmm
 * @version 1.0
 * @date 2021/2/13 2:18 下午
 */
@Service
@FeignClient(value = "service-edu", fallback = EduCourseServiceFallBack.class)
public interface EduCourseService {

    @GetMapping(value = "/api/edu/course/inner/get-course-dto/{courseId}")
    CourseDto getCourseDtoById(@PathVariable(value = "courseId") String courseId);


    @GetMapping("/api/edu/course/inner/update-buy-count/{id}")
    R updateBuyCountById(@PathVariable("id") String id);

}
