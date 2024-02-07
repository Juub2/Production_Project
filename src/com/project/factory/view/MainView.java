package com.project.factory.view;

import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.member.Identify;
import com.project.factory.member.Modify;
import com.project.factory.view.dept.ProductionView;
import com.project.factory.view.dept.DistributionView;
import com.project.factory.view.dept.HumanView;
import com.project.factory.view.dept.ManagementView;


public class MainView {

	public static Scanner scan = new Scanner(System.in);
	
	public static void logo() {
		System.out.print("                                                           ~::::::~             .:::::::::::::::::::::::::~                       \r\n"
				+ "                                                      .-=@$===**====@#-,        :@@=====================$@$                       \r\n"
				+ "                                                     =@#~   ..@@:.   .#@@.      :@@,,,,,,,,,,,,,,,,,,,,,!@$                       \r\n"
				+ "                                                   .@@:    @@#@@$@@    ,@@~     :@@,,,,,,,,,,,,,,,,,,,,,!@$                       \r\n"
				+ "                                                   #@~     @@ @@~.-.    .@@  $@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                    \r\n"
				+ "                                                   @@,     -::@@=@#      @@  @@;,,*====*,,-*====;,,~=====:--@@                    \r\n"
				+ "                                                   :@#.    ;;.@@~=@~    !@*  @@;,~@@!!@@~,;@@!!@@,,=@$!$@=--@@                    \r\n"
				+ "                                                    ;@@~   ~==@@$=!   ~#@@***@@;,~@@**@@~,;@@**@@,,=@#*$@=--@@                    \r\n"
				+ "                                                      ;#@@,,,.  .,,,=@@$~~~::@@;,,,,,,,,,,,,,,,,,,,,,,------@@                    \r\n"
				+ "                                                          :@@@@@@@@$:~~~~~~::@@;,~@@@@@@~,;@@@@@@,,=@@@@@=--@@                    \r\n"
				+ "                                                            @@~~~~~~~~~~~~~::@@;,~@@  @@~,;@@  @@,,=@; ;@=--@@                    \r\n"
				+ "                                                            @@~~~~~~~~~~~~~::@@;,-;;;;;;-,-;;;;;:,,~;;;;;:--@@                    \r\n"
				+ "                                                            @@~~$@*~*@$~~@@::@@;,-!!!!!!-,~!!!!!!,,;!!***;--@@                    \r\n"
				+ "                                                            @@~~;!;~;!;~~!!::@@;,~@@  @@~,;@@  @@,,=@; ;@=--@@                    \r\n"
				+ "                                                            @@~~=#*~*#=~~##::@@;,~######~,:######,,*##@@@*--@@                    \r\n"
				+ "                                                            @@~~$@=~*@$~~@@::@@;,,,,,,,,,,,,,,,,,,,,,,------@@                    \r\n"
				+ "                                                            @@~~:::~:::~~::::@@;,,,,,-----------------------@@                    \r\n"
				+ "                                                            @@~~$@=~*@$~~@@::@@;,,,,@@:::::::@@::::::=@*----@@                    \r\n"
				+ "                                                            @@~~~~~~~~~~~~~::@@;,,,,@@,,,,,,,@@,,,,,,!@*----@@                    \r\n"
				+ "                                                            @@~~~~~~~~~~~~~::@@;,,,,@@,,,,,,,@@,,,,,,!@*----@@                    \r\n"
				+ "                                                            @@~~~~~~~~~~~~~::@@;,,,,@@,,,,,,,@@,,,,,,!@*----@@                    \r\n"
				+ "                                                            @@~~~~~~~~~~~~~::@@;,,,,@@,,,,,,,@@,,,,,,!@*----@@                    \r\n"
				+ "                                                            @@:::::::::::::::@@!----@@,,,,,,,@@,,,,,,!@*----@@            		  \r\n"
				+ "	    		 	         .#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@=  \r\n"
				+ "	    	:@@@******************************************************************************************************************************************#%@@*\r\n"
				+ " 		:@@@     ######   ######     ###    ##    ##  ######   ##    ##  #######  ##     ## ##    ##  ######   ######## ########  ######  ##     ##   #%@@*\r\n"
				+ "   		:@@@	##    ## ##    ##   ## ##   ###   ## ##    ##   ##  ##  ##     ## ##     ## ###   ## ##    ##     ##    ##       ##    ## ##     ##   #%@@*\r\n"
				+ "   		:@@@	##       ##        ##   ##  ####  ## ##          ####   ##     ## ##     ## ####  ## ##           ##    ##       ##       ##     ##   #%@@*\r\n"
				+ "    		:@@@	 ######   ######  ##     ## ## ## ## ##   ####    ##    ##     ## ##     ## ## ## ## ##   ####    ##    ######   ##       #########   #%@@*\r\n"
				+ " 		:@@@	      ##       ## ######### ##  #### ##    ##     ##    ##     ## ##     ## ##  #### ##    ##     ##    ##       ##       ##     ##   #%@@*\r\n"
				+ "   		:@@@	##    ## ##    ## ##     ## ##   ### ##    ##     ##    ##     ## ##     ## ##   ### ##    ##     ##    ##       ##    ## ##     ##   #%@@*\r\n"
				+ "    		:@@@	 ######   ######  ##     ## ##    ##  ######      ##     #######   #######  ##    ##  ######      ##    ########  ######  ##     ##   #%@@*\r\n"
				+ "	    	:@@@#*****************************************************************************************************************************************#%@@*\r\n"
				+ "	    	:@@@###########################################################################################################################################@@@* ");
		System.out.println();
	}

