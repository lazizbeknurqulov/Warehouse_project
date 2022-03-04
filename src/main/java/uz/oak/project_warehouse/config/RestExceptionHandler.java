package uz.oak.project_warehouse.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.oak.project_warehouse.exception.ProductException;
import uz.oak.project_warehouse.payload.resp.ApiExceptionErrorResponse;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //O'zimiz yaratgan ProductExeptionni ushlab Errorni o'zimiz xohlagan holatda qaytarayapmiz
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Object> handleTrainException(ProductException e){
        ApiExceptionErrorResponse errorResponse = new ApiExceptionErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }
}

