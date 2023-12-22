	package com.sunbeam.pojos;
	
	public class Movie
	{
	private int id;
	private String title;
	private String releasedate;
	
	public Movie(int id, String title, String releasedate) {
	this.id = id;
	this.title = title;
	this.releasedate = releasedate;
	}
	
	public Movie() {
	
	}
	
	public int getId() {
	return id;
	}
	
	public void setId(int id) {
	this.id = id;
	}
	
	public String getTitle() {
	return title;
	}
	
	public void setTitle(String title) {
	this.title = title;
	}
	
	public String getreleasedate() {
	return releasedate;
	}
	
	public void setreleasedate(String releasedate) {
	this.releasedate = releasedate;
	}
	
	@Override
	public String toString() {
	return "Movies [id=" + id + ", title=" + title + ", releasedate=" + releasedate + "]";
	
	}
	
	}