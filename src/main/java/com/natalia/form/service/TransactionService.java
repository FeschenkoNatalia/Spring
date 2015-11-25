package com.natalia.form.service;

import java.util.List;
import com.natalia.form.model.Transaction;

public interface TransactionService {
	Transaction findById(Integer id);
	List<Transaction> findAll();
	List<Transaction> findByPattern(String pattern);
	void saveOrUpdate(Transaction transaction);
	void delete(int id);
}