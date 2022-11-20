package spring.user.domain;

public class User {

	String id;
	String name;
	String password;
	
	//자바빈 규약을 따르는 클래스 생성사를 명시적으로 추가할 시
	//파라미터 없는 default 생성자도 함께 정의해야함
	public User() {
		
	}
	
	public User(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
