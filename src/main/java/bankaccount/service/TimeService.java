package bankaccount.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeService {
	
	public static String currentTime() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String current = dateFormat.format(date); //2016/11/16 12:08:43
		
		return current;
	
	}
}
