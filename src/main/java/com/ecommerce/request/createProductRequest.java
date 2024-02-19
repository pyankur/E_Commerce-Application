package com.ecommerce.request;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.model.Size;


public class createProductRequest {

	private String title;
	private String description;
	private int price;
	private int discountedPrice;
	private int discountPresent;
	private int quantity;
	private String brand;
	private String color;
	
	private Set<Size>sizes=new HashSet<>();
	
	private String imageUrl;
	private String topLevelCateogory;
	private String secondLevelCateogory;
	private String thirdLevelCateogory;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public int getDiscountPresent() {
		return discountPresent;
	}
	public void setDiscountPresent(int discountPresent) {
		this.discountPresent = discountPresent;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Set<Size> getSizes() {
		return sizes;
	}
	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getTopLevelCateogory() {
		return topLevelCateogory;
	}
	public void setTopLevelCateogory(String topLevelCateogory) {
		this.topLevelCateogory = topLevelCateogory;
	}
	public String getSecondLevelCateogory() {
		return secondLevelCateogory;
	}
	public void setSecondLevelCateogory(String secondLevelCateogory) {
		this.secondLevelCateogory = secondLevelCateogory;
	}
	public String getThirdLevelCateogory() {
		return thirdLevelCateogory;
	}
	public void setThirdLevelCateogory(String thirdLevelCateogory) {
		this.thirdLevelCateogory = thirdLevelCateogory;
	}
	
	
}
