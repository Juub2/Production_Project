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
		while (true) {
			MainView.doubleLine();
			System.out.println();
			System.out.println("모델별 생산 목표량을 입력해주세요.");
			System.out.println();
			ProductionView.viewModelInven();
			System.out.println("종료를 원하시면  0을 입력하시오.");
			System.out.print("모델입력: ");
			String selectedModel = sc.next();
			if (selectedModel.equals("0")) {
				break;
			}
			if (ProductionTarget.TargetNum.containsKey(selectedModel)) {
				try {
					System.out.print(selectedModel + " : ");
					target = sc.nextInt();
					ProductionTarget.TargetNum.put(selectedModel, target);
					// 숫자가 아닌값 입력시 catch
				} catch (Exception e) {
					MainView.singleLine();
					System.out.println("숫자만 입력하세요");
					System.out.println();
					MainView.pause();
				}

			} else {
				MainView.singleLine();
				System.out.println("입력하신 모델이 존재하지 않습니다.");
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
				String selectedModel1 = sc.nextLine();
				if (ProductionTarget.TargetNum.containsKey(selectedModel1)) {
					try {
						System.out.println();
						System.out.print(selectedModel1 + " : ");
						target = sc.nextInt();
						ProductionTarget.TargetNum.put(selectedModel1, target);
						// 숫자가 아닌값 입력시 catch
					} catch (Exception e) {
						MainView.singnleLine();
						System.out.println("숫자만 입력하세요");
						System.out.println();
						MainView.pause();
					}

				} else {
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
	}
		System.out.println();

		while (true) {
			MainView.doubleLine();
			System.out.println("\t\t\t\t생산 목표량 입력 및 수정");
			MainView.doubleLine();
			System.out.println("모델별 생산 목표량을 입렵해주세요.");
			System.out.println();
			ProductionView.viewModelInven();
			System.out.println();
			System.out.println("종료를 원하시면 0을 입력해주세요.");
			System.out.print("모델 입력: ");
			String selectedModel = sc.next();
			if (ProductionTarget.TargetNum.containsKey(selectedModel)) {

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
