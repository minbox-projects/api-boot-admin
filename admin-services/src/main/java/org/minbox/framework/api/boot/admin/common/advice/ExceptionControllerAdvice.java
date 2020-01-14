package org.minbox.framework.api.boot.admin.common.advice;

import org.minbox.framework.api.boot.admin.common.enums.ResponseCode;
import org.minbox.framework.api.boot.admin.common.exception.LogicException;
import org.minbox.framework.api.boot.admin.common.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerWebInputException;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Locale;

/**
 * 全局控制器异常通知
 * 只会处理控制器发生的异常，如果你在service把异常自行try-catch，则不会经过该类
 * 建议：把Mapper/Service遇到的异常抛给Controller,由ControllerAdvice统一处理
 * "annotations"属性配置只有控制器的类上存在{@link RestController}以及{@link Controller}注解时才会经过该类处理
 *
 * @author 恒宇少年
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class ExceptionControllerAdvice {
    /**
     * 错误信息源
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * 处理{@link LogicException}业务逻辑异常
     *
     * @param e {@link LogicException} 系统中遇到的业务逻辑异常实例
     * @return {@link ApiResponse} 统一响应实体
     */
    @ExceptionHandler(value = LogicException.class)
    public ApiResponse logicException(LogicException e) {
        return ApiResponse.error(e.getCode()).errorMsg(e.getErrorMessage());
    }

    /**
     * 处理{@link HttpMessageNotReadableException}异常
     * "@RequestBody"方式参数不传递时异常
     *
     * @return {@link ApiResponse}
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ApiResponse.builder().code(ResponseCode.PARAMETER_VALID_FAIL);
    }

    /**
     * 处理{@link ServerWebInputException}异常
     *
     * @return {@link ApiResponse}
     */
    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse serverWebInputException(ServerWebInputException e) {
        return ApiResponse.builder().code(ResponseCode.PARAMETER_VALID_FAIL);
    }

    /**
     * 处理{@link MethodArgumentNotValidException}异常
     *
     * @return {@link ApiResponse}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ApiResponse.builder().code(ResponseCode.PARAMETER_VALID_FAIL).errorMsg(this.getErrorFieldMessage(e.getBindingResult()));
    }

    /**
     * 处理{@link MethodArgumentTypeMismatchException} 异常
     *
     * @return {@link ApiResponse}
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ApiResponse.builder().code(ResponseCode.PARAMETER_VALID_FAIL);
    }

    /**
     * 处理{@link BindException}异常
     * 参数绑定验证失败时执行
     *
     * @return {@link ApiResponse}
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse illegalParamsExceptionHandler(BindException e) {
        return ApiResponse.builder().code(ResponseCode.PARAMETER_VALID_FAIL).errorMsg(this.getErrorFieldMessage(e.getBindingResult()));
    }

    /**
     * 根据错误字段对象获取错误消息
     *
     * @param fieldError 错误字段对象
     * @return
     */
    String resolveLocalErrorMessage(FieldError fieldError) {
        //获取本地locale,zh_CN
        Locale currentLocale = LocaleContextHolder.getLocale();
        //返回错误信息
        return messageSource.getMessage(fieldError, currentLocale);
    }

    /**
     * 获取参数错误字段message
     *
     * @param bindingResult
     * @return
     */
    private String getErrorFieldMessage(BindingResult bindingResult) {
        //获取错误字段集合
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        //错误消息集合
        //JSONObject msg = new JSONObject();
        StringBuilder errorMsg = new StringBuilder();
        for (int i = 0; i < fieldErrors.size(); i++) {
            FieldError fieldError = fieldErrors.get(i);
            //获取错误信息
            String errorMessage = String.format("%s：%s", fieldError.getField(), resolveLocalErrorMessage(fieldError));
            //添加到错误消息
            errorMsg.append(errorMessage).append(i == fieldErrors.size() - 1 ? "" : " | ");
        }
        return errorMsg.toString();
    }
}
