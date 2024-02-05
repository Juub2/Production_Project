package com.project.factory.dept.production.admin;

import java.util.Scanner;

import com.project.factory.dept.management.admin.ModelManagement;
import com.project.factory.dept.production.admin.resource.ProductionTarget;
import com.project.factory.dept.production.admin.resource.TodayProduction;
import com.project.factory.dept.production.admin.resource.TodayProductionData;
import com.project.factory.resource.inventory.ModelInfo;
import com.project.factory.resource.inventory.ModelInfoData;
import com.project.factory.view.MainView;
import com.project.factory.view.dept.ProductionView;

//TODO productionManagement 클래스명 오타 수정
public class ProductionManagement {
	 static // 생산관리
	
	ProductThread production = new ProductThread();
	
	public static void productionSelect() {
		
		Scanner sc = new Scanner(System.in);
		
		ProductionView.viewproductionMenu();
		System.out.print("생산 입력: ");
		int sel = sc.nextInt();
		
		if (sel == 1) {
			if(production.isStarted()) {
				MainView.pause();
				if(production.isStopRequested()) {
					production.setStopRequested(false);
				}else {
					System.out.println("이미 실행중");
					
				}
			}else if(production.isStarted() == false){
				production.start();
				MainView.pause();
				production.setStarted(true);
				
			}
		} else if (sel == 2) {
			production.setStopRequested(true);
			MainView.pause();
		} else if (sel == 3) {
			for(TodayProduction today : TodayProductionData.todayProductionList) {
				System.out.printf("%s : %d대\n", today.getModel(), today.getTodayProductNum());
			}
			setRejectproduct();
			stopProduction();
			production.setTerminate(false);
		} else {
			System.out.println("잘못된입력");
			MainView.pause();
		}
	}
	

	
	private static void stopProduction() {
		
		// 생산 정지 하루 재고 -> 재고량
		for (ModelInfo model : ModelInfoData.modelInfoList) {
			for(TodayProduction today : TodayProductionData.todayProductionList) {
				if(model.getModelName().equals(today.getModel()))
					model.setModelInventory(today.getTodayProductNum()-today.getTodayDefectiveNum());
				
			}

			
		}
		

		// 하루 목표량 -> 0
		for (String key : ProductionTarget.TargetNum.keySet()) {
			ProductionTarget.TargetNum.put(key, 0);

		}
		System.out.println("생산이 종료되었습니다. 오늘도 고생하셨습니다.");
		System.out.println();
		MainView.doubleLine();
		System.out.println("재고 현황");
		for(ModelInfo model : ModelInfoData.modelInfoList) {
			System.out.printf("%s : %d대 ", model.getModelName(), model.getModelInventory());
		}
		MainView.pause();
	}

	private static void setRejectproduct() {
		// 불량품 개수 입력
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		// 불량품 개수
		while (loop) {
			ProductionView.viewModel();
			System.out.print(" 모델 입력(종료. 0): ");
			String sel = sc.nextLine();
			if(sel.equals("0")) {
				loop = false;
				break;
			}
		
			for(TodayProduction product : TodayProductionData.todayProductionList) {
				if(product.getModel().equals(sel)) {
					System.out.printf("%s : ",product.getModel());
					int rejectNum = sc.nextInt();
					product.setTodayDefectiveNum(rejectNum);
				}
				
			}
		}
		MainView.pause();

	}

}// class




