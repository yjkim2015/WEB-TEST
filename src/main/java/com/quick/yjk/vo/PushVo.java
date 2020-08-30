package com.quick.yjk.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.quick.yjk.common.constants.PushType;

/**
 * Push Vo
 * @author 배수현
 *
 */
public class PushVo implements Serializable {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1199284752720924292L;

	/**
	 * Push 타입
	 */
	private PushType type;
	
	/**
	 * payload
	 */
	private Object payload;
	
	public PushType getType() {
		return type;
	}
	
	public void setType(final PushType type) {
		this.type = type;
	}
	
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
