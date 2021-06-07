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
	private String customerName;

	@Column(name = "customer_id")
	private long customerId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "quantity")
	private long quantity;

	@Column(name = "sale_date")
	private Date saleDate;

	@Column(name = "address")
	private String address;

	public Sale(int id, String customerName, long customerId, String productName, long productId, long quantity,
			Date saleDate, String address) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerId = customerId;
		this.productName = productName;
		this.productId = productId;
		this.quantity = quantity;
		this.saleDate = saleDate;
		this.address = address;
	}

	public Sale(String customerName, int customerId, String productName, int productId, int quantity, Date saleDate,
			String address) {
		this.customerName = customerName;
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
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
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
