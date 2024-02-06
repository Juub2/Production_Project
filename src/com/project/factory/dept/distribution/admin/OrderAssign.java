package com.project.factory.dept.distribution.admin;


import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.dept.distribution.user.Assign;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.resource.dept.AssignData;
import com.project.factory.resource.dept.DistributionData;
import com.project.factory.resource.sub.Order;
import com.project.factory.resource.sub.OrderData;
import com.project.factory.view.MainView;
import com.project.factory.view.dept.OrderView;

public class OrderAssign {
	// OrderAssign.orderView();

	public static void OrderAssign() {

		OrderView.orderTitle();
		OrderView.orderView();

		Scanner scan = new Scanner(System.in);

		System.out.print("번호 입력: ");
		
		Main.selectNum = scan.nextLine();
		
		if (Main.selectNum.equals("1")) {
			// 주문서 확인 화면 넘어가기
			OrderAssign.checkOrder();
			System.out.println();

		} else if (Main.selectNum.equals("2")) {
			// 배정 수정 화면 넘어가기
			
			MainView.doubleLine();
			
			System.out.println("[배정된 주문서]");
			
			assignPrint("k3");
			System.out.println();
			assignPrint("k5");
			System.out.println();
			assignPrint("k7");
			System.out.println();
			assignPrint("k9");
			System.out.println();
			
			MainView.singnleLine();
			
			modifyOrder(scan);
			
			
			

		} else if (Main.selectNum.equals("\n")) {
			// 엔터 누르면 초기 화면 넘어가기

		} else {
			System.out.println("다시 입력하세요.");
		}

	}

	private static void modifyOrder(Scanner scan) {
		
		AssignData.load();
		
		System.out.println("수정할 주문서 번호: ");
		
		String orderId = scan.nextLine();
		
		for (Assign assignOrder : AssignData.assignList) {
			if(assignOrder.getId().equals(orderId)) {
				System.out.println("[주문서 번호]\t[날짜]\t\t[대리점 명칭]\t[주소]\t\t\t\t\t[수량]\t[납기일]\t[사원 이름]\t[사원 번호]");
				System.out.printf("%s\t%s\t%s\t%s\t\t%d\t%s\t%s\t%s\n", assignOrder.getId(), assignOrder.getWriteDate(),
						assignOrder.getAgencyName(), assignOrder.getAgencyAddress(), assignOrder.getQuantity(),assignOrder.getDueDate(),assignOrder.getUserName(),assignOrder.getUserId());
			
			
			}
		}
		
		MainView.singnleLine();
		
		modifyUserId(scan);
		
		
		
		
	}

	private static void modifyUserId(Scanner scan) {
		
		String userName = "";
		
		System.out.println();
		System.out.println("해당 주문을 담당할 사원번호: ");
		
		String assignUserId = scan.nextLine();
		
		for(Members member : Data.memberList) {
			//[사원번호]     	 [이름]     	 [생년월일]             [전화번호]              [주소]
			if(member.getId().equals(assignUserId)) {
				System.out.println("[사원번호]\t[이름]\t\t[생년월일]\t[전화번호]\t\t[주소]");
				System.out.printf("%s\t%s\t%s\t%s\t%s\n"
														,member.getId()
														,member.getName()
														,member.getBirth()
														,member.getPhoneNum()
														,member.getAddress());
				userName = member.getName();
			}
			
		
			
		}
		
		System.out.println();
		MainView.singnleLine();
		
		System.out.println("해당 직원으로 진행하시겠습니까?(Y/N)");
		
		Main.answer = scan.nextLine().toUpperCase();
		
		if(Main.answer.equals("Y")) {
			System.out.println();
			
			changeAssignUser(assignUserId, userName);
			
		}
		
	}

	private static void changeAssignUser(String userId, String userName) {
		AssignData.load();
		
		for (Assign assignOrder : AssignData.assignList) {
		
				if(assignOrder.getUserId().equals(userId)){
					assignOrder.setUserId(userId);
					assignOrder.setUserName(userName);
					System.out.println("[주문서 번호]\t[날짜]\t\t[대리점 명칭]\t[주소]\t\t\t\t\t[수량]\t[납기일]\t[사원 이름]\t[사원 번호]");
					System.out.printf("%s\t%s\t%s\t%s\t\t%d\t%s\t%s\t%s\n", assignOrder.getId(), assignOrder.getWriteDate(),
							assignOrder.getAgencyName(), assignOrder.getAgencyAddress(), assignOrder.getQuantity(),assignOrder.getDueDate(),assignOrder.getUserName(),assignOrder.getUserId());
					
					
					
					
				
			}
		}
		
		
		
	}

