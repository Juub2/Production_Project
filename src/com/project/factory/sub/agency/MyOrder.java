package com.project.factory.sub.agency;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.Today;
import com.project.factory.Toolkit;
import com.project.factory.member.Identify;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.resource.Path;
import com.project.factory.resource.sub.Order;
import com.project.factory.resource.sub.OrderData;
import com.project.factory.view.MainView;
import com.project.factory.view.sub.MyOrderView;

public class MyOrder {

	static Scanner scan = new Scanner(System.in);

	private static String id; // 주문서 번호
	private static String agencyName; // 대리점명
	private static String agencyAddress; // 대리점 주소
	private static String agencyPhoneNum; // 대리점 전화번호
	private static int quantity; // 수량
	private static String modelId; // 모델ID

	public static void myOrder() {

		OrderData.load();

		MyOrderView.myOrderMenu();
		Main.selectNum = scan.nextLine();

		if (Main.selectNum.equals("1")) {
			MyOrder.orderAdd();
		} else if (Main.selectNum.equals("2")) {
			MyOrder.orderEdit();
		} else if (Main.selectNum.equals("3")) {
			MyOrder.orderDelete();
		} else if (Main.selectNum.equals("4")) {
			MyOrder.orderView();
		} else {
			System.out.println();
			System.out.println("잘못된 번호입니다.");
			if (MainView.checkContinueBoolean()) {
				MyOrder.myOrder();
				return;
			} else {
				MainView.pause();
				return;
			}
		}
	}// MyOrder

