package com.project.factory.dept.production.admin;

import com.project.factory.dept.production.admin.resource.TodayProduction;
import com.project.factory.dept.production.admin.resource.TodayProductionData;
import com.project.factory.resource.inventory.ModelInfo;
import com.project.factory.resource.inventory.ModelInfoData;
import com.project.factory.resource.inventory.Piece;
import com.project.factory.resource.inventory.PieceData;


public class ProductThread extends Thread {

	private boolean stopRequested;

	public boolean isStopRequested() {
		return stopRequested;
	}


	public void setStopRequested(boolean stopRequested) {
		this.stopRequested = stopRequested;
	}
	
	
	private boolean isStarted;
	
	public boolean isStarted() {
		return isStarted;
	}


	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	
	private boolean terminate;
	
	
	
	public boolean isTerminate() {
		return terminate;
	}


	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}


	int K3count = 0;
	int K5count = 0;
	int K7count = 0;
	int K9count = 0;


	@Override
	public void run() {
		terminate = true;

		while (true) {
			if(!terminate) {
				break;
			}
			if(stopRequested) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
				
			}
			
			try {
				for (TodayProduction today : TodayProductionData.todayProductionList) {
					if (today.getModel().equals("K3")) {
						today.setTodayProductNum(K3count);
//						System.out.printf("%s . %d\n", today.getModel(), today.getTodayProductNum());
					}
					ProductionManagement.countPiece("K3", "엔진A");
					if (today.getModel().equals("K5")) {
						today.setTodayProductNum(K5count);
//						System.out.printf("%s . %d\n", today.getModel(), today.getTodayProductNum());
					}
					ProductionManagement.countPiece("K5", "엔진B");
					if (today.getModel().equals("K7")) {
						today.setTodayProductNum(K7count);
//						System.out.printf("%s . %d\n", today.getModel(), today.getTodayProductNum());
					}
					ProductionManagement.countPiece("K7", "엔진C");
					if (today.getModel().equals("K9")) {
						today.setTodayProductNum(K9count);
//						System.out.printf("%s . %d\n", today.getModel(), today.getTodayProductNum());
					}
					ProductionManagement.countPiece("K9", "엔진D");
					
				}
				K3count += 9;
				K5count += 7;
				K7count += 5;
				K9count += 3;
				Thread.sleep(10000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}

}