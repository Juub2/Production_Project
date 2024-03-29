package com.project.factory.member;

import java.util.Scanner;

import com.project.factory.Main;
import com.project.factory.resource.Data;
import com.project.factory.resource.Members;
import com.project.factory.view.MainView;
import com.project.factory.view.member.FindView;

/** 회원가입이 된 멤버의 아이디와 비밀번호를 찾는 기능을 하는 클래스이다.
 * 
 */
public class Find {
	
	//TODO Find 변수와 메서드 모두 삭제하고 다시 만듬
	
	//TODO find 메서드 추가
	//1. 아이디 찾기
	//2. 비밀먼호 찾기
	
	/** 아이디/비밀번호 찾기 기능을 실행하는 메서드이다.
	 * 
	 */
    public static void find() {

		Scanner scan = new Scanner(System.in);
		
        FindView.findMenu();
        

        Main.selectNum = scan.nextLine();


        if (Main.selectNum.equals("1")) {
            findId();
        } else if (Main.selectNum.equals("2")) {
            findPw();
        } else {
        	System.out.println();
        	System.out.println("잘못된 번호입니다.");
        	
        }
        System.out.println();
    }

    //TODO findId 메서드 추가
    //입력받은 이름과 전화번호를 memberList에 있는 이름과 전화번호를 비교해서 
    //해당하는 아이디를 반환하는 메서드
    
    /** 아이디 찾기 기능을 수행하는 메서드이다.
     * 
     */
    private static void findId() {
        Scanner scan = new Scanner(System.in);
        
        System.out.println();
        System.out.print("이름: ");
        String name = scan.nextLine();

        System.out.println();
        System.out.print("전화번호(xxx-xxxx-xxxx): ");
        String phoneNum = scan.nextLine();

        String memberId = findMemberId(name, phoneNum);



        if (memberId != null) {
        	System.out.println();
            System.out.println("아이디: " + memberId + " 입니다.");
            MainView.pause();
            
        } else {
        	System.out.println();
            System.out.println("아이디를 찾을 수 없습니다.");
            MainView.checkContinue();

			if (Main.answer.equals("Y") || Main.answer.equals("y")) {
				find();
			} else if (Main.answer.equals("N") || Main.answer.equals("n")) {

				MainView.pause();

			} else {
				System.out.println();
				MainView.singleLine();
				System.out.println();
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				MainView.checkContinueBoolean();
			}
         
        }
    }

    //TODO findMemberId 메서드 추가
    //memberList에 있는 name과 phoneNum을 비교 후 해당하는 아이디 반환
    /** 멤버리스트에 존재하는 회원인지 확인하고 아이디를 반환하는 메서드이다.
     * 
     * @param name 아이디를 찾을 사원의 이름
     * @param phoneNum 아이디를 찾을 사원의 전화번호
     * @return 해당 아이디가 존재하지 않는다면 null, 아니면 해당 아이디 존재
     */
    public static String findMemberId(String name, String phoneNum) {
        for (Members member : Data.memberList) {
            if (member.getName().equals(name) && member.getPhoneNum().equals(phoneNum)) {
                return member.getId();
            }
        }
        return null;
    }

    //TODO findId 메서드 추가
    //입력받은 아이디과 전화번호를 memberList에 있는 아이디과 전화번호를 비교해서 
    //해당하는 비밀번호를 반환하는 메서드
    
    /** 비밀번호 찾기 기능을 수행하는 메서드이다.
     * 
     */
    private static void findPw() {
        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.print("아이디: ");
        
        String Id = scan.nextLine();
        
        System.out.println();
        System.out.print("전화번호(xxx-xxxx-xxxx): ");
        String phoneNum = scan.nextLine();

        String memberPw = findMemberPw(Id, phoneNum);

        if (memberPw != null) {
        	System.out.println();
            System.out.println("비밀번호: " + memberPw);
            MainView.pause();
        } else {
        	System.out.println();
            System.out.println("비밀번호를 찾을 수 없습니다.");
            MainView.checkContinue();

            if(Main.answer.equals("Y") || Main.answer.equals("y")) {
            	find();
            	
            } else if (Main.answer.equals("N") || Main.answer.equals("n")) {
          
            MainView.pause();
            	
            } else {
            	System.out.println();
				MainView.singleLine();
				System.out.println();
				System.out.println("잘못 입력입니다. 다시 입력해주세요.");
				MainView.checkContinue();
            } 
        }
    }

    //TODO findPw 메서드 추가
    //memberList에 있는 아이디와 phoneNum을 비교 후 해당하는 비밀번호 반환
    /** 멤버리스트에 존재하는 회원인지 확인하고 비밀번호를 반환하는 메서드이다.
     * 
     * @param Id 비밀번호를 찾을 사원 아이디
     * @param phoneNum 비밀번호를 찾을 사원의 전화번호
     * @return 해당 비밀번호가 존재하지 않는다면 null, 아니면 해당 아이디 존재
     */
    private static String findMemberPw(String Id, String phoneNum) {
        for (Members member : Data.memberList) {
            if (member.getId().equals(Id) && member.getPhoneNum().equals(phoneNum)) {
                return member.getPw();
            }
        }
        return null;
    }
}
