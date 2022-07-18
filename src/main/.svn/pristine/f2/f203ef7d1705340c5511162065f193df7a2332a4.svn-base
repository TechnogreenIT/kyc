package com.tes.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "all_product_name")
public class AllProductName implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int allProductNameId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "status")
	private String status;

	@Column(name = "type")
	private String type;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdDate;

	public int getAllProductNameId()
	{
		return allProductNameId;
	}

	public void setAllProductNameId(int allProductNameId)
	{
		this.allProductNameId = allProductNameId;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getProductType()
	{
		return productType;
	}

	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public AllProductName(String productName)
	{
		super();
		this.productName = productName;
	}

	public AllProductName()
	{

	}
}
