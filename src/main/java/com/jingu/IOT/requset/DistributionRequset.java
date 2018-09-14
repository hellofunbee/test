/**  
*   
* 项目名称：IOT  
* 类名称：DistributionRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月11日 下午6:56:43  
* 修改人：jianghu  
* 修改时间：2017年10月11日 下午6:56:43  
* 修改备注： 下午6:56:43
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import java.util.List;

import com.jingu.IOT.entity.DistributionEntity;

/**

* @ClassName: DistributionRequset
* @Description: TODO
* @author jianghu
* @date 2017年10月11日 下午6:56:43

*/
public class DistributionRequset extends DistributionEntity {

	private String ckuid;
	private String cksid;
//	private List<DistributionEntity> distributionEntity;
	public String getCkuid() {
		return ckuid;
	}
	public void setCkuid(String ckuid) {
		if (null == ckuid || ckuid.trim().length() == 0) {
			this.ckuid = "";
		}
		this.ckuid = ckuid;
	}
	public String getCksid() {
		return cksid;
	}
	public void setCksid(String cksid) {
		if (null == cksid || cksid.trim().length() == 0) {
			this.cksid = "";
		}
		this.cksid = cksid;
	}
//	public List<DistributionEntity> getDistributionEntity() {
//		return distributionEntity;
//	}
//	public void setDistributionEntity(List<DistributionEntity> distributionEntity) {
//		this.distributionEntity = distributionEntity;
//	}
	
	
}
