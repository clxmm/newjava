package org.clxmm.testlogging;

import org.clmxx.logging.TimeLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clx
 * @date 2020-09-06 18:20
 */
@RestController
public class HelloController {


    @RequestMapping("/test")
    @TimeLog
    public String hello() {
        return "hello();";
    }


}
