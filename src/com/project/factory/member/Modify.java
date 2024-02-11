package com.project.factory.member;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.factory.Main;
import com.project.factory.Toolkit;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.view.MainView;
import com.project.factory.view.member.ModifyView;

/**
 * 회원 정보 수정 기능을 제공하는 클래스이다.
 */
public class Modify {

	static String regex = ""; // 정규식을 이용한 유효성 검사를 위한 변수
	
	/**
     * 회원 정보를 조회 및 수정하는 메서드이다.
     */
	public static void modify() {

		Scanner scan = new Scanner(System.in);

		ModifyView.modifyMenu();

		Main.selectNum = scan.nextLine();

		if (Main.selectNum.equals("1")) {
			Modify.modifyName();
		} else if (Main.selectNum.equals("2")) {
			Modify.modifyPhoneNum();
		} else if (Main.selectNum.equals("3")) {
			Modify.modifyAddress();
		} else if (Main.selectNum.equals("4")) {
			Modify.modifyPw();
		} else {
			System.out.println();
			System.out.println("잘못된 번호입니다.");
			MainView.pause();
		}

	}

	/**
	 * 이름을 수정하는 메서드이다.
	 */
	public static void modifyName() {

		String name = "";

		Scanner scan = new Scanner(System.in);

		MainView.title("이름 수정");
		System.out.print("이름: ");
		name = scan.nextLine();

		if (invalidateName(name)) {
			System.out.println();
			System.out.println("이름을 잘못 입력했습니다.");
			System.out.println("이름은 2-5글자, 한글만 입력 가능합니다.");

			Modify.checkContinue();

		} else {
			// 유효성 검사는 통과한 경우 이전 이름에서 변경된 내용이 없는지 확인한다.
			if (checkNameExist(name)) {
				// 변경된 내용이 없는 경우
				System.out.println();
				System.out.println("변경된 내용이 없습니다.");

				Modify.checkContinue();

			} else {
				// 변경된 이름인 경우
				System.out.println();
				System.out.println("이름이 변경되었습니다.");
				MainView.pause();
			}
		}

	}// modifyName

	/**
	 * 전화번호를 수정하는 메서드이다.
	 */
	public static void modifyPhoneNum() {

		String phoneNum = "";

		Scanner scan = new Scanner(System.in);

		MainView.title("전화번호 수정");
		System.out.print("전화번호: ");
		phoneNum = scan.nextLine();

		if (Toolkit.invalidatePhoneNum(phoneNum)) {
			System.out.println();
			System.out.println("전화번호를 잘못 입력했습니다.");
			System.out.println("전화번호는 12-13글자(하이픈 포함), 숫자만 입력 가능합니다.");

			Modify.checkContinue();

		} else {

			if (checkPhoneNumExist(phoneNum)) {
				System.out.println();
				System.out.println("변경된 내용이 없습니다.");

				Modify.checkContinue();

			} else {
				System.out.println();
				System.out.println("전화번호가 변경되었습니다.");
				MainView.pause();
			}
		}
	}// modifyPhoneNum

	/**
	 * 주소를 수정하는 메서드이다.
	 */
	public static void modifyAddress() {
		String address = "";

		Scanner scan = new Scanner(System.in);

		MainView.title("주소 수정");
		System.out.print("주소: ");
		address = scan.nextLine();

		if (Toolkit.invalidateAddress(address)) {
			System.out.println();
			System.out.println("주소를 잘못 입력했습니다.");
			System.out.println("주소는 2-13글자(로/길), 한글&숫자만 입력 가능합니다.");

			Modify.checkContinue();

		} else {

			if (checkAddressExist(address)) {
				System.out.println();
				System.out.println("변경된 내용이 없습니다.");

				Modify.checkContinue();

			} else {
				System.out.println();
				System.out.println("주소가 변경되었습니다.");
				MainView.pause();
			}
		}

	}// modifyAddress

	/**
	 * 비밀번호를 수정하는 메서드이다.
	 */
	public static void modifyPw() {
		String pw = "";

		Scanner scan = new Scanner(System.in);

		MainView.title("비밀번호 수정");
		System.out.print("비밀번호: ");
		pw = scan.nextLine();

		if (invalidatePw(pw)) {
			System.out.println();
			System.out.println("비밀번호를 잘못 입력했습니다.");
			System.out.println("비밀번호는 10-16글자, 영문자&숫자만 입력 가능합니다.");

			Modify.checkContinue();

		} else {

			if (checkPwExist(pw)) {
				System.out.println();
				System.out.println("변경된 내용이 없습니다.");

				Modify.checkContinue();

			} else {
				System.out.println();
				System.out.println("비밀번호가 변경되었습니다.");
				MainView.pause();
			}
		}

	}// modifyPw

