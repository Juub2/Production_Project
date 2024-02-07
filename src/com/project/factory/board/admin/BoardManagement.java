package com.project.factory.board.admin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.Today;
import com.project.factory.Toolkit;
import com.project.factory.member.Identify;
import com.project.factory.resource.Path;
import com.project.factory.resource.dept.Board;
import com.project.factory.resource.dept.BoardData;
import com.project.factory.view.BoardManagementView;
import com.project.factory.view.MainView;

//TODO WriteBoard 클래스명 > BoardManagement으로 수정 
public class BoardManagement {

	static Scanner scan = new Scanner(System.in);

	static String regex = ""; // 유효성 검사를 위한 변수
	static int noticeNumber = 0; // 공지사항 번호
	static String title = ""; // 제목
	static String content = ""; //내용
	static String deleteDate = ""; // 삭제할 날짜

	// TODO 공지사항 데이터에 작성일 추가
	// 공지사항번호■작성자ID(사원번호)■제목■내용■작성일■삭제할날짜

	public static void boardManagement() {
		
		BoardData.load();

		BoardManagementView.boardManagementMenu();

		Main.selectNum = scan.nextLine();

		if (Main.selectNum.equals("1")) {
			BoardManagement.boardWrite();
		} else if (Main.selectNum.equals("2")) {
			BoardManagement.boardEdit();
		} else if (Main.selectNum.equals("3")) {
			BoardManagement.boardDelete();
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
		
		//BoardData.save();

	}// boardManagement

	// 공지사항 등록
	private static void boardWrite() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.BOARD, true)); // true인 경우 이어쓰기

			BoardData.setNewNoticeNumber(); // 공지사항 번호 설정

			BoardManagementView.boardWriteView();

			if (BoardManagement.writeDeleteDate()) {

				if (BoardManagement.writeTitle()) {

					if (BoardManagement.writeContents()) {
						// 공지사항번호■작성자ID(사원번호)■부서■제목■내용■작성일■삭제할날짜
						writer.write(BoardData.getNoticeNumber() + "■" + Identify.auth + "■" + Identify.dept + "■"
								+ BoardManagement.title + "■" + BoardManagement.content + "■"
								+ Today.day() + "■" + BoardManagement.deleteDate);
						writer.newLine();
						writer.close();

						System.out.println("게시글이 성공적으로 작성되었습니다.");
						MainView.pauseToSel();

						BoardManagement.boardManagement();
						return;
					} else {
						MainView.pauseToSel();

						BoardManagement.boardManagement();
						return;
					}
				} else {
					MainView.pauseToSel();

					BoardManagement.boardManagement();
					return;
				}
			} else {
				MainView.pauseToSel();

				BoardManagement.boardManagement();
				return;
			}

		} catch (Exception e) {
			System.out.println("BoardManagement.boardWrite");
			e.printStackTrace();
		}
	}

	// 공지사항 수정
	private static void boardEdit() {
		while (true) {
			BoardManagementView.boardEditView();

			Main.selectNum = scan.nextLine(); // 입력 받기

			if (!Main.selectNum.isEmpty()) { // 입력이 공백이 아닌 경우
				if (Toolkit.isInteger(Main.selectNum)) { // 정수값인지 확인

					BoardManagement.noticeNumber = Integer.parseInt(Main.selectNum); // 수정할 공지사항 번호

					// 유효한 공지사항 번호인지 & 공지사항 작성자인지 확인
					if (checkNoticeNumberExists()) {
						while (true) {
							BoardManagementView.boardEditMenu();

							Main.selectNum = scan.nextLine();

							if (Main.selectNum.equals("1")) {
								BoardManagement.boardEditTitle();
								break;
							} else if (Main.selectNum.equals("2")) {
								BoardManagement.boardEditcontents();
								break;
							} else if (Main.selectNum.equals("3")) {
								BoardManagement.boardEditDeleteDate();
								break;
							} else {
								System.out.println("잘못된 번호입니다.");
								if (MainView.checkContinueBoolean()) {
									continue;
								} else {
									MainView.pauseToSel();

									BoardManagement.boardEdit();
									return;
								}
							}
						}
					} else {
						System.out.println("잘못된 번호입니다.");
						if (MainView.checkContinueBoolean()) {
							continue;
						} else {
							MainView.pauseToSel();
							BoardManagement.boardManagement();
							return;
						}
					}
					break;
				} else {
					System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
					if (MainView.checkContinueBoolean()) {
						continue;
					} else {
						MainView.pauseToSel();
						BoardManagement.boardManagement();
						return;
					}
				}
			} else {
				System.out.println("잘못된 번호입니다.");
				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					MainView.pauseToSel();
					BoardManagement.boardManagement();
					return;
				}
			}
		} // while
	} // boardEdit

	// 공지사항 삭제
	private static void boardDelete() {
		boolean loop = false;

		while (true) {
			BoardManagementView.boardEditView();

			Main.selectNum = scan.nextLine();

			if (!Main.selectNum.isEmpty()) {
				if (Toolkit.isInteger(Main.selectNum)) {

					BoardManagement.noticeNumber = Integer.parseInt(Main.selectNum);

					for (Board board : BoardData.boardList) {
						if (board.getNoticeNumber() == BoardManagement.noticeNumber && board.getId().equals(Identify.auth)) {
							BoardData.boardList.remove(board);// 해당 board 삭제

							BoardData.save();

							System.out.println();
							System.out.println("공지사항이 성공적으로 삭제되었습니다.");

							loop = true;
							break;
						}
					}

					if (loop) {
						MainView.pauseToSel();

						BoardManagement.boardManagement();
						return;
					} else {
						System.out.println("잘못된 번호입니다.");

						if (MainView.checkContinueBoolean()) {
							continue;
						} else {
							MainView.pauseToSel();

							BoardManagement.boardManagement();
							return;
						}
					}

				} else {
					System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
					if (MainView.checkContinueBoolean()) {
						continue;
					} else {
						MainView.pauseToSel();
						BoardManagement.boardManagement();
						return;
					}
				}
			} else {
				System.out.println("잘못된 번호입니다.");
				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					MainView.pauseToSel();
					BoardManagement.boardManagement();
					return;
				}
			}
		} // while
	}// boardDelete

	// 공지사항 수정 메서드
	// 수정 메서드
	private static void boardEditDeleteDate() {

		while (true) {
			if (BoardManagement.writeDeleteDate()) {
				if (checkDeleteDateChange()) {
					BoardData.save();

					System.out.println();
					System.out.println("삭제 날짜가 성공적으로 수정되었습니다.");
					MainView.pauseToSel();

					BoardManagement.boardManagement();
					return;

				} else {
					System.out.println("변경된 내용이 없습니다.");

					if (MainView.checkContinueBoolean()) {
						continue;

					} else {
						MainView.pauseToSel();

						BoardManagement.boardEdit();
						return;
					}
				}
			} else {
				BoardManagement.boardManagement();
				return;
			}
		}

	}// boardEditDeleteDate

	private static void boardEditTitle() {
		while (true) {
			if (BoardManagement.writeTitle()) {
				if (checkTitleChange()) {
					BoardData.save();

					System.out.println();
					System.out.println("제목이 성공적으로 수정되었습니다.");
					MainView.pauseToSel();

					BoardManagement.boardManagement();
					return;

				} else {
					System.out.println("변경된 내용이 없습니다.");

					if (MainView.checkContinueBoolean()) {
						continue;

					} else {
						MainView.pauseToSel();

						BoardManagement.boardEdit();
						return;
					}
				}
			} else {
				BoardManagement.boardManagement();
				return;
			}
		}
	}// boardEditTitle

	private static void boardEditcontents() {
		while (true) {
			for (Board board : BoardData.boardList) {
				if (board.getNoticeNumber() == BoardManagement.noticeNumber) {
					System.out.println();
					System.out.println("기존 내용: " + board.getContents().replace("\\n", "\n"));
					break;
				}
			}

			if (BoardManagement.writeContents()) {
				if (checkContentsChange()) {
					BoardData.save();

					System.out.println();
					System.out.println("내용이 성공적으로 수정되었습니다.");
					MainView.pauseToSel();

					BoardManagement.boardManagement();
					return;

				} else {
					System.out.println("변경된 내용이 없습니다.");

					if (MainView.checkContinueBoolean()) {
						continue;

					} else {
						MainView.pauseToSel();

						BoardManagement.boardEdit();
						return;
					}
				}
			} else {
				BoardManagement.boardManagement();
				return;
			}
		}
	}// boardEditcontents

	// 작성 메서드
	// 공지사항 작성 메서드
	private static boolean writeDeleteDate() {
		while (true) {
			System.out.print("삭제할 날짜: ");
			BoardManagement.deleteDate = scan.nextLine();

			if (Today.invalidateDate(deleteDate)) {
				System.out.println();
				System.out.println("잘못된 형식의 날짜입니다.");
				System.out.println("날짜는 10글자(하이픈 포함), 숫자만 입력 가능합니다.");

				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					return false;
				}

			} else {
				if (checkDeleteDate(BoardManagement.deleteDate)) {
					System.out.println();
					System.out.println("삭제할 날짜는 작성일보다 과거로 설정할 수 없습니다.");

					if (MainView.checkContinueBoolean()) {
						continue;
					} else {
						return false;
					}

				} else {
					return true;
				}
			}
		}
	}

	private static boolean writeTitle() {
		while (true) {
			System.out.print("제목: ");
			BoardManagement.title = scan.nextLine();

			if (BoardManagement.invalidateTitle()) {
				System.out.println();
				System.out.println("제목은 1-30글자까지 입력 가능합니다.");

				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					return false;
				}

			} else {
				return true;
			}
		}
	}

	private static boolean writeContents() {
		while (true) {
			StringBuilder contents = new StringBuilder(); // 내용을 저장할 StringBuilder
			
			System.out.println();
			System.out.println("종료하려면 빈 줄에서 엔터를 두 번 입력하세요.");
			System.out.print("내용: ");
			while (true) {
				String line = scan.nextLine(); // 한 줄씩 입력받음
				if (line.isEmpty()) { // 입력이 빈 줄인 경우 반복문 종료
					break;
				}
				contents.append(line).append("\\n"); // 줄바꿈된 상태로 저장하지 말고 줄바꿈 문자를 추가하여 표시
			}
			if (invalidateContents(contents.toString())) { //StringBuilder 객체에 저장된 문자열 버퍼를 String으로 변환
				System.out.println();
				System.out.println("내용은 1-200글자까지 입력 가능합니다.");

				if (MainView.checkContinueBoolean()) {
					continue;
				} else {
					return false;
				}
			} else {
				BoardManagement.content = contents.toString().trim();
				return true;
			}
		}
	}

	// 유효성 검사
	// java.util.Date와 java.sql.Date는 서로 다른 클래스입니다. 따라서 직접적인 형변환이 불가능

	// 유효성 검사
	//TODO 파일 load할 때 사용하려고 public로 변경
	public static boolean checkDeleteDate(String deleteDate) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Calendar today = Calendar.getInstance();
			// 삭제 날짜를 Calendar 객체로 변환
			Calendar deleteDateCal = Calendar.getInstance();
			deleteDateCal.setTime(dateFormat.parse(deleteDate));

			// 삭제 날짜가 현재 날짜보다 이전인지 검사
			return deleteDateCal.before(today);

		} catch (ParseException e) {
			System.out.println("BoardManagement.checkDeleteDate");
			e.printStackTrace();
		}
		return false;
	}

	private static boolean invalidateTitle() {
		// 제목 > 최대 30글자
		return BoardManagement.title.isEmpty() || BoardManagement.title.length() > 30;
	}

	private static boolean invalidateContents(String contents) {
		// 내용 > 최대 200글자
		return contents.isEmpty() || contents.length() > 200;
	}
	
	private static boolean checkNoticeNumberExists() {
		for (Board board : BoardData.boardList) {
			if (board.getNoticeNumber() == BoardManagement.noticeNumber && board.getId().equals(Identify.auth)) {
				return true;
			}
		}
		return false;
	}

	// 중복성 검사
	private static boolean checkDeleteDateChange() {
		for (Board board : BoardData.boardList) {
			if (board.getNoticeNumber() == BoardManagement.noticeNumber) {
				if (board.getDeleteDate().equals(BoardManagement.deleteDate)) {
					return false;

				} else {
					board.setDeleteDate(BoardManagement.deleteDate); // 수정
					return true;
				}
			}
		} // for
		return false;
	}

	public static boolean checkTitleChange() {
		for (Board board : BoardData.boardList) {
			if (board.getNoticeNumber() == BoardManagement.noticeNumber) {
				if (board.getTitle().equals(BoardManagement.title)) {
					return false;

				} else {
					board.setTitle(BoardManagement.title); // 수정
					return true;
				}
			}
		} // for
		return false;
	}

	private static boolean checkContentsChange() {
		for (Board board : BoardData.boardList) {
			if (board.getNoticeNumber() == BoardManagement.noticeNumber) {
				if (board.getContents().equals(BoardManagement.content)) {
					return false;
				} else {
					board.setContents(BoardManagement.content.toString().trim()); // 수정
					return true;
				}
			}
		} // for
		return false;
	}
}
