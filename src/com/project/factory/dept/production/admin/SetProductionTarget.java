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
		int target = 0; //사용자로부터 입력받는 목표량 변수
		Scanner sc = new Scanner(System.in);
		System.out.println();
		MainView.doubleLine();
		System.out.println("\t\t\t\t생산 목표량 입력 및 수정");
		MainView.doubleLine();
		System.out.println();
		System.out.println("모델별 생산 목표량을 입렵해주세요.");
		System.out.println();
		ProductionView.viewModelInven();
		
		System.out.println();
		System.out.print("모델 입력: ");
		String selectedModel = sc.nextLine();
		if(ProductionTarget.TargetNum.containsKey(selectedModel)) {
			try {
				System.out.println();
				System.out.print(selectedModel +" : ");
				target = sc.nextInt();
				ProductionTarget.TargetNum.put(selectedModel, target);
			//숫자가 아닌값 입력시 catch
			} catch (Exception e) {
				MainView.singnleLine();
				System.out.println("숫자만 입력하세요");
				System.out.println();
				MainView.pause();
			}
			
		}else {
			MainView.singnleLine();
			System.out.println("입력하신 모델이 존재하지 않습니다.");
		}
		
		ProductionTarget.save_target();
		System.out.println();
		MainView.singnleLine();
		ProductionView.viewModelInven();
		MainView.pause();
	}
	


}
