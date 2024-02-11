package com.project.factory.resource.sub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.project.factory.resource.Path;

/**
 * 주문 데이터를 관리하는 클래스이다.
 */
public class OrderData {
	
	/**
	 * 주문서 목록
	 */
	public static ArrayList<Order> orderList = new ArrayList<Order>();

	/**
	 * 파일에서 주문서 데이터를 로드하는 메서드이다.
	 */
	public static void load() {
		try {

			orderList.clear(); // 기존 데이터 초기화

			BufferedReader reader = new BufferedReader(new FileReader(Path.ORDER));

			String line = null;
			while ((line = reader.readLine()) != null) {
				//주문서번호■주문서 작성일■대리점명■대리점 주소■전화번호■개수■납기일■모델명
				String[] temp = line.split("■");

				Order orderItem = new Order(temp[0], temp[1], temp[2], temp[3], temp[4], Integer.parseInt(temp[5]), temp[6], temp[7]);

				 orderList.add(orderItem);

			}

			reader.close();
			
			 // 주문서 작성일을 기준으로 오름차순 정렬
            Collections.sort(orderList, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getWriteDate().compareTo(o2.getWriteDate());
                }
            });

		} catch (Exception e) {
			System.out.println("OrderData.load");
			e.printStackTrace();
		}

	}//load
	
	/**
	 * 주문서 데이터를 파일에 저장하는 메서드이다.
	 */
	public static void save() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.ORDER));

			for (Order orderItem : OrderData.orderList) {

				//주문서번호■주문서 작성일■대리점명■대리점 주소■전화번호■개수■납기일■모델명
				String line = String.format("%s■%s■%s■%s■%s■%d■%s■%s\r\n", 
						orderItem.getId(),
						orderItem.getWriteDate(),
						orderItem.getAgencyName(), 
						orderItem.getAgencyAddress(),
						orderItem.getAgencyPhoneNum(),
						orderItem.getQuantity(),
						orderItem.getDueDate(),
						orderItem.getModelId());

				writer.write(line);

			}

			writer.close();

		} catch (Exception e) {
			System.out.println("OrderData.save");
			e.printStackTrace();
		}

	}//save
}
