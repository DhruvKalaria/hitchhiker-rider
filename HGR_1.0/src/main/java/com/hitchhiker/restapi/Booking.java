package com.hitchhiker.restapi;

import java.io.Serializable;

public class Booking implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;

	private String bookingId;
	private String customerId;
	private String timeSlot;
	private String numberOfPeople;
	private String source;
	private String destination;
	private String date;
	private String joinee1;
	private String joinee2;
	private String joinee3;
	private String primary;
	private int countJoinee;
	private int joinee1People;
	private int joinee2People;
	private int joinee3People;
	private int customerPeople;

	public int getJoinee1People() {
		return joinee1People;
	}
	public void setJoinee1People(int joinee1People) {
		this.joinee1People = joinee1People;
	}
	public int getJoinee2People() {
		return joinee2People;
	}
	public void setJoinee2People(int joinee2People) {
		this.joinee2People = joinee2People;
	}
	public int getJoinee3People() {
		return joinee3People;
	}
	public void setJoinee3People(int joinee3People) {
		this.joinee3People = joinee3People;
	}
	public int getCustomerPeople() {
		return customerPeople;
	}
	public void setCustomerPeople(int customerPeople) {
		this.customerPeople = customerPeople;
	}
	public String getPrimary() {
		return primary;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	public String getJoinee1() {
		return joinee1;
	}
	public void setJoinee1(String joinee1) {
		this.joinee1 = joinee1;
	}
	public String getJoinee2() {
		return joinee2;
	}
	public void setJoinee2(String joinee2) {
		this.joinee2 = joinee2;
	}
	public String getJoinee3() {
		return joinee3;
	}
	public void setJoinee3(String joinee3) {
		this.joinee3 = joinee3;
	}
	public int getCountJoinee() {
		return countJoinee;
	}
	public void setCountJoinee(int countJoinee) {
		this.countJoinee = countJoinee;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public static final int maxPeople = 4;

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(String numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}