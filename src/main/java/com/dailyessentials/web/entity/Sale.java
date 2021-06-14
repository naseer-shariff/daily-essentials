package com.dailyessentials.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales")

public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "customer_name")
	private String CustomerName;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "sale_date")
	private Date saleDate;

	@Column(name = "address")
	private String address;

	public Sale(int id, String customerName, Long customerId, String productName, Long productId, Long quantity,
			Date saleDate, String address) {
		super();
		this.id = id;
		this.CustomerName = customerName;
		this.customerId = customerId;
		this.productName = productName;
		this.productId = productId;
		this.quantity = quantity;
		this.saleDate = saleDate;
		this.address = address;
	}

	public Sale(String customerName, Long customerId, String productName, Long productId, Long quantity, Date saleDate,
			String address) {
		this.CustomerName = customerName;
		this.customerId = customerId;
		this.productName = productName;
		this.productId = productId;
		this.quantity = quantity;
		this.saleDate = saleDate;
		this.address = address;
	}

	public Sale() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		this.CustomerName = customerName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setCreateDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
