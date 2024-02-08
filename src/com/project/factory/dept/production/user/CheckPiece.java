package com.project.factory.dept.production.user;

import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.resource.inventory.Piece;
import com.project.factory.resource.inventory.PieceData;
import com.project.factory.view.MainView;
import com.project.factory.view.dept.ProductionView;

public class CheckPiece {

	public static void pieceMenu() {
		Scanner sc = new Scanner(System.in);
		ProductionView.checkPieceMenu();
		System.out.print("번호 입력: ");
		int sel = sc.nextInt();
		if (sel == 1) {
			checkPiece();
			MainView.pause();
		} else if (sel == 2) {
			addPiece();
		} else {
			System.out.println("잘못된 번호입니다.");
			MainView.pause();
		}

	}

	public static void checkPiece() {
		ProductionView.checkPiece();

	}

	private static void addPiece() {
		Scanner sc = new Scanner(System.in);
		ProductionView.addPieceMenu();
		String addPieceType = "";
		System.out.print("해당업체 ID: ");
		String companyID = sc.nextLine();
		System.out.println();

		for (Piece piece : PieceData.pieceList) {
			if (piece.getPieceCompanyId().equals(companyID)) {
				addPieceType = piece.getPieceType();
				break;
			}
			
		}
		
		System.out.printf("%s의 수량: ", addPieceType);
		int plusPieceNum = sc.nextInt();
		System.out.println();
		System.out.print("해당 내용으로 발주를 진행하시겠습니까? (Y/N): ");
		String result = sc.next();
	
			
		if (result.equals("Y") || result.equals("y")) {
			for (Piece piece : PieceData.pieceList) {
				if (piece.getPieceType().equals(addPieceType)) {
					piece.setPieceNum(piece.getPieceNum() + plusPieceNum);
					System.out.println();
					System.out.println("발주가 완료되었습니다.");
					System.out.println("부품 입고는 2일정도 소요됩니다.");
				}
			}
			MainView.pause();
			return;
			
		}else if(result.equals("N") || result.equals("n")) {
			System.out.println("발주가 취소되었습니다.");
		}else {
			CheckPiece.checkContinue();
			
		}
		
	}

	private static void checkContinue() {
		MainView.checkContinue();

		if (Main.answer.equals("Y") || Main.answer.equals("y")) {
			addPiece();
		} else if (Main.answer.equals("N") || Main.answer.equals("n")) {
			MainView.pause();
		} else {
			System.out.println();
			MainView.singleLine();
			System.out.println();
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			CheckPiece.checkContinue();
		}
	}
	

}
