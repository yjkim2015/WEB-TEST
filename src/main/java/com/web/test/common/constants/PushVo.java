package com.web.test.common.constants;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class PushVo {
	/**
	 * Push 타입
	 */

	
	/**
	 * payload
	 */
	private Object payload;

	private String query;
	
	private String timeType;
	
	
	public Object getPayload() {
		return payload;
	}
	
	public void setPayload(final Object payload) {
		this.payload = payload;
	}
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
