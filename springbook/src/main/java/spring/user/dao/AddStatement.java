package spring.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import spring.user.domain.User;

public class AddStatement implements StatementStrategy {
	User user;
	
	public AddStatement(User user) {
		this.user = user;
	}
	
	public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
		PreparedStatement ps = 
				c.prepareStatement("insert into users(id, name, password) "
								 + "values(?, ?, ?)");
		//add의 특성 상 user를 가져와야 하기에 생성자를 생성함
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		return ps;
	}

}
