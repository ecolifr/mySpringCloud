package sample.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller层的统一异常处理
 * Create By Xie ZuoZhi On 2018/5/11
 */
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {
    // 统一处理ConstraintViolationException异常
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object constraintViolationExceptionHandler( HttpServletRequest request,
                                                       ConstraintViolationException exception ) {
        //按需重新封装需要返回的错误信息
        List<String> invalidArguments = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (ConstraintViolation violation : exception.getConstraintViolations()) {
            invalidArguments.add(violation.getMessage());
        }
        request.setAttribute("message", invalidArguments);
        return "welcome";
    }
}
