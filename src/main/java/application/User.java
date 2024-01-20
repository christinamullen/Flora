/**********************************************************************
 * Programmer: Christina Mullen
 * 0861078
 * Advanced Java Programming
 * Final Project: Flora
 * Copyright 12/04/2022
 *************************************************************************/
package application;

public class User {
	private int id;
	private String username;
	private String password;
	private String date_created;
	
	public User(int id, String un, String pw, String created) {
		this.setId(id);
		this.setUsername(un);
		this.setPassword(pw);
		//setDate_acctCreated(new java.util.Date());
		this.date_created = created;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public int getId() {
		return id;
	}

	private void setId(int id2) {
		this.id = id2;	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
