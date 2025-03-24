package java16.onlinestorepr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException e) {
      return   ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }
    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBadRequestException(Exception e) {
        return   ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }
    @ExceptionHandler(ForBiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleForBiddenException(Exception e) {
        return   ExceptionResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleIllegalArgumentException(Exception e) {
        return   ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ExceptionResponse handleException(Exception e) {
//        return   ExceptionResponse.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .message(e.getMessage())
//                .className(e.getClass().getSimpleName())
//                .build();
//    }


}
