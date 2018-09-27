package com.jingu.IOT.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


    /**
     * 数字？
     *
     * @param o
     * @return
     */
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

    public static boolean has(Object o) {
        if (o == null) {
            return false;
        }

        if ("".equals(null)) {
            return false;
        }

        if (o instanceof Integer) {
            if ((int) o > 0)
                return true;
            else
                return false;
        }

        return true;
    }

    /**
     * 以天为单位的平均值
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> effectToDay(List<Map<String, Object>> list, String field, int span) {
        List<Map<String, Object>> back = new ArrayList<>();
        if (list == null || list.size() == 0)
            return back;

        Timestamp t;
        long day = 24 * 3600000;
        float sum = 0;
        float value;
        long now;
        long end = 0;
        int count = 0;
        for (Map m : list) {
            t = (Timestamp) m.get("infoDataTime");
            value = Float.parseFloat((String) m.get(field));

            now = t.getTime();
            if (end == 0) {
                end = now + day;
            }
            sum += value;
            count++;

            if (now >= end) {
                Map dv = new HashMap();
                dv.put("infoDataTime", end);
                dv.put(field, sum / count);
                back.add(dv);

                end += day;
                sum = 0;
                count = 0;
            }
        }

        return back;
    }


    /**
     * 以天为单位的峰值分析
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> effectToMaxDay(List<Map<String, Object>> list, String field, boolean isMax, int span) {
        List<Map<String, Object>> back = new ArrayList<>();
        if (list == null || list.size() == 0)
            return back;

        List<Float> temp = new ArrayList<>();
        Timestamp t;
        long day = 24 * 3600000;
        float value;
        long now;
        long end = 0;
        for (Map m : list) {
            t = (Timestamp) m.get("infoDataTime");
            value = Float.parseFloat((String) m.get(field));
            now = t.getTime();

            if (end == 0) {
                end = now + day;
            }
            temp.add(value);

            if (now >= end) {
                Map dv = new HashMap();
                dv.put("infoDataTime", end);
                if (isMax)
                    dv.put(field, Collections.max(temp));
                else
                    dv.put(field, Collections.min(temp));
                back.add(dv);
                end += day;
                temp.clear();
            }
        }

        return back;
    }


}
