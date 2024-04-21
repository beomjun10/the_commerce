package com.thecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users")
@DynamicUpdate
public class User {
	
	@Id
	@SequenceGenerator(name = "user_user_no_seq", sequenceName = "user_user_no_seq", initialValue = 1 , allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "user_user_no_seq")
	private Long id;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	@Column(unique = true)
	private String nickname;
	
	private String name;
	@Column(unique = true)
	private String phoneNo;
	@Column(unique = true)
	private String email;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createTime; // 데이터 생성시간
	
	@UpdateTimestamp
	private LocalDateTime updateTime; // 데이터 갱신시간
	
}
