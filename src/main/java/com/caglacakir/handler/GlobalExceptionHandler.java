package com.caglacakir.handler;

import com.caglacakir.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    // base exception kendi exceptionlarımız
    @ExceptionHandler(value = {BaseException.class} ) // value si  exception'un tıpını veriyoruz.Böyle bir exc. fırlatıldıgında yakalayacaksın
    // Yakalanan exception' ıda parametre olarak metoda geçiyoruz :)
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request) {
       return ResponseEntity.badRequest().body(createApiError(ex.getMessage(),request ));

    }

    // Spring validation
    @ExceptionHandler(value = {MethodArgumentNotValidException.class} )
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, List<String>> map = new HashMap<>();
        for(ObjectError objError : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError)objError).getField();

            if(map.containsKey(fieldName)) {
                map.put(fieldName, addValue(map.get(fieldName), objError.getDefaultMessage()));
            }else {
                map.put(fieldName, addValue(new ArrayList<>(), objError.getDefaultMessage()));
            }

        }
        return ResponseEntity.badRequest().body(createApiError(map, request));

        }
    private List<String> addValue(List<String> list, String newValue) {
        list.add(newValue);
        return list;


    }


    private String getHostName(){
        try{
            return Inet4Address.getLocalHost().getHostName();
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }

    public <E> ApiError<E> createApiError(E messsage, WebRequest request) { // Api oluşturduk
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        Exception<E> exception = new Exception<>();  // Exception tanımladık
        exception.setPath(request.getDescription(false).substring(4)); // uri= "/jslkjslkjgf uri= kısmını attık
        exception.setCreateTime(new Date());
        exception.setMessage(messsage);
        exception.setHostName(getHostName());

        apiError.setException(exception);


        return apiError;

    }
}
