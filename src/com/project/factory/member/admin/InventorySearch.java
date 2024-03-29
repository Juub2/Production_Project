package com.project.factory.member.admin;

import com.project.factory.resource.inventory.ModelInfo;
import com.project.factory.resource.inventory.ModelInfoData;
import com.project.factory.view.MainView;
/**
 * 재고를 확인할 수 있는 클래스이다.
 */
public class InventorySearch {
	// 재고확인
	
	/** 재고 확인 기능을 수행하는 메서드이다.
	 * 
	 */
	public static void checkInventory() {
		MainView.title("재고 확인");
		
		System.out.println();
		for (ModelInfo modelInventory : ModelInfoData.modelInfoList) {
			System.out.printf("%s : %d대\n", modelInventory.getModelName(), modelInventory.getModelInventory());
		}
		System.out.println();
		MainView.singleLine();
		MainView.pause();
	}
	

}