	/**
	 * 주어진 이름의 변경 여부를 확인하고 수정하는 메서드이다.
	 * 
	 * @param name 확인할 이름
	 * @return 이름이 현재 사용자의 이름과 일치하면 true를 반환하고, 그렇지 않으면 false를 반환
	 */
	public static boolean checkNameExist(String name) {
		for (Members member : Data.memberList) {
			if (member.getId().equals(Identify.auth) && member.getName().equals(name)) {
				// 현재 로그인 중인 사용자 아이디와 리스트의 아이디가 일치하는 경우
				// 본인이 이미 사용 중인 이름으로 수정하려는 경우
				return true;

			} else {
				// 다른 이름으로 수정하려는 경우
				member.setName(name); // 수정
				return false;
			}

		} // for
		return true;
	}

	/**
	 * 주어진 전화번호의 변경 여부를 확인하고 수정하는 메서드이다.
	 * 
	 * @param phoneNum 확인할 전화번호
	 * @return 전화번호가 이미 존재하면 true를 반환하고, 그렇지 않으면 false를 반환
	 */
	private static boolean checkPhoneNumExist(String phoneNum) {
		for (Members member : Data.memberList) {
			if (member.getPhoneNum().equals(phoneNum)) {
				// 모든 회원의 전화번호와 중복성 검사
				return true;
			} else {
				// 전화번호가 중복하지 않는 경우 사원 번호 일치하는 전화번호 수정
				if (member.getId().equals(Identify.auth)) {
					member.setPhoneNum(phoneNum);
					return false;
				}
			}
		} // for
		return true;
	}

	/**
	 * 주어진 주소의 변경 여부를 확인하고 수정하는 메서드이다.
	 * 
	 * @param address 확인할 주소
	 * @return 주소가 현재 사용자의 주소와 일치하면 true를 반환하고, 그렇지 않으면 false를 반환
	 */
	public static boolean checkAddressExist(String address) {
		for (Members member : Data.memberList) {
			if (member.getId().equals(Identify.auth) && member.getAddress().equals(address)) {
				return true;
			} else {
				member.setAddress(address); // 수정
				return false;
			}
		} // for
		return true;
	}

	/**
	 * 주어진 비밀번호의 변경 여부를 확인하고 수정하는 메서드이다.
	 * 
	 * @param pw 확인할 비밀번호
	 * @return 비밀번호가 현재 사용자의 비밀번호와 일치하면 true를 반환하고, 그렇지 않으면 false를 반환
	 */
	public static boolean checkPwExist(String pw) {
		for (Members member : Data.memberList) {
			if (member.getId().equals(Identify.auth) && member.getPw().equals(pw)) {
				return true;
			} else {
				member.setPw(pw); // 수정
				return false;
			}
		} // for
		return true;
	}

	/**
     * 주어진 이름이 유효한지 검사하는 메서드이다.
     * 이름은 2자에서 5자 사이의 한글로 구성되어야 한다.
     * 
     * @param name 검사할 이름
     * @return 이름이 유효하면 false를 반환하고, 그렇지 않으면 true를 반환
     */
	private static boolean invalidateName(String name) {
		// 이름 > 2-5글자, 한글만
		Modify.regex = "^[가-힣]{2,5}$";
		Pattern namePattern = Pattern.compile(Modify.regex);
		Matcher nameMatcher = namePattern.matcher(name);

		return !nameMatcher.find();
	}

	 /**
     * 주어진 비밀번호가 유효한지 검사하는 메서드이다.
     * 비밀번호는 10자에서 16자 사이의 영문자와 숫자의 조합이어야 한다.
     * 
     * @param pw 검사할 비밀번호
     * @return 비밀번호가 유효하면 false를 반환하고, 그렇지 않으면 true를 반환
     */
	private static boolean invalidatePw(String pw) {
		// 비밀번호 > 10-16글자, 영문자&숫자
		Modify.regex = "^[a-zA-Z0-9]{10,16}$";
		Pattern pwPattern = Pattern.compile(Modify.regex);
		Matcher pwMatcher = pwPattern.matcher(pw);

		return !pwMatcher.find();
	}

	/**
     * 계속 진행할지 여부를 확인하는 메서드이다.
     */
	private static void checkContinue() {

		MainView.checkContinue();

		if (Main.answer.equals("Y") || Main.answer.equals("y")) {
			Modify.modify();
		} else if (Main.answer.equals("N") || Main.answer.equals("n")) {
			MainView.pause();
		} else {
			System.out.println();
			MainView.singleLine();
			System.out.println();
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			Modify.checkContinue();
		}
	}
}
