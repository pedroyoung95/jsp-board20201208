package member.service;

import java.util.Map;

public class JoinRequest {
	private String id;
	private String name;
	private String password;
	private String confirmPassword;
	
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public void validate(Map<String, Boolean> errors) {
		//id값이 잘 들어왔는지(비워져 있을 때 true로 map에 담김, 비워져 있는가가 기준)
		checkEmpty(errors, id, "id");
		//name값이 잘 들어왔는지
		checkEmpty(errors, name, "name");
		//password값이 잘 들어왔는지
		checkEmpty(errors, password, "password");
		//confirmPassword값이 잘 들어왔는지 검사하는 메소드
		checkEmpty(errors, confirmPassword, "confirmPassword");
		
		//password와 confirmPassword가 일치하는지 검사하는 과정
		if(!errors.containsKey("confirmPassword")) {
			if(!isPasswordEqualToConfirm()) {
				errors.put("notMatch", true);
			}
		}
	}
	
	public boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPassword);
	}
	
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName, true);
		}
	}
}
