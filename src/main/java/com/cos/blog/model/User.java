package com.cos.blog.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.convert.DataSizeUnit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // ORM 클래스, User 클래스를  mysql에 테이블생성
public class User {
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB넘버링전략 사용

	private int id; //시퀀스
	
	@Column(nullable = false, length = 30, unique = true)
	private String userName; //id
	
	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;
	
	@Enumerated (EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp
	private Timestamp creDate;
}
