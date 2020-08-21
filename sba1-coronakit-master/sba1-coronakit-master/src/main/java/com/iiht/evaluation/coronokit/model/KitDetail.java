package com.iiht.evaluation.coronokit.model;

public class KitDetail {

	private int id;
	private int coronaKitId;
	private int productId;
	private int quantity;
	private int amount;
	
	private String productName;
	private String cost;
	private String productDescription;
	
	private int orderTotal;
	
	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}

	public KitDetail() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCoronaKitId() {
		return coronaKitId;
	}

	public void setCoronaKitId(int coronaKitId) {
		this.coronaKitId = coronaKitId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "KitDetail [id=" + id + ", coronaKitId=" + coronaKitId + ", productId=" + productId + ", quantity="
				+ quantity + ", amount=" + amount + ", productName=" + productName + ", cost=" + cost
				+ ", productDescription=" + productDescription + ", orderTotal=" + orderTotal + "]";
	}
	


	
	
}
