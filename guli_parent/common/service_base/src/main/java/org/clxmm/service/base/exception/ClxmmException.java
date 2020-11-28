package org.clxmm.service.base.exception;

import lombok.Data;
import org.clxmm.common.base.result.ResultCodeEnum;

/**
 * 自定义异常
 * @author clxmm
 * @version 1.0
 * @date 2020/11/18 8:40 下午
 */
@Data
public class ClxmmException extends RuntimeException {

    private Integer code;


    public ClxmmException(Integer code) {
        this.code = code;
    }

    public ClxmmException(String message, Integer code) {
        super(message);
        this.code = code;
    }


    public ClxmmException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }


    @Override
    public String toString() {
        return "ClxmmException{" +
                "code=" + code +
                "message=" + this.getMessage() +
                '}';
    }
}
