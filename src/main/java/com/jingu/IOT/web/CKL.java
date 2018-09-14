/**  
*   
  * 项目名称：nxy  
* 类名称：CKL  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月3日 下午3:42:13  
* 修改人：jianghu  
* 修改时间：2017年8月3日 下午3:42:13  
* 修改备注： 下午3:42:13
* @version   
*   
*/ 
package com.jingu.IOT.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jingu.IOT.entity.HkSdkEx;
import com.jingu.IOT.entity.IPCPointEntity;
import com.jingu.IOT.entity.MonitorHBM;
import com.jingu.IOT.entity.VRAConfigBean;
import com.jingu.IOT.entity.VideoShemaBean;
import com.jingu.IOT.switcher.VRASwitchBean;
import com.jingu.IOT.switcher.VRASwitchConfBean;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.PublicMethod;

/**

* @ClassName: CKL
* @Description: TODO
* @author jianghu
* @date 2017年8月3日 下午3:42:13

*/
public class CKL {

	public static void main(String[] args) {
		//根据ip和端口号找设备
//		VRAConfigBean vraConfig = Client.getVRAConfig("192.168.0.198", 52390);
//		System.out.println("原:"+vraConfig.toString());
//		s_repIpServIp1:120.132.53.44;s_repIpServPort1:9999;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:120.132.53.44;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.7.35;s_deviceIdServPort:8889;s_logServIp:123.127.240.42;s_logServPort:8889;s_alarmServIP:192.168.7.35;s_alarmServPort:8889;s_timeServIP:192.168.7.35;s_timeServPort:8889;s_imgServIP:120.132.53.44;s_imgServPort:8889;s_localIP:192.168.0.198;s_netmask:255.255.255.0;s_gatewayIP:192.168.0.1;s_proxyIP:0.0.0.0;s_dnsIP:0.0.0.0;power_mode:0;nouse_ctrl:2;use_time_ctrl:000000~210000;use_light_ctrl:90;adc_cap_time:1800;v_netType:1;s_is_aes:1;s_aes_key:01020102010201020102010201020102;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:60;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:0;img_capResol:0;
		//根据ip和端口号找设备---可以改成根据设备id号找设备,把ip和端口号和id号对应存入数据库
//		String config = "s_repIpServIp1:192.168.0.125;s_repIpServPort1:7889;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:192.168.0.125;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.0.124;s_deviceIdServPort:8889;s_logServIp:192.168.0.125;s_logServPort:8889;s_alarmServIP:192.168.0.124;s_alarmServPort:8889;s_timeServIP:192.168.0.124;s_timeServPort:8889;s_imgServIP:60.223.239.241;s_imgServPort:8889;s_localIP:192.168.0.198;s_netmask:255.255.255.0;s_gatewayIP:192.168.0.1;s_proxyIP:0.0.0.0;s_dnsIP:0.0.0.0;power_mode:0;nouse_ctrl:1;use_time_ctrl:060000~180000;use_light_ctrl:101;adc_cap_time:1800;v_netType:1;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:1;img_capEnable:0;img_capInterval:6;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:1;img_capResol:0;";
//		String config = "s_repIpServIp1:192.168.0.125;s_repIpServPort1:7889;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:192.168.0.125;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.0.124;s_deviceIdServPort:8889;s_logServIp:192.168.0.125;s_logServPort:8889;s_alarmServIP:192.168.0.124;s_alarmServPort:8889;s_timeServIP:192.168.0.124;s_timeServPort:8889;s_imgServIP:60.223.239.241;s_imgServPort:8889;s_localIP:192.168.0.198;s_netmask:255.255.255.0;s_gatewayIP:192.168.1.1;s_proxyIP:0.0.0.0;s_dnsIP:0.0.0.0;power_mode:0;nouse_ctrl:1;use_time_ctrl:060000~180000;use_light_ctrl:101;adc_cap_time:1800;v_netType:1;v_targetBitRate:1500000;v_frameRate:12;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:6;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:1;img_capResol:1;";
//	    String config = "s_repIpServIp1:120.132.53.44;s_repIpServPort1:8889;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:120.132.53.44;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.7.35;s_deviceIdServPort:8889;s_logServIp:123.127.240.42;s_logServPort:8889;s_alarmServIP:192.168.7.35;s_alarmServPort:8889;s_timeServIP:192.168.7.35;s_timeServPort:8889;s_imgServIP:120.132.53.44;s_imgServPort:8889;s_localIP:192.168.0.198;s_gatewayIP:192.168.0.1;s_netmask:255.255.255.0;s_proxyIP:0.0.0.0;power_mode:0;nouse_ctrl:2;use_time_ctrl:000000~210000;use_light_ctrl:90;adc_cap_time:1800;v_netType:1;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:60;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:0;s_dnsIP:0.0.0.0;img_capResol:0;";
//		String config = "s_repIpServIp1:120.132.53.44;s_repIpServPort1:9999;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:120.132.53.44;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.7.35;s_deviceIdServPort:8889;s_logServIp:123.127.240.42;s_logServPort:8889;s_alarmServIP:192.168.7.35;s_alarmServPort:8889;s_timeServIP:192.168.7.35;s_timeServPort:8889;s_imgServIP:120.132.53.44;s_imgServPort:8889;s_localIP:192.168.0.198;s_gatewayIP:192.168.0.1;s_netmask:255.255.255.0;s_proxyIP:0.0.0.0;power_mode:0;nouse_ctrl:2;use_time_ctrl:000000~210000;use_light_ctrl:90;adc_cap_time:1800;v_netType:1;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:60;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:0;s_dnsIP:0.0.0.0;img_capResol:0;";
	   	String config = "s_repIpServIp1:120.132.53.44;s_repIpServPort1:9999;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:120.132.53.44;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.7.35;s_deviceIdServPort:8889;s_logServIp:123.127.240.42;s_logServPort:8889;s_alarmServIP:192.168.7.35;s_alarmServPort:8889;s_timeServIP:192.168.7.35;s_timeServPort:8889;s_imgServIP:120.132.53.44;s_imgServPort:8889;s_localIP:192.168.0.198;s_netmask:255.255.255.0;s_gatewayIP:192.168.0.1;s_proxyIP:0.0.0.0;s_dnsIP:0.0.0.0;power_mode:0;nouse_ctrl:2;use_time_ctrl:000000~210000;use_light_ctrl:90;adc_cap_time:1800;v_netType:1;s_is_aes:1;s_aes_key:01020102010201020102010201020102;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:60;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:0;img_capResol:0;";
//		String config = "s_repIpServIp1:192.168.0.124;s_repIpServPort1:8889;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:192.168.0.8;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.0.8;s_deviceIdServPort:8889;s_logServIp:192.168.0.8;s_logServPort:8889;s_alarmServIP:192.168.0.8;s_alarmServPort:8889;s_timeServIP:192.168.0.8;s_timeServPort:8889;s_imgServIP:192.168.0.8;s_imgServPort:8889;s_localIP:192.168.0.198;s_netmask:255.255.255.0;s_gatewayIP:192.168.0.1;s_proxyIP:0.0.0.0;s_dnsIP:0.0.0.0;power_mode:0;nouse_ctrl:2;use_time_ctrl:000000~210000;use_light_ctrl:90;adc_cap_time:1800;v_netType:1;s_is_aes:1;s_aes_key:01020102010201020102010201020102;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:6;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:1;img_capResol:0;";
//		String config = "s_repIpServIp1:192.168.0.186;s_repIpServPort1:8889;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:192.168.0.206;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.0.8;s_deviceIdServPort:8889;s_logServIp:192.168.0.8;s_logServPort:8889;s_alarmServIP:192.168.0.8;s_alarmServPort:8889;s_timeServIP:192.168.0.8;s_timeServPort:8889;s_imgServIP:192.168.0.8;s_imgServPort:8889;s_localIP:192.168.0.198;s_gatewayIP:192.168.0.1;s_netmask:255.255.255.0;s_proxyIP:0.0.0.0;power_mode:0;nouse_ctrl:2;use_time_ctrl:000000~210000;use_light_ctrl:90;adc_cap_time:1800;v_netType:1;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:6;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_input Standard:1;v_inputStandard:1;s_dnsIP:0.0.0.0;img_capResol:0;";
//		String config ="s_repIpServIp1:120.132.53.44;s_repIpServPort1:9999;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:120.132.53.44;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.7.35;s_deviceIdServPort:8889;s_logServIp:123.127.240.42;s_logServPort:9999;s_alarmServIP:192.168.7.35;s_alarmServPort:8889;s_timeServIP:192.168.7.35;s_timeServPort:8889;s_imgServIP:120.132.53.44;s_imgServPort:8889;s_localIP:192.168.0.198;s_gatewayIP:0.0.0.0;s_netmask:0.0.0.0;s_proxyIP:0.0.0.0;s_dnsIP:0.0.0.0;power_mode:1;nouse_ctrl:1;use_time_ctrl:060000~180000;use_light_ctrl:101;adc_cap_time:5;v_netType:1;s_is_aes:1;s_aes_key:01020102010201020102010201020102;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:60;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:0;img_capResol:0;";
//		String config ="s_repIpServIp1:120.132.53.44;s_repIpServPort1:9999;s_repIpServIp2:192.168.0.206;s_repIpServPort2:8889;s_repIpServIp3:192.168.7.35;s_repIpServPort3:8889;s_adcServIp1:120.132.53.44;s_adcServPort1:8889;s_adcServIp2:192.168.0.206;s_adcServPort2:8889;s_adcServIp3:192.168.7.35;s_adcServPort3:8889;s_deviceIdServIp:192.168.7.35;s_deviceIdServPort:8889;s_logServIp:123.127.240.42;s_logServPort:8889;s_alarmServIP:192.168.7.35;s_alarmServPort:8889;s_timeServIP:192.168.7.35;s_timeServPort:8889;s_imgServIP:120.132.53.44;s_imgServPort:8889;s_localIP:192.168.0.198;s_netmask:255.255.255.0;s_gatewayIP:192.168.0.1;s_proxyIP:0.0.0.0;s_dnsIP:0.0.0.0;power_mode:0;nouse_ctrl:2;use_time_ctrl:000000~210000;use_light_ctrl:90;adc_cap_time:1800;v_netType:1;s_is_aes:1;s_aes_key:01020102010201020102010201020102;v_targetBitRate:1500000;v_frameRate:25;v_mode:0;img_capManu:0;img_capEnable:0;img_capInterval:60;v_brgVal:128;v_conVal:128;v_satVal:128;v_hueVal:0;c_chn1Val:0;c_chn2Val:0;c_chn3Val:0;c_chn4Val:0;c_chn5Val:0;c_chn6Val:0;c_chn7Val:0;c_chn8Val:0;c_chn9Val:0;c_chn10Val:0;c_chn11Val:0;c_chn12Val:0;c_chn13Val:0;c_chn14Val:0;c_chn15Val:0;c_chn16Val:0;c_chn17Val:0;c_chn18Val:0;c_chn19Val:0;c_chn20Val:0;c_chn21Val:0;c_chn22Val:0;c_chn23Val:0;c_chn24Val:0;c_chn25Val:0;c_chn26Val:0;c_chn27Val:0;c_chn28Val:0;c_chn29Val:0;c_chn30Val:0;c_chn31Val:0;c_chn32Val:0;c_chn1Max:65535;c_chn2Max:65535;c_chn3Max:65535;c_chn4Max:65535;c_chn5Max:65535;c_chn6Max:65535;c_chn7Max:65535;c_chn8Max:65535;c_chn9Max:65535;c_chn10Max:65535;c_chn11Max:65535;c_chn12Max:65535;c_chn13Max:65535;c_chn14Max:65535;c_chn15Max:65535;c_chn16Max:65535;c_chn17Max:65535;c_chn18Max:65535;c_chn19Max:65535;c_chn20Max:65535;c_chn21Max:65535;c_chn22Max:65535;c_chn23Max:65535;c_chn24Max:65535;c_chn25Max:65535;c_chn26Max:65535;c_chn27Max:65535;c_chn28Max:65535;c_chn29Max:65535;c_chn30Max:65535;c_chn31Max:65535;c_chn32Max:65535;c_chn1Min:0;c_chn2Min:0;c_chn3Min:0;c_chn4Min:0;c_chn5Min:0;c_chn6Min:0;c_chn7Min:0;c_chn8Min:0;c_chn9Min:0;c_chn10Min:0;c_chn11Min:0;c_chn12Min:0;c_chn13Min:0;c_chn14Min:0;c_chn15Min:0;c_chn16Min:0;c_chn17Min:0;c_chn18Min:0;c_chn19Min:0;c_chn20Min:0;c_chn21Min:0;c_chn22Min:0;c_chn23Min:0;c_chn24Min:0;c_chn25Min:0;c_chn26Min:0;c_chn27Min:0;c_chn28Min:0;c_chn29Min:0;c_chn30Min:0;c_chn31Min:0;c_chn32Min:0;v_inputStandard:0;img_capResol:0;"
//		int port = 52390;
//		String DeviceId="10.00.22.98";
//		String ip = "192.168.0.198";
	   	
//		int port = 52398;
//		String DeviceId="10.00.21.18";
//		String ip = "111.53.182.34";
		
//		int port = 52402;
//		String DeviceId="10.00.21.24";
//		String ip = "111.53.182.34";
	   	
		int port = 52392;
		String DeviceId="10.00.21.32";
		String ip = "111.53.182.34";
		String ipcProxy1 = Client.getIpcProxy1(DeviceId,ip,port);
		//String proxyStr= "s_host:192.168.0.234;s_rport:8000;s_lport:9001;s_pwr:1;s_pwrval:0;s_timeout:1800;";
						//s_num:1;s_no:0;s_host:;s_rport:80;s_lport:8080;s_pwr:0;s_pwrval:0;s_timeout:150;
		//String ipcProxy1 = Client.setIpcProxyEx1("add", proxyStr, "10.00.21.74",ip,port);
		System.out.println(ipcProxy1);
		
//		IpcBean ipc = Client.getIpc2(ip,port,DeviceId);
//		boolean setIpc1 = Client.setIpc1("delete", "s_nod:1;s_ip:192.168.0.168;s_port:80;s_username:admin;s_password:12345;", DeviceId,ip,port);
		//String[] split = ss.split("\\.");
		//System.out.println("修改成:"+config);//10.00.21.47
		//Client.startupDevice(null, DeviceId, "192.168.0.192", port);
//		boolean setVRAConfig = Client.setVRAConfig(config, DeviceId,ip, 52390);
//		System.out.println(setVRAConfig);
		//Client.setVRAConfig(config, "10.00.21.47","192.168.0.186", 52390);
		//VRAConfigBean vraConfig2 = Client.getVRAConfig("111.53.182.34", 52398);
/*		VRAConfigBean vraConfig2 = Client.getVRAConfig(ip, 52390);
		System.out.println("改后:"+vraConfig2.toString());*/
		HttpServletRequest request = null;
		//Client.writeVRAConfig(request, DeviceId, ip, port);
		//Client.getDeviceStatus(request, DeviceId, ip, port);
		
		//Client.startupDevice(request, DeviceId, ip, port);

//		byte b = 1;
////		Client.getIpcMonitor("1", DeviceId,b , ip, port);
//		//Client.setIpc(type, config, deviceId)
//		MonitorHBM hbm = new MonitorHBM();
//		hbm.setBeginTime("08:00:00");
//		hbm.setEndTime("19:00:00");
//		hbm.setCycleDay(1);
//		hbm.setDeviceId(DeviceId);
//		hbm.setMonitorName("新设置的监视点");
//		hbm.setMonitorId(10);
//		hbm.setRateSecond(5);
//		hbm.setId("1");
//		byte b =1;
//		boolean clearIpcMonitor = Client.clearIpcMonitor(ip, port, b, DeviceId);
//		System.out.println(clearIpcMonitor);
//		try {
//			List<IPCPointEntity> ipcMonitor2 = Client.getIpcMonitor2("1", DeviceId, b, ip, port);
//			System.out.println(ipcMonitor2);
//			boolean setIpcMonitor = Client.setIpcMonitor(ip, port, b, hbm);
//			System.out.println(setIpcMonitor);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			List<IPCPointEntity> ipcMonitor2 = Client.getIpcMonitor2("1", DeviceId, b, ip, port);
//			System.out.println(ipcMonitor2);
//			e.printStackTrace();
//		}
//		boolean setIpcMonitor = Client.setIpcMonitor(ip, port, b, hbm);
//		System.out.println(setIpcMonitor);
//		boolean delIpcMonitor = Client.delIpcMonitor(ip, port, b, hbm);
//		System.out.println(delIpcMonitor);
		//Client.getIpcMonitor("1", DeviceId3,b , ip3, port3);
		//boolean delIpcMonitor = Client.delIpcMonitor(ip3, port3, b, hbm);
		//System.out.println(delIpcMonitor);
		//VRALogBean vraLog = Client.getVRALog(request, DeviceId3, ip3, port3);
		/*String sendClientCommand1 = Client.sendClientCommand("127.0.0.1", 9999, "isStarted");
		System.out.println(sendClientCommand1);*/
	/*	String sendClientCommand2 = Client.sendClientCommand("127.0.0.1", 9999, "stopServer");
		System.out.println(sendClientCommand2);*/
		//String sendClientCommand3 = Client.sendClientCommand("127.0.0.1", 9999, "isStarted");
		//System.out.println(sendClientCommand3);
		/*String sendClientCommand4 = Client.sendClientCommand("127.0.0.1", 9999, "startServer");
		System.out.println(sendClientCommand4);*/
//		VideoShemaBean ipcAbility = HkSdkEx.getIpcAbility("admin", "12345", "192.168.0.168", "9001");
//		System.out.println(ipcAbility);
		//System.out.println(compressInfo.toString());
//		VRASwitchConfBean switchConfOut = Client.getSwitchConfOut(ip, port, DeviceId);
//		System.out.println(switchConfOut);
		//byte[] bs = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
//		String ip3 = "111.53.182.34";
//		int port3 =52399;
//		String DeviceId3 = "10.00.21.30 ";
//		byte[] bs2 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
//		VRASwitchBean switch1 = Client.getSwitch(ip, port, bs2, DeviceId, false);
//		System.out.println(switch1);
//		VRASwitchBean switchOut = Client.getSwitchOut(ip, port, bs, DeviceId);
//		System.out.println(switchOut);
//		VRASwitchBean switchOut = Client.getSwitchOut(ip3, port3, bs2, DeviceId3);
//		System.out.println(switchOut);
//		VRASwitchConfBean switchConfOut = Client.getSwitchConfOut(ip, port, DeviceId);
//		System.out.println(switchConfOut);
	
	}
	
	
	
