package com.project.factory.dept.production.admin;

import java.util.Scanner;

import com.project.factory.dept.production.admin.resource.ProductionTarget;
import com.project.factory.view.MainView;
import com.project.factory.view.dept.ProductionView;

/**
 * 생산 목표 입력 클래스
 */
public class SetProductionTarget {
	public static void setproductionTarget() {
		int target = 0; // 사용자로부터 입력받는 목표량 변수
		Scanner sc = new Scanner(System.in);
		System.out.println();

		while (true) {
			MainView.title("생산 목표량 입력 및 수정");
			
			System.out.println();
			System.out.println("모델별 현재 생산 목표량");
			System.out.println();
			ProductionView.viewModelInven();
			System.out.println();
			MainView.singleLine();
			
			System.out.println("종료를 원하시면 0을 입력해주세요.");
			System.out.println();
			
			System.out.print("모델명 입력: ");
			String selectedModel = sc.next();
			if (ProductionTarget.TargetNum.containsKey(selectedModel)) {

				System.out.println();
				System.out.println("생산 목표량을 입력해주세요.");
				System.out.println();
				System.out.print(selectedModel + " : ");
				target = sc.nextInt();
				ProductionTarget.TargetNum.put(selectedModel, target);

			} else if (selectedModel.equals("0")) {
				System.out.println();
				System.out.println("생산 목표량 입력 및 수정을 종료합니다.");
				MainView.pause();
				break;

			} else {
				System.out.println();
				MainView.singleLine();
				System.out.println("입력하신 모델이 존재하지 않습니다.");
				System.out.println();
			}

		}
		ProductionTarget.save_target();
	}

}
