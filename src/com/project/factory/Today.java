package com.project.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

//TODO Today 메서드 추가 생성
public class Today {
	//당일 시간
	public static String time() {
		Calendar Today = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String currentTime = sdf.format(Today.getTime());
		return currentTime;
	}

	//당일 날짜
	public static String day() {
		Calendar Today = Calendar.getInstance();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(Today.getTime());
		return formattedDate;

	}

	// 주문일로부터 5일 후 배송(납기)
	public static String daysLater() {
		Calendar daysLater = Calendar.getInstance();
		daysLater.add(Calendar.DATE, 5);
		SimpleDateFormat daysLaterFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fiveDaysLater = daysLaterFormat.format(daysLater.getTime());
		return fiveDaysLater;

	}


	//날짜 형식 유효성 검사
	public static boolean checkDate(String date) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        return Pattern.matches(regex, date);
    }
	
	//날짜 유효성 검사 > 유효하지 않으면 true
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


}
