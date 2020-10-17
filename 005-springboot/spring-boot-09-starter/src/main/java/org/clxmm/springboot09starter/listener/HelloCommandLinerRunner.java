package org.clxmm.springboot09starter.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/10/15 8:36 下午
 */
@Component
public class HelloCommandLinerRunner  implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner run " + Arrays.asList(args));
    }
}
