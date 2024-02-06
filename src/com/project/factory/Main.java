package com.project.factory;

import java.util.Scanner;

import com.project.factory.dept.distribution.admin.DestManagement;
import com.project.factory.dept.distribution.admin.OrderAssign;
import com.project.factory.dept.distribution.user.CheckDest;
import com.project.factory.board.ReadBoard;
import com.project.factory.board.admin.BoardManagement;
import com.project.factory.dept.CommutePush;
import com.project.factory.dept.human.admin.HRM;
import com.project.factory.dept.management.admin.AgencyManagement;
import com.project.factory.dept.production.admin.ProductionManagement;
import com.project.factory.dept.production.admin.SetProductionTarget;
import com.project.factory.dept.production.admin.resource.ProductionTarget;
import com.project.factory.dept.production.admin.resource.TodayProductionData;
import com.project.factory.dept.production.user.CheckPiece;
import com.project.factory.member.Find;
import com.project.factory.member.Identify;
import com.project.factory.member.Login;
import com.project.factory.member.Logout;
import com.project.factory.member.Modify;
import com.project.factory.member.SignUp;
import com.project.factory.member.admin.CommuteSearch;
import com.project.factory.member.admin.InventorySearch;
import com.project.factory.resource.CommuteData;
import com.project.factory.resource.Data;
import com.project.factory.resource.inventory.ModelInfoData;
import com.project.factory.resource.inventory.PieceData;
import com.project.factory.sub.agency.MyOrder;
import com.project.factory.view.MainView;
import com.project.factory.view.dept.HumanView;

public class Main {

	// TODO selectNum String으로 변경
	public static String selectNum;
	public static String answer;
	public static Object production;

	public static void main(String[] args) {

		// TODO loop 변수 추가 > 초기 화면 메뉴 반복문
		boolean loop = true;
		Scanner scan = new Scanner(System.in);
		PieceData.pieceLoad();
		Data.load();
		ModelInfoData.modelInfoLoad();
		TodayProductionData.todayInventoryLoad();
		ProductionTarget.load_target();
		CommuteData.load();
		
		while (loop) {

			MainView.mainMenu();
			Main.selectNum = scan.nextLine();

			// 로그인하지 않은 경우
			if (Identify.auth == null) {
				if (selectNum.equals("1")) {
					// 회원가입
					SignUp.cheackid();
				} else if (selectNum.equals("2")) {
					// 로그인
					Login.login();
				} else if (selectNum.equals("4")) {
					// 아이디/비번 찾기
					Find.find();
				} else if (selectNum.equals("0")) {
					// 종료
					loop = false;
					Data.save();
				} else {
					System.out.println("잘못된 번호입니다.");
					MainView.pause();
				}
			} else if (Identify.auth != null) {

				// 회원 공통 기능
				if (selectNum.equals("3")) {
					// 로그아웃
					Logout.logout();
				} else if (selectNum.equals("5")) {
					// 개인정보 조회 및 수정
					Modify.modify();
				} else if (selectNum.equals("0")) {
					// 종료
					loop = false;
					Data.save();
				} else {
					System.out.println("잘못된 번호입니다.");
					MainView.pause();
				}

				// 사원, 관리자 공통
				if (Identify.level.equals("1") || Identify.level.equals("2")) {
					if (selectNum.equals("6")) {
						// 근태 등록
						CommutePush.commutePush();
					} else if (selectNum.equals("7")) {
						// 공지 사항(읽기)
						ReadBoard.readBoard();
					}
				}

				// 관리자 공통
				if (Identify.level.equals("1")) {
					if (selectNum.equals("10")) {
						// 사원 출/퇴근 조회
						CommuteSearch.commuteSearch();
					} else if (selectNum.equals("11")) {
						// 재고 확인
						// InventorySearch.inventorySearch(); > 메서드 생성 필요
					} else if (selectNum.equals("12")) {
						// 공지사항 관리
						BoardManagement.boardManagement();
					}
				}

				// 생산부 사원
				if (Identify.level.equals("2") && Identify.dept.equals("생산")) {
					if (selectNum.equals("8")) {
						// 부품 재고 파악 및 발주
						// CheckPiece.checkPiece();
					}
				}

				// 유통부 사원
				if (Identify.level.equals("2") && Identify.dept.equals("유통")) {
					if (selectNum.equals("9")) {
						// 배송지 조회 및 유통
						CheckDest.checkDest();
					}
				}

				// 생산부 관리자
				if (Identify.level.equals("1") && Identify.dept.equals("생산")) {
					if (selectNum.equals("14")) {
						// 생산 목표량 입력 및 수정
						// ProductionTarget.productionTarget(); > 메서드 생성 필요
					} else if (selectNum.equals("15")) {
						// 생산 관리
						// ProductionManagement.productionManagement(); > 메서드 생성 필요
					}
				}

				// 유통부 관리자
				if (Identify.level.equals("1") && Identify.dept.equals("유통")) {
					if (selectNum.equals("16")) {
						// 주문서 확인 및 배정 수정
						OrderAssign.orderView();
					} else if (selectNum.equals("17")) {
						// 사원 배송 구역 관리
						DestManagement.destManagement();
					}
				}

				// 인사부 관리자
				if (Identify.level.equals("1") && Identify.dept.equals("인사")) {
					if (selectNum.equals("13")) {
						// 사원 관리(입사, 퇴사)
						HRM.hrd();
					}
				}

				// 경영부 관리자
				if (Identify.level.equals("1") && Identify.dept.equals("경영")) {
					if (selectNum.equals("18")) {
						// 대리점 관리
						AgencyManagement.agencyManagement();
					}
				}

				// 대리점
				if (Identify.level.equals("3")) {
					if (selectNum.equals("19")) {
						// 주문서 관리
						MyOrder.myOrder();
					}
				}
			}
		} // while

		scan.close();

		ModelInfoData.modelInfoSave();
		TodayProductionData.todayInventorySave();
		ProductionTarget.save_target();
		PieceData.PieceSave();

	}// main
}