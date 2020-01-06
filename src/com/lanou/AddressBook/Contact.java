package com.lanou.AddressBook;

import java.io.Serializable;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Contact  implements Serializable {
	static private Pinyin4j py = new Pinyin4j();
	private String name;	//姓名
	private String group;	//分组
	private String phone;	//电话
	public String getName() {
		return name;
	}
	public void setName(String name) throws BadHanyuPinyinOutputFormatCombination {
		this.name = name;
		this.group = py.toPinYinUppercaseInitials(name);
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(String name, String phone) throws BadHanyuPinyinOutputFormatCombination {
		super();
		this.name = name;
		this.phone = phone;
		this.group = py.toPinYinUppercaseInitials(name);
	}
	@Override
	public String toString() {
		return name + "(" + phone + ")";
	}
	
}
