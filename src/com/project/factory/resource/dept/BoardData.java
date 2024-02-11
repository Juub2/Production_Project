package com.project.factory.resource.dept;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.factory.Toolkit;
import com.project.factory.resource.Path;

/**
 * 공지사항 데이터를 관리하는 클래스이다.
 */
public class BoardData {

	/**
	 * 공지사항 목록
	 */
	public static ArrayList<Board> boardList = new ArrayList<Board>();
	/**
	 * 공지사항 번호
	 */
	public static int noticeNumber; // 공지사항 번호

	/**
	 * 파일에서 공지사항 데이터를 로드하는 메서드이다.
	 */
	public static void load() {
		try {

			boardList.clear(); // 기존 데이터 초기화

			BufferedReader reader = new BufferedReader(new FileReader(Path.BOARD));

			String line = null;
			while ((line = reader.readLine()) != null) {
				// 공지사항번호■작성자ID(사원번호)■부서■제목■내용■작성일■삭제할날짜
				String[] temp = line.split("■");

				Board boardItem = new Board(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3], temp[4], temp[5],
						temp[6]);

				// boardList.add(boardItem);

				// 삭제일 지났으면 건너뛰기
				if (Toolkit.checkDeleteDate(temp[6])) {
					continue;
				} else {
					boardList.add(boardItem);
				}

			}

			reader.close();

		} catch (Exception e) {
			System.out.println("BoardData.load");
			e.printStackTrace();
		}

	}

	/**
	 * 마지막 공지사항의 번호를 가져오는 메서드이다.
	 * 
	 * @return 마지막 공지사항 번호
	 */
	public static int getLastNoticeNumber() {
		if (boardList.isEmpty()) {
			return 0; // 공지사항이 없는 경우 0 반환
		} else {
			Board lastBoard = boardList.get(boardList.size() - 1); // 마지막 공지사항 가져오기
			return lastBoard.getNoticeNumber(); // Board의 getNoticeNumber() 메서드
		}
	}

	/**
	 * 새로운 공지사항의 번호를 설정하는 메서드이다.
	 */
	public static void setNewNoticeNumber() {
		noticeNumber = BoardData.getLastNoticeNumber() + 1; // 마지막 공지사항 번호에 1을 더한 값을 설정
	}

	/**
	 * 새로 작성할 공지사항 번호를 가져오는 메서드이다.
	 * 
	 * @return 새로 작성할 공지사항 번호
	 */
	public static int getNoticeNumber() {
		return noticeNumber;
	}

	/**
	 * 공지사항 데이터를 파일에 저장하는 메서드이다.
	 */
	public static void save() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.BOARD));

			for (Board boardItem : BoardData.boardList) {

				// 공지사항번호■작성자ID(사원번호)■부서■제목■내용■작성일■삭제할날짜
				String line = String.format("%d■%s■%s■%s■%s■%s■%s\r\n", boardItem.getNoticeNumber(), boardItem.getId(),
						boardItem.getDept(), boardItem.getTitle(), boardItem.getContents(), boardItem.getWriteDate(),
						boardItem.getDeleteDate());

				writer.write(line);

			}

			writer.close();

		} catch (Exception e) {
			System.out.println("BoardData.save");
			e.printStackTrace();
		}

	}
}
