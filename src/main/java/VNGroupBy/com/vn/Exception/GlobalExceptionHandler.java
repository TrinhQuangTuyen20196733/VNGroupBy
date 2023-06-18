package VNGroupBy.com.vn.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({TokenInValid.class})
    public ResponseEntity<ErrorResponse> TokenInValidException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(403);
        errorResponse.setDescription(e.getMessage());
        return ResponseEntity.status(403).body(errorResponse);

    }
}
