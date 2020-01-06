package com.kid510.vhr.config;

import com.kid510.vhr.common.resp.ResultResp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Classname GlobalExceptionHandler
 * @Description 全局异常处理
 * @Date 2020/1/6 11:39
 * @Author kid
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SQLException.class)
    public ResultResp sqlHandler(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return ResultResp.error("该数据在其他地方引用，不能删除！");
        }
        return ResultResp.error(e.getMessage());
    }

    @ExceptionHandler(value = CustomException.class)
    public ResultResp customHandler(CustomException c) {
        return ResultResp.error(c.getMessage());
    }
}
