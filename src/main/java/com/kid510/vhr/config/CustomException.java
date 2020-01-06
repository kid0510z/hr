package com.kid510.vhr.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Classname CustomException
 * @Description 自定义异常处理
 * @Date 2020/1/6 14:21
 * @Author kid
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private String message;

    public CustomException error(String message) {
        return new CustomException(message);
    }
}
