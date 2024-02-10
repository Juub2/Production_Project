package com.project.factory.resource.dept;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.factory.dept.distribution.user.Assign;
import com.project.factory.resource.Path;

/** 배정된 주문서 데이터를 파일에서 읽어와 리스트로 만드는 클래스이다.
 * 
 */
public class AssignData {
	

	public static ArrayList<Assign> assignList = new ArrayList<Assign>();
	public static int orderCount;

	public static void load() {
	    try {
	        assignList.clear(); // 기존 데이터 초기화

	        BufferedReader reader = new BufferedReader(new FileReader(Path.ASSIGNORDER));

	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            // 주문서번호■주문서 작성일■대리점명■대리점 주소■전화번호■개수■납기일■모델명■사원 이름■사원 번호
	            String[] temp = line.split("■");
	            Assign assignOrder = new Assign(
	                    temp[0],        // 주문서 번호
	                    temp[1],        // 주문서 작성일
	                    temp[2],        // 대리점명
	                    temp[3],        // 대리점 주소
	                    temp[4],        // 전화번호
	                    Integer.parseInt(temp[5]), // 개수 (정수로 변환)
	                    temp[6],        // 납기일
	                    temp[7],        // 모델명
	                    temp[8],        // 사원 이름
	                    temp[9], 		//사원아이디
	                    temp[10],
	                    temp[11]
	              


	            );
	            assignList.add(assignOrder);
	        }

	        reader.close();

	    } catch (Exception e) {
	        System.out.println("AssignData.load");
	        e.printStackTrace();
	    }
	}

	/** 유통부 사원에게 배정된 주문서 개수를 반환하는 메서드이다.
	 * 
	 * @param memberId 해당 주문서를 배정받은 유통부 사원번호
	 * @return 배정된 주문서 개수
	 */
	public static int getOrderCountByMemberId(String memberId) {
	    int orderCount = 0; // 메서드 호출 시마다 초기화

	    for (Assign assignOrder : AssignData.assignList) {
	        if (assignOrder.getUserId().equals(memberId)) {
	            orderCount++;
	        }
	    }

	    return orderCount;
	}

	/** 유통부 사원에게 배정된 주문서 중 유통을 완료한 주문서 개수를 반환하는 메서드 
	 * 
	 * @param memberId 해당 주문서를 배정받은 유통부 사원번호
	 * @return 유통이 완료된 주문서 개수
	 */
	public static int getCompletedOrderCountByMemberId(String memberId) {
	    int completedOrderCount = 0;

	    for (Assign assignOrder : AssignData.assignList) {
	        if (assignOrder.getUserId().equals(memberId) && assignOrder.getState().equals("완료")) {
	            completedOrderCount++;
	        }
	    }

	    return completedOrderCount;
	}


	/** 파일에 배정된 주문서 리스트 정보를 저장하는 메서드이다.
	 * 
	 */
	public static void save() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.ASSIGNORDER));

			for (Assign assignOrder : AssignData.assignList) {
				  // 주문서번호■주문서 작성일■대리점명■대리점 주소■전화번호■개수■납기일■모델명■사원 이름■사원 번호 배송상태
				String line = String.format("%s■%s■%s■%s■%s■%d■%s■%s■%s■%s■%s■%s\r\n",
						assignOrder.getId(),
						assignOrder.getWriteDate(), assignOrder.getAgencyName(),
						assignOrder.getAgencyAddress(), assignOrder.getAgencyPhoneNum(),
						assignOrder.getQuantity(), assignOrder.getDueDate(),assignOrder.getModelId(),assignOrder.getUserName()
						,assignOrder.getUserId(),assignOrder.getArea(),assignOrder.getState());
						

				writer.write(line);

			}

			writer.close();

		} catch (Exception e) {
			System.out.println("AssignData.save");
			e.printStackTrace();
		}

	}// save

}
