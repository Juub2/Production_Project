package com.project.factory.dept.distribution.admin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.resource.Path;
import com.project.factory.resource.dept.DistributionData;

public class AssignRegion {

	private static HashMap<Integer, String> regionMap;
	
	static {
		// 해시맵 초기화
		regionMap = new HashMap<>();
		regionMap.put(1, "서울특별시");
		regionMap.put(2, "부산광역시");
		regionMap.put(3, "대구광역시");
		regionMap.put(4, "인천광역시");
		regionMap.put(5, "광주광역시");
		regionMap.put(6, "대전광역시");
		regionMap.put(7, "울산광역시");
		regionMap.put(8, "세종특별자치시");
		regionMap.put(9, "경기도");
		regionMap.put(10, "충청북도");
		regionMap.put(11, "충청남도");
		regionMap.put(12, "전라남도");
		regionMap.put(13, "경상북도");
		regionMap.put(14, "경상남도");
		regionMap.put(15, "제주특별자치도");
		regionMap.put(16, "강원특별자치도");
		regionMap.put(17, "전북특별자치도");
	}
	

	public static void distributionMember() {
	    // 필요한 필드만 로드

	    Random random = new Random();

	    for (Members member : Data.memberList) {
	        if (member.getDept().equals("유통") && member.getLevel().equals("2")) {
	            int randomRegionCode = random.nextInt(regionMap.size()) + 1;
	            String assignedRegion = regionMap.get(randomRegionCode);

	                EmployeeInfo employeeInfo = new EmployeeInfo(member.getId(), member.getName(), member.getPhoneNum(), assignedRegion);
	                DistributionData.distributionMembersList.add(employeeInfo);
	            
	        }
	    }


	    saveToUserRegionFile();
	}


    public static void saveToUserRegionFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Path.USERREGION))) {
            for (EmployeeInfo employeeInfo : DistributionData.distributionMembersList) {
                writer.write(String.format("%s■%s■%s■%s%n", employeeInfo.getId(), employeeInfo.getName(),
                        employeeInfo.getPhoneNum(), employeeInfo.getAssignedRegion()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
