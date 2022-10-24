package com.luxoft.clm.dto;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaceDetailDTO {
	
	@JsonProperty("Order")
	private BigInteger orderId;
	
	@JsonProperty("Level")
	private int orderLevel;
	
	@JsonProperty("Code")
	private String orderCode;
	
	@JsonProperty("Parent")
	private String parent;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("This item includes")
	private String itemIncludes1;
	
	@JsonProperty("This item also includes")
	private String itemIncludes2;
	
	@JsonProperty("Rulings")
	private String rulings;
	
	@JsonProperty("This item excludes")
	private String itemExcludes;
	
	@JsonProperty("Reference to ISIC Rev. 4")
	private String referenceISIC;
	
	public BigInteger getOrderId() {
		return orderId;
	}
	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}
	public int getOrderLevel() {
		return orderLevel;
	}
	public void setOrderLevel(int orderLevel) {
		this.orderLevel = orderLevel;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getItemIncludes1() {
		return itemIncludes1;
	}
	public void setItemIncludes1(String itemIncludes1) {
		this.itemIncludes1 = itemIncludes1;
	}
	public String getItemIncludes2() {
		return itemIncludes2;
	}
	public void setItemIncludes2(String itemIncludes2) {
		this.itemIncludes2 = itemIncludes2;
	}
	public String getRulings() {
		return rulings;
	}
	public void setRulings(String rulings) {
		this.rulings = rulings;
	}
	public String getItemExcludes() {
		return itemExcludes;
	}
	public void setItemExcludes(String itemExcludes) {
		this.itemExcludes = itemExcludes;
	}
	public String getReferenceISIC() {
		return referenceISIC;
	}
	public void setReferenceISIC(String referenceISIC) {
		this.referenceISIC = referenceISIC;
	}

	
	

}
