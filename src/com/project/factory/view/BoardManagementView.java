package com.project.factory.view;

import com.project.factory.Today;
import com.project.factory.member.Identify;
import com.project.factory.resource.dept.Board;
import com.project.factory.resource.dept.BoardData;

public class BoardManagementView {

	public static void boardManagementMenu() {
		MainView.title("공지사항 관리");

		System.out.println();
		System.out.println("1. 공지사항 등록");
		System.out.println("2. 공지사항 수정");
		System.out.println("3. 공지사항 삭제");
		System.out.println();
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	public static void boardEditMenu() {
		System.out.println();
		MainView.singleLine();
		System.out.println();
		System.out.println("1. 제목");
		System.out.println("2. 내용");
		System.out.println("3. 삭제 날짜");
		System.out.println();
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	public static void boardWriteView() {
		MainView.title("공지사항 등록");
		
		System.out.println("날짜: " + Today.day());
		MainView.singleLine();
	}
	
	public static void boardEditView() {
		MainView.title("공지사항 수정");

		BoardManagementView.boardManagementListView();

		System.out.println();
		System.out.println("수정하실 공지사항 번호를 입력해주세요.");
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	public static void boardDeleteView() {
		MainView.title("공지사항 삭제");

		BoardManagementView.boardManagementListView();

		System.out.println();
		System.out.println("삭제하실 공지사항 번호를 입력해주세요.");
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	private static void boardManagementListView() {
		System.out.printf("%-5s\t%-40S\t%-10S\t%-10S\r\n", "[번호]", "[제목]", "[작성일]", "[삭제 예정일]");

		for (Board board : BoardData.boardList) {
			if (board.getId().equals(Identify.auth)) {
				System.out.printf("%3d\t%-40S\t%10S\t%-10S\n", board.getNoticeNumber(), board.getTitle().trim(),
						board.getWriteDate(), board.getDeleteDate());
			}
		}
	}
}
