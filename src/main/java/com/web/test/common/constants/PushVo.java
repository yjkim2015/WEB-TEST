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

	
	public Object getPayload() {
		return payload;
	}
	
	public void setPayload(final Object payload) {
		this.payload = payload;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
