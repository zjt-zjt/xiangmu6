package com.lanou.AddressBook;

import java.util.List;
import java.util.Scanner;

public class mysqlTest {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		boolean isExit = false;
		while(isExit == false) {
			displayMenu();//打印菜单
			int choose = getChoose();
			switch(choose) {
			case 1:{
			  MysqlAddressBook.addContact();
				System.out.println("添加成功！");
				break;
			}
			case 2:{
				MysqlAddressBook.showAllCotacts();
				break;
			}
			case 3:{
				MysqlAddressBook.findCotactsBygroup();
				break;
			}
			case 4:{
				MysqlAddressBook.findCotactsByName();
				break;
			}
			case 5:{
				MysqlAddressBook.findCotactsBynumber();
				break;
			}
			case 6:{
				MysqlAddressBook.update();
				System.out.println("修改成功!");
				break;
			}
			case 7:{
				MysqlAddressBook.deleteContact();
				System.out.println("删除成功！");
				break;
			}
			case 8:{
				System.out.print("您确定要退出吗？（Y/N）：");
				String str = sc.nextLine();
				if(str.equalsIgnoreCase("Y")) {
					isExit = true;
					
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
