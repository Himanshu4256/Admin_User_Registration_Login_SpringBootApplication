package com.config.advice;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {			// this T is generic type. means we can take any class as return in this.
	String message;
	String status;
	Map<String, String> respMap;
	T data;
}
