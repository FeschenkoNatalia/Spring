package com.natalia.form.dao;

import java.util.List;

import com.natalia.form.model.Transaction;

public interface TransactionDao {

	Transaction findById(Integer id);

	List<Transaction> findAll();

	List<Transaction> findByPattern(String pattern);

	void save(Transaction transaction);

	void update(Transaction transaction);

	void delete(Integer id);

}