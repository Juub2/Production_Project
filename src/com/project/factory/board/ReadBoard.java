package com.project.factory.board;

import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.Toolkit;
import com.project.factory.resource.dept.Board;
import com.project.factory.resource.dept.BoardData;
import com.project.factory.view.MainView;
import com.project.factory.view.dept.BoardView;
/**
 * 공지사항을 읽는 기능을 담당하는 클래스이다.
 */
public class ReadBoard {

	static Scanner scan = new Scanner(System.in);
	
	static int noticeNumber = 0; // 공지사항 번호
	/**
     * 공지사항을 읽는 기능을 실행하는 메서드이다.
     * 모든 부서 관리자가 작성한 공지사항을 읽을 수 있다.
     */
	public static void readBoard() {

		BoardData.load();

		while (true) {

			BoardView.boardView();

			Main.selectNum = scan.nextLine(); // 입력 받기

			if (!Main.selectNum.isEmpty()) { // 입력이 공백이 아닌 경우
				if (Toolkit.isInteger(Main.selectNum)) { // 정수값인지 확인

					ReadBoard.noticeNumber = Integer.parseInt(Main.selectNum); // 확인할 공지사항 번호

					// 유효한 공지사항 번호인지 확인
					if (ReadBoard.checkNoticeNumberExists()) {
						BoardView.boardDetailView(ReadBoard.noticeNumber);

						MainView.pauseToSel();

					} else {
						System.out.println("잘못된 번호입니다.");
						if (MainView.checkContinueBoolean()) {
							continue;
						} else {
							MainView.pause();
							return;
						}
					}
				} else {
					System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
					if (MainView.checkContinueBoolean()) {
						continue;
					} else {
						MainView.pause();
						return;
					}
				}
			} else {
				System.out.println("잘못된 번호입니다.");
				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					MainView.pause();
					return;
				}
			}
		} // while
	}// readBoard
	
	/**
     * 입력된 공지사항 번호가 존재하는지 확인하는 메서드이다.
     * 
     * @return 공지사항 번호가 존재하면 true, 그렇지 않으면 false를 반환
     */
	private static boolean checkNoticeNumberExists() {
		for (Board board : BoardData.boardList) {
			if (board.getNoticeNumber() == ReadBoard.noticeNumber) {
				return true;
			}
		}
		return false;
	}
}// ReadBoard
