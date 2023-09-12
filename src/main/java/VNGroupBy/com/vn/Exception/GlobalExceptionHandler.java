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
    @ExceptionHandler({LoginException.class})
    public ResponseEntity<ErrorResponse> LoginFailException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(403);
        errorResponse.setDescription(e.getMessage());
        return ResponseEntity.status(403).body(errorResponse);

    }
    @ExceptionHandler({InternalServerException.class})
    public ResponseEntity<ErrorResponse> InternalServerException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(500);
        errorResponse.setDescription(e.getMessage());
        return ResponseEntity.status(500).body(errorResponse);

    }
    @ExceptionHandler({ImageException.class})
    public ResponseEntity<ErrorResponse> ImageException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(400);
        errorResponse.setDescription(e.getMessage());
        return ResponseEntity.status(400).body(errorResponse);

    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> Exception(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(500);
        errorResponse.setDescription(e.getMessage());
        return ResponseEntity.status(500).body(errorResponse);

    }
}
