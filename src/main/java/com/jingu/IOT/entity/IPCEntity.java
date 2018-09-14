/**  
*   
* 项目名称：IOT  
* 类名称：IPCEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月5日 上午11:11:40  
* 修改人：jianghu  
* 修改时间：2017年9月5日 上午11:11:40  
* 修改备注： 上午11:11:40
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**

* @ClassName: IPCEntity
* @Description: TODO
* @author jianghu
* @date 2017年9月5日 上午11:11:40

*/
public class IPCEntity implements RowMapper<IPCEntity>,Comparable<Map<String, Object>>{

	private int id;
	private int s_nod =-1;
	private String deviceId;
	private String s_ip;
	private int s_port;
	private String s_username;
	private String s_password;
	private int s_stream =-1;
	private int s_online =-1;
	private int orderNo;
	private String mapingDeviceId;
	private int ipcProxyId;
	private int status;
	private int s_power =-1;
	private int ipcCtrlProxyId;
	private int streamType;
	private String name;
	private int monitorid;
	private int mchannel;
	private String mvideoname;
	private int mresolution;
	private int mvideobitrate;
	private int mvideoframeRate;
	private int mencodetype; // 编码类型
	private int mencodeefficiency;
	private int schannel;
	private String svideoname;
	private int sresolution;
	private int svideobitrate;
	private int svideoframeRate;
	private int sencodetype; // 编码类型
	private int sencodeefficiency;
	
	

