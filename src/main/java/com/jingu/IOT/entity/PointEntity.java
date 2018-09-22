/**
 * 项目名称：IOT
 * 类名称：PointEntity
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年8月28日 下午5:49:29
 * 修改人：jianghu
 * 修改时间：2017年8月28日 下午5:49:29
 * 修改备注： 下午5:49:29
 *
 * @version
 */
package com.jingu.IOT.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**

 * @ClassName: PointEntity
 * @Description: TODO
 * @author jianghu
 * @date 2017年8月28日 下午5:49:29

 */
public class PointEntity implements RowMapper<PointEntity>, Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */

    private static final long serialVersionUID = 1L;
    private int tp_id;
    private String tp_name;
    private int tp_type;
    private int tp_pid = -1;
    private int tp_state;
    private int tp_order;
    private String tp_time;
    private long uid;
    private String ip;
    private int port;
    private String deviceId;
    private String role;
    private double zoom;
    private int index;
    private int u_type;


    private String ids;

    private double x, y;
    private int listDisplay;
    private int statDisplay;
    private int chartDisplay;


    public int getU_type() {
        return u_type;
    }

    public void setU_type(int u_type) {
        this.u_type = u_type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        if (null == ids || ids.trim().length() == 0) {
            this.ids = "";
        }
        this.ids = ids;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (null == role || role.trim().length() == 0) {
            this.role = "";
        }
        this.role = role;
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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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

    public String getTp_time() {
        return tp_time;
    }

    public void setTp_time(String tp_time) {
        if (null == tp_time || tp_time.trim().length() == 0) {
            this.tp_time = "";
        }
        this.tp_time = tp_time;
    }

    public int getTp_id() {
        return tp_id;
    }

    public void setTp_id(int tp_id) {
        this.tp_id = tp_id;
    }

    public String getTp_name() {
        return tp_name;
    }

    public void setTp_name(String tp_name) {
        if (null == tp_name || tp_name.trim().length() == 0) {
            this.tp_name = "";
        }
        this.tp_name = tp_name;
    }

    public int getTp_type() {
        return tp_type;
    }

    public void setTp_type(int tp_type) {
        this.tp_type = tp_type;
    }

    public int getTp_pid() {
        return tp_pid;
    }

    public void setTp_pid(int tp_pid) {

        this.tp_pid = tp_pid;
    }

    public int getTp_state() {
        return tp_state;
    }

    public void setTp_state(int tp_state) {
        this.tp_state = tp_state;
    }

    public int getTp_order() {
        return tp_order;
    }

    public void setTp_order(int tp_order) {
        this.tp_order = tp_order;
    }


    public PointEntity(int tp_id, String tp_name, int tp_type, int tp_pid, int tp_state, int tp_order, String tp_time,
                       long uid, String ip, int port, String deviceId, String t_role, double x, double y, double zoom) {
        super();
        this.tp_id = tp_id;
        this.tp_name = tp_name;
        this.tp_type = tp_type;
        this.tp_pid = tp_pid;
        this.tp_state = tp_state;
        this.tp_order = tp_order;
        this.tp_time = tp_time;
        this.uid = uid;
        this.ip = ip;
        this.port = port;
        this.deviceId = deviceId;
        this.role = t_role;
        this.x = x;
        this.y = y;
        this.zoom = zoom;


    }


    public PointEntity() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
     */
//	@Override
//	public PointEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
//		// TODO Auto-generated method stub
//		if(!rs.next()){
//			return null;
//		}
//		return new PointEntity(rs.getInt("tp_id"),rs.getString("tp_name"),rs.getInt("tp_type"),rs.getInt("tp_pid"),rs.getInt("tp_state"),rs.getInt("tp_order"),rs.getString("tp_time"),rs.getLong("uid"),rs.getString("ip"),rs.getInt("port"),rs.getString("deviceId"));
//	}
    /* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
    @Override
    public PointEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        return new PointEntity(rs.getInt("tp_id"), rs.getString("tp_name"), rs.getInt("tp_type"), rs.getInt("tp_pid"), rs.getInt("tp_state"), rs.getInt("tp_order"), rs.getString("tp_time"), rs.getLong("uid"), rs.getString("ip"), rs.getInt("port"), rs.getString("deviceId"), rs.getString("t_role"), rs.getDouble("x"), rs.getDouble("y"), rs.getDouble("zoom"));
    }

    public int getListDisplay() {
        return listDisplay;
    }

    public void setListDisplay(int listDisplay) {
        this.listDisplay = listDisplay;
    }

    public int getStatDisplay() {
        return statDisplay;
    }

    public void setStatDisplay(int statDisplay) {
        this.statDisplay = statDisplay;
    }

    public int getChartDisplay() {
        return chartDisplay;
    }

    public void setChartDisplay(int chartDisplay) {
        this.chartDisplay = chartDisplay;
    }
}
