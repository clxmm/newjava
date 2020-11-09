package org.clxmm.service.base.handler;

import lombok.extern.slf4j.Slf4j;
import org.clxmm.common.base.result.ExceptionUtils;
import org.clxmm.common.base.result.R;
import org.clxmm.common.base.result.ResultCodeEnum;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统一的异常处理
 * @author clxmm
 * @version 1.0
 * @date 2020/11/8 3:24 下午
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        log.error("全局异常的处理" + ExceptionUtils.getMessage(e));
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }


    /**
     * sql 异常
     * @param e
     * @return
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public R sqlException(BadSqlGrammarException e) {
        log.error("全局异常的处理" + ExceptionUtils.getMessage(e));
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }











}
