package com.project.factory.resource.dept;

/**
 * 공지사항을 나타내는 클래스이다.
 */
public class Board {
	private int noticeNumber;
	private String id;
	private String dept;
	private String title;
	private String contents;
	private String writeDate;
	private String deleteDate;

	/**
	 * Board 클래스의 생성자이다.
	 * 
	 * @param noticeNumber = 공지사항 번호
	 * @param id           = 작성자의 사원 번호
	 * @param dept         = 작성자의 부서
	 * @param title        = 제목
	 * @param contents     = 내용
	 * @param writeDate    = 작성일
	 * @param deleteDate   = 삭제할 날짜
	 */
	public Board(int noticeNumber, String id, String dept, String title, String contents, String writeDate,
			String deleteDate) {
		this.noticeNumber = noticeNumber;
		this.id = id;
		this.dept = dept;
		this.title = title;
		this.contents = contents;
		this.writeDate = writeDate;
		this.deleteDate = deleteDate;
	}

	/**
	 * 제목을 반환하는 메서드이다.
	 * 
	 * @return 제목
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 제목을 설정하는 메서드이다.
	 * 
	 * @param title 제목
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 내용을 반환하는 메서드이다.
	 * 
	 * @return 내용
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * 내용을 설정하는 메서드이다.
	 * 
	 * @param contents 내용
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * 삭제할 날짜를 반환하는 메서드이다.
	 * 
	 * @return 삭제할 날짜
	 */
	public String getDeleteDate() {
		return deleteDate;
	}

	/**
	 * 삭제할 날짜를 설정하는 메서드이다.
	 * 
	 * @param deleteDate 삭제할 날짜
	 */
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}

	/**
	 * 공지사항 번호를 반환하는 메서드이다.
	 * 
	 * @return 공지사항 번호
	 */
	public int getNoticeNumber() {
		return noticeNumber;
	}

	/**
	 * 작성자의 사원번호를 반환하는 메서드이다.
	 * 
	 * @return 작성자의 사원번호
	 */
	public String getId() {
		return id;
	}

	/**
	 * 작성자의 부서를 반환하는 메서드이다.
	 * 
	 * @return 작성자의 부서
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * 작성일을 반환하는 메서드이다.
	 * 
	 * @return 작성일
	 */
	public String getWriteDate() {
		return writeDate;
	}

	@Override
	public String toString() {
		return "noticeNumber=" + noticeNumber + ", id=" + id + ", dept=" + dept + ", title=" + title + ", contents="
				+ contents + ", writeDate=" + writeDate + ", deleteDate=" + deleteDate;
	}

}
