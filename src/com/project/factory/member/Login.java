package com.project.factory.member;

import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.view.MainView;

//TODO checkContinue 유효성 검사시 반복문 돌음
public class Login {
	public static void login() {
		int checkId = 0;
		int checkPw = 0;
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 입력: ");
		String id = scan.nextLine();
		System.out.print("비밀번호 입력: ");

		String pw = scan.nextLine();
		if (id == "" || pw == "") {
			System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			login();
		}

		for (Members member : Data.memberList) {
			if (member.getId().equals(id) && member.getPw().equals(pw)) {
				Identify.auth = id;
				Identify.level = member.getLevel();
				Identify.dept = member.getDept();
				Identify.name = member.getName();
				return;

			} else if (pw.equals(member.getPw())) {
				checkId++;
			} else if (id.equals(member.getId())) {
				checkPw++;

			}

		} // for문

		if (checkId > checkPw) {
			System.out.println("아이디를 잘못 입력했습니다.");
			checkContinue();
		} else if (checkId < checkPw) {
			System.out.println("비밀번호를 잘못 입력했습니다.");
			checkContinue();

		} else if (checkId < 1 && checkPw < 1) {
			System.out.println("아이디와 비밀번호를 잘못 입력했습니다.");
			checkContinue();

		}
	}

	private static void checkContinue() {
		MainView.checkContinue();
		
		if (Main.answer.equals("Y") || Main.answer.equals("y")) {
			login();
		} else if (Main.answer.equals("N") || Main.answer.equals("n")) {
			MainView.pause();
		} else {
			System.out.println();
			MainView.singleLine();
			System.out.println();
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			Login.checkContinue();
		}
	}
}
