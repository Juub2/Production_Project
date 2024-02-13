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
/**
 * 생산부에서 쓰이는 View 클래스이다.
 */
public class ProductionView {
	
	
	/**
	 * 하루 목표량 view이다.
	 */
	public static void viewModelInven() {
		int count = 1;
		Set<String> keySet = ProductionTarget.TargetNum.keySet();
		for (String model : keySet) {
			System.out.println(count + ". " + model + ": " + ProductionTarget.TargetNum.get(model) + "대");
			count++;
		}
	}
	
	/**
	 * 생산관리 View이다.
	 */
	public static void viewproductionMenu() {
		MainView.title("생산 관리");
		System.out.println();
		System.out.println("1. 생산 시작");
		System.out.println("2. 생산 정지");
		System.out.println("3. 생산 종료");
		System.out.println();
		MainView.singleLine();
	}
	
	

	/**
	 * 불량품 개수 입력 View
	 */
	public static void viewModel() {
		MainView.title("불량품 입력");
		System.out.println();
		int count = 1;
		for (TodayProduction modelInventory : TodayProductionData.todayProductionList) {
			System.out.printf("%d. %s: %d \n", count, modelInventory.getModel(), modelInventory.getTodayDefectiveNum());
			count++;
		}
		System.out.println();
		MainView.singleLine();
		
	}
	/**
	 * 부품개수를 보여주는 View이다.
	 */
	public static void viewCheckPiece() {
		MainView.doubleLine();
		System.out.println();
		System.out.println("부품 개수");
		for(Piece piece : PieceData.pieceList) {
			System.out.printf("- %s : %d", piece.getPieceType(), piece.getPieceNum());
		}
	}
	/**
	 * 부품 재고 파악 및 발주 메뉴 view이다.
	 */
	public static void checkPieceMenu() {
		MainView.title("부품 재고 파악 및 발주");
		System.out.println();
		System.out.println("1. 부품 재고 파악");
		System.out.println("2. 부품 발주");
		System.out.println();
		MainView.singleLine();
	}
	/**
	 * 부품별 재고 사항을 보여주는 View이다.
	 */
	public static void checkPiece() {
		MainView.title("부품 재고 파악");
		int wheelSum = 0;
		int frameSum = 0;
		int leatherSum = 0;
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
		MainView.singleLine();
	}
	/**
	 * 부품 발주 메뉴 view이다.
	 */
	public static void addPieceMenu() {
		MainView.title("부품 발주");
		System.out.println();
		System.out.println("[하청업체ID]\t[하청업체 이름]\t\t  [전화번호]");
		for(Piece piece : PieceData.pieceList) {
			System.out.printf("%-10s\t   %-8s\t\t%-8s\n", piece.getPieceCompanyId(), piece.getPieceCompany(), piece.getPieceCompanyPhoneNum());
		}
		System.out.println();
		MainView.singleLine();
	}
	/**
	 * 생산직원의 초기화면 view이다.
	 */
	public static void productionView() {
		String slash = ":";
		MainView.doubleLine();
		System.out.println();
		System.out.println(Today.day());
		System.out.println();
		System.out.println(" [ 구 분 ]\t\t[ K 3 ]\t\t[ K 5 ]\t\t[ K 7 ]\t\t[ K 9 ]");
		System.out.printf("생산 목표치\t |");
		Set<String> keySet = ProductionTarget.TargetNum.keySet();
		for (String model : keySet) {
			System.out.printf( "\t%3d대\t", ProductionTarget.TargetNum.get(model));
		}
		System.out.println();
		System.out.printf("  재고량\t |", slash);
		for(ModelInfo model : ModelInfoData.modelInfoList) {
			System.out.printf("\t%3d대\t", model.getModelInventory());
		}
		System.out.println();
		System.out.println();
		MainView.doubleLine();
		System.out.println();
		
	}
	/**
	 * 생산관리자의 초기메뉴 View이다.
	 */
	public static void productionAdminView() {
		
		System.out.println(Today.day());
		System.out.println();
		
		HumanView.countMembersBySpecificDept(Identify.dept);
		System.out.println();
		MainView.doubleLine();
		System.out.println();
	}

}
