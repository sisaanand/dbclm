package com.luxoft.clm.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "nace_details")
public class NaceDetail {
	
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
	
	public NaceDetail()
	{}
	
	
	
	public NaceDetail(BigInteger orderId, int orderLevel, String orderCode, String parent, String description,
			String itemIncludes1, String itemIncludes2, String rulings, String itemExcludes, String referenceISIC) {
		super();
		this.orderId = orderId;
		this.orderLevel = orderLevel;
		this.orderCode = orderCode;
		this.parent = parent;
		this.description = description;
		this.itemIncludes1 = itemIncludes1;
		this.itemIncludes2 = itemIncludes2;
		this.rulings = rulings;
		this.itemExcludes = itemExcludes;
		this.referenceISIC = referenceISIC;
	}
	
	@Column(name = "ORDER_ID", nullable = false)
	@Id
	public BigInteger getOrderId() {
		return orderId;
	}
	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "ORDER_LEVEL")
	public int getOrderLevel() {
		return orderLevel;
	}
	public void setOrderLevel(int orderLevel) {
		this.orderLevel = orderLevel;
	}
	
	@Column(name = "ORDER_CODE")
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	@Column(name = "PARENT")
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "ITEMS_INCLUDES_1")
	public String getItemIncludes1() {
		return itemIncludes1;
	}
	public void setItemIncludes1(String itemIncludes1) {
		this.itemIncludes1 = itemIncludes1;
	}
	
	@Column(name = "ITEMS_INCLUDES_2")
	public String getItemIncludes2() {
		return itemIncludes2;
	}
	public void setItemIncludes2(String itemIncludes2) {
		this.itemIncludes2 = itemIncludes2;
	}
	
	@Column(name = "RULINGS")
	public String getRulings() {
		return rulings;
	}
	public void setRulings(String rulings) {
		this.rulings = rulings;
	}
	
	@Column(name = "ITEMS_EXCLUDES")
	public String getItemExcludes() {
		return itemExcludes;
	}
	public void setItemExcludes(String itemExcludes) {
		this.itemExcludes = itemExcludes;
	}
	
	@Column(name = "REFERENCE_ISIC")
	public String getReferenceISIC() {
		return referenceISIC;
	}
	public void setReferenceISIC(String referenceISIC) {
		this.referenceISIC = referenceISIC;
	}
	
	
	
}
