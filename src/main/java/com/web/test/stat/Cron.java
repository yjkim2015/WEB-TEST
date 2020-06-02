package com.web.test.stat;

import java.util.ArrayList;
import java.util.List;

public enum Cron {
	DEFAULT("", "", ""),
	PT_ST_1MIN_CPU("1","CPU","com.web.test.common.constants.CpuVo"),
	PT_ST_1MIN_MEMORY("2","MEMORY","com.web.test.common.constants.MemoryVo"),
	PT_ST_1MIN_DISK("3","DISK","com.web.test.common.constants.DiskVo");
	

	private String pmItemOid;
	private String pmItemName;
	private String unitClassName;

	private Cron(String pmItemOid, String pmItemName, String unitClassName) {
		this.pmItemOid = pmItemOid;
		this.pmItemName = pmItemName;
		this.unitClassName = unitClassName;
	}
	
	public static Cron getValue(String pmItemOid) {

		for(Cron enumObj : Cron.values()) {
			if(enumObj.getPmItemOid().equals(pmItemOid)) {
				return enumObj;
			}
		}
		return DEFAULT;
	}
	
	public static List<Cron> getCompressUnitEnumList() {
		List<Cron> enumList = new ArrayList<Cron>();
		for(Cron enumObj : Cron.values()) {

			if(enumObj.getPmItemOid().equals(Cron.DEFAULT.getPmItemOid()))
			{
				continue;
			}
			enumList.add(enumObj);
		}
		return enumList;
	}
	
	
	public String getPmItemOid() {
		return pmItemOid;
	}

	public void setPmItemOid(String pmItemOid) {
		this.pmItemOid = pmItemOid;
	}

	public String getPmItemName() {
		return pmItemName;
	}

	public void setPmItemName(String pmItemName) {
		this.pmItemName = pmItemName;
	}

	public String getUnitClassName() {
		return unitClassName;
	}

	public void setUnitClassName(String unitClassName) {
		this.unitClassName = unitClassName;
	}
}
