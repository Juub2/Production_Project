package com.project.factory.view.dept;

import com.project.factory.Main;
import com.project.factory.member.Identify;
import com.project.factory.view.MainView;

//TODO 근태 등록 뷰 클래스 추가
public class commutePushView {
	
	//TODO 근태 등록 뷰 메서드
	public static void commutePushViewMain() {
		System.out.println();
		MainView.doubleLine();
		System.out.println("       근태 등록");
		MainView.doubleLine();
	}

	//TODO 근태 등록 뷰 메서드 (출근을 하지 않은 경우)
	public static void notCommutePushView() {
		

		//로그인이 안 된 상태이면
			System.out.println();
			System.out.println("1.출근하기");
			System.out.println();
			MainView.singleLine();
			
			
			System.out.print("번호 입력: ");
			
	}
	
	
	public static void commutePushViewEnd() {
		

		//로그인이 된 상태이면
			System.out.println();
			System.out.println("2.퇴근하기");
			System.out.println();
			MainView.singleLine();
			
			
			System.out.print("번호 입력: ");
			
	}
	
	public static void alreadyCommute() {
			System.out.println();
			System.out.println("이미 출/퇴근을 완료했습니다.");
			System.out.println();
			MainView.pause();
	}
}
