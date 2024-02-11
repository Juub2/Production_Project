package com.project.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 유틸리티 기능을 제공하는 클래스이다.
 * 
 */
public class Toolkit {

	/**
     * 주어진 문자열이 정수값인지 확인하는 메서드이다.
     * @param s 확인할 문자열
     * @return 정수값이면 true, 아니면 false
     */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 주어진 날짜 문자열의 유효성을 검사하는 메서드이다.
	 * 날짜가 지정된 형식인 "yyyy-MM-dd"에 부합하는지를 확인한다.
	 * @param date 검사할 날짜 문자열 (형식: "yyyy-MM-dd")
	 * @return 주어진 날짜 문자열이 유효하지 않은 경우 true, 유효한 경우 false를 반환
	 */
	public static boolean invalidateDate(String date) {
		Calendar calendar = Calendar.getInstance();
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false); // 엄격한 모드로 설정
	
	        calendar.setTime(dateFormat.parse(date));
	
	        // ParseException이 발생하지 않으면 유효한 날짜
	        return false;
	    } catch (ParseException e) {
	        // ParseException이 발생하면 유효하지 않은 날짜
	        return true;
	    }
	}

	/**
     * 공지사항 삭제 예정일의 유효성을 검사하는 메서드이다.
     * 공지사항을 작성할 때와 공지사항 데이터를 불러올 때 삭제일과 당일 날짜를 비교한다.
     * 
     * @param deleteDate 삭제 예정일 문자열
     * @return 삭제 예정일이 현재 날짜보다 이전이면 true, 이후이면 false
     */
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
			System.out.println("Toolkit.checkDeleteDate");
			e.printStackTrace();
		}
		return false;
	}

	/**
     * 대리점이 주문서를 작성할 때 납기일을 자동으로 계산하기 위한 메서드이다.
     * 현재 날짜를 기준으로 5일 후의 날짜를 계산하여 문자열로 반환한다.
     * 
     * @return 현재 날짜에서 5일 후의 날짜를 나타내는 문자열
     */
	public static String daysLater() {
		Calendar daysLater = Calendar.getInstance();
		daysLater.add(Calendar.DATE, 5);
		SimpleDateFormat daysLaterFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fiveDaysLater = daysLaterFormat.format(daysLater.getTime());
		return fiveDaysLater;
	
	}

	/**
	 * 전화번호의 유효성을 검사하는 메서드이다.
	 * 전화번호는 "-"를 포함한 12자 또는 13자 숫자만으로 구성되어야 한다.
	 * 
	 * @param phoneNum 검사할 전화번호 문자열
	 * @return 유효한 경우 true를 반환하고, 그렇지 않은 경우 false를 반환
	 */
	public static boolean invalidatePhoneNum(String phoneNum) {
		String regex = "";
		
		// 전화 번호 > 12-13글자, 숫자만
		regex = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$|^[0-9]{3}-[0-9]{4}-[0-9]{4}$";
		Pattern phoneNumPattern = Pattern.compile(regex);
		Matcher phoneNumMatcher = phoneNumPattern.matcher(phoneNum);
	
		return !phoneNumMatcher.find();
	}

	/**
	 * 주소의 유효성을 검사하는 메서드이다.
	 * 주소는 2자 이상 34자 이하의 한글, 숫자, 공백으로 구성될 수 있다.
	 * 
	 * @param address 검사할 주소 문자열
	 * @return 주소가 유효한 경우 true를 반환하고, 그렇지 않은 경우 false를 반환
	 */
	public static boolean invalidateAddress(String address) {
		String regex = "";
		
		// 주소 > 2-34글자(로/길), 한글&숫자
		regex = "^[가-힣0-9\\s]{2,34}$";
		Pattern addressPattern = Pattern.compile(regex);
		Matcher addressMatcher = addressPattern.matcher(address);
	
		return !addressMatcher.find();
	}
}