	private static byte[] readInputStream(InputStream _in) throws IOException {
		if (_in == null)
			throw new IOException("InputStream can't be null!");

		// /读取包头
		int haveread = 0;
		int readed = 14;
		//		
		byte[] head = new byte[14];
		//		
		while (haveread < readed) {
			int s = _in.read(head, haveread, readed - haveread);
			if (s == -1) {
				return null; // Connection lost
			}
			haveread = haveread + s;
		}
		// 读取数据类型
		int dataType = head[1];
		// 读取加密长度
		byte[] AES = new byte[2];
		AES[0] = head[10];
		AES[1] = head[11];
		int AESLen = PublicMethod.byteToInt2(AES);
		// 数据长度
		int dataLen = AESLen - 2;

		haveread = 0;
		byte[] data = new byte[dataLen];

		// 获取数据
		while (haveread < dataLen) {
			int s = _in.read(data, haveread, dataLen - haveread);
			if (s == -1) {
				return null; // Connection lost
			}
			haveread = haveread + s;
		}

		// CRC
		byte[] CRC = new byte[1];
		haveread = 0;
		while (haveread < 1) {
			int s = _in.read(CRC, haveread, 1);
			if (s == -1) {
				return null; // Connection lost
			}
			haveread = haveread + s;
		}
		// 返回全部数据
		int allLen = 14 + dataLen + 1;
		byte[] allData = new byte[allLen];
		System.arraycopy(head, 0, allData, 0, head.length);
		System.arraycopy(data, 0, allData, 14, data.length);
		allData[allLen - 1] = CRC[0];
		return allData;
	}
	
	
}

