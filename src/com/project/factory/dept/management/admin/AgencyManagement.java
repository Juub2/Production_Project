package com.project.factory.dept.management.admin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.factory.Main;
import com.project.factory.Toolkit;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.resource.Path;
import com.project.factory.view.MainView;
import com.project.factory.view.dept.AreaView;
import com.project.factory.view.sub.AgencyManagementView;

//대리점 ID■비밀번호■대리점 명■전화번호■이메일■주소■지역 번호
//S8132101■1234■강남1호점■010-1234-4567■gangnam1@auto.com■서울특별시 강남구 테헤란로 132■2
/**
 * 대리점 계정 관리를 담당하는 클래스이다.
 */
public class AgencyManagement {

	static Scanner scan = new Scanner(System.in);

	static String regex = ""; // 유효성 검사를 위한 변수

	static String name = ""; // 이름
	static String phoneNum = ""; // 전화번호
	static String areaNum = ""; // 구역번호(부서)
	static String address = ""; // 주소
	static String id = ""; // 아이디

	/**
	 * 대리점 계정 관리 메뉴를 실행하는 메서드이다.
	 */
	public static void agencyManagement() {

		Data.load(); //대리점 계정 등록 후 멤버 리스트 초기화후 다시 불러오기 위해서 필요

		AgencyManagementView.agencyManagementMenu();

		Main.selectNum = scan.nextLine();

		if (Main.selectNum.equals("1")) {
			AgencyManagement.registerAgency();
		} else if (Main.selectNum.equals("2")) {
			AgencyManagement.deleteAgency();
		} else {
			System.out.println();
			System.out.println("잘못된 번호입니다.");
			if (MainView.checkContinueBoolean()) {
				AgencyManagement.agencyManagement();
				;
				return;
			} else {
				MainView.pause();
				return;
			}
		}
	}// agencyManagement

	/**
	 * 대리점 계정을 등록하는 메서드이다.
	 */
	private static void registerAgency() {

		try {
			MainView.title("대리점 등록");
			System.out.println();

			if (AgencyManagement.registerAgencyName()) {
				System.out.println();
				if (AgencyManagement.registerAgencyPhoneNum()) {
					System.out.println();
					if (AgencyManagement.registerAgencyArea()) {
						System.out.println();
						if (AgencyManagement.registerAgencyAddress()) {
							if (AgencyManagement.checkRegisterContinue()) {
								BufferedWriter writer = new BufferedWriter(new FileWriter(Path.MEMBER, true)); // true인 경우 이어쓰기

								AgencyManagement.createAgencyId();// 대리점 아이디 생성

								// 사원번호■비밀번호■이름■생년월일■전화번호■주소■직급■부서■이메일
								// 대리점ID■비밀번호■이름■■전화번호■주소■직급■구역■이메일
								writer.write(AgencyManagement.id + "■" + "1234" + "■" + AgencyManagement.name + "■" + ""
										+ "■" + AgencyManagement.phoneNum + "■" + AgencyManagement.address + "■" + "3"
										+ "■" + AgencyManagement.areaNum + "■" + AgencyManagement.id + "@auto.com");
								writer.newLine();
								writer.close();

								System.out.println();
								System.out.println("새로운 대리점이 등록되었습니다.");
								System.out.println();
								System.out.println("아이디: " + AgencyManagement.id);
								System.out.println("초기 비밀번호: 1234");

								MainView.pauseToSel();

								AgencyManagement.agencyManagement();
								return;

							} else {
								MainView.pauseToSel();

								AgencyManagement.agencyManagement();
								return;
							}

						} else {
							MainView.pauseToSel();

							AgencyManagement.agencyManagement();
							return;
						}
					} else {
						MainView.pauseToSel();

						AgencyManagement.agencyManagement();
						return;
					}
				} else {
					MainView.pauseToSel();

					AgencyManagement.agencyManagement();
					return;
				}
			} else {
				MainView.pauseToSel();

				AgencyManagement.agencyManagement();
				return;
			}
		} catch (Exception e) {
			System.out.println("AgencyManagement.registerAgency");
			e.printStackTrace();
		}

	}// registerAgency

