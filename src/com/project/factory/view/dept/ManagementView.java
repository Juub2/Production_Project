package com.project.factory.view.dept;

import com.project.factory.Today;
import com.project.factory.member.Identify;
import com.project.factory.resource.CommuteData;
import com.project.factory.resource.sub.Order;
import com.project.factory.resource.sub.OrderData;
import com.project.factory.view.MainView;

/**
 * 경영부 관련 화면을 관리하는 클래스이다.
 */
public class ManagementView {

	/**
     * 경영부 관리자 화면에 오늘 출근한 경영부 인원을 출력하는 메서드이다.
     */
	public static void managementAdminView() {
		
		CommuteData.load();
		OrderData.load();
		
		System.out.println(Today.day());
		System.out.println();
		
		HumanView.countMembersBySpecificDept(Identify.dept); //경영부 출근 인원
		System.out.println();
		
		ManagementView.totalOrderNum();
		
		System.out.println();
		MainView.doubleLine();
		System.out.println();
		
	}
	
	/**
     * 신규 주문과 누적 주문 수를 출력하는 메서드이다.
     */
	private static void totalOrderNum() {
		int count = 0; //신규 주문
		
		for(Order order : OrderData.orderList) {
			if(order.getWriteDate().equals(Today.day())) {
				count++;
			}	
		}
		
		System.out.printf("신규 주문: %d\n", count);
		System.out.printf("누적 주문: %d\n", OrderData.orderList.size() - count);
	}

}
