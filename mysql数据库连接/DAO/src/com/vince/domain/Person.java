package com.vince.domain;

public class Person {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	private int id;
	private String name;
	private int age;
	private String des;
	public Person(int id, String name, int age, String des) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.des = des;
	}
	@Override
	public String toString() {
		return "Person [age=" + age + ", des=" + des + ", id=" + id + ", name="
				+ name + "]";
	}
	public Person(String name, int age, String des) {
		super();
		this.name = name;
		this.age = age;
		this.des = des;
	}
	public Person() {
		// TODO Auto-generated constructor stub
	}
}