	// 이름도 중복 검사를 진행해서 이름으로 삭제하는 게 편할 듯
	/**
	 * 대리점 계정을 삭제하는 메서드이다.
	 */
	private static void deleteAgency() {

		boolean loop = false;

		while (true) {
			AgencyManagementView.deleteAgencyMenu();
			AgencyManagement.name = scan.nextLine();

			for (Members member : Data.memberList) {
				if (member.getName().equals(AgencyManagement.name)) {
					
					loop = true;
									
					System.out.println();
					MainView.singleLine();
					System.out.println();
					System.out.printf("%-10s\t%-12S\t%-10S\t%-40S\r\n", "[대리점명]", "[전화번호]", "[구역]", "[주소]");
					System.out.printf("%-10s\t%-12S\t%-10S\t%-40S\n", member.getName(),member.getPhoneNum(),member.getArea(member.getDept()), member.getAddress());
					System.out.println();
					MainView.singleLine();
					
					if (AgencyManagement.checkDeleteContinue()) {
						Data.memberList.remove(member);// 해당 member 삭제

						Data.save();

						System.out.println();
						System.out.println("대리점이 성공적으로 삭제되었습니다.");
					} 
					break;
				} 
			}//for

			if (loop) {
				MainView.pauseToSel();

				AgencyManagement.agencyManagement();
				return;
			} else {
				System.out.println("존재하지 않는 대리점입니다.");

				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					MainView.pauseToSel();

					AgencyManagement.agencyManagement();
					return;
				}
			}
		}
	}// deleteAgency

	/**
	 * 대리점 등록 시 대리점명을 작성하는 메서드이다.
	 * 
	 * @return 대리점명 등록 여부
	 */
	private static boolean registerAgencyName() {
		while (true) {
			System.out.print("대리점명: ");
			AgencyManagement.name = scan.nextLine();

			if (AgencyManagement.invalidateName()) {
				System.out.println();
				System.out.println("대리점명을 잘못 입력했습니다.");
				System.out.println("대리점명은 최소 5글자, 한글&숫자만 입력 가능합니다.");

				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					return false;
				}
			} else {
				if (AgencyManagement.checkNameExists()) {
					System.out.println("이미 등록된 대리점입니다.");

					if (MainView.checkContinueBoolean()) {
						continue;
					} else {
						return false;
					}
				} else {
					return true;
				}
			}

		}
	}// registerAgencyName

	/**
	 * 대리점 등록 시 전화번호를 작성하는 메서드이다.
	 * 
	 * @return 전화번호 등록 여부
	 */
	private static boolean registerAgencyPhoneNum() {
		while (true) {
			System.out.print("전화번호: ");
			AgencyManagement.phoneNum = scan.nextLine();

			if (Toolkit.invalidatePhoneNum(AgencyManagement.phoneNum)) {
				System.out.println();
				System.out.println("전화번호를 잘못 입력했습니다.");
				System.out.println("전화번호는 12-13글자(하이픈 포함), 숫자만 입력 가능합니다.");

				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					return false;
				}
			} else {
				if (AgencyManagement.checkPhoneNumExists()) {
					System.out.println("이미 등록된 전화번호입니다.");

					if (MainView.checkContinueBoolean()) {
						continue;
					} else {
						return false;
					}
				} else {
					return true;
				}
			}

		}
	}// registerAgencyPhoneNum

	/**
	 * 대리점 등록 시 구역 번호를 작성하는 메서드이다.
	 * 
	 * @return 구역 번호 등록 여부
	 */
	private static boolean registerAgencyArea() {
		while (true) {
			MainView.singleLine();
			AreaView.areaView();
			MainView.singleLine();
			System.out.print("구역 번호: ");
			AgencyManagement.areaNum = scan.nextLine();

			if (Main.selectNum.isEmpty()) { // 입력이 공백인 경우
				System.out.println("잘못된 번호입니다.");
				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					return false;
				}
			} else {
				if (Toolkit.isInteger(AgencyManagement.areaNum)) { // 정수값인지 확인

					if (AgencyManagement.invalidateArea()) {
						System.out.println();
						System.out.println("구역 번호를 잘못 입력했습니다.");
						System.out.println("구역 번호는 1-17사이 숫자만 입력 가능합니다.");

						if (MainView.checkContinueBoolean()) {
							continue;
						} else {
							return false;
						}
					} else {
						return true;
					}

				} else {
					System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
					if (MainView.checkContinueBoolean()) {
						continue;
					} else {
						return false;
					}
				}
			}
		}
	}// registerAgencyArea

	/**
	 * 대리점 등록 시 주소를 작성하는 메서드이다.
	 * 
	 * @return 주소 등록 여부
	 */
	private static boolean registerAgencyAddress() {
		while (true) {
			System.out.print("주소: ");
			AgencyManagement.address = scan.nextLine();

			if (Toolkit.invalidateAddress(AgencyManagement.address)) {
				System.out.println();
				System.out.println("주소를 잘못 입력했습니다.");
				System.out.println("주소는 2-13글자(로/길), 한글&숫자만 입력 가능합니다.");

				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					return false;
				}
			} else {
				return true;
			}

		}
	}

	/**
	 * 대리점명의 유효성을 검사하는 메서드이다.
	 * 
	 * @return 이름의 유효성 여부
	 */
	private static boolean invalidateName() {
		// 대리점명 > 최소 5글자 한글&숫자만
		AgencyManagement.regex = "^[가-힣0-9]{5,}$";
		Pattern namePattern = Pattern.compile(AgencyManagement.regex);
		Matcher nameMatcher = namePattern.matcher(AgencyManagement.name);

		return !nameMatcher.find();
	}

	/**
	 * 대리점 구역 번호의 유효성을 검사하는 메서드이다.
	 * 
	 * @return 구역 번호의 유효성 여부
	 */
	private static boolean invalidateArea() {
		// 구역 번호 > 1-17
		return Integer.parseInt(AgencyManagement.areaNum) < 1 || Integer.parseInt(AgencyManagement.areaNum) > 17;
	}

	/**
	 * 대리점명의 중복을 검사하는 메서드이다.
	 * 
	 * @return 대리점명의 중복 여부
	 */
	private static boolean checkNameExists() {
		for (Members member : Data.memberList) {
			if (member.getLevel().equals("3")) {
				if (member.getName().equals(AgencyManagement.name)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 전화번호의 중복을 검사하는 메서드이다.
	 * 
	 * @return 전화번호의 중복 여부
	 */
	private static boolean checkPhoneNumExists() {
		for (Members member : Data.memberList) {
			if (member.getPhoneNum().equals(AgencyManagement.phoneNum)) {
				return true;
			}
		} // for
		return false;
	}

	/**
	 * 대리점 아이디를 생성하는 메서드이다.
	 */
	private static void createAgencyId() {

		Random random = new Random(); // 랜덤 객체 생성

		while (true) {
			int ranNumValue = 1000000 + random.nextInt(9000000); // 7자리 랜덤 숫자 (1000000~9999999)
			AgencyManagement.id = getAgencyAreaStr() + ranNumValue;

			boolean isDuplicate = false;

			for (Members member : Data.memberList) {
				if (member.getLevel().equals("3") && member.getId().equals(AgencyManagement.id)) {
					isDuplicate = true;
					break;
				}
			}

			if (!isDuplicate) {
				// 중복이 없으면 반복문 종료
				break;
			}
		}
	}// createAgencyId

	/**
	 * 행정 구역 번호에 해당하는 문자를 반환하는 메서드이다.
	 * 
	 * @return 구역 번호에 해당하는 문자
	 */
	private static String getAgencyAreaStr() {
		switch (AgencyManagement.areaNum) {
		case "1":
			return "S";
		case "2":
			return "F";
		case "3":
			return "H";
		case "4":
			return "I";
		case "5":
			return "J";
		case "6":
			return "E";
		case "7":
			return "U";
		case "8":
			return "Z";
		case "9":
			return "G";
		case "10":
			return "K";
		case "11":
			return "L";
		case "12":
			return "M";
		case "13":
			return "N";
		case "14":
			return "O";
		case "15":
			return "P";
		case "16":
			return "Q";
		case "17":
			return "R";
		}
		return "";
	}

	/**
	 * 대리점 등록 과정을 계속할지 여부를 확인하는 메서드이다.
	 * 
	 * @return 등록 과정을 계속할지 여부
	 */
	private static boolean checkRegisterContinue() {
		while (true) {
			System.out.println();
			System.out.print("대리점 등록을 진행하시겠습니까?(Y/N)\n");
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
	}

	/**
	 * 삭제 과정을 계속할지 여부를 확인하는 메서드이다.
	 * 
	 * @return 삭제 과정을 계속할지 여부
	 */
	private static boolean checkDeleteContinue() {
		while (true) {
			System.out.println();
			System.out.print("대리점 삭제를 진행하시겠습니까?(Y/N)\n");
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
	}

}
