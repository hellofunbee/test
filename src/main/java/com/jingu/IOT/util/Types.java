package com.jingu.IOT.util;

/**
 * Created by weifengxu on 2018/9/16.
 */
public class Types {
    /*1 作物 2 农资  3 预警  4 政策  5 即时信息  6 专家 7 首页 8 生产进度'*/

    /* 作物 */
    public final static int CT_ZUOWU = 1;
    /* 农资 */
    public final static int CT_NONGZI = 2;
    /* 预警 */
    public final static int CT_YUJING = 3;
    /* 政策 */
    public final static int CT_ZHENGCE = 4;
    /* 即时信息 */
    public final static int CT_JISHI = 5;
    /* 专家 */
    public final static int CT_ZHUANJIA = 6;
    /* 首页 */
    public final static int CT_SHOUYE = 7;
    /* 生产进度 */
    public final static int CT_JINDU = 8;

      /*message （m_type）*/
    /*类型 1 政策 2及时 3 预警 4 首页资讯*/

    public final static int MT_ZHENGCE = 1;
    public final static int MT_JISHI = 2;
    public final static int MT_YUJING = 3;
    public final static int MT_SHOUYE = 4;

    /*state 1:ok,2:fail*/

    public final static int STATE_OK = 1;
    public final static int STATE_FAIL = 2;
    /*用户 0  1 超级管理员  2 管理员 3 普通用户 4监管者用户 5生产者用户 6专家用户 */
    public final static int usr_s_admin = 1;
    public final static int usr_admin = 2;
    public final static int usr = 3;
    public final static int usr_superviser = 4;
    public final static int usr_roducer = 5;
    public final static int usr_expert = 6;


}
