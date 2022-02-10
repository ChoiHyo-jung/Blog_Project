package com.cos.blog.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ResponseDto <T>{
	
	HttpStatus status; //브라우저 응답 상태
	T data; //db데이터 조회 성공여부 

}
