/**
 * 项目名称：IOT
 * 类名称：SettingDao
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年8月28日 下午6:50:56
 * 修改人：jianghu
 * 修改时间：2017年8月28日 下午6:50:56
 * 修改备注： 下午6:50:56
 *
 * @version
 */
package com.jingu.IOT.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jingu.IOT.entity.ControlEntity;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.VAREntity;

/**
 * @param <E>
 * @author jianghu
 * @ClassName: SettingDao
 * @Description: TODO
 * @date 2017年8月28日 下午6:50:56
 */
@Component
public class SettingDao {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public String getSettings(VAREntity var) {
        String sql = "select ts_var_configstr from t_setting where ts_deviceId=? limit 1";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, var.getDeviceId());
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public int updateSettings(VAREntity var) {
        //update t_setting set ts_var_configstr =?, where ts_deviceId = ?
        List<Object> list = new ArrayList<>();
        if (var != null) {
            String sql = "update t_setting set ts_deviceId = ?";
            list.add(var.getDeviceId());
            if (var.getConfigStr() != null && var.getConfigStr().trim().length() > 0) {
                sql += " , ts_var_configstr =? ";
                list.add(var.getConfigStr());
            }
            if (var.getIp() != null && var.getIp().trim().length() > 0) {
                sql += " , ts_var_ip =? ";
                list.add(var.getIp());
            }
            if (var.getPort() > 0) {
                sql += " , ts_var_port =? ";
                list.add(var.getPort());
            }
            if (var.getState() > 0) {
                sql += " , ts_var_state =? ";
                list.add(var.getState());
            }
            if (list.size() == 1) {
                return 1;
            }
            sql += " where ts_deviceId =?";
            list.add(var.getDeviceId());
            return jdbcTemplate.update(sql, list.toArray());
        }
        return 0;
    }

    public int deleteSettings(VAREntity var) {
        String sql = "delete from  t_setting  where ts_deviceId = ?";
        return jdbcTemplate.update(sql, var.getDeviceId());
    }

    public int addSettings(VAREntity var) {
        String sql = null;
        var.setState(1);
        String conf = getSettings(var);
        if (conf != null && conf.length() > 0) {
            return updateSettings(var);
        }
        sql = "insert into  t_setting (ts_deviceId,ts_var_ip,ts_var_configstr,ts_var_time,ts_var_state,ts_var_port) value (?,?,?,UNIX_TIMESTAMP(),?,?)";
        return jdbcTemplate.update(sql, var.getDeviceId(), var.getIp(), var.getConfigStr(), var.getState(), var.getPort());
    }
    //注意逻辑
    //还有一个ip表要写

    //创建表上
    public int creatTable(String name) {
        String table = "t_vartriver_" + name;
        String sql = " create table " + table + " like t_vartriver_first";
        System.out.println(sql);
        jdbcTemplate.execute(sql);
        return 0;
    }

    // 复制规格文件
    public int copyParaseFile(String name) {
        String sql = " insert into t_vartriver_parsedata (id,channel,deviceId,name,beginPosition,len,dataType,decimalFormat,fieldName,formula,unit,orderIndex,listDisplay,statDisplay,lowerLimit,upperLimit,diffPercent,chartID,chartDisplay,chartOrderIndex) select id,channel,?,name,beginPosition,len,dataType,decimalFormat,fieldName,formula,unit,orderIndex,listDisplay,statDisplay,lowerLimit,upperLimit,diffPercent,chartID,chartDisplay,chartOrderIndex from t_vartriver_parsedata where deviceId = '10.00.21.74'";
        return jdbcTemplate.update(sql, name);
    }

    public Map<String, Object> getPointSetting(PointEntity pe) {
        String sql = "select * from t_point where tp_id = ?";
        return jdbcTemplate.queryForMap(sql, pe.getTp_id());
    }

