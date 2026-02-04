package springTask.example.taskBoot.globalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handlerException(IOException e){
       ErrorResponse errorResponse=new ErrorResponse(e.getMessage(),"input output error", LocalDateTime.now());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
