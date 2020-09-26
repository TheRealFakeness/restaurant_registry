package model;

public enum OrderStatus{
	R("Requested"),
	P("Processing"),
	S("Sent"),
	D("Delivered");
	
	private String status;
	
	OrderStatus(String status){
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}