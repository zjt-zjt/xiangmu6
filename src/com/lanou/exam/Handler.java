package com.lanou.exam;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;







public class Handler {
	private List<ExamItem>items;
	private char[] answers;
	private int score = 0;
	public Handler() throws Exception {
		super();
		initData();
	}
	
	public  void initData() throws Exception {
		String sql= "select eid id,equestion question,eanswer1 answer1,eanswer2 answer2 ,eanswer3 answer3, eanswer4 answer4,eanswer answer from exam";
		List<question> list = jdbcUtils.query(sql, question.class);
		items = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			ExamItem item = new ExamItem(list.get(i).getQuestion(),list.get(i).getAnswer1(),list.get(i).getAnswer2(),list.get(i).getAnswer3(),list.get(i).getAnswer4(),list.get(i).getAnswer());
			items.add(item);
		}
		
		answers = new char[items.size()];
		
	}
	
	public ExamItem getExamItemOfIndex(int index) {
		return items.get(index);
	}
	
	public void writeAnswerOfIndex(int index, char answer) {
		answers[index] = answer;
	}
	
	public void displayHelp() {
		System.out.println();
        System.out.println("-----------欢迎进入考试-----------");
        System.out.println();
        System.out.println("       使用以下按键进行考试：");
       System.out.println();
        System.out.println("        A-D：选择指定答案");
        System.out.println("        P  ：显示上一题");
        System.out.println("        N  ：显示下一题");
        System.out.println("        F  ：结束考试");
       System.out.println();
        System.out.print("        请按N键进入考试...");
        char choose = 0;
        while(true) {
        	choose = getUserAction();
        	if(choose == 'N') {
        		break;
        	}
        }
        startExam();
	}
	public void startExam() {
		int currentIndex = 0;
		boolean isFinish = false;
		while(isFinish == false) {
			ExamItem item = getExamItemOfIndex(currentIndex);
			System.out.println(item);
			char answer = answers[currentIndex];
			if(answer != 0) {
				System.out.println("本题您已经作答，您的答案是" + answer);
			}
		char choose = getUserAction();
			switch(choose) {
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			{
			writeAnswerOfIndex(currentIndex, choose);
				if(currentIndex < 9) {
					currentIndex++;
				}
				break;
			}
			case 'N':
			{
				if(currentIndex < 9) {
					currentIndex++;
				}
				break;
			}
			case 'F':
			{
				judgeExamPaper();//判卷
				saveAnswer();//存储成绩
				isFinish = true;
				break;
			}
			case 'P':
			{
				if(currentIndex > 0) {
					currentIndex--;
				}
				break;
			}
			
			}
		}
		
		
	}

	public char getUserAction() {
		char[] rightKeys = {'A','B','C','D','F','N','P','Y'};
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		char key = 0;
		while(flag) {
			String str = sc.nextLine();
		key = str.toUpperCase().charAt(0);
		for(int i = 0;i < rightKeys.length; i++) {
				if(key == rightKeys[i]) {
					flag = false;
				break;
				}
			}
		}
		return key;
	}
	
	public void judgeExamPaper() {
		for(int i = 0; i < items.size(); i++) {
			ExamItem item = items.get(i);
			String answer = "" + answers[i];
			if(answer.equals(item.getAnswer())) {
				score += 10;
			}
		}
	}
	
	public void saveAnswer() {
		try(FileWriter fos = new FileWriter("answer.txt");
				BufferedWriter bw = new BufferedWriter(fos);) {
			bw.write(Arrays.toString(answers)+",得分是：" + score);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
