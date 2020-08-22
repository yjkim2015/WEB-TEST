package com.quick.yjk.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * HTTP 요청에 대한 결과 Vo
 * @author 배수현
 *
 */
public class ResultVo {
	/**
	 * HTTP 상태 코드
	 */
	private HttpStatus status;
	
	/**
	 * 사유
	 */
	private String reason;

	/**
	 * 결과 data
	 */
	private Object data;
	
	/**
	 * dhtmlx dynamic paging용 현재 페이지
	 */
	private long pos;
	
	/**
	 * dhtmlx dynamic paging용 전체 페이지수
	 */
	private long total_count;  
	
	/**
	 * 생성자
	 * @param status
	 */
	public ResultVo(final HttpStatus status) {
		this(null, status);
	}

	/**
	 * 생성자
	 * @param data
	 * @param status
	 */
	public ResultVo(final Object data, final HttpStatus status) {
		this.status = status;
		this.data = data;
	}
	
	/**
	 * 생성자
	 * @param status
	 * @param reason
	 */
	public ResultVo(final HttpStatus status, final String reason) {
		this(null, status);
		this.reason = reason;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(final HttpStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public Object getData() {
		return data;
	}

	public void setData(final Object data) {
		this.data = data;
	}

	public long getPos() {
		return pos;
	}

	public void setPos(final long pos) {
		this.pos = pos;
	}

	public long getTotal_count() {
		return total_count;
	}

	public void setTotal_count(final long total_count) {
		this.total_count = total_count;
	}
	
	/**
	 * ResultVo를 ResponseEntity로 변환
	 * @return
	 */
	public ResponseEntity<ResultVo> build() {
		return new ResponseEntity<ResultVo>(this, status);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
