package com.project.factory.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

//TODO 유저정보파일 데이터 ArrayList에 저장클래스
/**
 * 사용자 데이터 저장 리스트 관리 클래스
 */
public class Data {
	// TODO 유저 정보 담긴 리스트(memberList)
	public static ArrayList<Members> memberList = new ArrayList<Members>();

	public static void load() {
		try {
			
			Data.memberList.clear(); // 기존 데이터 초기화

			BufferedReader reader = new BufferedReader(new FileReader(Path.MEMBER));

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				
				// 사원번호■비밀번호■이름■생년월일■전화번호■주소■직급■부서■이메일
				// 대리점ID■비밀번호■이름■■전화번호■주소■직급■구역■이메일
				Members member = new Members(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8]);
				
				memberList.add(member);

			}

			reader.close();

		} catch (Exception e) {
			System.out.println("Data.load");
			e.printStackTrace();
		}
		
		
	}
	
	public static void save() {
		try {
			BufferedWriter writer =  new BufferedWriter(new FileWriter(Path.MEMBER));
			
			for(Members members : Data.memberList) {
				

				//사원번호■비밀번호■이름■생년월일■전화번호■주소■직급■부서■이메일
				//대리점ID■비밀번호■이름■■전화번호■주소■직급■구역■이메일

				String line = String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s\r\n"
																, members.getId()
																, members.getPw()
																, members.getName()
																, members.getBirth()
																, members.getPhoneNum()
																, members.getAddress()
																, members.getLevel()
																, members.getDept()
																, members.getEmail());
				
				writer.write(line);
				
				
			}
			
			
			
			
			writer.close();
			
		} catch (Exception e) {
			System.out.println("Data.save");
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void loadWithSelectedFields() {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(Path.MEMBER));

	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            String[] temp = line.split("■");

	            // 모든 필드를 사용하여 Members 객체 생성
	            Members member = new Members(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8]);
	            memberList.add(member);
	        }

	        reader.close();

	    } catch (Exception e) {
	        System.out.println("Data.loadWithSelectedFields");
	        e.printStackTrace();
	    }
	}
}
