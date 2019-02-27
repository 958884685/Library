package com.library.entity;

public class User {

	private Long id;
	private String username;
	private String sex;
	private Long iphone;
	private String email;
	private Long idcard;
	private String note;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String username, String sex, Long iphone, String email, Long idcard, String note) {
		super();
		this.id = id;
		this.username = username;
		this.sex = sex;
		this.iphone = iphone;
		this.email = email;
		this.idcard = idcard;
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getIphone() {
		return iphone;
	}

	public void setIphone(Long iphone) {
		this.iphone = iphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdcard() {
		return idcard;
	}

	public void setIdcard(Long idcard) {
		this.idcard = idcard;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", iphone=" + iphone + ", email=" + email
				+ ", idcard=" + idcard + ", note=" + note + "]";
	}

}
