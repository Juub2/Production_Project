package com.project.factory.resource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class Dummy {

	public static void makeUserData(String departCode) {
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(Path.MEMBER, true));
			Random rnd = new Random();
			String depart = "";
			String id = "";
			String name = "";
			String humanNum = "";
			String result = "";
			String email = "";

			for (int i = 0; i < 30; i++) {
				// 이름 생성
				String[] lastName = { "김", "박", "이", "송", "윤", "최", "황", "손", "한", "조", "백", "정" };
				String[] firstName = { "준", "미", "섭", "영", "욱", "원", "철", "지", "숙", "현", "민", "희", "대", "형", "나", "우",
						"진", "수", "승", "민", "은", "규", "석", "현", "성", "혜", "근", "정" };
				
				name = lastName[rnd.nextInt(lastName.length)] + firstName[rnd.nextInt(firstName.length)]
						+ firstName[rnd.nextInt(firstName.length)];
				
			for (int j = 0; j < 30; j++) {
				//생년월일 생성
				//생년
				String[] num1 = {"7", "8", "9"};
				String[] num2 = {"0","1","2","3","4","5","6","7","8","9"};
				//월
				String[] num3 = {"01","02","03","04","05","06","07","08","09","10","11","12"};
				//일
				String[] num4 = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
				
				humanNum = num1[rnd.nextInt(num1.length)] + num2[rnd.nextInt(num2.length)] 
						+ num3[rnd.nextInt(num3.length)] + num4[rnd.nextInt(num4.length)];
			}
				
				
				//주소 생성
				String[] seoulGu = {"서초구", "강남구", "용산구", "송파구", "성동구", "양천구", "마포구", "관악구"};
				String[] seocho = {"과천대로", "도구로", "방배로", "방배중앙로", "방배천로", "남부순환로", "헌릉로"};
				String[] gangnam = {"남부순환로", "양재대로", "강남대로", "도산대로", "올림픽대로", "언주로", "논현로", "삼성로", "선릉로", "봉은사로"};
				String[] yoongsan = {"녹사평대로", "한강대로", "남산공원", "대사관로", "두텁바위로", "만리재로", "백범로", "서빙고", "소월로", "신흥로"};
				String[] songpa = {"가락로", "강동대로", "거마로", "도곡로", "동남로", "백제고문보", "삼전로", "송파대로", "위례송파로", "풍성로"};
				String[] sungdong = {"고산자로", "광나루호", "금호로", "금호산", "독서당로", "뚝섬로", "마장로", "청계천로", "마조로", "왕십리로", "행당로"};
				String[] yangchun = {"가로공원로", "곰달래로", "목동남로", "목동중앙본로", "신월로", "신정중앙로", "오목로", "월정로", "지양로", "화곡로"};
				String[] mapoo = {"굴레방로", "대흥로", "독막로", "마포대로", "머래내로", "성암로", "신촌로", "연남로", "와우산로", "월드컵로", "토정로"};
				String[] gwanak = {"관악로", "광신", "낙성대역", "난곡로", "대학", "문성로", "보라매로", "봉천로", "신림로", "조원로", "행운", "문성로"};
				
				String gu = seoulGu[rnd.nextInt(seoulGu.length)];
				String ro = "";
				switch(gu) {
				case "서초구":
					ro = seocho[rnd.nextInt(seocho.length)];
					break;
				
				case "강남구":
					ro = gangnam[rnd.nextInt(gangnam.length)];
					break;
					
				case "용산구":
					ro = yoongsan[rnd.nextInt(yoongsan.length)];
					break;
				case "송파구":
					ro = songpa[rnd.nextInt(songpa.length)];
					break;
				case "성동구":
					ro = sungdong[rnd.nextInt(sungdong.length)];
					break;
				case "양천구":
					ro = yangchun[rnd.nextInt(yangchun.length)];
					break;
				case "마포구":
					ro = mapoo[rnd.nextInt(mapoo.length)];
					break;
				case "관악구":
					ro = gwanak[rnd.nextInt(gwanak.length)];
					break;
				}
				ro +=  (int)(Math.random() * 100) + 1 + "길";
				String address = "서울특별시" + " " + gu + " " + ro;
				
				// 사원번호
				id = (int) (Math.random() * 899999) + 100000 + departCode;
				email = id + "@auto.com";
				// 부서
				switch (id.substring(id.length() - 1)) {
				case "A":
					depart = "생산";
					break;
				case "B":
					depart = "유통";
					break;
				case "C":
					depart = "인사";
					break;
				case "D":
					depart = "경영";
					break;

				}

				// 전화번호
				String phoneNum = "010" + "-" + ((int) (Math.random() * 8999) + 1000) + "-"
						+ ((int) (Math.random() * 8999) + 1000);

				// 주소 생성

				// 사원번호■비밀번호■이름■생년월일■전화번호■주소■직급■부서■이메일(사원번호@auto.com)
				result = id + "■" 
						+ "134113411341" + "■" 
						+ name + "■" 
						+ humanNum + "■" 
						+ phoneNum + "■" 
						+ address + "■" 
						+ "2" + "■" 
						+ depart + "■" 
						+ email;

				writer.write(result + "\r\n");

			}
			writer.close();

		}catch(

	Exception e)
	{
		System.out.println("Main.main");
		e.printStackTrace();
	}

}}
