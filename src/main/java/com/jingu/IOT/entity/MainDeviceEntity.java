/**  
*   
* 项目名称：IOT  
* 类名称：MainDeviceDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月4日 上午10:40:27  
* 修改人：jianghu  
* 修改时间：2017年9月4日 上午10:40:27  
* 修改备注： 上午10:40:27
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hp.hpl.sparta.xpath.ThisNodeTest;
import com.jingu.IOT.entity.HCNetSDK.struRecordingHost;

/**

* @ClassName: MainDeviceDao
* @Description: TODO
* @author jianghu
* @date 2017年9月4日 上午10:40:27
rderNo,NIP,NPort,IP,Port,proxyIp,proxyPort,x,y,zoom,DeviceId,name,videoPort,softWareUpdatePort,useIPConnect,videoPlayPort,ctrPort,title,smartCtrl,benTian,yuYang
*/
public class MainDeviceEntity implements RowMapper<MainDeviceEntity> {
	private int id;
	private int orderNo;
	private String nip;
	private int nport;
	private String ip;
	private int port;
	private String proxyIp;
	private int proxyPort;
	private double x;
	private double y;
	private double zoom;
	private String deviceId;
	private String name;
	private int videoPort;
	private int softWareUpdatePort;
	private int useIPConnect;
	private int videoPlayPort;
	private int ctrPort;
	private String title;
	private int smartCtrl;
	private int benTian;
	private int yuYang;
	private String province;
	private String city;
	private String district;
	private String superivsername;
	private String producername;
	private String exportorname;
	private int superviserid;
	private int producerid;
	private int exportorid;
	private int siteid;
	private int groupid;
	private String softWareVersion;
	private String hardWareVersion;
	private String provincename;
	private String cityname;
	private String districtname;
	
	
	
	
	
	
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getDistrictname() {
		return districtname;
	}
	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	public String getSoftWareVersion() {
		return softWareVersion;
	}
	public void setSoftWareVersion(String softWareVersion) {
		this.softWareVersion = softWareVersion;
	}
	public String getHardWareVersion() {
		return hardWareVersion;
	}
	public void setHardWareVersion(String hardWareVersion) {
		this.hardWareVersion = hardWareVersion;
	}
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getSuperviserid() {
		return superviserid;
	}
	public void setSuperviserid(int superviserid) {
		this.superviserid = superviserid;
	}

	public int getProducerid() {
		return producerid;
	}
	public void setProducerid(int producerid) {
		this.producerid = producerid;
	}
	public int getExportorid() {
		return exportorid;
	}
	public void setExportorid(int exportorid) {
		this.exportorid = exportorid;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		if (null == province || province.trim().length() == 0) {
			this.province = "";
		}
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		if (null == city || city.trim().length() == 0) {
			this.city = "";
		}
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		if (null == district || district.trim().length() == 0) {
			this.district = "";
		}
		this.district = district;
	}
	public String getSuperivsername() {
		return superivsername;
	}
	public void setSuperivsername(String superivsername) {
		if (null == superivsername || superivsername.trim().length() == 0) {
			this.superivsername = "";
		}
		this.superivsername = superivsername;
	}
	public String getProducername() {
		return producername;
	}
	public void setProducername(String producername) {
		if (null == producername || producername.trim().length() == 0) {
			this.producername = "";
		}
		this.producername = producername;
	}
	public String getExportorname() {
		return exportorname;
	}
	public void setExportorname(String exportorname) {
		if (null == exportorname || exportorname.trim().length() == 0) {
			this.exportorname = "";
		}
		this.exportorname = exportorname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}


	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		if (null == nip || nip.trim().length() == 0) {
			this.nip = "";
		}
		this.nip = nip;
	}
	public int getNport() {
		return nport;
	}
	public void setNport(int nport) {
		this.nport = nport;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		if (null == ip || ip.trim().length() == 0) {
			this.ip = "";
		}
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getProxyIp() {
		return proxyIp;
	}
	public void setProxyIp(String proxyIp) {
		if (null == proxyIp || proxyIp.trim().length() == 0) {
			this.proxyIp = "";
		}
		this.proxyIp = proxyIp;
	}
	public int getProxyPort() {
		return proxyPort;
	}
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZoom() {
		return zoom;
	}
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		if (null == deviceId || deviceId.trim().length() == 0) {
			this.deviceId = "";
		}
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (null == name || name.trim().length() == 0) {
			this.name = "";
		}
		this.name = name;
	}
	public int getVideoPort() {
		return videoPort;
	}
	public void setVideoPort(int videoPort) {
		this.videoPort = videoPort;
	}
	public int getSoftWareUpdatePort() {
		return softWareUpdatePort;
	}
	public void setSoftWareUpdatePort(int softWareUpdatePort) {
		this.softWareUpdatePort = softWareUpdatePort;
	}
	public int getUseIPConnect() {
		return useIPConnect;
	}
	public void setUseIPConnect(int useIPConnect) {
		this.useIPConnect = useIPConnect;
	}
	public int getVideoPlayPort() {
		return videoPlayPort;
	}
	public void setVideoPlayPort(int videoPlayPort) {
		this.videoPlayPort = videoPlayPort;
	}
	public int getCtrPort() {
		return ctrPort;
	}
	public void setCtrPort(int ctrPort) {
		this.ctrPort = ctrPort;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if (null == title || title.trim().length() == 0) {
			this.title = "";
		}
		this.title = title;
	}
	public int getSmartCtrl() {
		return smartCtrl;
	}
	public void setSmartCtrl(int smartCtrl) {
		this.smartCtrl = smartCtrl;
	}
	public int getBenTian() {
		return benTian;
	}
	public void setBenTian(int benTian) {
		this.benTian = benTian;
	}
	public int getYuYang() {
		return yuYang;
	}
	public void setYuYang(int yuYang) {
		this.yuYang = yuYang;
	}
	
	public MainDeviceEntity() {
		// TODO Auto-generated constructor stub
	}
