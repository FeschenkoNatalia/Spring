package com.natalia.form.model;

import java.util.List;

public class Transaction {
	Integer id;
    String user;
	String transaction_date;
	List<String> transaction_type;
	List<String> source_type;
	double source_sum;
	String transaction_desc;
	public boolean isNew() {
		return (this.id == null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	public List<String> getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(List<String> transaction_type) {
		this.transaction_type = transaction_type;
	}

	public List<String> getSource_type() {
		return source_type;
	}

	public void setSource_type(List<String> source_type) {
		this.source_type = source_type;
	}

	public double getSource_sum() {
		return source_sum;
	}

	public void setSource_sum(double source_sum) {
		this.source_sum = source_sum;
	}

	public String getTransaction_desc() {
		return transaction_desc;
	}

	public void setTransaction_desc(String transaction_desc) {
		this.transaction_desc = transaction_desc;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", user=" + user + ", transaction_date=" + transaction_date + ", transaction_type=" + transaction_type
				+ ", source_type=" + source_type + ", source_sum=" + source_sum
				+ ", transaction_desc=" + transaction_desc + "]"+ isNew();
	}

}
