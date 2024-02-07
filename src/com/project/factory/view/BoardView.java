package com.project.factory.view;

import com.project.factory.resource.dept.Board;
import com.project.factory.resource.dept.BoardData;

public class BoardView {

	public static void boardView() {
		System.out.println();
		MainView.doubleLine();
		System.out.println("\t\t\t\t<공지사항>");
		MainView.doubleLine();

		BoardView.boardListView();

		System.out.println();
		System.out.println("확인하실 공지사항 번호를 입력해주세요.");
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	// 어차피 관리자만 공지사항을 작성할 수 있어서 부서만 출력하면 될 듯
	public static void boardListView() {
		System.out.printf("%-5s\t%-4s\t%-40S\t%-10S\n", "[번호]", "[부서]", "[제목]", "[작성일]");

		for (Board board : BoardData.boardList) {
			System.out.printf("%3d\t%3S\t%-40S\t%-10S\n", board.getNoticeNumber(), board.getDept(),
					board.getTitle().trim(), board.getWriteDate());
		}
	}

	public static void boardDetailView(int noticeNumber) {
		for (Board board : BoardData.boardList) {
			if (board.getNoticeNumber() == noticeNumber) {
				System.out.println("제목: " + board.getTitle().trim());
				System.out.println(board.getContents().replace("\\n", "\n"));
				break;
			}
		}
	}
}
