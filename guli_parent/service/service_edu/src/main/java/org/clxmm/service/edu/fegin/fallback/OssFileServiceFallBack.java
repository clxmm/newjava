package org.clxmm.service.edu.fegin.fallback;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.R;
import org.clxmm.service.edu.fegin.OssFileService;
import org.springframework.stereotype.Service;

/**
 *  容错类
 * @author clxmm
 * @version 1.0
 * @date 2020/11/24 8:20 下午
 */
@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {


    @Override
    public R test() {
        return R.error();
    }

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        System.out.println("熔断保护");
        return R.error();
    }

}
