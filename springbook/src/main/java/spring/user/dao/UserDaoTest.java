package spring.user.dao;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.user.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
@DirtiesContext //테스트 메소드에서 애플리케이션 컨텍스트 구성 및 상태의 변경을 알려줌
public class UserDaoTest {
	
	@Autowired
	private ApplicationContext context;
	
	private UserDao dao; //setup 안의 오브젝트를 test 메소드에서 사용할 수 있도록 인스턴스 변수로 선언
	private User user1;
	private User user2;
	private User user3;
	
	//test가 실행되기 전 먼저 실행할 메소드 정의
	@Before
	public void setUp() {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		this.dao = context.getBean("userDao", UserDao.class);
		
		this.user1 = new User("gyumee", "박성철", "springno1");
		this.user2 = new User("leegw700", "이길원", "springno2");
		this.user3 = new User("bumjin", "박범진", "springno3");
		
		System.out.println(this.context);
		System.out.println(this);
		
	}
	
	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException{
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		
//		UserDao dao = context.getBean("userDao", UserDao.class);
//		
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//		
//		User user = new User("gyumee", "박성철", "springno1");
//		User user2 = new User("leegw700", "이길원", "springno2");
//		User user3 = new User("bumjin", "박범진", "springno3");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(),is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(),is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
		
		
		//User의 클래스를 수정함으로써 get/set을 사용할 수 없게 됨
//		user.setId("gyumee1");
//		user.setName("박성철");
//		user.setPassword("spring1");
//		
//		dao.add(user);
//		assertThat(dao.getCount(), is(1));
//		
//		User user2 = dao.get(user.getId());
//		
//		assertThat(user2.getName(), is(user.getName()));
//		assertThat(user2.getPassword(), is(user.getPassword()));
		
	}
	
	//테스트 중 발생할 것으로 예상되는 예외 클래스 지정
//	@Test(expected = EmptyResultDataAccessException.class) - 예외 import가 안됨
//	public void getUserFailure() throws SQLException {
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		
//		UserDao dao = context.getBean("userDao", UserDao.class);
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//		
//			dao.get("unknown_id");
		
		
		
//	}
	
	
	public static void main(String[] args) {
		JUnitCore.main("spring.user.dao.UserDaoTest");
	}


//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		
////		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//		
//		UserDao dao = new UserDao();
//		
//		User user = new User();
//		user.setId("whiteship3");
//		user.setName("백기선");
//		user.setPassword("married");
//		
//		dao.add(user);
//		
//		System.out.println(user.getId() +  "등록 성공");
//		
//		User user2 =dao.get(user.getId());
////		System.out.println(user2.getName());
////		System.out.println(user2.getPassword());
////		System.out.println(user2.getId() +  "조회 성공");
//		
//		//실패의 종류를 파악하기 위해 조회 시 실패한 부분의 예외 분류
//		if(!user.getName().equals(user2.getName())) {
//			System.out.println("테스트 실패 (name)");
//		}else if(!user.getPassword().equals(user2.getPassword())) {
//			System.out.println("테스트 실패 (password)");
//		}else {
//			System.out.println("조회 테스트 성공");
//			
//		}
//		
//	}

}