    public int addControlSetting(ControlEntity ce) {
        String sql = "insert into control (" +
                "ctrl_name," +
                "ctrl_nickname," +
                "ctrl_deviceId," +
                "ctrl_mapingdeviceId," +
                "ctrl_min,ctrl_max," +
                "ctrl_channel," +
                "ctrl_type," +
                "ctrl_temperature," +
                "ctrl_water," +
                "ctrl_raise_groupId," +
                "ctrl_raise_switchId," +
                "ctrl_down_groupId," +
                "ctrl_down_switchId," +
                "ctrl_count," +
                "ctrl_picturetype," +
                "ctrl_picturetitle) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, ce.getCtrl_name(), ce.getCtrl_nickname(), ce.getCtrl_deviceId(), ce.getCtrl_mapingdeviceId(), ce.getCtrl_min(), ce.getCtrl_max(), ce.getCtrl_channel(), ce.getCtrl_type(), ce.getCtrl_temperature(), ce.getCtrl_water(), ce.getCtrl_raise_groupId(), ce.getCtrl_raise_switchId(), ce.getCtrl_down_groupId(), ce.getCtrl_down_switchId(), ce.getCtrl_count(), ce.getCtrl_picturetype(), ce.getCtrl_picturetitle());
    }

    public int deleteControlSetting(ControlEntity ce) {
        String sql = "delete from control where ctrl_id=?";
        return jdbcTemplate.update(sql, ce.getCtrl_id());
    }

    public int updateControlSetting(ControlEntity ce) {
        String sql = "update control set ctrl_id =?";
        List<Object> list = new ArrayList<Object>();
        list.add(ce.getCtrl_id());
        if (ce.getCtrl_name() != null && ce.getCtrl_name().trim().length() > 0) {
            sql += " ,ctrl_name =?";
            list.add(ce.getCtrl_name());
        }
        if (ce.getCtrl_deviceId() != null && ce.getCtrl_deviceId().trim().length() > 0) {
            sql += " ,ctrl_deviceId =?";
            list.add(ce.getCtrl_deviceId());
        }
        if (ce.getCtrl_mapingdeviceId() != null && ce.getCtrl_mapingdeviceId().trim().length() > 0) {
            sql += " ,ctrl_mapingdeviceId =?";
            list.add(ce.getCtrl_mapingdeviceId());
        }
        if (ce.getCtrl_max() != null && ce.getCtrl_max().trim().length() > 0) {
            sql += " ,ctrl_max =?";
            list.add(ce.getCtrl_max());
        }
        if (ce.getCtrl_nickname() != null && ce.getCtrl_nickname().trim().length() > 0) {
            sql += " ,ctrl_nickname =?";
            list.add(ce.getCtrl_nickname());
        }
        if (ce.getCtrl_picturetitle() != null && ce.getCtrl_picturetitle().trim().length() > 0) {
            sql += " ,ctrl_picturetitle =?";
            list.add(ce.getCtrl_picturetitle());
        }
        if (ce.getCtrl_temperature() != null && ce.getCtrl_temperature().trim().length() > 0) {
            sql += " ,ctrl_temperature =?";
            list.add(ce.getCtrl_temperature());
        }
        if (ce.getCtrl_water() != null && ce.getCtrl_water().trim().length() > 0) {
            sql += " ,ctrl_water =?";
            list.add(ce.getCtrl_water());
        }
        if (ce.getCtrl_down_groupId() > 0) {
            sql += " , ctrl_down_groupId =?";
            list.add(ce.getCtrl_down_groupId());
        }
        if (ce.getCtrl_picturetype() > 0) {
            sql += " , ctrl_picturetype =?";
            list.add(ce.getCtrl_picturetype());
        }
        if (ce.getCtrl_down_switchId() > 0) {
            sql += " ,ctrl_down_switchId =?";
            list.add(ce.getCtrl_down_switchId());
        }
        if (ce.getCtrl_raise_groupId() > 0) {
            sql += " , ctrl_raise_groupId =?";
            list.add(ce.getCtrl_raise_groupId());
        }
        if (ce.getCtrl_raise_switchId() > 0) {
            sql += " , ctrl_raise_switchId =?";
            list.add(ce.getCtrl_raise_switchId());
        }
        if (ce.getCtrl_type() > 0) {
            sql += " , ctrl_type =?";
            list.add(ce.getCtrl_type());
        }
        if (ce.getState_type() > -1) {
            sql += " , state_type =?";
            list.add(ce.getState_type());
        }
        if (ce.getS_state() > 0) {
            sql += " , s_state =?";
            list.add(ce.getS_state());
        }
        if (list.size() == 1) {
            return 0;
        }
        sql += " where ctrl_id =?";
        list.add(ce.getCtrl_id());
        return jdbcTemplate.update(sql, list.toArray());
    }


