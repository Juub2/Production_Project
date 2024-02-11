package com.project.factory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

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

	//날짜 형식 유효성 검사
	public static boolean checkDate(String date) {
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        return Pattern.matches(regex, date);
    }

}