//	private String province;
//	private String city;
//	private String district;
//	private String superivsername;
//	private String producername;
//	private String exportorname;
//	private int superviserid;
//	private int producerid;
//	private int exportorid;
	public MainDeviceEntity(int id, int orderNo, String nIP, int nPort, String ip, int port, String proxyIp,
			int proxyPort, double x, double y, double zoom, String deviceId, String name, int videoPort,
			int softWareUpdatePort, int useIPConnect, int videoPlayPort, int ctrPort, String title, int smartCtrl,
			int benTian, int yuYang,String province,String city,String district,String supervisername,String producername,String exportorname,int superviserid,int producerid,int exportorid,int groupid,int siteid
			,String softWareVersion,String hardWareVersion,String provicename,String cityname,String districtname) {
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.nip = nIP;
		this.nport = nPort;
		this.ip = ip;
		this.port = port;
		this.proxyIp = proxyIp;
		this.proxyPort = proxyPort;
		this.x = x;
		this.y = y;
		this.zoom = zoom;
		this.deviceId = deviceId;
		this.name = name;
		this.videoPort = videoPort;
		this.softWareUpdatePort = softWareUpdatePort;
		this.useIPConnect = useIPConnect;
		this.videoPlayPort = videoPlayPort;
		this.ctrPort = ctrPort;
		this.title = title;
		this.smartCtrl = smartCtrl;
		this.benTian = benTian;
		this.yuYang = yuYang;
		this.province = province;
		this.city = city;
		this.district = district;
		this.superivsername = supervisername;
		this.producername =producername;
		this.exportorname = exportorname;
		this.superviserid = superviserid;
		this.producerid =producerid;
		this.exportorid = exportorid;
		this.groupid =groupid;
		this.siteid =siteid;
		this.softWareVersion =softWareVersion;
		this.hardWareVersion = hardWareVersion;
		this.provincename = provicename;
		this.cityname =cityname;
		this.districtname =districtname;
	}
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 * nt id, int orderNo, String nIP, int nPort, String ip, int port, String proxyIp,
			int proxyPort, double x, double y, double zoom, String deviceId, String name, int videoPort,
			int softWareUpdatePort, int useIPConnect, int videoPlayPort, int ctrPort, String title, int smartCtrl,
			int benTian, int yuYang
	 */
//	@Override
//	public MainDeviceEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
//		// TODO Auto-generated method stub
//		return new MainDeviceEntity(rs.getInt("id"),rs.getInt("orderNo"),rs.getString("nIp"),rs.getInt("nPort"),rs.getString("ip"),rs.getInt("port")
//				,rs.getString("proxyIp"),rs.getInt("proxyPort"),rs.getDouble("x"),rs.getDouble("y"),rs.getDouble("zoom"),rs.getString("deviceId"),rs.getString("name"),rs.getInt("videoPort"),
//				rs.getInt("softWareUpdatePort"),rs.getInt("useIPConnect"),rs.getInt("videoPlayPort"),rs.getInt("ctrPort"),rs.getString("title"),rs.getInt("smartCtrl"),
//				rs.getInt("benTian"),rs.getInt("yuYang"));
//	}
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 * 	private String province;
	private String city;
	private String district;
	private String superivsername;
	private String producername;
	private String exportorname;
	 */
	@Override
	public MainDeviceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new MainDeviceEntity(rs.getInt("id"),rs.getInt("orderNo"),rs.getString("nIp"),rs.getInt("nPort"),rs.getString("ip"),rs.getInt("port")
				,rs.getString("proxyIp"),rs.getInt("proxyPort"),rs.getDouble("x"),rs.getDouble("y"),rs.getDouble("zoom"),rs.getString("deviceId"),rs.getString("name"),rs.getInt("videoPort"),
				rs.getInt("softWareUpdatePort"),rs.getInt("useIPConnect"),rs.getInt("videoPlayPort"),rs.getInt("ctrPort"),rs.getString("title"),rs.getInt("smartCtrl"),
				rs.getInt("benTian"),rs.getInt("yuYang"),rs.getString("province"),rs.getString("city"),rs.getString("district"),rs.getString("supervisername"),rs.getString("producername"),rs.getString("exportorname"),rs.getInt("superviserid"),rs.getInt("producerid"),rs.getInt("exportorid"),rs.getInt("groupid"),rs.getInt("siteid"),rs.getString("Softwareversion"),rs.getString("HardwareVersion"),rs.getString("provincename"),rs.getString("cityname"),rs.getString("districtname"));
	}
	
	

}