	public int getMchannel() {
		return mchannel;
	}
	public void setMchannel(int mchannel) {
		this.mchannel = mchannel;
	}
	public String getMvideoname() {
		return mvideoname;
	}
	public void setMvideoname(String mvideoname) {
		if (null == mvideoname || mvideoname.trim().length() == 0) {
			this.mvideoname = "";
		}
		this.mvideoname = mvideoname;
	}
	public int getMresolution() {
		return mresolution;
	}
	public void setMresolution(int mresolution) {
		this.mresolution = mresolution;
	}
	public int getMvideobitrate() {
		return mvideobitrate;
	}
	public void setMvideobitrate(int mvideobitrate) {
		this.mvideobitrate = mvideobitrate;
	}
	public int getMvideoframeRate() {
		return mvideoframeRate;
	}
	public void setMvideoframeRate(int mvideoframeRate) {
		this.mvideoframeRate = mvideoframeRate;
	}
	public int getMencodetype() {
		return mencodetype;
	}
	public void setMencodetype(int mencodetype) {
		this.mencodetype = mencodetype;
	}
	public int getMencodeefficiency() {
		return mencodeefficiency;
	}
	public void setMencodeefficiency(int mencodeefficiency) {
		this.mencodeefficiency = mencodeefficiency;
	}
	public int getSchannel() {
		return schannel;
	}
	public void setSchannel(int schannel) {
		this.schannel = schannel;
	}
	public String getSvideoname() {
		return svideoname;
	}
	public void setSvideoname(String svideoname) {
		if (null == svideoname || svideoname.trim().length() == 0) {
			this.svideoname = "";
		}
		this.svideoname = svideoname;
	}
	public int getSresolution() {
		return sresolution;
	}
	public void setSresolution(int sresolution) {
		this.sresolution = sresolution;
	}
	public int getSvideobitrate() {
		return svideobitrate;
	}
	public void setSvideobitrate(int svideobitrate) {
		this.svideobitrate = svideobitrate;
	}
	public int getSvideoframeRate() {
		return svideoframeRate;
	}
	public void setSvideoframeRate(int svideoframeRate) {
		this.svideoframeRate = svideoframeRate;
	}
	public int getSencodetype() {
		return sencodetype;
	}
	public void setSencodetype(int sencodetype) {
		this.sencodetype = sencodetype;
	}
	public int getSencodeefficiency() {
		return sencodeefficiency;
	}
	public void setSencodeefficiency(int sencodeefficiency) {
		this.sencodeefficiency = sencodeefficiency;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getS_nod() {
		return s_nod;
	}
	public void setS_nod(int s_nod) {
		this.s_nod = s_nod;
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
	public String getS_ip() {
		return s_ip;
	}
	public void setS_ip(String s_ip) {
		if (null == s_ip || s_ip.trim().length() == 0) {
			this.s_ip = "";
		}
		this.s_ip = s_ip;
	}
	public int getS_port() {
		return s_port;
	}
	public void setS_port(int s_port) {
		this.s_port = s_port;
	}
	public String getS_username() {
		return s_username;
	}
	public void setS_username(String s_username) {
		if (null == s_username || s_username.trim().length() == 0) {
			this.s_username = "";
		}
		this.s_username = s_username;
	}
	public String getS_password() {
		return s_password;
	}
	public void setS_password(String s_password) {
		if (null == s_password || s_password.trim().length() == 0) {
			this.s_password = "";
		}
		this.s_password = s_password;
	}
	public int getS_stream() {
		return s_stream;
	}
	public void setS_stream(int s_stream) {
		this.s_stream = s_stream;
	}
	public int getS_online() {
		return s_online;
	}
	public void setS_online(int s_online) {
		this.s_online = s_online;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getMapingDeviceId() {
		return mapingDeviceId;
	}
	public void setMapingDeviceId(String mapingDeviceId) {
		if (null == mapingDeviceId || mapingDeviceId.trim().length() == 0) {
			this.mapingDeviceId = "";
		}
		this.mapingDeviceId = mapingDeviceId;
	}
	public int getIpcProxyId() {
		return ipcProxyId;
	}
	public void setIpcProxyId(int ipcProxyId) {
		this.ipcProxyId = ipcProxyId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {

		this.status = status;
	}
	public int getS_power() {
		return s_power;
	}
	public void setS_power(int s_power) {
		this.s_power = s_power;
	}
	public int getIpcCtrlProxyId() {
		return ipcCtrlProxyId;
	}
	public void setIpcCtrlProxyId(int ipcCtrlProxyId) {
		this.ipcCtrlProxyId = ipcCtrlProxyId;
	}
	public int getStreamType() {
		return streamType;
	}
	public void setStreamType(int streamType) {
		this.streamType = streamType;
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
	public int getMonitorid() {
		return monitorid;
	}
	public void setMonitorid(int monitorid) {
		this.monitorid = monitorid;
	}
	
	
	public IPCEntity(int id, int s_nod, String deviceId, String s_ip, int s_port, String s_username, String s_password,
			int s_stream, int s_online, int orderNo, String mapingDeviceId, int ipcProxyId, int status, int s_power,
			int ipcCtrlProxyId, int streamType, String name, int monitorid,int mchannel,String mvideoname,int mresolution,int mvideobitrate,int mvideoframeRate,int mencodetype ,int mencodeefficiency,int schannel,String svideoname,int sresolution,int svideobitrate,int svideoframeRate,int sencodetype ,int sencodeefficiency ) {
		super();
		this.id = id;
		this.s_nod = s_nod;
		this.deviceId = deviceId;
		this.s_ip = s_ip;
		this.s_port = s_port;
		this.s_username = s_username;
		this.s_password = s_password;
		this.s_stream = s_stream;
		this.s_online = s_online;
		this.orderNo = orderNo;
		this.mapingDeviceId = mapingDeviceId;
		this.ipcProxyId = ipcProxyId;
		this.status = status;
		this.s_power = s_power;
		this.ipcCtrlProxyId = ipcCtrlProxyId;
		this.streamType = streamType;
		this.name = name;
		this.monitorid = monitorid;
		this.mchannel =mchannel;
		this.mvideoname =mvideoname;
		this.mresolution = mresolution;
		this.mvideobitrate = mvideobitrate;
		this.mvideoframeRate =mvideoframeRate;
		this.mencodetype = mencodetype;
		this.mencodeefficiency =mencodeefficiency;
		this.schannel =schannel;
		this.svideoname =svideoname;
		this.sresolution = sresolution;
		this.svideobitrate = svideobitrate;
		this.svideoframeRate =svideoframeRate;
		this.sencodetype = sencodetype;
		this.sencodeefficiency =sencodeefficiency;

	}
	
	
	public IPCEntity() {
	}
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 * 	private int id;
	private int s_nod;
	private String deviceId;
	private String s_ip;
	private int s_port;
	private String s_username;
	private String s_password;
	private int s_stream;
	private int s_online;
	private int orderNo;
	private String IPCid;
	private int ipcProxyId;
	private int status;
	private int s_power;
	private int ipcCtrlProxyId;
	private int streamType;
	private String name;
	private int monitorid;
	 */
//	@Override
//	public IPCEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
//		// TODO Auto-generated method stub
//		return new IPCEntity(rs.getInt("id"),rs.getInt("s_nod"),rs.getString("deviceId"),rs.getString("s_ip"),
//				rs.getInt("s_port"),rs.getString("s_username"),rs.getString("s_password"),
//				rs.getInt("s_stream"),rs.getInt("s_online"),rs.getInt("orderNo"),rs.getString("mapingDeviceId"),
//				rs.getInt("ipcProxyId"),rs.getInt("status"),rs.getInt("s_power"),rs.getInt("ipcCtrlProxyId"),rs.getInt("streamType"),rs.getString("name"),rs.getInt("monitorid"),
//				rs.getInt("channel"),rs.getString("videoname"),rs.getInt("resolution"),rs.getInt("videobitrate"),rs.getInt("videoframeRate"),rs.getInt("encodetype"),rs.getInt("encodeefficiency"));
//	}
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public IPCEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new IPCEntity(rs.getInt("id"),rs.getInt("s_nod"),rs.getString("deviceId"),rs.getString("s_ip"),
				rs.getInt("s_port"),rs.getString("s_username"),rs.getString("s_password"),
				rs.getInt("s_stream"),rs.getInt("s_online"),rs.getInt("orderNo"),rs.getString("mapingDeviceId"),
				rs.getInt("ipcProxyId"),rs.getInt("stauts"),rs.getInt("s_power"),rs.getInt("ipcCtrlProxyId"),rs.getInt("streamType"),rs.getString("name"),rs.getInt("monitorid"),
				rs.getInt("mchannel"),rs.getString("mvideoname"),rs.getInt("mresolution"),rs.getInt("mvideobitrate"),rs.getInt("mvideoframeRate"),rs.getInt("mencodetype"),rs.getInt("mencodeefficiency"),
				rs.getInt("schannel"),rs.getString("svideoname"),rs.getInt("sresolution"),rs.getInt("svideobitrate"),rs.getInt("svideoframeRate"),rs.getInt("sencodetype"),rs.getInt("sencodeefficiency"));
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 	private int mchannel;
	private String mvideoname;
	private int mresolution;
	private int mvideobitrate;
	private int mvideoframeRate;
	private int mencodetype; // 编码类型
	private int mencodeefficiency;
	private int schannel;
	private String svideoname;
	private int sresolution;
	private int svideobitrate;
	private int svideoframeRate;
	private int sencodetype; // 编码类型
	private int sencodeefficiency;
	 */
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Map<String, Object> o) {
		// TODO Auto-generated method stub
		if(		mchannel ==(int)o.get("mchannel") &&
				mvideoname.equals(o.get("mvideoname")) &&
				mresolution ==(int)o.get("mresolution") &&
				mvideobitrate ==(int)o.get("mvideobitrate") &&
				mvideoframeRate ==(int)o.get("mvideoframeRate") &&
				mencodetype ==(int)o.get("mencodetype") &&
				mencodeefficiency ==(int)o.get("mencodeefficiency") &&
				schannel ==(int)o.get("schannel") &&
				svideoname.equals(o.get("svideoname")) &&
				sresolution ==(int)o.get("sresolution") &&
				svideobitrate ==(int)o.get("svideobitrate") &&
				svideoframeRate ==(int)o.get("svideoframeRate") &&
				sencodetype ==(int)o.get("sencodetype") &&
				sencodeefficiency ==(int)o.get("sencodeefficiency")
				){
			return 0;
		}
		return 1;
	}
	
}
