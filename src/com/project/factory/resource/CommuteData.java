package com.project.factory.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.factory.resource.user.Commute;

/** 근태 등록 데이터를 관리하는 클래스이다.
 * 
 */
public class CommuteData {
	
		// 출근사원 목록
		public static ArrayList<Commute> commuteMemberList = new ArrayList<Commute>();

		/** 파일에서 출근사원 데이터를 로드하는 메서드이다.
		 * 
		 */
		public static void load() {
			try {
				
				commuteMemberList.clear();
				
				BufferedReader reader = new BufferedReader(new FileReader(Path.COMMUTE));

				String line = null;//reader.readLine();
				while ((line = reader.readLine()) != null) {
					// 홍길동,100,90,80
					String[] temp = line.split("■");
					
					Commute commuteMember = new Commute(temp[0], temp[1], temp[2], temp[3], temp[4],temp[5],temp[6]);
					
					commuteMemberList.add(commuteMember);

				}

				reader.close();

			} catch (Exception e) {
				System.out.println("Data.load");
				e.printStackTrace();
			}
			
			
		}
		
		/** 파일에 근태 등록 리스트 정보를 저장하는 메서드이다.
		 * 
		 */
		public static void save() {
			
			try {
				BufferedWriter writer =  new BufferedWriter(new FileWriter(Path.MEMBER));
				
				for(Commute commuteMember : CommuteData.commuteMemberList) {
					
					//Score 객체 1개 > 홍길동, 100, 90, 80
					String line = String.format("\r\n%s■%s■%s■%s■%s■%s■%s"
																	, commuteMember.getCal()
																	, commuteMember.getId()
																	, commuteMember.getName()
																	, commuteMember.getDept()
																	, commuteMember.getLevel()
																	, commuteMember.getCurrentTime()
																	, commuteMember.getCommute());
																	
					
					writer.write(line);
					
					
				}
				
				
				
				
				writer.close();
				
			} catch (Exception e) {
				System.out.println("Data.save");
				e.printStackTrace();
			}
			
			
		}

}
