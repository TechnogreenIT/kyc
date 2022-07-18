package com.tes.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "all_products")
public class AllProducts implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int allProductsId;

	@ManyToOne
	@JoinColumn(name = "consent_id")
	private Consent consent;

	@ManyToOne
	@JoinColumn(name = "all_product_name_id")
	private AllProductName allProductName;// productName (no longer use)

	@Column(name = "quantity")
	private float quantity;

	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdDate;

	public int getAllProductsId()
	{
		return allProductsId;
	}

	public void setAllProductsId(int allProductsId)
	{
		this.allProductsId = allProductsId;
	}

	public Consent getConsent()
	{
		return consent;
	}

	public void setConsent(Consent consent)
	{
		this.consent = consent;
	}

	public AllProductName getAllProductName()
	{
		return allProductName;
	}

	public void setAllProductName(AllProductName allProductName)
	{
		this.allProductName = allProductName;
	}

	public float getQuantity()
	{
		return quantity;
	}

	public void setQuantity(float quantity)
	{
		this.quantity = quantity;
	}

	public Unit getUnit()
	{
		return unit;
	}

	public void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	public Users getUsers()
	{
		return users;
	}

	public void setUsers(Users users)
	{
		this.users = users;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public AllProducts()
	{
	};

	public AllProducts(int allProductsId, Consent consent, AllProductName allProductName, float quantity, Unit unit, Users users)
	{
		super();

		Consent cn = new Consent();
		cn.setConsentId(consent.getConsentId());
		Users un = new Users();
		this.allProductsId = allProductsId;
		this.consent = cn;
		this.allProductName = allProductName;
		this.quantity = quantity;
		this.unit = unit;
		this.users = un;
	}

	// findByConsTypeAndConsId()
	public AllProducts(int allProductsId, AllProductName allProductName, float quantity, Unit unit)
	{
		super();
		this.allProductsId = allProductsId;
		this.allProductName = allProductName;
		this.quantity = quantity;
		this.unit = unit;
	}

	@Override
	public String toString()
	{
		return "AllProducts [allProductsId=" + allProductsId + ", consent=" + consent + ", allProductName="
				+ allProductName + ", quantity=" + quantity + ", unit=" + unit + ", users=" + users + ", createdDate="
				+ createdDate + "]";
	}

}
