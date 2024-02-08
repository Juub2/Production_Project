package com.project.factory.view.member;

import com.project.factory.member.Identify;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.view.MainView;

public class ModifyView {

	public static void modifyMenu() {
		System.out.println();
		MainView.doubleLine();
		System.out.println("\t\t\t\t\t\t\t개인정보 조회 및 수정");
		MainView.doubleLine();

		for (Members member : Data.memberList) {
			if (member.getId().equals(Identify.auth)) {
			    System.out.printf("%-5s\t%-12S\t%-40S\t%-16S\r\n", "[이름]", "[전화번호]", "[주소]", "[비밀번호]");
				System.out.printf("%-5s\t%-12s\t%-40s\t%-16s\n", member.getName(), member.getPhoneNum(), member.getAddress(), member.getPw());
				break;
			}
		}
		MainView.singleLine();

		System.out.println();
		System.out.println("수정하고 싶은 개인정보 번호를 선택해주세요.");
		System.out.println();
		System.out.println("1. 이름");
		System.out.println("2. 전화번호");
		System.out.println("3. 주소");
		System.out.println("4. 비밀번호");
		System.out.println();
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}
}
