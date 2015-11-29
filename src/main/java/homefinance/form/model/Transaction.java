package homefinance.form.model;

import java.util.List;

public class Transaction {
	private Integer id;
    private String user;
	private String transactionDate;
	private List<String> transactionType;
	private List<String> sourceType;
	private double sourceSum;
	private String transactionDesc;

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

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public List<String> getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(List<String> transactionType) {
		this.transactionType = transactionType;
	}

	public List<String> getSourceType() {
		return sourceType;
	}

	public void setSourceType(List<String> sourceType) {
		this.sourceType = sourceType;
	}

	public double getSourceSum() {
		return sourceSum;
	}

	public void setSourceSum(double sourceSum) {
		this.sourceSum = sourceSum;
	}

	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", user=" + user + ", transactionDate=" + transactionDate + ", transactionType=" + transactionType
				+ ", sourceType=" + sourceType + ", sourceSum=" + sourceSum
				+ ", transactionDesc=" + transactionDesc + "]"+ isNew();
	}

}
