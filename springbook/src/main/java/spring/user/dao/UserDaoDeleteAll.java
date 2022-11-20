package spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDeleteAll extends UserDao {
	//템플릿 메소드 패턴
	//OCP는 지켜지나 접근 제한이 많아 DAO로직마다 상속을 통해 새로운 클래스 생성해야함
	
	
	
	//접근제한자
	//같은 패키지일 경우 접근 제한이 없으나
	//다른 패키지일 경우 자식 클래스만 접근 가능
	protected PreparedStatement makeStatement(Connection c) throws SQLException{
		
		PreparedStatement ps = c.prepareStatement("delete from users");
		
		return ps;
	}

}
