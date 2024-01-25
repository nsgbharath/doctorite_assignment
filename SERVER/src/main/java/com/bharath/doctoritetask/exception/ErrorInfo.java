package com.bharath.doctoritetask.exception;

import java.time.LocalDateTime;

public class ErrorInfo {
	private String errorMessage;
	private LocalDateTime occuredAt;
	private Integer errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getOccuredAt() {
		return occuredAt;
	}

	public void setOccuredAt(LocalDateTime occuredAt) {
		this.occuredAt = occuredAt;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorInfo(String errorMessage, LocalDateTime occuredAt, Integer errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.occuredAt = occuredAt;
		this.errorCode = errorCode;
	}

	public ErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
