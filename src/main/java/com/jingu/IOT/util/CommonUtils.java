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

    public static long str2Date(String date_str) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(date_str);
            return date.getTime();
        } catch (Exception e) {

            e.printStackTrace();
            return 0;
        }

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

    /**
     * 日、月度、年度 投入量分析
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> effectByTime(List<Map<String, Object>> list, int span) {
        List<Map<String, Object>> back = new ArrayList<>();
        if (list == null || list.size() == 0)
            return back;

        long day = 24 * 3600000;
        switch (span) {
            case 0:
                break;
            case 1:
                day = day * 30;
            case 2:
                day = day * 360;
            default:
                break;
        }
        float sum = 0;
        float value;
        long now;
        long end = 0;

        for (Map m : list) {
            now = str2Date((String) m.get("in_time"));
            if (now == 0)
                continue;
            value = Float.parseFloat((String) m.get("in_total"));
            if (end == 0) {
                end = now + day;
            }
            sum += value;
            if (now >= end) {
                Map dv = new HashMap();
                dv.put("in_time", end);
                dv.put("in_total", sum);
                back.add(dv);
                end += day;
                sum = 0;
            }
        }

        return back;
    }


    /**
     * 查找map 与list<Map>是否存在key为field的元相同素
     * @param m
     * @param list
     * @param field
     * @return
     */
    public static boolean hasObj(Map m, List<Map<String, Object>> list, String field) {

        if (m == null || list == null || field == null)
            return false;
        for (Map src : list) {
            if (src.get(field) != null && src.get(field).equals(m.get(field))) {
                return true;
            }
        }

        return false;

    }

}
