package com.web.test.common.constants;

public class PcInfoVo {
	private double 	cpuUsedrate;
	private String 	yymmdd;
	private double 	memTotal;
	private double	memFree;
	private double 	jvmUsedrate;
	private double 	jvmMax;
	
	public double getCpuUsedrate() {
		return cpuUsedrate;
	}
	
	public void setCpuUsedrate(double cpuUsedrate) {
		this.cpuUsedrate = cpuUsedrate;
	}
	
	public String getYymmdd() {
		return yymmdd;
	}
	
	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}
	
	public double getMemTotal() {
		return memTotal;
	}
	
	public void setMemTotal(double memTotal) {
		this.memTotal = memTotal;
	}
	
	public double getMemFree() {
		return memFree;
	}
	
	public void setMemFree(double memFree) {
		this.memFree = memFree;
	}
	
	public double getJvmUsedrate() {
		return jvmUsedrate;
	}
	
	public void setJvmUsedrate(double jvmUsedrate) {
		this.jvmUsedrate = jvmUsedrate;
	}
	
	public double getJvmMax() {
		return jvmMax;
	}
	
	public void setJvmMax(double jvmMax) {
		this.jvmMax = jvmMax;
	}
	
	@Override
	public String toString() {
		return "PcInfoVo [cpuUsedrate=" + cpuUsedrate + ", yymmdd=" + yymmdd + ", memTotal=" + memTotal + ", memFree="
				+ memFree + ", jvmUsedrate=" + jvmUsedrate + ", jvmMax=" + jvmMax + "]";
	}
	
}
