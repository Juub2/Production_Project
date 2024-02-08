package com.project.factory.member;

import com.project.factory.view.MainView;

public class Logout {
	
	public static void logout(){
		MainView.title("로그아웃");
		Identify.auth = null;
		Identify.dept = null;
		Identify.level = null;
		
		System.out.println();
		System.out.println("로그아웃되었습니다.");
		MainView.pause();
	}
	
//	MainView.pause;
	
	
}
