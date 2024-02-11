package com.project.factory.view;

import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.member.Identify;
import com.project.factory.view.dept.DistributionView;
//import com.project.factory.view.dept.DistributionView;
import com.project.factory.view.dept.HumanView;
import com.project.factory.view.dept.ManagementView;
import com.project.factory.view.dept.ProductionView;

/**
 * 메인 뷰를 담당하는 클래스이다.
 */
public class MainView {

	public static Scanner scan = new Scanner(System.in);
	
	/**
     * 로고를 출력하는 메서드이다.
     */
	public static void logo() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
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
	
	
	/**
     * 메인 메뉴를 출력하는 메서드이다.
     */
	public static void mainMenu() {
		
		MainView.title("쌍용 테크");

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

	/**
     * 화면 일시 정지 후 초기 화면으로 돌아가기 위한 메서드이다.
     */
	public static void pause() {
		System.out.println();
		System.out.print("엔터를 치면 초기화면으로 돌아갑니다.");
		scan.nextLine();
		System.out.println();
		System.out.println();
		System.out.println();

	}
	
	/**
     * 화면 일시 정지 후 선택 화면으로 돌아가기 위한 메서드이다.
     */
	public static void pauseToSel() {
		System.out.println();
		System.out.println("엔터를 치면 선택 화면으로 돌아갑니다.");
		scan.nextLine();
		System.out.println();
		System.out.println();
		System.out.println();
	}

	/**
     * 계속 진행 여부를 확인하는 메서드이다.
     */
	public static void checkContinue() {
		System.out.println();
		System.out.print("계속 진행하시겠습니까?(Y/N)\n");
		System.out.print("입력: ");
		Main.answer = scan.nextLine();
	}

	/**
	 * 계속 진행 여부를 확인하고 boolean 값으로 반환하는 메서드이다.
	 * @return 계속 진행할 경우 true를 반환하고, 그렇지 않은 경우 false를 반환
	 */
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

	/**
     * 이중선을 출력하는 메서드이다.
     */
	public static void doubleLine() {
		System.out.println("==========================================================================================================================================");
	}

	/**
     * 단일선을 출력하는 메서드이다.
     */
	public static void singleLine() {
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
	}

	/**
     * 제목을 출력하는 메서드이다.
     * @param title 출력할 제목 문자열
     */
	public static void title(String title) {
		System.out.println();
		MainView.doubleLine();
		System.out.println("\t\t\t\t\t\t\t" + title);
		MainView.doubleLine();
	}
}