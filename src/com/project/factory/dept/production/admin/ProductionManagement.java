package com.project.factory.dept.production.admin;

import java.util.Scanner;

import com.project.factory.dept.production.admin.resource.ProductionTarget;
import com.project.factory.dept.production.admin.resource.TodayProduction;
import com.project.factory.dept.production.admin.resource.TodayProductionData;
import com.project.factory.resource.inventory.ModelInfo;
import com.project.factory.resource.inventory.ModelInfoData;
import com.project.factory.resource.inventory.Piece;
import com.project.factory.resource.inventory.PieceData;
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
			if (production.isStarted()) {
				MainView.pause();
				if (production.isStopRequested()) {
					production.setStopRequested(false);
				}
			} else if (production.isStarted() == false) {
				production.start();
				System.out.println();
				System.out.println("생산이 시작되었습니다.");

				MainView.pause();
				production.setStarted(true);
				production.setTerminate(true);
				System.out.println("이미 실행중");

			}
		} else if (sel == 2) {
			production.setStopRequested(true);

			System.out.println();
			System.out.println("생산이 정지되었습니다.");

			MainView.pause();
		} else if (sel == 3) {
			if (production.isStarted() == false) {
				System.out.println();
				System.out.println("생산이 시작되지 않았습니다.");
				MainView.pause();
				return;
			}

			production.setTerminate(false);
			setRejectproduct();
			stopProduction();
			for (TodayProduction today : TodayProductionData.todayProductionList) {
				today.setTodayDefectiveNum(0);

			}
		} else {
			System.out.println("잘못된 입력입니다.");
			MainView.pause();

		}
	}

	private static void stopProduction() {

		// 생산 정지 하루 재고 -> 재고량
		for (ModelInfo model : ModelInfoData.modelInfoList) {
			for (TodayProduction today : TodayProductionData.todayProductionList) {
				if (model.getModelName().equals(today.getModel()))
					model.setModelInventory(
							model.getModelInventory() + today.getTodayProductNum() - today.getTodayDefectiveNum());

			}

		}

		// 하루 목표량 -> 0
		for (String key : ProductionTarget.TargetNum.keySet()) {
			ProductionTarget.TargetNum.put(key, 0);
		}

		// 모든 종료절차가 끝나고난 뒤
		System.out.println("생산이 종료되었습니다. 오늘도 고생하셨습니다.");
		System.out.println();
		MainView.singleLine();
		System.out.println(" [ 구 분 ]\t\t[ K 3 ]\t\t[ K 5 ]\t\t[ K 7 ]\t\t[ K 9 ]");
		MainView.singleLine();
		System.out.printf("금일 총 생산량\t");
		for (TodayProduction today : TodayProductionData.todayProductionList) {
			System.out.printf("\t%3d대\t", today.getTodayProductNum());
		}
		System.out.println();
		System.out.printf("금일 불량품 수\t");
		for (TodayProduction today : TodayProductionData.todayProductionList) {
			System.out.printf("\t%3d대\t", today.getTodayDefectiveNum());
		}
		System.out.println();
		System.out.print("재고 현황\t");
		for (ModelInfo model : ModelInfoData.modelInfoList) {
			System.out.printf("\t%3d대\t", model.getModelInventory());
		}

		// 하루 생산량 -> 0
		for (TodayProduction today : TodayProductionData.todayProductionList) {
			today.setTodayProductNum(0);
			today.setTodayDefectiveNum(0);
		}
		System.out.println();
		MainView.singleLine();
		System.out.println();
		MainView.pause();
	}

	private static void setRejectproduct() {
		// 불량품 개수 입력
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		// 불량품 개수

		while (loop) {
			ProductionView.viewModel();
			System.out.println("종료를 원하시면 0을 입력해주세요.");

			System.out.println();
			System.out.print("모델명 입력: ");
			String sel = sc.nextLine();
			if (sel.equals("0")) {

				loop = false;
			}

			System.out.println();
			for (TodayProduction product : TodayProductionData.todayProductionList) {
				if (product.getModel().equals(sel)) {
					System.out.printf("%s의 불량품 개수: ", product.getModel());

					int rejectNum = sc.nextInt();
					product.setTodayDefectiveNum(rejectNum);
				}

			}

		}

	}

	public static void countPiece(String modelName, String engineType) {

		switch (modelName) {
		case "K3":
			for (Piece piece : PieceData.pieceList) {
				for (ModelInfo model : ModelInfoData.modelInfoList) {
					if (piece.getPieceName().equals("바퀴")) {
						piece.setPieceNum(piece.getPieceNum() - model.getWheel());
					}
					if (piece.getPieceName().equals("철판")) {
						piece.setPieceNum(piece.getPieceNum() - model.getFrame());
					}
					if (piece.getPieceName().equals("가죽")) {
						piece.setPieceNum(piece.getPieceNum() - model.getLeather());
					}
					if (piece.getPieceName().equals(engineType)) {
						piece.setPieceNum(piece.getPieceNum() - 1);
					}
				}
			}
			break;

		case "K5":
			for (Piece piece : PieceData.pieceList) {
				for (ModelInfo model : ModelInfoData.modelInfoList) {
					if (piece.getPieceName().equals("바퀴")) {
						piece.setPieceNum(piece.getPieceNum() - model.getWheel());
					}
					if (piece.getPieceName().equals("철판")) {
						piece.setPieceNum(piece.getPieceNum() - model.getFrame());
					}
					if (piece.getPieceName().equals("가죽")) {
						piece.setPieceNum(piece.getPieceNum() - model.getLeather());
					}
					if (piece.getPieceName().equals(engineType)) {
						piece.setPieceNum(piece.getPieceNum() - 1);
					}
				}
			}
			break;
		case "K7":
			for (Piece piece : PieceData.pieceList) {
				for (ModelInfo model : ModelInfoData.modelInfoList) {
					if (piece.getPieceName().equals("바퀴")) {
						piece.setPieceNum(piece.getPieceNum() - model.getWheel());
					}
					if (piece.getPieceName().equals("철판")) {
						piece.setPieceNum(piece.getPieceNum() - model.getFrame());
					}
					if (piece.getPieceName().equals("가죽")) {
						piece.setPieceNum(piece.getPieceNum() - model.getLeather());
					}
					if (piece.getPieceName().equals(engineType)) {
						piece.setPieceNum(piece.getPieceNum() - 1);
					}
				}
			}
			break;
		case "K9":
			for (Piece piece : PieceData.pieceList) {
				for (ModelInfo model : ModelInfoData.modelInfoList) {
					if (piece.getPieceName().equals("바퀴")) {
						piece.setPieceNum(piece.getPieceNum() - model.getWheel());
					}
					if (piece.getPieceName().equals("철판")) {
						piece.setPieceNum(piece.getPieceNum() - model.getFrame());
					}
					if (piece.getPieceName().equals("가죽")) {
						piece.setPieceNum(piece.getPieceNum() - model.getLeather());
					}
					if (piece.getPieceName().equals(engineType)) {
						piece.setPieceNum(piece.getPieceNum() - 1);
					}
				}
			}
			break;
		}

//		boolean loop = true;
//		for (ModelInfo model : ModelInfoData.modelInfoList) {
//			if (loop) {
//				for (Piece piece : PieceData.pieceList) {
//					if (piece.getPieceType().equals("바퀴") && model.getModelName().equals(modelName)) {
//						if (piece.getPieceNum() - model.getWheel() < 1) {
//							System.out.println("바퀴 부품 개수가 부족합니다.");
//							System.out.println("생산을 종료합니다.");
//							production.setTerminate(false);
//							loop = false;
//							break;
//						} else {
//							piece.setPieceNum(piece.getPieceNum() - model.getWheel());
//						}
//					}
//					if (piece.getPieceType().equals("철판") && model.getModelName().equals(modelName)) {
//						if (piece.getPieceNum() - model.getFrame() < 1) {
//							System.out.println("철판 부품 개수가 부족합니다.");
//							System.out.println("생산을 종료합니다.");
//							production.setTerminate(false);
//							loop = false;
//							break;
//						} else {
//							piece.setPieceNum(piece.getPieceNum() - model.getFrame());
//						}
//					}
//					if (piece.getPieceName().equals(engineType) && model.getEngineType().equals(engineType)) {
//						if ((piece.getPieceNum() - 1) < 1) {
//							System.out.println("엔진 부품 개수가 부족합니다.");
//							System.out.println("생산을 종료합니다.");
//							production.setTerminate(false);
//							loop = false;
//							break;
//						} else {
//							piece.setPieceNum(piece.getPieceNum() - 1);
//						}
//					}
//					if (piece.getPieceType().equals("가죽") && model.getModelName().equals(modelName)) {
//						if (piece.getPieceNum() - model.getLeather() < 1) {
//							System.out.println("가죽 부품 개수가 부족합니다.");
//							System.out.println("생산을 종료합니다.");
//							production.setTerminate(false);
//							loop = false;
//							break;
//						} else {
//							piece.setPieceNum(piece.getPieceNum() - model.getLeather());
//						}
//					}
//
//				}
//			} else {
//				production.setTerminate(false);
//				break;
//			}
//		}
	}

}// class