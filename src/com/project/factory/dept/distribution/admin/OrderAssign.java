package com.project.factory.dept.distribution.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.resource.Path;
import com.project.factory.resource.sub.Order;
import com.project.factory.resource.sub.OrderData;
import com.project.factory.view.MainView;

public class OrderAssign {
	// OrderAssign.orderView();
	public static void orderView() {

		boolean loop = true;

		while (loop) {
			Scanner scan = new Scanner(System.in);

			MainView.doubleLine();
			System.out.println();
			System.out.println("1. 주문서 확인");
			System.out.println();
			System.out.println("2. 배정 확인 및 수정");
			System.out.println();
			MainView.doubleLine();
			System.out.println();

			System.out.println("엔터를 누르면 초기 화면으로 돌아갑니다.");
			System.out.println();
			System.out.print("번호 입력: ");
			String num = scan.nextLine();
			if (num.equals("1")) {
				// 주문서 확인 화면 넘어가기
				checkOrder();
				System.out.println();
				checkContinue();

			} else if (num.equals("2")) {
				// 배정 수정 화면 넘어가기
				modifyAssign();

			} else if (num.equals("\n")) {
				// 엔터 누르면 초기 화면 넘어가기
				loop = false;
			} else {
				System.out.println("다시 입력하세요.");
			}

		}
	}// orderView()

	public static void checkOrder() {

		// 주문서 리스트 불러오기

		System.out.println();
		System.out.println("주문서");
		System.out.println();
		orderList("k3");
		orderList("k5");
		orderList("k7");
		orderList("k9");

	}// checkOrder()

	private static void orderList(String model) {
		// 주문 리스트 확인
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(Path.ORDER));
			System.out.printf("%s 주문서\n", model);
			System.out.println("[주문서 번호]\t[날짜]\t\t[대리점 명칭]\t[대리점 번호]\t[주문 댓수]\t[납기일]\t\t[주소]");
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				if (temp[7].equals(model)) {
					System.out.println(temp[0] + "\t\t" + temp[1] + "\t" + temp[2] + "\t\t" + temp[4] + "\t" + temp[5]
							+ "\t\t" + temp[6] + "\t" + temp[3]);
				}
			}
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// orderList()

	public static void assignOrder() {
		// 배송자 배정
		try {
			// 출근한 인원 리스트 받아서 랜덤으로 자동 배치 시키기

			// 1. 출근자 목록중 유통직원 확인
			BufferedReader reader = new BufferedReader(new FileReader("data\\commute.txt"));
			String delivery = null;
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
			String time = now.format(formatter);

			String deliveryMember = "";
			while ((delivery = reader.readLine()) != null) {
				String[] temp = delivery.split("■");
				if (temp[0].equals(time) && temp[4].equals("유통") && temp[5].equals("2") && temp[7].equals("출근")) {
					deliveryMember += Arrays.toString(temp) + "\r\n";
				}
			}
			System.out.println(deliveryMember);
			// 출근한 유통 가능직원 추출 후 목록(파일) 생성
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.DELIVERYMEMBER));
			writer.write(deliveryMember.replace(", ", "■").replace("[", "").replace("]", ""));
			writer.close();

			// 유통 출근자 목록(deliveryMember)과 지역 관리자(userRegion)합쳐서 출근자의 관리지역 확인하기
			addOrderListAddDeliveryMember();

			for (Order order : OrderData.orderList) {
				for (Members member : Data.memberList) {
					if (order.getAgencyName().equals(member.getName())) {//주문서의 대리점 이름과 일치하는 멤버 정보를 불러올 수 있게 됨
						System.out.println(order.getAgencyName());
						System.out.println(member.getName());
						member.getArea(member.getDept());
						System.out.println(member.getArea(member.getDept()));
					}
				}
			}

			// ----------------------------------------------------------------------------

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("예외 assignOrder()");
			e.printStackTrace();
		}

	}// assignOrder()

	public static void addOrderListAddDeliveryMember() {

		// 유통 출근자 목록(deliveryMember)과 지역 관리자(userRegion)합쳐서 출근자의 관리지역 확인하기
		try {

			String file1Path = "data\\deliveryMember.txt";
			String file2Path = "data\\userRegion.txt";
			String file3Path = "data\\deliveryMemberAdduserRegion.txt";
			// 파일 리더 및 라이터 설정
			BufferedReader reader1 = new BufferedReader(new FileReader(file1Path));
			BufferedWriter writer = new BufferedWriter(new FileWriter(file3Path));

			String line = null;
			String line2 = null;
			String txt = "";

			// deliveryMember.txt의 각 라인에 대해 반복
			while ((line = reader1.readLine()) != null) {
				// userRegion.txt을 다시 열어야 함
				BufferedReader reader2 = new BufferedReader(new FileReader(file2Path));

				// 각 라인을 "■"로 분리하여 배열로 저장
				String[] temp = line.split("■");

				// userRegion.txt의 각 라인에 대해 반복
				while ((line2 = reader2.readLine()) != null) {
					// userRegion.txt의 각 라인을 "■"로 분리하여 배열로 저장
					String[] temp2 = line2.split("■");

					// 유통 출근자와 지역 관리자의 정보가 일치하는 경우
					if (temp[1].equals(temp2[0]) && temp[2].equals(temp2[1])) {
						// 필요한 정보를 합쳐서 문자열에 추가
						txt += temp[0] + "■" + temp[1] + "■" + temp[2] + "■" + temp[3] + "■" + temp[4] + "■" + temp[5]
								+ "■" + temp[6] + "■" + temp[7] + "■" + temp2[3] + "\r\n";
					}
				}

				// 파일2 리더 닫기
				reader2.close();
			}

			// 최종 결과 출력
//	        System.out.println(txt);

			// 파일 라이터를 사용하여 결과를 파일에 쓰기
			writer.write(txt);

			// 파일 리더 및 라이터 닫기
			reader1.close();
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("예외 addOrderListAddDeliveryMember()");
			e.printStackTrace();
		}

	}// addOrderListAddDeliveryMember()

	private static void orderFinishList(String model) {
		// 주문서와 유통자 리스트 출력
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\최종 유통.txt"));
			System.out.printf("%s 주문서\n", model);
			System.out.println("[주문서 번호]\t[날짜]\t\t[대리점 명칭]\t[대리점 번호]\t[주문 댓수]\t[납기일]\t\t[담당자 이름]\t[사원번호]");
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");

				if (temp[14].equals(model)) {
					System.out.println(temp[7] + "\t\t" + temp[8] + "\t" + temp[9] + "\t\t" + temp[11] + "\t" + temp[12]
							+ "\t\t" + temp[13] + "\t" + temp[1] + "\t\t" + temp[0]);
				}
			}
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// orderList()

	public static void modifyAssign() {
		// 수정할 주문서 번호 받기
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\최종 유통.txt"));
			BufferedReader reader2 = new BufferedReader(new FileReader("data\\deliveryMember.txt"));
			Scanner scan = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);

