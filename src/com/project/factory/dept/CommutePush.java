package com.project.factory.dept;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.Today;
import com.project.factory.board.admin.BoardManagement;
import com.project.factory.member.Identify;
import com.project.factory.resource.CommuteData;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.resource.Path;
import com.project.factory.resource.user.Commute;
import com.project.factory.view.MainView;

import com.project.factory.view.dept.CommutePushView;



public class CommutePush {

	// 근태 등록

	// 출근, 퇴근 누적시키기

	public static void commutePush() {

		CommuteData.load();

		int isGoWork = checkGoWork(Identify.auth);


		Scanner scan = new Scanner(System.in);
		CommutePushView.commutePushViewMain();
		
		

		// 출근을 안 하면
		if (isGoWork == 0) {
			CommutePushView.notCommutePushView();
			Main.selectNum = scan.nextLine();

			if (Main.selectNum.equals("1")) {

				String currentTime = Today.time();
				System.out.println();
				System.out.println(currentTime + " 출근하셨습니다.");
				System.out.println();
				gowork(Identify.auth);
				
				MainView.pause();

			} else {
				System.out.println();
				System.out.println("잘못된 입력입니다.");
				if(MainView.checkContinueBoolean()) {
					commutePush();
				}else {
					System.out.println();
					System.out.println("잘못된 입력입니다.");
					MainView.pause();
				}
				

			}

		} else if (isGoWork == 1) {
			CommutePushView.commutePushViewEnd();
			Main.selectNum = scan.nextLine();

			if (Main.selectNum.equals("2")) {

				String currentTime = Today.time();
				System.out.println();
				System.out.println(currentTime + " 퇴근하셨습니다.");
				System.out.println();
				leaveOut(Identify.auth);
				MainView.pause();

				// 퇴근 처리를 위한 메서드 호출 등 추가

			} else {
				System.out.println();
				System.out.println("잘못된 번호입니다.");
				if (MainView.checkContinueBoolean()) {
					BoardManagement.boardManagement();
					return;
				} else {
					MainView.pause();
					return;
				}
			}
		} else if (isGoWork == 2) {
			CommutePushView.alreadyCommute();

		}

	}

	private static int checkGoWork(String auth) {

		//System.out.println("CommuteData.commuteMemberList " + CommuteData.commuteMemberList);

		ArrayList<Commute> matchingCommutes = new ArrayList<>();

		for (Commute commuteMember : CommuteData.commuteMemberList) {
			if(commuteMember.getId().equals(auth) ) {
				if(!(Today.day().equals(commuteMember.getCal()))) {
					matchingCommutes.clear();
					
				}else {
					matchingCommutes.add(commuteMember);
				}
				
			}
			
		}
		int matchingCount = matchingCommutes.size();

		if (matchingCount == 0) {
			return 0;
		} else if (matchingCount == 1) {
			return 1;
		} else if (matchingCount == 2) {
			return 2;
		}
		return matchingCount;
		
	}

	// commute.txt에 퇴근 등록
	private static void leaveOut(String auth) {

		try {

			FileWriter writer = new FileWriter(Path.COMMUTE, true);

			
			for (Members member : Data.memberList) {
				if (member.getId().equals(auth)) {
					String commuteRecord = Today.day() + "■" + member.getId() + "■" + member.getName() + "■"
							+ member.getDept() + "■" + member.getLevel() + "■" + Today.time() + "■" + "퇴근";
					writer.write(commuteRecord);
					writer.write(System.getProperty("line.separator")); // 개행 문자 추가

					break;
				}
			}

		
			writer.close();

		} catch (Exception e) {
			System.out.println("CommutePush.gowork");
			e.printStackTrace();
		}

	}

	// commute.txt에 출근 등록
	private static void gowork(String auth) {

		try {

			FileWriter writer = new FileWriter(Path.COMMUTE, true);

			// 날짜■사원번호■이름■부서■직급■출근시간
			for (Members member : Data.memberList) {
				if (member.getId().equals(auth)) {
					String commuteRecord = Today.day() + "■" + member.getId() + "■" + member.getName() + "■"
							+ member.getDept() + "■" + member.getLevel() + "■" + Today.time() + "■" + "출근";

					writer.write(commuteRecord);
					writer.write("\r\n");

					break;
				}
			}

			
			writer.close();

		} catch (Exception e) {
			System.out.println("CommutePush.gowork");
			e.printStackTrace();
		}

	}

}
