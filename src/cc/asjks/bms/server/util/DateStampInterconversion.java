package cc.asjks.bms.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStampInterconversion {
	public static String dateToStamp(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
		try {
			date = simpleDateFormat.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
	public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
	public static void main(String[] args) {
		String date="2020-06-24 12:22:22";
		String stamp="1592972542000";
		System.out.println(dateToStamp(date));
		System.out.println(stampToDate(stamp));
	}
}
