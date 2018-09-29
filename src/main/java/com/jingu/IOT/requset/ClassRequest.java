package com.jingu.IOT.requset;

import com.jingu.IOT.entity.ClassEntity;
import com.jingu.IOT.entity.PointEntity;

public class ClassRequest extends ClassEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ckuid;
	private String cksid;

	private PointEntity point;

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
	public ClassRequest() {
	}

	public PointEntity getPoint() {
		return point;
	}

	public void setPoint(PointEntity point) {
		this.point = point;
	}
}