	// TODO mainMenu 메서드 추가
	public static void mainMenu() {

		if (Identify.auth == null) {
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("4. 아이디/비밀번호 찾기");
		} else {
			//로그인 시 초기 화면
			if(Identify.dept.equals("생산") && Identify.level.equals("2")) {
				//생산부 직원 뷰
				ProductionView.productionView();
			}
			if(Identify.dept.equals("유통") && Identify.level.equals("2")) {
				//유통부 사원
				DistributionView.DistributionMemberView();
			}
			if(Identify.dept.equals("생산") && Identify.level.equals("1")) {
				//생산부 관리자 뷰
				ProductionView.productionAdminView();
			}
			if(Identify.dept.equals("유통") && Identify.level.equals("1")) {
				//유통부 관리자
				DistributionView.DistributionAdminView();
			}if(Identify.dept.equals("경영") && Identify.level.equals("1")) {
				//경영부 관리자
				ManagementView.managementAdminView();
			}if(Identify.dept.equals("인사") && Identify.level.equals("1")) {
				//인사부 관리자
				HumanView.HumanAdminView();
			}
		}

		//회원 공통
		if (Identify.auth != null) {
			System.out.println("3. 로그아웃");
			System.out.println("5. 개인정보 조회 및 수정");
		}
		
		// 사원, 관리자 공통
		if (Identify.auth != null && (Identify.level.equals("1") || Identify.level.equals("2"))) {
			System.out.println("6. 근태 등록");
			System.out.println("7. 공지 사항");
		}

		// 관리자 공통
		if (Identify.auth != null && Identify.level.equals("1")) {
			// TODO 10번 사원 출/퇴근 조회로 이름 변경
			System.out.println("10. 사원 출/퇴근 조회");
			System.out.println("11. 재고 확인");
			System.out.println("12. 공지사항 관리");
		}

		// 생산부 사원
		if (Identify.auth != null && Identify.level.equals("2") && Identify.dept.equals("생산")) {
			System.out.println("8. 부품 재고 파악 및 발주");
			
		}

		// 유통부 사원
		if (Identify.auth != null && Identify.level.equals("2") && Identify.dept.equals("유통")) {
			System.out.println("9. 배송지 조회 및 유통");
		}

		// 생산부 관리자
		if (Identify.auth != null && Identify.level.equals("1") && Identify.dept.equals("생산")) {
			System.out.println("14. 생산 목표량 입력 및 수정");
			System.out.println("15. 생산 관리");
		}

		// 유통부 관리자
		if (Identify.auth != null && Identify.level.equals("1") && Identify.dept.equals("유통")) {
			System.out.println("16. 주문서 확인 및 배정 수정");
			System.out.println("17. 사원 배송 구역 관리");
		}

		// 인사부 관리자
		if (Identify.auth != null && Identify.level.equals("1") && Identify.dept.equals("인사")) {
			System.out.println("13. 사원 관리");
		}

		// 경영부 관리자
		if (Identify.auth != null && Identify.level.equals("1") && Identify.dept.equals("경영")) {
			System.out.println("18. 대리점 관리");
		}
		
		// 대리점
		if (Identify.auth != null && Identify.level.equals("3")) {
			System.out.println("19. 주문서 관리");
		}

		System.out.println("0. 종료");
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	// TODO pause 메서드 추가
	public static void pause() {
		System.out.println();
		System.out.print("엔터를 치면 초기화면으로 돌아갑니다.");
		scan.nextLine();
		System.out.println();

	}
	
	public static void pauseToSel() {
		System.out.println("엔터를 치면 선택 화면으로 돌아갑니다.");
		scan.nextLine();
		System.out.println();
	}

	// TODO checkContinue 메서드 추가
	// y,s 답변 받는 메서드
	public static void checkContinue() {
		System.out.println();
		System.out.print("계속 진행하시겠습니까?(Y/N)\n");
		System.out.print("입력: ");
		Main.answer = scan.nextLine();
	}


	public static boolean checkContinueBoolean() {
        Scanner scan = new Scanner(System.in);
        while (true) {

            System.out.println();
            System.out.print("계속 진행하시겠습니까?(Y/N)\n");
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

	// TODO doubleLine 메서드 추가
	public static void doubleLine() {
		System.out.println("===========================================================================");
	}

	// TODO singnleLine 메서드 추가
	public static void singleLine() {
		System.out.println("---------------------------------------------------------------------------");
	}

}