package bankaccount.service;


import org.apache.commons.codec.digest.DigestUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {

    public static String getPasswordHash(String password) {


        String hasshedPassword = DigestUtils.sha1Hex(password);

        return hasshedPassword;

    }
    
	public static String currentTime() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String current = dateFormat.format(date); //2016/11/16 12:08:43
		
		return current;
	
	}

}