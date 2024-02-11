package com.project.factory.view.dept;

import com.project.factory.resource.dept.Board;
import com.project.factory.resource.dept.BoardData;
import com.project.factory.view.MainView;

/**
 * 공지사항을 보여주는 화면을 관리하는 클래스이다.
 */
public class BoardView {

	/**
     * 공지사항 목록을 보여주는 화면을 출력하는 메서드이다.
     */
	public static void boardView() {
		MainView.title("공지사항");

		BoardView.boardListView();

		System.out.println();
		System.out.println("확인하실 공지사항 번호를 입력해주세요.");
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	/**
     * 공지사항 목록을 출력하는 메서드이다.
     */
	public static void boardListView() {
		System.out.printf("%-5s\t%-4s\t%-40S\t%-10S\n", "[번호]", "[부서]", "[제목]", "[작성일]");

		for (Board board : BoardData.boardList) {
			System.out.printf("%3d\t%3S\t%-40S\t%-10S\n", board.getNoticeNumber(), board.getDept(),
					board.getTitle().trim(), board.getWriteDate());
		}
	}

	 /**
     * 특정 공지사항의 상세 내용을 출력하는 메서드이다.
     * 
     * @param noticeNumber 확인하고자 하는 공지사항의 번호
     */
	public static void boardDetailView(int noticeNumber) {
		System.out.println();
		MainView.singleLine();
		System.out.println();
		for (Board board : BoardData.boardList) {
			if (board.getNoticeNumber() == noticeNumber) {
				System.out.println("제목: " + board.getTitle().trim());
				System.out.println();
				System.out.println(board.getContents().replace("\\n", "\n"));
				break;
			}
		}
	}
}
