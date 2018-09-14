/**  
*   
* 项目名称：IOT  
* 类名称：JsonToString  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月24日 下午5:30:38  
* 修改人：jianghu  
* 修改时间：2017年10月24日 下午5:30:38  
* 修改备注： 下午5:30:38
* @version   
*   
*/ 
package com.jingu.IOT.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

/**

* @ClassName: JsonToString
* @Description: TODO
* @author jianghu
* @date 2017年10月24日 下午5:30:38

*/
@Component
public class JsonToString {

	public static JSONObject StringToJson(String configstr) {
		int i =0;
		Map<String,Object> map = new HashMap<>();
//		String string  = "s_repIpServIp1:192.168.0.186;s_repIpServPort1:8889;s_repIpServIp2:192.168.0.194;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:192.168.0.186;s_adcServPort1:8889;s_adcServIp2:192.168.0.186;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.0.186;s_deviceIdServPort:8889;s_logServIp:192.168.0.186;s_logServPort:8889;s_alarmServIP:192.168.0.186;s_alarmServPort:8889;s_timeServIP:192.168.0.186;s_timeServPort:8889;s_imgServIP:192.168.0.186;s_imgServPort:8889;s_localIP:192.168.0.168;s_netmask:255.255.255.0;s_gatewayIP:192.168.0.1;s_proxyIP:0.0.0.0;s_dnsIP:0.0.0.0;power_mode:0;nouse_ctrl:2;use_time_ctrl:000000~210000;use_light_ctrl:90;adc_cap_time:1800;v_netType:1;s_is_aes:1;s_aes_key:01020102010201020102010201020102;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:6;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:1;img_capResol:0;";
		String[] split = configstr.split(";");
//		for (String string2 : split) {
//			if(string2.equals("localIP")){
//				i =1;
//			}
//			
//			if(i==0){
//				map.put(key, value)
//			}
//			System.out.println(string2);
//		}
		
		for (int j = 0; j < split.length; ) {
			if(split[j].split(":")[0].equals("s_localIP")){
				i =1;
	
			}
			
			if(i==0){
				String string2 = split[j].split(":")[0];
//				System.out.println(string2);
				String string3 = split[j].split(":")[1];
//				System.out.println(string3);
				String string4 = split[j+1].split(":")[1];
//				System.out.println(string4);
				map.put(split[j].split(":")[0], split[j].split(":")[1]+":"+split[j+1].split(":")[1]);
				j +=2;
			}
			if(i==1){
				map.put(split[j].split(":")[0], split[j].split(":")[1]);
				j += 1;
			}
		
		}
		JSONObject fromObject = JSONObject.fromObject(map);
		return fromObject;
		
	}
	public static void main(String[] args) {
		int i =0;
		Map<String,Object> map = new HashMap<>();
		String string  = "s_repIpServIp1:192.168.0.186;s_repIpServPort1:8889;s_repIpServIp2:192.168.0.194;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:192.168.0.186;s_adcServPort1:8889;s_adcServIp2:192.168.0.186;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.0.186;s_deviceIdServPort:8889;s_logServIp:192.168.0.186;s_logServPort:8889;s_alarmServIP:192.168.0.186;s_alarmServPort:8889;s_timeServIP:192.168.0.186;s_timeServPort:8889;s_imgServIP:192.168.0.186;s_imgServPort:8889;s_localIP:192.168.0.168;s_netmask:255.255.255.0;s_gatewayIP:192.168.0.1;s_proxyIP:0.0.0.0;s_dnsIP:0.0.0.0;power_mode:0;nouse_ctrl:2;use_time_ctrl:000000~210000;use_light_ctrl:90;adc_cap_time:1800;v_netType:1;s_is_aes:1;s_aes_key:01020102010201020102010201020102;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:6;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:1;img_capResol:0;";
		String[] split = string.split(";");
//		for (String string2 : split) {
//			if(string2.equals("localIP")){
//				i =1;
//			}
//			
//			if(i==0){
//				map.put(key, value)
//			}
//			System.out.println(string2);
//		}
		
		for (int j = 0; j < split.length; ) {
			if(split[j].split(":")[0].equals("s_localIP")){
				i =1;
	
			}
			
			if(i==0){
				String string2 = split[j].split(":")[0];
				System.out.println(string2);
				String string3 = split[j].split(":")[1];
				System.out.println(string3);
				String string4 = split[j+1].split(":")[1];
				System.out.println(string4);
				map.put(split[j].split(":")[0], split[j].split(":")[1]+":"+split[j+1].split(":")[1]);
				j +=2;
			}
			if(i==1){
				map.put(split[j].split(":")[0], split[j].split(":")[1]);
				j += 1;
			}
		
		}
		System.out.println(map);
	}
}
