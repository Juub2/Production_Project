package com.project.factory.dept.human.admin;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.resource.NewMemberData;
import com.project.factory.resource.NewMemberTempData;
import com.project.factory.view.MainView;
import com.project.factory.view.dept.HumanView;

public class HRM {

	private static String employeeId;

	public static void hrd() {
		NewMemberData.load();
		HumanView.hrmTitle();
		hrmSelect();
	}

	public static boolean continueOperation() {
		Scanner scan = new Scanner(System.in);

		System.out.print("계속 진행하시겠습니까? (Y/N): ");
		String answer = scan.nextLine().toUpperCase();

		return answer.equals("Y");
	}

	public static void hrmSelect() {
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.println("1. 입사 관리");
		System.out.println("2. 퇴사 관리");
		System.out.println("3. 사원 조회");
		System.out.println();
		MainView.singleLine();
		System.out.println();

		System.out.print("번호 입력: ");

		Main.selectNum = scan.nextLine();


		if (Main.selectNum.equals("1")) {


			// 리스트가 초기화되고 사원번호가 모두 부여된 경우에만 메시지 출력
			if (NewMemberData.NewMemberList.isEmpty()) {
				System.out.println("사원번호가 모두 부여되었습니다.");
				MainView.pause();

			} else {
				addUser();
			}
		} else if (Main.selectNum.equals("2")) {
			deleteUser();
		} else if (Main.selectNum.equals("3")) {
			searchUser();
		} else {
			System.out.println();
			System.out.println("잘못된 번호 입력입니다.");
			System.out.println();

			if (continueOperation()) {
				hrd();
			} else {
				MainView.pause();
			}
		}
	}

	private static void addUser() {
		newMemberList();

		System.out.println();
		System.out.println("1. 사원번호 생성하기");
		MainView.singleLine();
		System.out.print("번호 입력: ");

		Scanner scan = new Scanner(System.in);

		Main.selectNum = scan.nextLine();

		if (Main.selectNum.equals("1")) {
			createUserId();
		} else {
			System.out.println("잘못된 번호입니다.");
			hrd();
		}
	}

	private static void deleteUser() {
		System.out.println();
		System.out.print("사원 번호 입력: ");

		Scanner scan = new Scanner(System.in);

		String id = scan.nextLine();

		Iterator<Members> iterator = Data.memberList.iterator();
		boolean found = false; // 일치하는 회원이 있는지 여부를 나타내는 변수

		while (iterator.hasNext()) {
			Members member = iterator.next();

			if (member.getId().equals(id)) {
				MainView.doubleLine();
				System.out.println();
				System.out.println("[사원번호]\t[이름]\t[생년월일]\t\t[전화번호]\t\t[주소]\t[부서]");
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", member.getId() ,member.getName(), member.getBirth(),
						member.getPhoneNum(), member.getAddress(), member.getDept());

				System.out.println();
				MainView.doubleLine();

				System.out.println();
				System.out.print("퇴사 처리하시겠습니까? (Y/N): ");
				String answer = scan.nextLine().toUpperCase();

				if (answer.equals("Y")) {
					System.out.println("퇴사 처리가 완료되었습니다.");
					iterator.remove(); // Remove the member from the list

					// 파일에서도 삭제하기 위해 새로운 데이터를 파일에 쓰기
					Data.save();

				} else if (answer.equals("N")) {
					System.out.println("퇴사 처리가 취소되었습니다.");
					hrd();
				} else {
					System.out.println("잘못된 입력입니다.");
					MainView.checkContinue();
					if (answer.equals("Y")) {
						deleteUser();
					} else {
						hrd();
					}
				}

				found = true; // 일치하는 회원이 있음을 표시
				break; // Exit the loop once the member is found and processed
			}
		}

		if (!found) {
			System.out.println();
			System.out.println("일치하는 회원이 없습니다. 다시 시도해주세요.");
			System.out.println();
			hrd(); // 일치하는 회원이 없으면 hrd() 메서드를 호출하여 다시 시작
		}
	}

	private static void searchUser() {
		System.out.println();
		System.out.print("사원 이름 입력: ");

		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		
		  if (name.isEmpty()) {
			  System.out.println();
		        System.out.println("사원 이름을 입력해주세요.");
		        hrd();
		        return;
		    }

		Iterator<Members> iterator = Data.memberList.iterator();
		boolean found = false; // 일치하는 회원이 있는지 여부를 나타내는 변수

		while (iterator.hasNext()) {
			Members member = iterator.next();

			if (member.getName().contains(name)) {
				if (!found) {
					MainView.doubleLine();
					System.out.println();
					System.out.println("[사원번호]\t[이름]\t\t[생년월일]\t\t[전화번호]\t\t[주소]\t\t\t\t[부서]");
				}

				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\t%s\n", member.getId(), member.getName(), member.getBirth(),
						member.getPhoneNum(), member.getAddress(), member.getDept());
				System.out.println();

				found = true; // 일치하는 회원이 있음을 표시
				
				MainView.pause(); // 검색이 완료된 후에 초기 화면으로 돌아감
			}
		}

		if (!found) {
			System.out.println();
			System.out.println("일치하는 회원이 없습니다. 다시 시도해주세요.");
			System.out.println();
			hrd();
		}

		
	}

	private static void createUserId() {
		Iterator<NewMembers> iterator = NewMemberData.NewMemberList.iterator();
		boolean allAssigned = true;

		while (iterator.hasNext()) {
			NewMembers newMember = iterator.next();
			String department = newMember.getDept();
			String employeeIdDept = "";

			if (newMember.getId().equals("0")) {
				switch (department) {
				case "생산":
					employeeIdDept = "A";
					break;
				case "유통":
					employeeIdDept = "B";
					break;
				case "인사":
					employeeIdDept = "C";
					break;
				case "경영":
					employeeIdDept = "D";
					break;
				// 다른 부서에 대한 처리 추가
				}

				Random random = new Random();
				int randomNumber = 100000 + random.nextInt(900000); // 6자리 랜덤 숫자 (100000~999999)
				newMember.setId(String.valueOf(randomNumber) + employeeIdDept);

				System.out.println("생성된 사원번호: " + newMember.getId());


				// 사원번호가 생성되면 newMemberTemp.txt에 저장

				NewMemberTempData.save(newMember);
			}
		}
		NewMemberData.NewMemberList.clear();

		NewMemberData.save();


	}

	private static void newMemberList() {
		MainView.doubleLine();
		System.out.println();
		System.out.println("[신입사원 리스트]");
		System.out.println("[사원번호]\t[비밀번호]\t[이름]\t\t[전화번호]\t\t[생년월일]\t\t[주소]\t\t\t[부서]\t[직급]");

		Iterator<NewMembers> iterator = NewMemberData.NewMemberList.iterator();
		List<NewMembers> tempList = new ArrayList<>();

		while (iterator.hasNext()) {
			NewMembers newMember = iterator.next();

			System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t%s\t%s\n", newMember.getId(),newMember.getPw() ,newMember.getName(),
					newMember.getPhoneNum(), newMember.getBirth(), newMember.getAddress(), newMember.getDept(),
					newMember.getLevel());

			if (!"0".equals(newMember.getId())) {
				// 생성된 사원번호를 부여한 후에 해당 멤버를 리스트에서 삭제
				iterator.remove();
			}
		}
	}

}
