package com.project.factory.view.sub;

import com.project.factory.member.Identify;
import com.project.factory.resource.sub.Order;
import com.project.factory.resource.sub.OrderData;
import com.project.factory.view.MainView;

/**
 * 주문서 관리 화면을 관리하는 클래스이다.
 */
public class MyOrderView {

	/**
     * 주문서 관리 메뉴를 출력하는 메서드이다.
     */
	public static void myOrderMenu() {
		MainView.title("주문서 관리");

		System.out.println();
		System.out.println("1. 주문서 작성");
		System.out.println("2. 주문서 수정");
		System.out.println("3. 주문서 삭제");
		System.out.println("4. 주문서 조회");
		System.out.println();
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	/**
     * 주문서 작성 메뉴를 출력하는 메서드이다.
     */
	public static void orderAddMenu() {
		MainView.title("주문서 작성");

		MyOrderView.modelView();

		System.out.println("주문하실 모델 번호를 입력해주세요.");
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	/**
     * 주문서 수정 메뉴를 출력하는 메서드이다.
     */
	public static void orderEditMenu() {
		MainView.title("주문서 수정");

		MyOrderView.orderListView();

		System.out.println();
		MainView.singleLine();
		System.out.println("수정하실 주문서 번호를 입력해주세요.");
		System.out.println();
		System.out.print("번호 입력: ");
	}
	
	/**
     * 주문서 삭제 메뉴를 출력하는 메서드이다.
     */
	public static void orderDeleteMenu() {
		MainView.title("주문서 삭제");
		
		MyOrderView.orderListView();
		
		System.out.println();
		MainView.singleLine();
		System.out.println("삭제하실 주문서 번호를 입력해주세요.");
		System.out.println();
		System.out.print("번호 입력: ");
	}
	
	 /**
     * 주문서 조회 메뉴를 출력하는 메서드이다.
     */
	public static void orderViewMenu() {
		MainView.title("주문서 조회");
		
		System.out.println();
		MyOrderView.orderView();
		MainView.singleLine();
	}

	/**
     * 모델을 출력하는 메서드이다.
     */
	private static void modelView() {
		System.out.println();
		System.out.println("1. K3");
		System.out.println("2. K5");
		System.out.println("3. K7");
		System.out.println("4. K9");
		System.out.println();
	}

	 /**
     * 현재 로그인한 사용자가 작성한 주문서 목록을 출력하는 메서드이다.
     */
	private static void orderListView() {
		System.out.println();
		System.out.printf("%-8s\t%-5S\t%-4S\r\n", "[주문서 번호]", "[모델]", "[수량]");

		for (Order order : OrderData.orderList) {
			if (order.getAgencyName().equals(Identify.name)) {
				System.out.printf("%-8s\t%-5S\t%-4d\n", order.getId(), order.getModelId(), order.getQuantity());
			}
		}
	}

	/**
     * 모델별로 주문서를 조회하는 메서드이다.
     */
	private static void orderView() {
		myOrderViewList("K3");
		System.out.println();
		myOrderViewList("K5");
		System.out.println();
		myOrderViewList("K7");
		System.out.println();
		myOrderViewList("K9");
	}

	/**
	 * 주문서 목록을 출력하는 메서드이다.
	 * @param model 목록을 출력할 모델명
	 */
	private static void myOrderViewList(String model) {
		boolean loop = false;
		
		System.out.println(model + " 주문서");
		System.out.printf("%-8s\t%-10S\t%-4S\t%-10S\r\n","[주문서 번호]", "[주문일]", "[수량]", "[납기일]");
		
		for (Order order : OrderData.orderList) {
			if (order.getAgencyName().equals(Identify.name)) {// 주문자가 본인인 경우만
				if (order.getModelId().equals(model)) {
					System.out.printf("%-8s\t%-10S\t%-4d\t%-10S\n", order.getId(), order.getWriteDate(), order.getQuantity(), order.getDueDate());
					loop = true;
				}
			}
		}
		if(!loop) {
			System.out.println("해당 모델에 대한 주문이 존재하지 않습니다.");
		}
		System.out.println();
	}// myOrderViewList

	 
}
