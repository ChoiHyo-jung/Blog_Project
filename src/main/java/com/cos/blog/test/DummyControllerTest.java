package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;
import com.cos.blog.model.RoleType;
import com.cos.blog.repository.UserRepository;

@RestController 
public class DummyControllerTest {

	@Autowired //의존성주입
	private UserRepository ur ; 
	
	@DeleteMapping("dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		
		try {
			ur.deleteById(id);
		}catch(IllegalArgumentException e){
			return "해당id는 잘못된 id입니다"+ id;
		}catch(EmptyResultDataAccessException e){
			return "존재dsf하지않는 id입니다.";
		}
		
		
		
		return "삭제했씁니다. id : " + id;
	}

	@Transactional //더티체킹 영속성컨텍스트 체킹 변경감지
	@PutMapping ("dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {//메세지 컨버터의 JASON라이브러리가 JSON데이터를 -> 자바데이터로
		System.out.println("id = " + id);
		System.out.println("email = " + requestUser.getEmail());
		System.out.println("password = " + requestUser.getPassword());
		
		User user = ur.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했씁니다");
		});
		user.setEmail(requestUser.getEmail());
		user.setPassword(requestUser.getPassword());
		//merge
		//ur.save(user);
		return user; 
	}
	
	//http://localhost:9090/blog/dummy/users
	@GetMapping("dummy/users")
	public List<User> list(){
		return ur.findAll();
	}
	
	//Page 클래스가 페이징처리 기능을 제공 해준다 -> 결과는 list로 받아서 리턴해주는것이 좋다
	@GetMapping("dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id",direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = ur.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//http://localhost:9090/blog/dummy/user/3
	@GetMapping("dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//user/4번을 찾으면 내가 데이터베이스에서 못찾아오게되면 user이 null이 될것아냐/
		//그럼 return null이 리던되자나.. 그럼 문제가 있겠지?
		//Optional로 너의 user 객체를 감싸서 가져 올테니 null인지 아닌지 판단해서 return 해

//		람다식
//		User user = ur.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당유저는 없습니다. : " + id);
//		});		
		
		User user = ur.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당유저는 없습니다. : " + id);
			}
		});
		return user;
		
	}
	
	@PostMapping("/first/test")
	public String Join(User user) {
		
		user.setRole(RoleType.USER);
		
		System.out.println("user id : " + user.getId());
		System.out.println("user name : " + user.getUserName());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("creDate : " + user.getCreDate());   
		
		ur.save(user);
		return "저장완료";
	}

}