//			String EmployeeNum = "";
//			String name = "";
//			String humanNum = "";
//			String phonNum = "";
//			String address = "";

			System.out.println();
			MainView.singleLine();
			System.out.println();
			System.out.println("배정된 주문서");
			System.out.println();
			orderFinishList("k3");
			orderFinishList("k5");
			orderFinishList("k7");
			orderFinishList("k9");
			System.out.println();
			MainView.doubleLine();
			System.out.println();
			System.out.print("수정 할 주문서 번호를 입력하세요: ");

			String orderNum = scan.nextLine();
			System.out.println();
			modifyAssignCheck1(orderNum);

			System.out.print("해당 주문을 담당할 사원번호를 입력하세요: ");
			String workmanNum = scan2.nextLine();
			System.out.println();
			modifyAssignCheck2(workmanNum);

		} catch (Exception e) {
			// TODO: handle exception
		}
		// 수정

	}// modifyAssign()

	public static void modifyAssignCheck1(String orderNum) {
		// 수정할 주문번호 찾기
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\최종 유통.txt"));
			BufferedReader reader2 = new BufferedReader(new FileReader("data\\최종 유통.txt"));
			String line = null;
			boolean orderList = false;

			while ((line = reader.readLine()) != null) {
				if (line.contains(orderNum)) {
					orderList = true; // 찾고자 하는 문자열이 포함된 경우 true 반환
					break;
				}
			}

			if (orderList) {
				while ((line = reader2.readLine()) != null) {
					String[] temp = line.split("■");
					if (orderNum.equals(temp[7])) {
						System.out.println("[주문서 번호]\t[날짜]\t\t[대리점 명칭]\t[대리점 번호]\t[주문 댓수]\t[납기일]\t\t[담당자 이름]\t[사원번호]");
						System.out.println(temp[7] + "\t\t" + temp[8] + "\t" + temp[9] + "\t\t" + temp[11] + "\t"
								+ temp[12] + "\t\t" + temp[13] + "\t" + temp[1] + "\t\t" + temp[0]);
						System.out.println();
					}
				}
			} else {
				System.out.println("주문서 번호를 정확하게 입력해 주세요.");

				boolean back = true;

				while (back) {
					System.out.println();
					Scanner scan = new Scanner(System.in);
					System.out.print("계속 진행하시겠습니까?(Y/N) ");
					String check = scan.nextLine();
					System.out.println();

					if (check.equals("Y") || check.equals("y")) {
						System.out.println();
						back = false;
						modifyAssign();

					} else if (check.equals("N") || check.equals("n")) {
						back = false;
						orderView();
					} else {
						System.out.println();
						MainView.singleLine();
						System.out.println();
						System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
					}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("modifyAssignCheck1() 예외");
			e.printStackTrace();
		}
	}// modifyAssignCheck1()

	public static void modifyAssignCheck2(String workmanNum) {

		// 수정할 주문번호 찾기
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data\\deliveryMember.txt"));
			BufferedReader reader2 = new BufferedReader(new FileReader("data\\최종 유통.txt"));
			String EmployeeNum = "";
			String name = "";
			String humanNum = "";
			String phonNum = "";
			String address = "";

			String line = null;
			boolean orderList = false;
			while ((line = reader.readLine()) != null) {
				if (line.contains(workmanNum)) {
					orderList = true; // 찾고자 하는 문자열이 포함된 경우 true 반환
					break;
				}
			}

			if (orderList) {
				while ((line = reader.readLine()) != null) {
					String[] temp = line.split("■");
					if (workmanNum.equals(temp[0])) {
						System.out.println("[사원번호]\t\t[이름]\t\t[생년월일]\t\t[전화번호]");
						System.out.println(temp[0] + "\t\t" + temp[1] + "\t\t" + temp[2] + "\t\t" + temp[3]);
						System.out.println();
						EmployeeNum = temp[0];
						name = temp[1];
						humanNum = temp[2];
						phonNum = temp[3];
						address = temp[4];
					}
				}
			} else {
//				System.out.println("주문서 번호를 정확하게 입력해 주세요.");
//				
//				boolean back = true;
//				
//				while(back) {
//					System.out.println();
//					Scanner scan = new Scanner(System.in);
//					System.out.print("계속 진행하시겠습니까?(Y/N) ");
//					String check = scan.nextLine();
//					System.out.println();
//					
//					if (check.equals("Y") || check.equals("y")) {
//						System.out.println();
//						back = false;
//						modifyAssign();
//						
//					} else if (check.equals("N") || check.equals("n")) {
//						back = false;
//						orderView();
//					} else {
//						System.out.println();
//						MainView.singnleLine();
//						System.out.println();
//						System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
//					}
//					
//				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("modifyAssignCheck2() 예외");
			e.printStackTrace();
		}

	}// modifyAssignCheck2()

//	String line = null;
//	boolean loop = true;
//	while(loop) {

//				String memberNum = scan2.nextLine();
//				
//				
//				String check = scan.nextLine();
//				System.out.println();
//				if (check.equals("Y") || check.equals("y")) {
//					MainView.singnleLine();
//					System.out.println();
//					System.out.println("해당 주문서의 담당자가 수정되었습니다.");
//					System.out.println();
//					
//					BufferedReader reader3 = new BufferedReader(new FileReader("data\\최종 유통.txt"));
//					String txt = "";
//					while ((line = reader3.readLine()) != null) {
//						txt += line.replace(temp[0], EmployeeNum)
//								   .replace(temp[1], name)
//								   .replace(temp[2], humanNum)
//								   .replace(temp[3], phonNum)
//								   .replace(temp[4], address) + "\r\n";
//					}
//					BufferedWriter modifyDelivery = new BufferedWriter(new FileWriter("data\\최종 유통.txt"));
//					modifyDelivery.write(txt);
//					modifyDelivery.close();
//					loop = false;
//					
//				} else if (check.equals("N") || check.equals("n")) {
//					
//				} else {
//					System.out.println();
//					MainView.singnleLine();
//					System.out.println();
//					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
//					checkContinue();
//				}
//				
//			}
//	System.out.println("주문서 번호를 정확히 입력해 주세요.");
//	modifyAssign();

	private static void checkContinue() {

		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("유통사원을 배정하시겠습니까?(Y/N) ");
		String check = scan.nextLine();
		System.out.println();

		if (check.equals("Y") || check.equals("y")) {
			assignOrder();
			orderFinishList("k3");
			orderFinishList("k5");
			orderFinishList("k7");
			orderFinishList("k9");
			System.out.println();
			System.out.println("선택화면으로 돌아갑니다.");

		} else if (check.equals("N") || check.equals("n")) {

		} else {
			System.out.println();
			MainView.singleLine();
			System.out.println();
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			checkContinue();
		}
	}

}
