package com.tes.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "all_product_comparative_sheet")
public class AllProductComparativeSheet implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int allProductComparativeSheetId;

	@ManyToOne
	@JoinColumn(name = "all_products_id")
	private AllProducts allProducts;

	@Column(name = "quantity")
	private float quantity;

	@Column(name = "balance")
	private float balance;

	@Column(name = "status")
	private String status;

	public int getAllProductComparativeSheetId()
	{
		return allProductComparativeSheetId;
	}

	public void setAllProductComparativeSheetId(int allProductComparativeSheetId)
	{
		this.allProductComparativeSheetId = allProductComparativeSheetId;
	}

	public AllProducts getAllProducts()
	{
		return allProducts;
	}

	public void setAllProducts(AllProducts allProducts)
	{
		this.allProducts = allProducts;
	}

	public float getQuantity()
	{
		return quantity;
	}

	public void setQuantity(float quantity)
	{
		this.quantity = quantity;
	}

	public float getBalance()
	{
		return balance;
	}

	public void setBalance(float balance)
	{
		this.balance = balance;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public AllProductComparativeSheet()
	{

	}

	public AllProductComparativeSheet(AllProducts allProducts, float quantity)
	{
		super();
		this.allProducts = allProducts;
		this.quantity = quantity;
	}
}
