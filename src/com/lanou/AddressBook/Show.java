package com.lanou.AddressBook;

public class Show {
    private int id ;
    private String group1;
    private String name;
    private String number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroup() {
		return group1;
	}
	public void setGroup(String group) {
		this.group1 = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Show(int id, String group, String name, String number) {
		super();
		this.id = id;
		this.group1 = group;
		this.name = name;
		this.number = number;
	}
	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "show [ " + group1 + " " + name + " " + number + "]";
	}
	
	
}
