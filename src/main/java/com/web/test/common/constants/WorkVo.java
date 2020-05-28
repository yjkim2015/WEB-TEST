package com.web.test.common.constants;

public class WorkVo {
	private int seqNo;
	private String workDay;
	private String startTime;
	private String endTime;
	private String workContent;
	private String workMemo;
	
	public int getSeqNo() {
		return seqNo;
	}
	
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	
	public String getWorkDay() {
		return workDay;
	}
	
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getWorkContent() {
		return workContent;
	}
	
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	
	public String getWorkMemo() {
		return workMemo;
	}
	
	public void setWorkMemo(String workMemo) {
		this.workMemo = workMemo;
	}

	@Override
	public String toString() {
		return "WorkVo [seqNo=" + seqNo + ", workDay=" + workDay + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", workContent=" + workContent + ", workMemo=" + workMemo + "]";
	}
	
}
