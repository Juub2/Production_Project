package com.project.factory.view.dept;

import java.util.Scanner;

import com.project.factory.dept.distribution.admin.OrderAssign;
import com.project.factory.view.MainView;

public class OrderView {

	public static void orderView() {
			System.out.println();
			System.out.println("1. 주문서 확인");
			System.out.println("2. 배정 수정");
			System.out.println();
			MainView.singleLine();
			System.out.print("번호 입력: ");
		}
	
	}// orderView()




