package com.project.factory.view.dept;

import java.util.Set;

import com.project.factory.Today;
import com.project.factory.dept.production.admin.resource.ProductionTarget;
import com.project.factory.dept.production.admin.resource.TodayProduction;
import com.project.factory.dept.production.admin.resource.TodayProductionData;
import com.project.factory.member.Identify;
import com.project.factory.resource.inventory.ModelInfo;
import com.project.factory.resource.inventory.ModelInfoData;
import com.project.factory.resource.inventory.Piece;
import com.project.factory.resource.inventory.PieceData;
import com.project.factory.view.MainView;

public class ProductionView {
	
	
	//하루목표 뷰
	public static void viewModelInven() {
		int count = 1;
		Set<String> keySet = ProductionTarget.TargetNum.keySet();
		for (String model : keySet) {
			System.out.println(count + ". " + model + ": " + ProductionTarget.TargetNum.get(model) + "대");
			count++;
		}
	}
	
	/**
	 * 생산관리 View
	 */
	public static void viewproductionMenu() {
		System.out.println();
		MainView.doubleLine();
		System.out.println("\t\t\t\t생산 관리");
		MainView.doubleLine();
		System.out.println();
		System.out.println("1. 생산 시작");
		System.out.println("2. 생산 정지");
		System.out.println("3. 생산 종료");
		System.out.println();
		MainView.singnleLine();
	}
	
	

	/**
	 * 불량품 개수 입력 View
	 */
	public static void viewModel() {
		MainView.doubleLine();
		System.out.println();
		System.out.println("모델별 불량품 개수를 입력해주세요");
		int count = 1;
		for (TodayProduction modelInventory : TodayProductionData.todayProductionList) {
			System.out.printf("%d. %s: %d \n", count, modelInventory.getModel(), modelInventory.getTodayDefectiveNum());
			count++;
		}
		
		
	}
	
	public static void viewCheckPiece() {
		MainView.doubleLine();
		System.out.println();
		System.out.println("부품 개수");
		for(Piece piece : PieceData.pieceList) {
			System.out.printf("- %s : %d", piece.getPieceType(), piece.getPieceNum());
		}
	}
	
	public static void checkPieceMenu() {
		MainView.doubleLine();
		System.out.println();
		System.out.println("1. 부품 재고 파악");
		System.out.println("2. 부품 발주");
		System.out.println();
		MainView.doubleLine();
	}
	
	public static void checkPiece() {
		int wheelSum = 0;
		int frameSum = 0;
		int leatherSum = 0;
		System.out.println();
		MainView.doubleLine();
		System.out.println("\t\t\t\t부품 재고 파악 및 발주");
		MainView.doubleLine();
		System.out.println();
		for(Piece piece : PieceData.pieceList) {
			if(piece.getPieceType().equals("바퀴")) {
				wheelSum += piece.getPieceNum();
			}
		}
		for(Piece piece : PieceData.pieceList) {
			if(piece.getPieceType().equals("철판")) {
				frameSum += piece.getPieceNum();
			}
		}
		for(Piece piece : PieceData.pieceList) {
			if(piece.getPieceType().equals("가죽")) {
				leatherSum += piece.getPieceNum();
			}
		}
		System.out.printf("- 바퀴 : %d\n", wheelSum);
		System.out.printf("- 철판 : %d\n", frameSum);
		System.out.printf("- 바퀴 : %d\n", leatherSum);
		for(Piece piece : PieceData.pieceList) {
			if(piece.getPieceType().contains("엔")) {
				System.out.printf("- %s : %d\n", piece.getPieceType(), piece.getPieceNum());
			}
		}
		System.out.println();
	}
	
	public static void addPieceMenu() {
		MainView.doubleLine();
		System.out.println();
		System.out.println("[하청업체ID]\t[하청업체 이름]\t\t  [전화번호]");
		for(Piece piece : PieceData.pieceList) {
			System.out.printf("%-10s\t   %-8s\t\t%-8s\n", piece.getPieceCompanyId(), piece.getPieceCompany(), piece.getPieceCompanyPhoneNum());
		}
		System.out.println();
		MainView.doubleLine();
	}
	
	public static void productionView() {
		String slash = ":";
		
		System.out.println();
		System.out.println(Today.day());
		System.out.println();
		System.out.println(" [ 구 분 ]\t\t[ K 3 ]\t\t[ K 5 ]\t\t[ K 7 ]\t\t[ K 9 ]");
		System.out.printf("생산 목표치\t :");
		Set<String> keySet = ProductionTarget.TargetNum.keySet();
		for (String model : keySet) {
			System.out.printf( "\t%3d대\t", ProductionTarget.TargetNum.get(model));
		}
		System.out.println();
		System.out.printf("  재고량\t\t :", slash);
		for(ModelInfo model : ModelInfoData.modelInfoList) {
			System.out.printf("\t%3d대\t", model.getModelInventory());
		}
		System.out.println();
		System.out.println();
		MainView.doubleLine();
		System.out.println();
		
	}
	
	public static void productionAdminView() {
		
		System.out.println(Today.day());
		System.out.println();
		
		HumanView.countMembersBySpecificDept(Identify.dept);
		System.out.println();
		MainView.doubleLine();
		System.out.println();
	}

}
