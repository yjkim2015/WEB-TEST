package com.web.test.common.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CpuVo {
	private int	 	PID;
	private int 	YYMMDD;
	private int 	TIMESTAMP;
	private int 	NE_ID;
	private int 	SYSTYPE;
	private int 	VM_TYPE;
	private int 	VM_INDEX;
	private String  VM_SIDE;
	private String  ACT_STANDBY;
	private float 	IDLE;
	private float 	SYS;
	private float 	USR;
	private float 	IO_WAIT;
	private float 	USEDRATE;
	private int		MIN_CPU;
	private int 	MAX_CPU;
	private int 	AVG_CPU;
	Map<String,String> dataColumnNameList;
	
	public int getPID() {
		return PID;
	}
	
	public void setPID(int pID) {
		PID = pID;
	}
	
	public int getYYMMDD() {
		return YYMMDD;
	}
	
	public void setYYMMDD(int yYMMDD) {
		YYMMDD = yYMMDD;
	}
	
	public int getTIMESTAMP() {
		return TIMESTAMP;
	}
	
	public void setTIMESTAMP(int tIMESTAMP) {
		TIMESTAMP = tIMESTAMP;
	}
	
	public int getNE_ID() {
		return NE_ID;
	}
	
	public void setNE_ID(int nE_ID) {
		NE_ID = nE_ID;
	}
	
	public int getSYSTYPE() {
		return SYSTYPE;
	}
	
	public void setSYSTYPE(int sYSTYPE) {
		SYSTYPE = sYSTYPE;
	}
	
	public int getVM_TYPE() {
		return VM_TYPE;
	}
	
	public void setVM_TYPE(int vM_TYPE) {
		VM_TYPE = vM_TYPE;
	}
	
	public int getVM_INDEX() {
		return VM_INDEX;
	}
	
	public void setVM_INDEX(int vM_INDEX) {
		VM_INDEX = vM_INDEX;
	}
	
	public String getVM_SIDE() {
		return VM_SIDE;
	}
	
	public void setVM_SIDE(String vM_SIDE) {
		VM_SIDE = vM_SIDE;
	}
	
	public String getACT_STANDBY() {
		return ACT_STANDBY;
	}
	
	public void setACT_STANDBY(String aCT_STANDBY) {
		ACT_STANDBY = aCT_STANDBY;
	}
	
	public float getIDLE() {
		return IDLE;
	}
	
	public void setIDLE(float iDLE) {
		IDLE = iDLE;
	}
	
	public float getSYS() {
		return SYS;
	}
	
	public void setSYS(float sYS) {
		SYS = sYS;
	}
	
	public float getUSR() {
		return USR;
	}
	public void setUSR(float uSR) {
		USR = uSR;
	}
	public float getIO_WAIT() {
		return IO_WAIT;
	}
	public void setIO_WAIT(float iO_WAIT) {
		IO_WAIT = iO_WAIT;
	}
	public float getUSEDRATE() {
		return USEDRATE;
	}
	public void setUSEDRATE(float uSEDRATE) {
		USEDRATE = uSEDRATE;
	}
	public int getMIN_CPU() {
		return MIN_CPU;
	}
	public void setMIN_CPU(int mIN_CPU) {
		MIN_CPU = mIN_CPU;
	}
	public int getMAX_CPU() {
		return MAX_CPU;
	}
	public void setMAX_CPU(int mAX_CPU) {
		MAX_CPU = mAX_CPU;
	}
	public int getAVG_CPU() {
		return AVG_CPU;
	}
	public void setAVG_CPU(int aVG_CPU) {
		AVG_CPU = aVG_CPU;
	}
	
	

	public Map<String,String> getDataColumnNameList() {
		Map<String,String> dataColumnNameList = new HashMap<>();
		
		dataColumnNameList.put("PID","");
		dataColumnNameList.put("YYMMDD","");
		dataColumnNameList.put("TIMESTAMP","");
		dataColumnNameList.put("NE_ID","");
		dataColumnNameList.put("SYSTYPE","");
		dataColumnNameList.put("VM_TYPE","");
		dataColumnNameList.put("VM_INDEX","");
		dataColumnNameList.put("ACT_STANDBY","");
		dataColumnNameList.put("IDLE","AVG");
		dataColumnNameList.put("SYS","AVG");
		dataColumnNameList.put("USR","AVG");
		dataColumnNameList.put("IO_WAIT","");
		dataColumnNameList.put("USEDRATE","AVG");
		dataColumnNameList.put("MIN_CPU","AVG");
		dataColumnNameList.put("AVG_CPU","AVG");


		return dataColumnNameList;
	}

	@Override
	public String toString() {
		return "CpuVo [PID=" + PID + ", YYMMDD=" + YYMMDD + ", TIMESTAMP=" + TIMESTAMP + ", NE_ID=" + NE_ID
				+ ", SYSTYPE=" + SYSTYPE + ", VM_TYPE=" + VM_TYPE + ", VM_INDEX=" + VM_INDEX + ", VM_SIDE=" + VM_SIDE
				+ ", ACT_STANDBY=" + ACT_STANDBY + ", IDLE=" + IDLE + ", SYS=" + SYS + ", USR=" + USR + ", IO_WAIT="
				+ IO_WAIT + ", USEDRATE=" + USEDRATE + ", MIN_CPU=" + MIN_CPU + ", MAX_CPU=" + MAX_CPU + ", AVG_CPU="
				+ AVG_CPU + "]";
	}
	
}
