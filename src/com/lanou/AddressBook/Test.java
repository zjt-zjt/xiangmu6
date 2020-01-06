package com.lanou.AddressBook;

import java.util.List;
import java.util.Scanner;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Test {
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		Scanner sc = new Scanner(System.in);
		AddressBook ab = new AddressBook();
		boolean isExit = false;
		while(isExit == false) {
			displayMenu();//打印菜单
			int choose = getChoose();
			switch(choose) {
			case 1:{
				System.out.print("请输入联系人的姓名:");
				String name = sc.nextLine();
				System.out.print("请输入手机号码：");
				String phone = sc.nextLine();
				Contact c = new Contact(name, phone);
				ab.addContact(c);
				System.out.println("添加成功！");
				break;
			}
			case 2:{
				ab.showAllCotacts();
				break;
			}
			case 3:{
				System.out.print("请输入您要查询的分组：");
				String group = sc.nextLine();
				List<Contact>list = ab.findContactsByGroup(group);
				ab.showCotacts(list);
				break;
			}
			case 4:{
				System.out.print("请输入要查询的联系人的姓名关键字:");
				String name = sc.nextLine();
				List<Contact>list = ab.findCotactsByName(name);
				ab.showCotacts(list);
				break;
			}
			case 5:{
				System.out.print("请输入要查询的联系人的手机号码关键字:");
				String phone = sc.nextLine();
				List<Contact>list = ab.findCotactsByPhone(phone);
				ab.showCotacts(list);
				break;
			}
			case 6:{
				System.out.print("请输入您要修改的联系人的姓名：");
				String name = sc.nextLine();
				System.out.print("请输入新的姓名：");
				String newName = sc.nextLine();
				System.out.print("请输入新的电话:");
				String phone = sc.nextLine();
				Contact c = new Contact(newName, phone);
				ab.modifyContact(name, c);
				System.out.println("修改成功!");
				break;
			}
			case 7:{
				System.out.print("请输入想要删除的联系人的姓名(全名)：");
				String name = sc.nextLine();
				ab.deleteContact(name);
				System.out.println("删除成功！");
				break;
			}
			case 8:{
				System.out.print("您确定要退出吗？（Y/N）：");
				String str = sc.nextLine();
				if(str.equalsIgnoreCase("Y")) {
					isExit = true;
					ab.saveToFile();
				}
				break;
			}
			}
		}
		
		
	}
	
	public static void displayMenu() {
		System.out.println("=======通讯录=======");
		System.out.println("1.添加联系人");
		System.out.println("2.查看全部联系人");
		System.out.println("3.根据分组查看联系人");
		System.out.println("4.根据姓名关键字查看联系人");
		System.out.println("5.根据电话关键字查看联系人");
		System.out.println("6.修改联系人");
		System.out.println("7.删除联系人");
		System.out.println("8.退出");
	}
	
	public static int getChoose() {
		System.out.print("请输入您的选择(1-8)：");
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		while(true) {
			if(choose < 1 || choose > 8) {
				System.out.print("您输入有误，请重新输入：");
				choose = sc.nextInt();
			}else {
				break;
			}
		}
		return choose;
	}
	
	
	
}
