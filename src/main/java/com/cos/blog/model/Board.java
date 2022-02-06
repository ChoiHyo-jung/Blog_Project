package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class Board {
	
	@Id 
	@GeneratedValue(strategy = GenerationType .IDENTITY)
	private int id;
	
	@Column (nullable = false, length = 100)
	private String title;
	
	@Lob //대용량데이터
	private String content; // 썸머노트 라이브러리 <thml> 데이터가 섞여서 디자인 
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne
	@JoinColumn (name ="userId")
	private User user;
	
	@OneToMany (mappedBy = "board", fetch = FetchType.EAGER)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp creDate;

	
}
