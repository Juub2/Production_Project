package com.project.factory.dept.production.admin;

import com.project.factory.dept.production.admin.resource.TodayProduction;
import com.project.factory.dept.production.admin.resource.TodayProductionData;
import com.project.factory.resource.inventory.ModelInfo;
import com.project.factory.resource.inventory.ModelInfoData;
import com.project.factory.resource.inventory.Piece;
import com.project.factory.resource.inventory.PieceData;

public class ProductionSystem {

	public static void productK3() {
		for (TodayProduction today : TodayProductionData.todayProductionList) {
			if (today.getModel().equals("K3")) {
				today.setTodayProductNum(today.getTodayProductNum() + 9);
				for (Piece piece : PieceData.pieceList) {
					for (ModelInfo model : ModelInfoData.modelInfoList) {
						if (piece.getPieceType().equals("바퀴") && model.getModelName().equals("K3")) {
							piece.setPieceNum(piece.getPieceNum() - model.getWheel()*9);
						}
						if (piece.getPieceType().equals("철판") && model.getModelName().equals("K3")) {
							piece.setPieceNum(piece.getPieceNum() - model.getFrame()*9);
						}
						if (piece.getPieceType().equals("가죽") && model.getModelName().equals("K3")) {
							piece.setPieceNum(piece.getPieceNum() - model.getLeather()*9);
						}
						if (piece.getPieceName().equals("E94-A") && model.getModelName().equals("K3")) {
							piece.setPieceNum(piece.getPieceNum() - 9);
						}
					}
				}
			}
		}

	}

	public static void productK5() {
		for (TodayProduction today : TodayProductionData.todayProductionList) {
			if (today.getModel().equals("K5")) {
				today.setTodayProductNum(today.getTodayProductNum() + 7);
				for (Piece piece : PieceData.pieceList) {
					for (ModelInfo model : ModelInfoData.modelInfoList) {
						if (piece.getPieceType().equals("바퀴") && model.getModelName().equals("K5")) {
							piece.setPieceNum(piece.getPieceNum() - model.getWheel()*7);
						}
						if (piece.getPieceType().equals("철판") && model.getModelName().equals("K5")) {
							piece.setPieceNum(piece.getPieceNum() - model.getFrame()*7);
						}
						if (piece.getPieceType().equals("가죽") && model.getModelName().equals("K5")) {
							piece.setPieceNum(piece.getPieceNum() - model.getLeather()*7);
						}
						if (piece.getPieceName().equals("E94-B") && model.getModelName().equals("K5")) {
							piece.setPieceNum(piece.getPieceNum() - 7);
						}
					}
				}
			}
		}

	}

	public static void productK7() {
		for (TodayProduction today : TodayProductionData.todayProductionList) {
			if (today.getModel().equals("K7")) {
				today.setTodayProductNum(today.getTodayProductNum() + 5);
				for (Piece piece : PieceData.pieceList) {
					for (ModelInfo model : ModelInfoData.modelInfoList) {
						if (piece.getPieceType().equals("바퀴") && model.getModelName().equals("K7")) {
							piece.setPieceNum(piece.getPieceNum() - model.getWheel()*5);
						}
						if (piece.getPieceType().equals("철판") && model.getModelName().equals("K7")) {
							piece.setPieceNum(piece.getPieceNum() - model.getFrame()*5);
						}
						if (piece.getPieceType().equals("가죽") && model.getModelName().equals("K7")) {
							piece.setPieceNum(piece.getPieceNum() - model.getLeather()*5);
						}
						if (piece.getPieceName().equals("E94-C") && model.getModelName().equals("K7")) {
							piece.setPieceNum(piece.getPieceNum() - 5);
						}
					}
				}
			}
		}

	}

	public static void productK9() {
		for (TodayProduction today : TodayProductionData.todayProductionList) {
			if (today.getModel().equals("K9")) {
				today.setTodayProductNum(today.getTodayProductNum() + 3);
				for (Piece piece : PieceData.pieceList) {
					for (ModelInfo model : ModelInfoData.modelInfoList) {
						if (piece.getPieceType().equals("바퀴") && model.getModelName().equals("K9")) {
							piece.setPieceNum(piece.getPieceNum() - model.getWheel()*3);
						}
						if (piece.getPieceType().equals("철판") && model.getModelName().equals("K9")) {
							piece.setPieceNum(piece.getPieceNum() - model.getFrame()*3);
						}
						if (piece.getPieceType().equals("가죽") && model.getModelName().equals("K9")) {
							piece.setPieceNum(piece.getPieceNum() - model.getLeather()*3);
						}
						if (piece.getPieceName().equals("E94-D") && model.getModelName().equals("K9")) {
							piece.setPieceNum(piece.getPieceNum() - 3);
						}
					}
				}
			}

		}
	}

}
