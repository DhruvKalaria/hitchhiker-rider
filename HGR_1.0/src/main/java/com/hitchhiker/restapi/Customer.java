package com.hitchhiker.restapi;

import java.io.Serializable;

public class Customer implements Serializable	{

	private static final long serialVersionUID = -7788619177798333712L;

	private String customerId;
	private String customerName;
	private String customerPassword;
	private int successBooking=0;
	private int totalBookings=0;
	private String bookingId[];


	public int getSuccessBooking() {
		return successBooking;
	}
	public void setSuccessBooking(int successBooking) {
		this.successBooking = successBooking;
	}
	public int getTotalBookings() {
		return totalBookings;
	}
	public void setTotalBookings(int totalBookings) {
		this.totalBookings = totalBookings;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String[] getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId[]) {
		this.bookingId = bookingId;
	}
}