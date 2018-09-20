package com.jingu.IOT.util;

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

}
