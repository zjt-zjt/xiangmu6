package com.lanou.exam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class Exam {
	public static void main(String[] args) throws Exception {
		Handler eh = new Handler();
		boolean isExit = false;
		while(isExit == false) {
			displayMenu();
			int choose = getChoose();
			switch(choose) {
				case 1:{
					eh.displayHelp();
					break;
				}
				case 2:{
					readLastScore();
					break;
				}
				case 3:{
					isExit = true;
					break;
				}
			}
		}
		
	}
	
	public static void readLastScore() {
		File f = new File("answer.txt");
		if(f.exists() == false) {
			System.out.println("没有上次的成绩，请先考试。");
		}else {
			BufferedReader br = null;
			try {
				FileReader fr = new FileReader(f);
				br = new BufferedReader(fr);
				String content = br.readLine();
				System.out.println(content);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	public static void displayMenu() {
		System.out.println("===================");
		System.out.println("xxx在线考试系统");
		System.out.println("1.进入考试");
		System.out.println("2.查看上次考试成绩");
		System.out.println("3.退出系统");
		System.out.print("请选择：");
	}
	
	public static int getChoose() {
		Scanner sc = new Scanner(System.in);
		int choose = 0;
		while(true) {
			choose = sc.nextInt();
			if(choose > 0 && choose < 4) {
				break;
			}
		}
		return choose;
	}
	

}