    public List<Map<String, Object>> listControlSetting(ControlEntity ce) {
        String sql = "select * from control where 1=1 ";
        List<Object> list = new ArrayList<Object>();
        if (ce.getCtrl_name() != null && ce.getCtrl_name().trim().length() > 0) {
            sql += " and ctrl_name =?";
            list.add(ce.getCtrl_name());
        }
        if (ce.getCtrl_deviceId() != null && ce.getCtrl_deviceId().trim().length() > 0) {
            sql += " and ctrl_deviceId =?";
            list.add(ce.getCtrl_deviceId());
        }
        if (ce.getCtrl_mapingdeviceId() != null && ce.getCtrl_mapingdeviceId().trim().length() > 0) {
            sql += " and ctrl_mapingdeviceId =?";
            list.add(ce.getCtrl_mapingdeviceId());
        }
        if (ce.getCtrl_max() != null && ce.getCtrl_max().trim().length() > 0) {
            sql += " and ctrl_max =?";
            list.add(ce.getCtrl_max());
        }
        if (ce.getCtrl_nickname() != null && ce.getCtrl_nickname().trim().length() > 0) {
            sql += " and ctrl_nickname =?";
            list.add(ce.getCtrl_nickname());
        }
        if (ce.getCtrl_picturetitle() != null && ce.getCtrl_picturetitle().trim().length() > 0) {
            sql += " and ctrl_picturetitle =?";
            list.add(ce.getCtrl_picturetitle());
        }
        if (ce.getCtrl_temperature() != null && ce.getCtrl_temperature().trim().length() > 0) {
            sql += " and ctrl_temperature =?";
            list.add(ce.getCtrl_temperature());
        }
        if (ce.getCtrl_water() != null && ce.getCtrl_water().trim().length() > 0) {
            sql += " and ctrl_water =?";
            list.add(ce.getCtrl_water());
        }
        if (ce.getCtrl_down_groupId() > 0) {
            sql += " and  ctrl_down_groupId =?";
            list.add(ce.getCtrl_down_groupId());
        }
        if (ce.getCtrl_down_switchId() > 0) {
            sql += " and ctrl_down_switchId =?";
            list.add(ce.getCtrl_down_switchId());
        }
        if (ce.getCtrl_raise_groupId() > 0) {
            sql += " and  ctrl_raise_groupId =?";
            list.add(ce.getCtrl_raise_groupId());
        }
        if (ce.getCtrl_raise_switchId() > 0) {
            sql += " and  ctrl_raise_switchId =?";
            list.add(ce.getCtrl_raise_switchId());
        }
        if (ce.getCtrl_type() > 0) {
            sql += " and  ctrl_type =?";
            list.add(ce.getCtrl_type());
        }
        if (ce.getCtrl_id() > 0) {
            sql += " and ctrl_id =?";
            list.add(ce.getCtrl_id());
        }
        return jdbcTemplate.queryForList(sql, list.toArray());
    }


    public List<Map<String, Object>> listControlDevByDeviceId(PointEntity pe) {
        String sql = "select * from control where 1=1";
        List<Object> list = new ArrayList<>();
        if (pe.getDeviceId() != null && pe.getDeviceId().trim().length() > 0) {
            sql += " and ctrl_deviceId =? ";
            list.add(pe.getDeviceId());
        }
        return jdbcTemplate.queryForList(sql, list.toArray());
    }

    /**
     * 2018年1月18日
     * jianghu
     *
     * @param ctrl_id
     * @return TODO
     */
    public Map<String, Object> getControlSetting(int ctrl_id) {
        // TODO Auto-generated method stub
        String sql = "select count(*) from control where ctrl_id = ?";
        Integer queryForObject = jdbcTemplate.queryForObject(sql, Integer.class, ctrl_id);
        if (queryForObject == 0) {
            return null;
        }
        sql = "select * from control where ctrl_id = ? ";
        return jdbcTemplate.queryForMap(sql, ctrl_id);
    }


}
