package com.core.assignment.admin.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

	private int status;
	private Object response;
	private String message;

//	public static <T> ApiResponse<T> success(T data, String message) {
//	//	return ApiResponse.<T>builder().status(200) // Default success HTTP status
//				.message(message).data(data).timestamp(LocalDateTime.now()).build();
//	}
//
//	public static <T> ApiResponse<T> error(int status, String message) {
//		return ApiResponse.<T>builder().status(status).message(message).data(null).timestamp(LocalDateTime.now())
//				.build();
//	}
}
