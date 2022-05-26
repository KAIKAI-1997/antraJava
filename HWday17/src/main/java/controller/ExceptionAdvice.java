package controller;

import controller.Exceptions.BusinessException;
import controller.Exceptions.SystemException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    public final Log exceptionsLog = LogFactory.getLog(ExceptionAdvice.class);

    @ExceptionHandler(SystemException.class)
    public Result handleSystemException(SystemException ex){
        exceptionsLog.error(ex.getMessage(), ex);
        return new Result(404, null, ex.getMessage());
    }

    public Result handleBusinessException(BusinessException ex){
        exceptionsLog.error(ex.getMessage(), ex);
        return new Result(404, null, ex.getMessage());
    }

    @ExceptionHandler
    public controller.Result doException(Exception exception){
        exceptionsLog.error(exception.getMessage(), exception);
        return new Result(404, null, "unknown exception");
    }
}
