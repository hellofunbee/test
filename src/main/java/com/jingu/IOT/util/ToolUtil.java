package com.jingu.IOT.util;

import com.jingu.IOT.entity.MonitorEntity;
import com.jingu.IOT.entity.RuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ToolUtil {
	// public static final String URL = "http://192.168.0.206:80";

	// 本地
	// public static final String URL = "http://192.168.0.206:80";
	// public static final String CMSURL = "http://192.168.0.206:8787";
	// public static final String EXPORTURL = "http://192.168.0.206:9003";
	// public static final String IOTURL = "http://192.168.0.206:8087";
	// 虚拟机
	// public static final String URL = "http://192.168.0.223:80";
	// public static final String CMSURL = "http://192.168.0.223:8787";
	// public static final String EXPORTURL = "http://192.168.0.223:9003";
	// public static final String IOTURL = "http://192.168.0.223:8087";
	// 测试
	 public static final String URL = "http://localhost:80";
	 public static final String CMSURL = "http://localhost:8787";
	 public static final String EXPORTURL = "http://localhost:9003";
	 public static final String IOTURL = "http://localhost:8087";
	//
	
//	public static final String URL = "http://10.10.19.1:80";
//	public static final String IOTURL = "http://10.10.19.2:8087";
//	public static final String CMSURL = "http://10.10.19.3:8787";
//	public static final String EXPORTURL = "http://10.10.19.:9003";

	// 测试开关
	// public static final String URL = "http://192.168.0.7.35:80";
	// public static final String CMSURL = "http://192.168.0.7.35:8787";
	// public static final String EXPORTURL = "http://192.168.0.7.35:9003";
	// public static final String IOTURL = "http://192.168.0.7.35:8087";

	public static final String WXUSER = "wxuser";
	public static final String NOTIFY_URL = URL + "/wx_notifyurl";
	public static final String TRADE_TYPE = "JSAPI";
	public static final String SCAN_TYPE = "NATIVE";
	public static final String ALIPAY = "ALIPAY";
	public static final String NOTIFY = URL + "/ali_notifyurl";
	public static final String RETURNURL = URL + "/";// 这个是支付完成之后的返回的页面
	public static final String NOSTR = "123456";
	public static final String USERS = "USERS"; // uid sid
	public static final String SELLER = "SELLER";
	public static final String ADMIN = "ADMIN";
	public static final String IOT = "IOT";
	public static final String IOTUID = "IOTUID";
	public static final String PHONE_CODE = "phone_code";
	public static final String TREEID = "TREEID";
	public static final String CONFIG = "CONFIG";
	public static final String PROXY = "PROXY";
	public static final String VIDEO = "VIDEO";
	public static final String CTRL = "CTRL";
	public static final String ABLIYTY = "ABLIYTY";
	public static final String PROXYID = "PROXYID";
	public static final String PORT = "PORT";
	public static final String CMS = "CMS";
	// public static final int Role = 10;
	public static final String RULE = "RULE";
	public static final String MONITOR = "MONITOR";
	public static final String RULEID = "RULEID";
	public static final String ANALYSIS = "ANALYSIS";
	public static final String DATA = "DATA";
	public static final String MESSAGE = "MESSAGE";
	public static final String MESSAGEREADING = "MESSAGEREADING";
	public static final String READING = "READING";
	public static final String READ = "READ";
	public static final String ALARM = "ALARM";
	public static final String ALARMGRADE = "ALARMGRADE";

	// 线上
//	 public static final String FILEPATH = File.separator+"data"+File.separator+"IOT"+File.separator+"resources"+File.separator+"picture"+File.separator;
	// 线下
	public static final String FILEPATH = "resources" + File.separator + "IOT" + File.separator;

	public static final String DEVICELOG = "resources" + File.separator + "deviceLog" + File.separator;

	// public static final String IMG
	// ="D:"+File.separator+"workspace"+File.separator+"IotPlatForm"+File.separator+"context";

	public static final String IMG = File.separator + "data" + File.separator + "IOT" + File.separator + "Image";

	public static final String CMSIMG = File.separator + "data" + File.separator + "cms" + File.separator + "resources"
			+ File.separator + "sxcms" + File.separator;

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	public ToolUtil(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;

	}

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;
	// 粗情最大id
	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> infoId;

	// maxid
	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> maxId;

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> check;

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> intval;

	@Resource(name = "redisTemplate")
	HashOperations<String, String, Object> hashOperations;

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> rule;

	/**
	 *
	 * 匹配手机号
	 *
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		// 生成一个Pattern,同时编译一个正则表达式
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0678])|(18[0-9]))\\d{8}$");
		// 尝试对整个目标字符展开匹配检测，也就是只有整个目标字符串完全匹配时才返回真值。
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 *
	 * 拆分字符串
	 *
	 * @return
	 */
	public static List<Map<String, String>> SplitString(String str) {
		String[] split = str.split(";");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (String string : split) {
			String[] split2 = string.split(":");
			if (map.containsKey(split2[0])) {
				list.add(map);
				map = new HashMap<String, String>();
			}
			map.put(split2[0], split2[1]);

		}
		return list;
		/*
		 * List<Map<String, String>> list = new ArrayList<Map<String,
		 * String>>(); for (int i = begin; i < split.length; i+=step) {
		 * Map<String, String> map = new HashMap<String,String>(); for (int j =
		 * 0; j < step; j++) { String[] split2 = split[j].split(":");
		 * map.put(split2[0], split2[1]); } list.add(map);
		 * 
		 * }
		 */
		// return false;
	}

	public void putCodeByPhone(String key, String value) {
		valOpsStr.set(key, value);
	}

	public String getCodeByPhone(String key) {
		return valOpsStr.get(key);
	}

	public String getCodeByPhoto(String key) {
		return valOpsStr.get(key);
	}

	public void putCodeByPhoto(String key, String value) {
		valOpsStr.set(key, value);
	}

	// set uid sid
	public void putSidByUid(String uid, String sid) {
		valOpsStr.set(uid, sid);
	}

	// get sid
	public String getSidByUid(String uid) {
		return valOpsStr.get(uid);
	}

	public void setMaxId(String uid, Integer sid) {
		maxId.set(uid, sid.toString());
	}

	// get sid
	public String getMaxId(String uid) {
		return maxId.get(uid);
	}

	public Long MaxIdInc(String uid) {
		return maxId.increment(uid, 1);
	}

	public void setRuleList(String key, List<RuleEntity> ruleSet) {
		byte[] serialize = SerializeUtil.serialize(ruleSet);
		String encode = Base64.encode(serialize);
		rule.set(key, encode);
	}

	public List<RuleEntity> getRuleList(String key) throws UnsupportedEncodingException {
		String string = rule.get(key);
		if (string == null) {
			return null;
		}
		byte[] decode = Base64.decode(string);
		Object rulelist = SerializeUtil.unserialize(decode);
		return (List<RuleEntity>) rulelist;
	}

	public void setMonitorList(String key, List<MonitorEntity> ruleSet) {
		byte[] serialize = SerializeUtil.serialize(ruleSet);
		String encode = Base64.encode(serialize);
		rule.set(key, encode);
	}

	public List<MonitorEntity> getMonitorList(String key) throws UnsupportedEncodingException {
		byte[] decode;
		try {
			String string = rule.get(key);
			if (string == null || string.trim().length() < 1) {
				return null;
			}
			decode = Base64.decode(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Object rulelist = SerializeUtil.unserialize(decode);
		return (List<MonitorEntity>) rulelist;
	}

	// public List<MonitorEntity> getAlarmList(String key) throws
	// UnsupportedEncodingException{
	// byte[] decode;
	// try {
	// String string = rule.get(key);
	// if(string ==null||string.trim().length()<1){
	// return null;
	// }
	// decode = Base64.decode(string);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return null;
	// }
	// Object rulelist = SerializeUtil.unserialize(decode);
	// return (List<MonitorEntity>)rulelist;
	// }

	/*
	 * //自动设置最大id public void setOrderid(String out_trade_no,String o_id){
	 * orderid.set("order"+out_trade_no,o_id); }
	 */

	// 根据code取user
	/*
	 * public UserEntity getWxUser(String code) throws
	 * UnsupportedEncodingException{ String user = wxuser.get("wx"+code);
	 * if(user ==null ||user.trim().length()<1){ return null; } return
	 * (UserEntity) SerializeUtil.unserialize(Base64.decode(user)); }
	 * //根据code存user public void setWxUser(String code,UserEntity user){ String
	 * u = Base64.encode(SerializeUtil.serialize(user));
	 * wxuser.set("wx"+code,Base64.encode(SerializeUtil.serialize(user))); }
	 */

	public static Long getStartTime() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
				0, 0, 0);
		Date beginOfDate = calendar1.getTime();
		return beginOfDate.getTime();
	}

	public static Long getEndTime() {
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
				23, 59, 59);
		Date endOfDate = calendar2.getTime();
		return endOfDate.getTime();
	}

	// 根据uid取sid
	public String getCheck(String uid) {
		try {
			return check.get(uid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	// public void setCheck(String uid,String sid){
	// check.set(uid,sid);
	// }

	// 根据uid存sid
	public void setCheck(String uid, String sid) {
		check.set(uid, sid);
	}

	public void setCheckexpire(String uid, int time) {
		check.getOperations().expire(uid, time, TimeUnit.SECONDS);
	}

	public void setIntVal(String key, Integer value) {
		intval.set(key, value.toString());
	}

	public String getIntVal(String key) {
		try {
			return intval.get(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}

	public Long incIntVal(String key) {
		return intval.increment(key, 1);
	}

	public Long decIntVal(String key) {
		return intval.increment(key, -1);
	}

	public void setHashUidSid(String base_uid, String sid, long uid) {
		hashOperations.put(base_uid, sid, uid);
	}

	public long getbase_uidSid(String base_uid, String sid) {
		return (long) hashOperations.get(base_uid, sid);
	}

	public void setHashUidSidexpire(String base_uid, int time) {
		hashOperations.getOperations().expire(base_uid, time, TimeUnit.SECONDS);
	}

	public void deleteSidTimeUid(String base_uid, String sid, long uid) {
		hashOperations.getOperations().delete(base_uid);
	}

	public String cktime(String time) {
		// String tString = "2017-1-01";
		if (time.length() < 10) {
			String[] split = time.split("-");
			if (split[1].length() < 2) {
				split[1] = String.valueOf(0) + split[1];
			}
			if (split[2].length() < 2) {
				split[2] = String.valueOf(0) + split[2];
			}
			return split[0] + "-" + split[1] + "-" + split[2];
		} else {
			return time;
		}
	}

	public String cktimeHour(String time) {
		// String tString = "2017-1-01";
		if (time.length() < 10) {
			String[] split = time.split(":");
			if (split[1].length() < 2) {
				split[1] = String.valueOf(0) + split[1];
			}
			if (split[2].length() < 2) {
				split[2] = String.valueOf(0) + split[2];
			}
			return split[0] + ":" + split[1] + ":" + split[2];
		} else {
			return time;
		}
	}
	// public static void main(String[] args) {
	// String cktime = cktime("2017-10-1");
	// System.out.println(cktime);
	// }

}
