package com.basewebproject.rest.model;

public class HeaderResponse {
	
	public enum StatusHeader{
		OK(200,"Operacion Exitosa!"),
		ERROR(500,"Ocurrio un error!");
		
		private Integer code;
		private String status;
		
		private StatusHeader(Integer code, String status) {
			this.code = code;
			this.status = status;
		}

		public Integer getCode() {
			return code;
		}

		public String getStatus() {
			return status;
		}
		
	}
	
	private Integer code;
	private String status;

	public String getStatus() {
		return status;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setStatus(StatusHeader status) {
		this.code = status.getCode();
		this.status = status.getStatus();
	}
	
}