	private static void orderAdd() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.ORDER, true)); // true인 경우 이어쓰기

			while (true) {

				MyOrderView.orderAddMenu();
				Main.selectNum = scan.nextLine();

				if (MyOrder.checkModelExists()) { // MyOrder.modelId 설정

					if (MyOrder.writeQuantity()) {

						MyOrder.createOrderId(); // 주문서 번호 생성

						MyOrder.getAgencyInfo(); // 이름, 주소, 전화번호

						// myOrder() 메서드에서 처음에 OrderData.load()를 하면서 리스트에 불러오기 때문에 따로 리스트에 저장할 필요X
						// 주문서번호■주문서 작성일■대리점명■대리점 주소■전화번호■개수■납기일■모델명
						writer.write(MyOrder.id + "■" + Today.day() + "■" + MyOrder.agencyName + "■"
								+ MyOrder.agencyAddress + "■" + MyOrder.agencyPhoneNum + "■" + MyOrder.quantity + "■"
								+ Today.daysLater() + "■" + MyOrder.modelId);
						writer.newLine();
						writer.close();

						System.out.println();
						System.out.println("주문이 성공적으로 완료되었습니다.");
						MainView.pauseToSel();

						MyOrder.myOrder();
						return;

					} else {
						MainView.pauseToSel();
						MyOrder.myOrder();
						return;
					}
				} else {
					System.out.println();
					System.out.println("잘못된 번호입니다.");
					if (MainView.checkContinueBoolean()) {
						continue;
					} else {
						MainView.pauseToSel();
						MyOrder.myOrder();
						return;
					}
				}
			} // while
		} catch (Exception e) {
			System.out.println("MyOrder.orderAdd");
			e.printStackTrace();
		}
	}// orderAdd

	private static void orderEdit() {
		boolean loop = false;
		while (true) {
			MyOrderView.orderEditMenu();

			MyOrder.id = scan.nextLine();// 주문서 번호

			if (checkOrderIdExists()) {
				for (Order order : OrderData.orderList) {
					if (order.getAgencyName().equals(Identify.name) && order.getId().equals(MyOrder.id)) {

						System.out.printf("%-8s\t%-5S\t%-4S\r\n", "[주문서 번호]", "[모델]", "[수량]");
						System.out.printf("%-8s\t%-5S\t%-4d\n", order.getId(), order.getModelId(), order.getQuantity());
						MainView.singleLine();

						if (MyOrder.checkEditContinue()) {
							System.out.println();
							if (MyOrder.writeQuantity()) {
								order.setQuantity(MyOrder.quantity);// 수정하기
								OrderData.save();

								System.out.println();
								System.out.println("수량이 변경되었습니다.");
							}
						}
						loop = true;
						break;
					} // 존재하는 경우
				}
			}
			
			if (loop) {
				MainView.pauseToSel();
				MyOrder.myOrder();
				return;
			} else {
				System.out.println("잘못된 번호입니다.");

				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					MainView.pauseToSel();
					MyOrder.myOrder();
					return;
				}
			}
		} // while
	}// orderEdit

	private static void orderDelete() {
		boolean loop = false;
		while (true) {
			MyOrderView.orderDeleteMenu();

			MyOrder.id = scan.nextLine();// 주문서 번호

			if (checkOrderIdExists()) {
				for (Order order : OrderData.orderList) {
					if (order.getAgencyName().equals(Identify.name) && order.getId().equals(MyOrder.id)) {
						System.out.println();
						System.out.printf("%-8s\t%-5S\t%-4S\r\n", "[주문서 번호]", "[모델]", "[수량]");
						System.out.printf("%-8s\t%-5S\t%-4d\n", order.getId(), order.getModelId(), order.getQuantity());
						MainView.singleLine();

						if (MyOrder.checkDeleteContinue()) {
							OrderData.orderList.remove(order);// 해당 order 삭제
							OrderData.save();

							System.out.println();
							System.out.println("주문이 취소되었습니다.");
						}
						loop = true;
						break;
					} // 존재하는 경우
				}
			}

			if (loop) {
				MainView.pauseToSel();
				MyOrder.myOrder();
				return;
			} else {
				System.out.println("잘못된 번호입니다.");

				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					MainView.pauseToSel();
					MyOrder.myOrder();
					return;
				}
			}
		} // while

	}// orderDelete

	private static void orderView() {
		MyOrderView.orderViewMenu();
		
		MainView.pauseToSel();
		
		MyOrder.myOrder();
		return;
	}// orderView

	// 주문서 작성 메서드
	private static void getAgencyInfo() {
		for (Members member : Data.memberList) {
			if (member.getId().equals(Identify.auth)) { // 주문서 작성 중인 회원

				MyOrder.agencyName = member.getName();
				MyOrder.agencyAddress = member.getAddress();
				MyOrder.agencyPhoneNum = member.getPhoneNum();

				break;
			}
		}
	}

	private static boolean writeQuantity() {
		while (true) {
			System.out.print("원하는 수량을 입력하세요: ");
			Main.answer = scan.nextLine();

			if (!Main.answer.isEmpty()) { // 빈 문자열이 아니면
				if (Toolkit.isInteger(Main.answer)) { // 정수인지 확인

					MyOrder.quantity = Integer.parseInt(Main.answer);

					return true;

				} else {
					System.out.println();
					System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
					if (MainView.checkContinueBoolean()) {
						continue;
					} else {
						return false;
					}
				}
			} else {
				System.out.println("잘못된 번호입니다.");
				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					return false;
				}
			}
		} // while
	}// writeQuantity

	private static void createOrderId() {

		Random random = new Random(); // 랜덤 객체 생성

		while (true) {
			MyOrder.id = String.valueOf(100000 + random.nextInt(900000)); // 6자리 랜덤 숫자 (100000~999999)
			boolean isDuplicate = false;

			for (Order order : OrderData.orderList) {
				if (order.getId().equals(MyOrder.id)) {
					isDuplicate = true;
					break;
				}
			}

			if (!isDuplicate) {
				// 중복이 없으면 반복문 종료
				break;
			}
		}
	}// createOrderId

	// 유효성 검사
	private static boolean checkModelExists() {
		if (Main.selectNum.equals("1")) {
			MyOrder.modelId = "K3";
			return true;
		} else if (Main.selectNum.equals("2")) {
			MyOrder.modelId = "K5";
			return true;
		} else if (Main.selectNum.equals("3")) {
			MyOrder.modelId = "K7";
			return true;
		} else if (Main.selectNum.equals("4")) {
			MyOrder.modelId = "K9";
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkOrderIdExists() {
		for (Order order : OrderData.orderList) {
			if (order.getAgencyName().equals(Identify.name) && order.getId().equals(MyOrder.id)) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkEditContinue() {
        while (true) {

            System.out.println();
            System.out.print("해당 주문의 수량을 변경하시겠습니까?(Y/N)\n");
            System.out.print("입력: ");
            Main.answer = scan.nextLine();

            if (Main.answer.equals("Y") || Main.answer.equals("y")) {
                return true;
            } else if (Main.answer.equals("N") || Main.answer.equals("n")) {
                return false;
            } else {
                System.out.println();
                MainView.singleLine();
                System.out.println();
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }//checkEditContinue

	private static boolean checkDeleteContinue() {
		while (true) {

            System.out.println();
            System.out.print("해당 주문을 취소하시겠습니까?(Y/N)\n");
            System.out.print("입력: ");
            Main.answer = scan.nextLine();

            if (Main.answer.equals("Y") || Main.answer.equals("y")) {
                return true;
            } else if (Main.answer.equals("N") || Main.answer.equals("n")) {
                return false;
            } else {
                System.out.println();
                MainView.singleLine();
                System.out.println();
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
	}//checkDeleteContinue
}
