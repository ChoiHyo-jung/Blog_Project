package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//컴포넌트 스캔을 통해서 빈에등록 ioc
@Service
public class UserService {
	
	@Autowired 
	private UserRepository ur;
	
	@Transactional //전체가 성공하면 commit
	public int join(User user) {
		try {
			ur.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 실패 : " + e.getMessage());
		}
		return -1;
	}

}
