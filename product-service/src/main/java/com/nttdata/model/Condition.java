package com.nttdata.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nttdata.model.Condition;

import lombok.Data;

@Data
@Entity
@Table(name = "Condition")
public class Condition {
	
	protected Condition() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long conditionId;
	
//	private List<String> customerTypeTarget;
	
	private boolean hasMaintenanceFee;
	private Double maintenanceFee;
	
	private boolean hasMonthlyTransactionLimit;
	private Integer monthlyTransactionLimit;
	
	private boolean hasDailyWithdrawalTransactionLimit;
	private Integer dailyWithdrawalTransactionLimit;
	
	private boolean hasDailyDepositTransactionLimit;
	private Integer dailyDepositTransactionLimit;
	
	private Integer productPerPersonLimit;
	
	private Integer productPerBusinessLimit;

	public Condition(boolean hasMaintenanceFee, Double maintenanceFee,
			boolean hasMonthlyTransactionLimit, Integer monthlyTransactionLimit,
			boolean hasDailyWithdrawalTransactionLimit, Integer dailyWithdrawalTransactionLimit,
			boolean hasDailyDepositTransactionLimit, Integer dailyDepositTransactionLimit,
			Integer productPerPersonLimit, Integer productPerBusinessLimit) {
//		this.customerTypeTarget = customerTypeTarget;
		this.hasMaintenanceFee = hasMaintenanceFee;
		this.maintenanceFee = maintenanceFee;
		this.hasMonthlyTransactionLimit = hasMonthlyTransactionLimit;
		this.monthlyTransactionLimit = monthlyTransactionLimit;
		this.hasDailyWithdrawalTransactionLimit = hasDailyWithdrawalTransactionLimit;
		this.dailyWithdrawalTransactionLimit = dailyWithdrawalTransactionLimit;
		this.hasDailyDepositTransactionLimit = hasDailyDepositTransactionLimit;
		this.dailyDepositTransactionLimit = dailyDepositTransactionLimit;
		this.productPerPersonLimit = productPerPersonLimit;
		this.productPerBusinessLimit = productPerBusinessLimit;
	} 
	
	
}
