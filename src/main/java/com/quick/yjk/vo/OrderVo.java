package com.quick.yjk.vo;

public class OrderVo {
	
	private int orderNum;
	private String brandName;
	private String pickupDest;
	private boolean pickup;
	private String dest;
	private String item;
	private boolean replaceItem;
	private String orderTime;
	private int driverNum;
	private int price;
	private String loginId;
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getPickupDest() {
		return pickupDest;
	}
	public void setPickupDest(String pickupDest) {
		this.pickupDest = pickupDest;
	}
	public boolean isPickup() {
		return pickup;
	}
	public void setPickup(boolean pickup) {
		this.pickup = pickup;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public boolean isReplaceItem() {
		return replaceItem;
	}
	public void setReplaceItem(boolean replaceItem) {
		this.replaceItem = replaceItem;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getDriverNum() {
		return driverNum;
	}
	public void setDriverNum(int driverNum) {
		this.driverNum = driverNum;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	@Override
	public String toString() {
		return "OrderVo [orderNum=" + orderNum + ", brandName=" + brandName + ", pickupDest=" + pickupDest + ", pickup="
				+ pickup + ", dest=" + dest + ", item=" + item + ", replaceItem=" + replaceItem + ", orderTime="
				+ orderTime + ", driverNum=" + driverNum + ", price=" + price + ", loginId=" + loginId + "]";
	}

}
