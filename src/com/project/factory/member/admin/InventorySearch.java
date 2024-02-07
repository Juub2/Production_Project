package com.project.factory.member.admin;

import com.project.factory.resource.inventory.ModelInfo;
import com.project.factory.resource.inventory.ModelInfoData;
import com.project.factory.view.MainView;

public class InventorySearch {
	// 재고확인
	public static void checkInventory() {
		System.out.println();
		MainView.doubleLine();
		System.out.println("\t\t\t\t재고 확인");
		MainView.doubleLine();
		
		System.out.println();
		for (ModelInfo modelInventory : ModelInfoData.modelInfoList) {
			System.out.printf("%s : %d대\n", modelInventory.getModelName(), modelInventory.getModelInventory());
		}
		System.out.println();
		MainView.doubleLine();
		MainView.pause();
	}
	

}
