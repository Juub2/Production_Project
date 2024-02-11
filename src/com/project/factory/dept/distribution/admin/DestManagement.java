package com.project.factory.dept.distribution.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.project.factory.resource.Path;
import com.project.factory.resource.dept.DistributionData;
import com.project.factory.view.MainView;
import com.project.factory.view.dept.AreaView;

public class DestManagement {

	public static String id;

	public static void destManagement() {
		DistributionData.load();

		MainView.title("사원 배송 구역 관리");

		// userRegion.txt에 있는 파일 출력
		// readAndPrintUserRegionFile();

		System.out.println();
		System.out.println("[사원번호]\t[이름]\t[전화번호]\t[담당구역]");

		for (EmployeeInfo employeeInfo : DistributionData.distributionMembersList) {
			System.out.printf("%s\t\t%s\t%s\t%s%n", employeeInfo.getId(), employeeInfo.getName(),
					employeeInfo.getPhoneNum(), employeeInfo.getAssignedRegion());
		}

		System.out.println();
		MainView.singleLine();
		
		if (checkMemberId()) {
			System.out.println();
		    MainView.singleLine();
			if (checkMember()) {
				area();
			} else {
				destManagement();
			}
		}
	}

	private static boolean checkMemberId() {
	    Scanner scan = new Scanner(System.in);

	    System.out.println();
	    System.out.print("담당 구역을 변경하실 사원의 사원번호를 입력하세요: ");

		id = scan.nextLine();
		
		System.out.println();
	    for (EmployeeInfo employeeInfo : DistributionData.distributionMembersList) {
	        if (employeeInfo.getId().equals(id)) {
	            System.out.println("[사원번호]\t[이름]\t[전화번호]\t[담당구역]");
	            System.out.printf("%s\t\t%s\t%s\t%s%n", employeeInfo.getId(), employeeInfo.getName(),
	                    employeeInfo.getPhoneNum(), employeeInfo.getAssignedRegion());
	            
	            return true; // 사원을 찾았으므로 true 반환
	        } 
	    }
	    
	    
	    // 반복문이 끝난 후에 해당 코드가 실행되므로 사원을 찾지 못한 경우에만 실행됨
	    System.out.println("해당 사원을 찾을 수 없습니다.");
	    if (MainView.checkContinueBoolean()) {
	        // 재귀 호출이 아니라 반복문을 다시 실행하도록 수정
	        return checkMemberId();
	    }
	    MainView.pause();
	    return false; // 사원을 찾지 못했으므로 false 반환
	}


	private static boolean checkMember() {
		Scanner scan = new Scanner(System.in);
		System.out.println("해당 직원이 맞습니까?(Y/N)");
		System.out.print("입력: ");

		String answer = scan.nextLine().toUpperCase();

		if (answer.equals("Y")) {

			return true;

		} else if (answer.equals("N")) {

			return false;
		}
		System.out.println();
		System.out.println("잘못된 입력입니다.");
		System.out.println();
		return false;
	}

	
	public static void area() {

		MainView.singleLine();
		System.out.println();
		AreaView.areaView();
		System.out.println();
		MainView.singleLine();

	    Scanner scanner = new Scanner(System.in);
	    int selectedRegion;

	    do {
	        System.out.print("행정구역 번호: ");
	        if (scanner.hasNextInt()) {
	            selectedRegion = scanner.nextInt();
	            if (selectedRegion >= 1 && selectedRegion <= 17) {
	                break; // 올바른 범위 내의 값이 입력되었을 때 루프 종료
	            } else {
	                System.out.println("1부터 17까지의 번호를 입력하세요.");
	            }
	        } else {
	        	System.out.println();
	            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
	            System.out.println();
	            scanner.next(); // 잘못된 입력을 처리하기 위해 다음 입력을 받음
	        }
	    } while (true);

	    switch (selectedRegion) {
		case 1:
			updateAssignedRegion("서울특별시");
			break;
		case 2:
			updateAssignedRegion("부산광역시");
			break;
		case 3:
			updateAssignedRegion("대구광역시");
			break;
		case 4:
			updateAssignedRegion("인천광역시");
			break;
		case 5:
			updateAssignedRegion("광주광역시");
			break;
		case 6:
			updateAssignedRegion("대전광역시");
			break;
		case 7:
			updateAssignedRegion("울산광역시");
			break;
		case 8:
			updateAssignedRegion("세종특별자치시");
			break;
		case 9:
			updateAssignedRegion("경기도");
			break;
		case 10:
			updateAssignedRegion("충청북도");
			break;
		case 11:
			updateAssignedRegion("충청남도");
			break;
		case 12:
			updateAssignedRegion("전라남도");
			break;
		case 13:
			updateAssignedRegion("경상북도");
			break;
		case 14:
			updateAssignedRegion("경상남도");
			break;
		case 15:
			updateAssignedRegion("제주특별자치도");
			break;
		case 16:
			updateAssignedRegion("강원특별자치도");
			break;
		case 17:
			updateAssignedRegion("전북특별자치도");
			break;
		default:
			System.out.println("잘못된 행정구역 번호입니다.");
		}
	}


	private static void updateAssignedRegion(String newRegion) {
		try (BufferedReader reader = new BufferedReader(new FileReader(Path.USERREGION));
				BufferedWriter writer = new BufferedWriter(new FileWriter(Path.USERREGIONTEMP))) {

			String line;

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				String existingId = temp[0];

				if (existingId.equals(DestManagement.id)) {
					// 이전에 할당된 지역과 새로운 지역이 동일한 경우 변경하지 않음
					if (!temp[3].equals(newRegion)) {
						// 선택된 행정구역으로 담당 구역 업데이트
						temp[3] = newRegion;
						System.out.println();
						System.out.println("담당 구역이 성공적으로 변경되었습니다.");

					} else {
						System.out.println();
						System.out.println("이미 해당 구역으로 할당되어 있습니다.");
					}
				}

				// 변경된 라인을 새로운 파일에 쓰기
				writer.write(String.join("■", temp) + "\n");
			}
			MainView.pause();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 파일이 사용 중이지 않은 상태에서 삭제 및 이름 변경
		File userRegionFile = new File(Path.USERREGION);
		File userRegionTempFile = new File(Path.USERREGIONTEMP);

		if (userRegionFile.exists()) {
			userRegionFile.delete(); // 기존 파일 삭제
		}

		if (userRegionTempFile.exists()) {
			userRegionTempFile.renameTo(userRegionFile); // 새 파일을 기존 파일 이름으로 변경
		}
	}


}
