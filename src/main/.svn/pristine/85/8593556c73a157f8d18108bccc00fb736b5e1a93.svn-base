package com.tes.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.poi.ss.usermodel.Row;
import lombok.Data;

@Entity
@Table(name = "regular_data")
@Data
public class RegularData implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int regularDataId;

	@ManyToOne
	@JoinColumn(name = "all_product_comparative_id")
	private AllProductComparativeSheet allProductComparativeSheet;

	@Column(name = "quantity")
	private Float quantity;

	@Column(name = "input_date")
	private String inputDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@Transient
	private EmpData empDataList = new EmpData();

	public RegularData(int regularDataId, Float quantity, String inputDate)
	{
		super();
		this.regularDataId = regularDataId;
		this.quantity = quantity;
		this.inputDate = inputDate;
	}

	public RegularData()
	{
	}

	public void setRegularDataDetails(Row row, int allProductComparativeId, int userId)
	{
		AllProductComparativeSheet objAllProductComparativeSheet = new AllProductComparativeSheet();
		Users objUsers = new Users();
		objUsers.setUsersId(userId);
		objAllProductComparativeSheet.setAllProductComparativeSheetId(allProductComparativeId);

		allProductComparativeSheet = objAllProductComparativeSheet;
		quantity = Float.parseFloat(row.getCell(1).toString());
		inputDate = row.getCell(2).toString();
		users = objUsers;
	}
}
