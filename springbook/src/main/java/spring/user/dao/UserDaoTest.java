package spring.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
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
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		this.dao = context.getBean("userDao", UserDao.class);
		
		this.user1 = new User("gyumee", "박성철", "springno1");
		this.user2 = new User("leegw700", "이길원", "springno2");
		this.user3 = new User("bumjin", "박범진", "springno3");
		
		System.out.println(this.context);
		System.out.println(this);
		
	}
	
	@Test
	public void getAll() throws SQLException, ClassNotFoundException {
		dao.deleteAll();
		
		List<User> users0 = dao.getAll();
		assertThat(users0.size(), is(0));
		
		dao.add(user1);
		List<User> users1 = dao.getAll();
		assertThat(users1.size(), is(1));
		checkSameUser(user1, users1.get(0));
		
		
		dao.add(user2);
		List<User> users2 = dao.getAll();
		assertThat(users2.size(), is(2));
		checkSameUser(user2, users2.get(0));
		checkSameUser(user2, users2.get(1));
		
		dao.add(user3);
		List<User> users3 = dao.getAll();
		assertThat(users3.size(), is(3));
		checkSameUser(user3, users3.get(0));
		checkSameUser(user3, users3.get(1));
		checkSameUser(user3, users3.get(2));
		
	}
	
	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		
	}
	
	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException{
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		User user2 = dao.get(user1.getId());
		assertThat(user2.getName(),is(user1.getName()));
		assertThat(user2.getPassword(), is(user1.getPassword()));
		
	}
	
	//테스트 중 발생할 것으로 예상되는 예외 클래스 지정
//	@Test(expected = EmptyResultDataAccessException.class) // 예외 import가 안됨
//	public void getUserFailure() throws SQLException {
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		
//		UserDao dao = context.getBean("userDao", UserDao.class);
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//		
//			try {
//				dao.get("unknown_id");
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		
//		
//		
//	}
	
	
	public static void main(String[] args) {
		JUnitCore.main("spring.user.dao.UserDaoTest");
	}

}
