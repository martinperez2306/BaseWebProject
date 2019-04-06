package com.basewebproject.rest.model;

public class GeneralResponse {
	
	private HeaderResponse header;
	private String mensaje;
	
	public GeneralResponse() {
		
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public HeaderResponse getHeaderResponse() {
		return header;
	}

	public void setHeaderResponse(HeaderResponse headerResponse) {
		this.header = headerResponse;
	}
	
}
