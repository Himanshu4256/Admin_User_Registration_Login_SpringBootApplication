package com.config.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerEngine {
	@ExceptionHandler(MethodArgumentNotValidException.class )
	public ResponseEntity<?> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		BaseResponse<?> resp = new BaseResponse<>();       //<?> Dynamic Data return type
		resp.setMessage("Sorry, your data could not be updated..");
		resp.setStatus("400");
		
		Map<String, String> respMap = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			respMap.put(fieldName, message);
		});
		resp.setRespMap(respMap);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleException(Exception ex){
		String errMsg = "You are giving wrong data :"+ex.getMessage();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMsg);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleIntegrityException() {
		String msg = "Data is already saved";
		return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
	}
}
