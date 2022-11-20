package spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {

	//전략 패턴
	//OCP를 준수하면서 템플릿 메소드 패턴보다 유연하고 확장성이 뛰어남
	//오브젝트를 둘로 분리 후 클래스 레벨에서는 인터페이스를 통해서만 의존하도록 만든 패턴
	public PreparedStatement makePreparedStatement(Connection c) throws SQLException{
		
		PreparedStatement ps = c.prepareStatement("delete from users");
		
		return ps;
	}
}
