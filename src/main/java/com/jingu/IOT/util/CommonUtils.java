package com.jingu.IOT.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by weifengxu on 2018/9/17.
 */
public class CommonUtils {
    static void validUser() {

    }


    public static Date getDate(String date_srt, String patt) {
        if (patt == null)
            patt = "yyyy/MM/dd HH:mm:ss";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        //必须捕获异常
        try {
            return simpleDateFormat.parse(date_srt);
        } catch (ParseException px) {
            px.printStackTrace();
        }
        return null;
    }

    public static String forFormatDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

    public static String formatDate(long time) {
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }


    public static String timestampToString(Timestamp ts) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(ts);
    }


    public static boolean isNumber(Object o) {
        try {
            if (o instanceof Integer)
                return true;
            Integer.parseInt(o.toString());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
