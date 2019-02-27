package com.library.entity;

public class Header {

	private long id;
	private String libraryname;
	private String header;
	private long iphone;
	private String email;
	private String address;
	private String time;
	private String information;

	public Header() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Header(long id, String libraryname, String header, long iphone, String email, String address, String time,
			String information) {
		super();
		this.id = id;
		this.libraryname = libraryname;
		this.header = header;
		this.iphone = iphone;
		this.email = email;
		this.address = address;
		this.time = time;
		this.information = information;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibraryname() {
		return libraryname;
	}

	public void setLibraryname(String libraryname) {
		this.libraryname = libraryname;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public long getIphone() {
		return iphone;
	}

	public void setIphone(long iphone) {
		this.iphone = iphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "Header [id=" + id + ", libraryname=" + libraryname + ", header=" + header + ", iphone=" + iphone
				+ ", email=" + email + ", address=" + address + ", time=" + time + ", information=" + information + "]";
	}

}