	public static void checkOrder() {

		// 주문서 리스트 불러오기
		
		Scanner scan = new Scanner(System.in);

		System.out.println();
		MainView.doubleLine();
		System.out.println();
		orderList("k3");
		System.out.println();
		orderList("k5");
		System.out.println();
		orderList("k7");
		System.out.println();
		orderList("k9");
		System.out.println();

		MainView.singnleLine();
		System.out.println();
		System.out.println("배정하시겠습니까?(Y/N)?");
		
		
		
		Main.answer = scan.nextLine().toUpperCase();
		
		if(Main.answer.equals("Y")) {
			
			assign();
			assignPrint("k3");
			System.out.println();
			assignPrint("k5");
			System.out.println();
			assignPrint("k7");
			System.out.println();
			assignPrint("k9");
			System.out.println();
			
			System.out.println("배정이 완료되었습니다.");
			MainView.pause();
		} 
		

		

	}// checkOrder()

	private static void assignPrint(String model) {
		
		AssignData.load();
		
		System.out.println("[주문서 번호]\t[날짜]\t\t[대리점 명칭]\t[주소]\t\t\t\t\t[수량]\t[납기일]\t[사원 이름]\t[사원 번호]");
		
		for (Assign assignOrder : AssignData.assignList) {
			//주문서번호■주문서 작성일■대리점명■대리점 주소■전화번호■개수■납기일■모델명 사원이름 아이디
			if(assignOrder.getModelId().equals(model)) {
				System.out.printf("%s\t%s\t%s\t%s\t\t%d\t%s\t%s\t%s\n", assignOrder.getId(), assignOrder.getWriteDate(),
						assignOrder.getAgencyName(), assignOrder.getAgencyAddress(), assignOrder.getQuantity(),assignOrder.getDueDate(),assignOrder.getUserName(),assignOrder.getUserId());
			
			}
		}
		
		
	}

	private static void assign() {
	    DistributionData.load(); // 사원 정보 로드
	    OrderData.load(); // 주문 정보 로드
	    for (Order orderItem : OrderData.orderList) {
	    	
	        boolean assigned = false; // 사원이 이미 배정되었는지 확인하는 변수
	        for (EmployeeInfo employeeInfo : DistributionData.distributionMembersList) {
	        	
	            if (orderItem.getAgencyAddress().contains(employeeInfo.getAssignedRegion())) {
	                
	                assigned = true;
	                
	                // 주문서에 사원을 배정하고 종료
	                Assign newAssignment = new Assign(
	                        orderItem.getId(),
	                        orderItem.getWriteDate(),
	                        orderItem.getAgencyName(),
	                        orderItem.getAgencyAddress(),
	                        orderItem.getAgencyPhoneNum(),
	                        orderItem.getQuantity(),
	                        orderItem.getDueDate(),
	                        orderItem.getModelId(),
	                        employeeInfo.getName(),
	                        employeeInfo.getId(),
	                        employeeInfo.getAssignedRegion()
	                       
	                       
	                       
	                        
	                );

	                AssignData.assignList.add(newAssignment);
	                break;
	            }
	        }
	        
	    }
	  
	    	
	    
	    // 데이터 추가 후 저장
	    AssignData.save();
	}



	
	private static void orderList(String model) {

		OrderData.load();

		System.out.println("[주문서 번호]\t[날짜]\t\t[대리점 명칭]\t[주소]\t\t\t\t\t[수량]\t[납기일]");

		for (Order orderItem : OrderData.orderList) {

			if (orderItem.getModelId().equals(model)) {
				System.out.printf("%s\t\t%s\t%s\t%s\t%d\t%s\n", orderItem.getId(), orderItem.getWriteDate(),
						orderItem.getAgencyName(), orderItem.getAgencyAddress(), orderItem.getQuantity(),
						orderItem.getDueDate());
			}
		}

	}// orderList()


	


}
