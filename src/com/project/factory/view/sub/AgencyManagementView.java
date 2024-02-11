package com.project.factory.view.sub;

import java.util.Collections;
import java.util.Comparator;

import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.view.MainView;

/**
 * 대리점 관리 화면을 관리하는 클래스이다.
 */
public class AgencyManagementView {

	/**
     * 대리점 관리 메뉴를 출력하는 메서드이다.
     */
	public static void agencyManagementMenu() {
		MainView.title("대리점 관리");

		System.out.println();
		AgencyManagementView.agencyManagementListView();

		System.out.println();
		MainView.singleLine();
		System.out.println();
		System.out.println("1. 대리점 등록");
		System.out.println("2. 대리점 삭제");
		System.out.println();
		MainView.singleLine();
		System.out.print("번호 입력: ");
	}

	/**
     * 대리점 삭제 메뉴를 출력하는 메서드이다.
     */
	public static void deleteAgencyMenu() {
		MainView.title("대리점 삭제");
		System.out.println();
		System.out.println("삭제하실 대리점 이름을 입력해주세요.");
		System.out.println();
		System.out.print("대리점명: ");
	}

	/**
     * 대리점 목록을 출력하는 내부 메서드이다.
     */
	private static void agencyManagementListView() {
	    System.out.printf("%-10s\t%-12S\t%-10S\t%-40S\r\n", "[대리점명]", "[전화번호]", "[구역]", "[주소]");
	    
	    // Comparator를 사용하여 getDept()를 기준으로 오름차순으로 정렬
	    Comparator<Members> deptComparator = new Comparator<Members>() {
	        @Override
	        public int compare(Members m1, Members m2) {
	            return m1.getDept().compareTo(m2.getDept());
	        }
	    };
	    
	    // 멤버 목록을 getDept()를 기준으로 오름차순으로 정렬
	    Collections.sort(Data.memberList, deptComparator);

	    for (Members member : Data.memberList) {
	        if (member.getLevel().equals("3")) {
	            System.out.printf("%-10s\t%-12S\t%-10S\t%-40S\n", member.getName(), member.getPhoneNum(),
	                    member.getArea(member.getDept()), member.getAddress());
	        }
	    }
	}//agencyManagementListView

}
