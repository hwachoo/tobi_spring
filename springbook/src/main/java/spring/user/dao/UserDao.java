package spring.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import spring.user.domain.User;

public class UserDao {
	private DataSource dataSource; 
	private JdbcContext jdbcContext;
	private JdbcTemplate jdbcTemplate;
//	public UserDao() {
//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//		this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
//	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<User> userMapper = new RowMapper<User>() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException{
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			
			return user;
		}
	};
	
	
	//클래스 내 코드는 간결해졌지만 그만큼 클래스 파일이 많아짐
	//=> 로컬 클래스를 이용
	
	//final = 단 한 번만 할당될 수 있는 entity 정의할 때 사용
	//변경할 수 없는 상수값이 됨
	public void add(final User user) throws SQLException{
		
		this.jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)",
								 user.getId(), user.getName(), user.getPassword());
		
		
//		Connection c = getConnection();
//		
//		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//		
//		ps.setString(1, user.getId());
//		ps.setString(2, user.getName());
//		ps.setString(3, user.getPassword());
//		
//		ps.executeUpdate();
//		
//		ps.close();
//		c.close();
		
		
		//AddStatement.java와 같은 코드지만 클래스 파일을 늘리지 않고 사용 가능함
//		class AddStatement implements StatementStrategy{
//
//			@Override
//			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//				PreparedStatement ps = 
//					c.prepareStatement("insert into users(id, name, password) "
//										 + "values(?, ?, ?)");
//				//로컬 클래스 코드에서 외부 메소드의 로컬 변수에 직접 접근 가능
//				//add의 특성 상 user를 가져와야 하기에 생성자를 생성함
//				ps.setString(1, user.getId());
//				ps.setString(2, user.getName());
//				ps.setString(3, user.getPassword());
//				
//				return ps;
//			}
			
//		}
//		//final이 있어서 파라미터를 전달하지 않아도 됨
//		StatementStrategy st = new AddStatement();
//		//컨텍스트 호출, 전략 오브젝트 전달
//		jdbcContextWithStatementStrategy(st);
//		
		
	}
	
	public List<User> getAll(){
		return this.jdbcTemplate.query("select * from users order by id",
										this.userMapper);
		
	}
	
	public User get(String id){
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", 
									new Object[] {id},
									this.userMapper);
		
//		Connection c = getConnection();
//		
//		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
//		ps.setString(1, id);
//		
//		ResultSet rs = ps.executeQuery();
//		//null 상태로 초기화
//		User user = null;
//		//쿼리 실행 결과가 있을 경우 User 오브젝트 생성, 값 입력
//		if(rs.next()) {
//			user = new User();
//			user.setId(rs.getString("id"));
//			user.setName(rs.getString("name"));
//			user.setPassword(rs.getString("password"));
//		}
//		
//		rs.close();
//		ps.close();
//		c.close();
//		
//		if(user == null) throw new EmptyResultDataAccessException(1);
//		
//		return user;
//		
	}
	
	public void deleteAll() throws SQLException{
		
		this.jdbcTemplate.update("delete from users");
//		this.jdbcContext.executeSql("delete from users");
		
//		Connection c = dataSource.getConnection();
//		
//		PreparedStatement ps = c.prepareStatement("delete from users");
//		ps.executeUpdate();
//		ps.close();
//		c.close();
		
//		Connection c = null;
//		PreparedStatement ps = null;
//		
//		try { //예외가 발생할 수 있는 코드는 try로
//			c = dataSource.getConnection();
//			
//			StatementStrategy strategy = new DeleteAllStatement();
//			ps = strategy.makePreparedStatement(c);
//			ps.executeUpdate();
//			
//		}catch(SQLException e){ //예외가 발생했을 때 이동할 곳(sql예외)
//			throw e; //메소드 밖으로 던짐
//		}finally { //예외발생여부에 관계없이 반드시 실행되는 코드를 넣을 때 사용
//			if(ps != null) { 
//				try {
//					ps.close();
//				}catch(SQLException e) {//제대로 ps가 닫히지 않았을 경우를 위해
//				}
//			}
//			if(c != null) {
//				try {
//					c.close(); //connection 반환
//				}catch(SQLException e) {
//				}
//			}
//		}
		
		//익명 내부 클래스 - 선언과 동시에 오브젝트 생성
		//이름이 없기 때문에 클래스 타입 가지지 못하고 인터페이스 타입의 변수에만 저장 가능
//				executeSql("delete from users");
		
//		//선정한 전력 클래스의 오브젝트 생성
//		StatementStrategy st = new DeleteAllStatement();
//		//컨텍스트 호출, 전략 오브젝트 전달
//		jdbcContextWithStatementStrategy(st);
		
	}
	
//	private void executeSql(final String query) throws SQLException{
//		this.jdbcContext.workWithStatemeentStatement(
//				new StatementStrategy() {
//					
//					@Override
//					public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//						return c.prepareStatement(query);
//					}
//				}
//				);
//	}
	
	//변할 수 있는 부분을 메소드 추출법으로 추출하여 따로 생성
//	private PreparedStatement makeStatement(Connection c) throws SQLException{
//		
//		PreparedStatement ps;
//		ps = c.prepareStatement("delete from users");
//		
//		return ps;
//		
//	}
	
	//add, deleteall의 변하지 않는 공통 부분을 메소드로 분리
//	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException{
//		Connection c = null;
//		PreparedStatement ps = null;
//		
//		try {
//			c = dataSource.getConnection();
//			ps = stmt.makePreparedStatement(c);
//			ps.executeUpdate();
//		}catch(SQLException e) {
//			throw e;
//		}finally {
//			if(ps != null) {
//				try {
//					ps.close();
//				}catch(SQLException e) {
//				}
//			}
//			if(c != null) {
//				try {
//					c.close();
//				}catch(SQLException e) {
//				}
//			}
//		}
//	}
	
	public int getCount(){
//		return this.jdbcTemplate.query(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				return con.prepareStatement("select count(*) from users");
//			}
//		},
//			new ResultSetExtractor<Integer>() {
//			@Override
//			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
//				rs.next();
//				return rs.getInt(1);
//			}
//			}
//		);
		return this.jdbcTemplate.queryForInt("select count(*) from users");
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test", "test");
		
		return c;
	}
}
