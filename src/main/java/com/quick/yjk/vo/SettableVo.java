package com.quick.yjk.vo;

import com.quick.yjk.common.constants.ActionType;

public class SettableVo {
	/**
	 * 설정 타입
	 */
	protected ActionType action;

	public ActionType getAction() {
		return action;
	}

	public void setAction(final ActionType action) {
		this.action = action;
	}
}
