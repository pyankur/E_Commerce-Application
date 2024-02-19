package com.ecommerce.model;

public class PaymentDetails {

	private String paymentMethod;
	private String status;
	private String paymentId;
	private String razorPaymentLinkId;
	private String razorPaymentLinkReferenceId;
	private String razorPaymentLinkStatus;
	private String razorPaymentId;
	public PaymentDetails() {
		super();
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getRazorPaymentLinkId() {
		return razorPaymentLinkId;
	}
	public void setRazorPaymentLinkId(String razorPaymentLinkId) {
		this.razorPaymentLinkId = razorPaymentLinkId;
	}
	public String getRazorPaymentLinkReferenceId() {
		return razorPaymentLinkReferenceId;
	}
	public void setRazorPaymentLinkReferenceId(String razorPaymentLinkReferenceId) {
		this.razorPaymentLinkReferenceId = razorPaymentLinkReferenceId;
	}
	public String getRazorPaymentLinkStatus() {
		return razorPaymentLinkStatus;
	}
	public void setRazorPaymentLinkStatus(String razorPaymentLinkStatus) {
		this.razorPaymentLinkStatus = razorPaymentLinkStatus;
	}
	public String getRazorPaymentId() {
		return razorPaymentId;
	}
	public void setRazorPaymentId(String razorPaymentId) {
		this.razorPaymentId = razorPaymentId;
	}
	
	
}
