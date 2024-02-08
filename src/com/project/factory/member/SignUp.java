package com.project.factory.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.resource.Path;
import com.project.factory.view.MainView;

public class SignUp {

	private static final Object[] String = null;

	public static void cheackid() {
		MainView.title("회원가입");
		
		try {
			// 사원번호
			String id = "";
			// 이름
			String name = "";
			// 생년월일
			String humanNum = "";
			// 핸드폰 번호
			String phoneNum = "";
			// 주소
			String address = "";
			// 부서
			String depart = "";
			// 비밀번호
			String pw = "";

			BufferedReader reader = new BufferedReader(new FileReader(Path.NEWMEMBERTEMP));
			
			boolean loop = true;
			
			while(loop) {
				System.out.println("회원가입을 진행할 사원 번호를 입력해주세요.");
				System.out.println();   
				System.out.print("사원번호: ");
				Scanner scan = new Scanner(System.in);
				String loadId = scan.nextLine();
				System.out.println();
				MainView.singleLine();
				System.out.println();
				
				boolean result = false;
				String tempMember = "";
				String line = null;
				while ((line = reader.readLine()) != null) {
					
					String[] temp = line.split("■");
					if (temp[0].equals(loadId)) {
						result = true;
						tempMember = line;
						break;
					} 
				}
							
				if (result) {
					String[] temp = tempMember.split("■");
					System.out.println("[기존 정보]");
					System.out.println();
					id = temp[0];
					phoneNum = temp[3];
					depart = temp[6];
					name = temp[2];
					humanNum = temp[4];
					address = temp[5];
					System.out.println("이름: " + name);
					System.out.println("생년월일: " + humanNum);
					System.out.println("전화번호: " + phoneNum);
					System.out.println("주소: " + address);
					System.out.println("부서: " + depart);
					System.out.println();
					MainView.singleLine();
					System.out.println();
					
					boolean pwLoop = true;
					while(pwLoop) {
						System.out.println("비밀번호를 설정해 주세요.");
						System.out.println("비밀번호는 10-16글자, 영문자&숫자만 입력 가능합니다.");
						System.out.println();
						MainView.singleLine();
						System.out.println();
						System.out.print("비밀번호: ");
						pw = scan.nextLine();
						if (pw.matches("^[a-zA-Z0-9]{10,16}$")) {
							System.out.println();
							System.out.println("회원가입이 완료되었습니다.");
							
							BufferedReader readerDelete = new BufferedReader(new FileReader(Path.NEWMEMBERTEMP));
							
							String txt = "";
							String line2 = null;
							
							while ((line2 = readerDelete.readLine()) != null) {
//								System.out.println(tempMember);
								txt += line2.replace(tempMember, "") + "\r\n";
							}
							
							// 수정된 파일을 가지고 원본 파일에 이어쓰기
							BufferedWriter writer = new BufferedWriter(new FileWriter(Path.MEMBER, true));
							writer.write(txt);
							
							Members member = new Members(id, pw, name, humanNum, phoneNum, address, "2", depart, id + "@auto.com");
							Data.memberList.add(member);
							writer.close();
							readerDelete.close();
							
							pwLoop = false;
						} else {
							System.out.println("비밀번호는 10-16자, 영문자, 숫자만 가능합니다.");
							MainView.checkContinue();
						}
					}
					loop = false;
					MainView.pause(); // 왜 문제 ? + newMember에서 추가된 부분 삭제시키기
					break;
					
				} else {
					System.out.println("사원번호를 잘못 입력하셨습니다.");
					System.out.println();
					checkContinue();
					break;
				}
				
			}

			reader.close();
			
			
		} catch (Exception e) {
			System.out.println("몰라");
			e.printStackTrace();
		}

	}
	
	private static void checkContinue() {
		MainView.checkContinue();
		
		if (Main.answer.equals("Y") || Main.answer.equals("y")) {
			cheackid();
		} else if (Main.answer.equals("N") || Main.answer.equals("n")) {
			MainView.pause();
		} else {
			System.out.println();
			MainView.singleLine();
			System.out.println();
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			SignUp.checkContinue();
		}
	}

}
