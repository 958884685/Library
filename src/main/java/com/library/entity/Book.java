package com.library.entity;

public class Book {

	private Long id;
	private Long bookid;
	private String bookname;
	private String type;
	private String author;
	private Double price;
	private String state;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Long id, Long bookid, String bookname, String type, String author, Double price, String state) {
		super();
		this.id = id;
		this.bookid = bookid;
		this.bookname = bookname;
		this.type = type;
		this.author = author;
		this.price = price;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookid() {
		return bookid;
	}

	public void setBookid(Long bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookid=" + bookid + ", bookname=" + bookname + ", type=" + type + ", author="
				+ author + ", price=" + price + ", state=" + state + "]";
	}

}
