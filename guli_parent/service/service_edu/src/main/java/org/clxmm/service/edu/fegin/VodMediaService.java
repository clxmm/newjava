package org.clxmm.service.edu.fegin;

import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.fegin.fallback.VodMediaServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/12/28 8:09 下午
 */
@Service
@FeignClient(value = "service-vod",fallback = VodMediaServiceFallBack.class)
public interface VodMediaService {
    @DeleteMapping("/admin/vod/media/remove/{vodId}")
    R removeVideo(@PathVariable("vodId") String vodId);

    @DeleteMapping("/admin/vod/media/remove")
    R removeVideoByIdList(@RequestBody List<String> videoIdList);
}
